package ws.lumo.billing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ws.lumo.domain.CustomerAccount;
import ws.lumo.domain.Product;
import ws.lumo.domain.Purchase;
import ws.lumo.notification.NotificationService;
import ws.lumo.payment.PaytrailService;
import ws.lumo.persistence.AdapterConfigurationRepository;
import ws.lumo.persistence.BillingMetricRepository;
import ws.lumo.persistence.CustomerAccountRepository;
import ws.lumo.persistence.ProductRepository;
import ws.lumo.persistence.PurchaseRepository;

import java.time.Instant;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScheduledBillingServiceTest {

    @Mock
    private CustomerAccountRepository customerAccountRepository;
    @Mock
    private AdapterConfigurationRepository adapterConfigurationRepository;
    @Mock
    private BillingMetricRepository billingMetricRepository;
    @Mock
    private PurchaseRepository purchaseRepository;
    @Mock
    private PaytrailService paytrailService;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private VatService vatService;
    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private ScheduledBillingService scheduledBillingService;

    private CustomerAccount account;
    private Product subscriptionProduct;

    @BeforeEach
    void setUp() {
        account = new CustomerAccount();
        account.setId("account-1");
        account.setName("Test Customer");
        account.setContactEmail("customer@test.com");
        account.setSubscriptionStatus("ACTIVE");
        account.setLastPaymentDate(Instant.now().minusSeconds(30 * 24 * 3600));
        account.setNextBillingDate(Instant.now().minusSeconds(1)); // Due for billing

        CustomerAccount.CustomerDetails details = new CustomerAccount.CustomerDetails();
        details.setCountryCode("FI");
        details.setLanguage("fi");
        account.setDetails(details);

        subscriptionProduct = new Product("LUMO-SUB-01", "Lumo.ws Service", 19900, "SUBSCRIPTION");
    }

    @Test
    void processAccount_withSuccessfulPayment_updatesAccountAndSendsReceipt() throws Exception {
        // Arrange
        when(adapterConfigurationRepository.findByCustomerAccountId(account.getId())).thenReturn(Collections.emptyList());
        when(billingMetricRepository.sumResponseCountForAdaptersBetween(any(), any(), any())).thenReturn(100L);
        when(productRepository.findById("LUMO-SUB-01")).thenReturn(Optional.of(subscriptionProduct));
        when(vatService.calculateVatAmount(anyInt(), eq("FI"))).thenReturn(4975); // 25.5% of 19900
        when(paytrailService.chargeTokenPayment(any(CustomerAccount.class), any(Purchase.class))).thenReturn("transaction-id-123");

        // Act
        // Koska processAccount on private, kutsumme julkista metodia, joka kutsuu sitä.
        // Annamme listan, joka sisältää meidän testimme tilin.
        when(customerAccountRepository.findBySubscriptionStatusInAndNextBillingDateBefore(any(), any()))
                .thenReturn(Collections.singletonList(account));
        scheduledBillingService.processMonthlyBilling();

        // Assert
        // Varmistetaan, että maksua yritettiin veloittaa
        verify(paytrailService, times(1)).chargeTokenPayment(any(), any());

        // Varmistetaan, että tili päivitettiin onnistuneen maksun jälkeen
        verify(customerAccountRepository, times(1)).save(argThat(savedAccount ->
                "ACTIVE".equals(savedAccount.getSubscriptionStatus()) &&
                savedAccount.getNextBillingDate().isAfter(Instant.now())
        ));

        // Varmistetaan, että kuitti tallennettiin valmiiksi
        verify(purchaseRepository, times(2)).save(any(Purchase.class)); // PENDING ja COMPLETED

        // Varmistetaan, että kuitti lähetettiin asiakkaalle
        verify(notificationService, times(1)).sendMonthlyBillingReceipt(any(), any());
    }
}