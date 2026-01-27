package ws.lumo.payment;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ws.lumo.billing.VatService;
import ws.lumo.domain.CustomerAccount;
import ws.lumo.domain.OnboardingToken;
import ws.lumo.domain.Purchase;
import ws.lumo.domain.User;
import ws.lumo.notification.NotificationService;
import ws.lumo.persistence.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaytrailServiceTest {

    @Mock
    private PaytrailHmacService hmacService;
    @Mock
    private CustomerAccountRepository customerAccountRepository;
    @Mock
    private PurchaseRepository purchaseRepository;
    @Mock
    private OnboardingTokenRepository onboardingTokenRepository;
    @Mock
    private NotificationService notificationService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private VatService vatService;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private PaytrailService paytrailService;

    private Map<String, String> validCallbackParams;
    private Purchase pendingPurchase;
    private String stamp;

    @BeforeEach
    void setUp() {
        stamp = UUID.randomUUID().toString();

        validCallbackParams = new HashMap<>();
        validCallbackParams.put("checkout-status", "ok");
        validCallbackParams.put("checkout-stamp", stamp);
        validCallbackParams.put("checkout-card-token", "test-token-123");
        // Lisätään allekirjoituksen laskentaan tarvittavat kentät
        validCallbackParams.put("checkout-account", "12345");
        validCallbackParams.put("checkout-algorithm", "sha512");
        validCallbackParams.put("signature", "valid-signature");

        pendingPurchase = new Purchase();
        pendingPurchase.setId(stamp);
        Purchase.CustomerDetails customerDetails = new Purchase.CustomerDetails();
        customerDetails.setEmail("test@example.com");
        customerDetails.setFirstname("Test");
        customerDetails.setLastname("User");
        customerDetails.setLanguage("en");
        pendingPurchase.setCustomer(customerDetails);
    }

    @Test
    void processSuccessfulPayment_forNewUser_createsAccountAndSendsEmails() {
        // Arrange
        // Mockataan allekirjoituksen tarkistus onnistuneeksi
        when(hmacService.calculateHmac(any(), any(), any())).thenReturn("valid-signature");

        // Mockataan, että ostos löytyy kannasta
        when(purchaseRepository.findById(stamp)).thenReturn(Optional.of(pendingPurchase));

        // Mockataan, että käyttäjää ei löydy ennestään
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());

        // Mockataan tallennusoperaatiot, jotta ne eivät yritä kirjoittaa oikeaan kantaan
        when(customerAccountRepository.save(any(CustomerAccount.class))).thenAnswer(i -> i.getArguments()[0]);
        when(purchaseRepository.save(any(Purchase.class))).thenAnswer(i -> i.getArguments()[0]);
        when(onboardingTokenRepository.save(any(OnboardingToken.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        boolean result = paytrailService.processSuccessfulPayment(validCallbackParams);

        // Assert
        // Varmistetaan, että metodi palauttaa true
        assert(result);

        // Varmistetaan, että asiakastili yritettiin tallentaa
        verify(customerAccountRepository, times(1)).save(any(CustomerAccount.class));

        // Varmistetaan, että ostoksen tila päivitettiin
        verify(purchaseRepository, times(1)).save(argThat(p -> "COMPLETED".equals(p.getStatus())));

        // Varmistetaan, että kaksi onboarding-tokenia luotiin (yksi tilin luontiin, toinen ajanvaraukseen)
        verify(onboardingTokenRepository, times(2)).save(any(OnboardingToken.class));

        // Varmistetaan, että molemmat sähköpostit lähetettiin
        verify(notificationService, times(1)).sendReceiptEmail(any(Purchase.class));
        verify(notificationService, times(1)).sendOnboardingInstructionsEmail(
                eq("test@example.com"),
                any(OnboardingToken.class),
                any(OnboardingToken.class),
                eq(false) // isExistingUser = false
        );
    }
}