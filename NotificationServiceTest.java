package ws.lumo.notification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import ws.lumo.adapters.email.MailSenderProvider;
import ws.lumo.api.provider.AdapterConfig;
import ws.lumo.api.provider.AdapterConfigurationProvider;
import ws.lumo.api.translate.TranslationResult;
import ws.lumo.api.translate.TranslationService;
import ws.lumo.domain.OnboardingToken;
import ws.lumo.domain.Purchase;
import ws.lumo.persistence.CustomerAccountRepository;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @Mock
    private MailSenderProvider mailSenderProvider;
    @Mock
    private AdapterConfigurationProvider configurationProvider;
    @Mock
    private TranslationService translationService;
    @Mock
    private CustomerAccountRepository customerAccountRepository;
    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        // Mockataan receiptBuilder, jotta se ei ole null
        notificationService.setReceiptBuilder(args -> "Mocked HTML Receipt");

        // Mockataan sähköpostin lähetys
        AdapterConfig senderConfig = new AdapterConfig(null, "default_email_sender", null, Map.of("email_username", "sender@lumo.ws"), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        when(configurationProvider.findConfigurationById("default_email_sender")).thenReturn(Optional.of(senderConfig));
        when(mailSenderProvider.getSender(any())).thenReturn(javaMailSender);
    }

    @Test
    void sendReceiptEmail_usesCustomerLanguageForTranslation() {
        // Arrange
        Purchase purchase = new Purchase();
        Purchase.CustomerDetails customer = new Purchase.CustomerDetails();
        customer.setLanguage("es");
        customer.setEmail("spanish.customer@test.com");
        purchase.setCustomer(customer);

        when(translationService.translate(anyString(), eq("es"))).thenAnswer(i -> new TranslationResult(i.getArgument(0) + " [es]", 1));

        // Act
        notificationService.sendReceiptEmail(purchase);

        // Assert
        verify(translationService, times(2)).translate(anyString(), eq("es"));
        verify(javaMailSender, times(1)).createMimeMessage(); // Varmistetaan, että sähköpostia yritettiin luoda
    }

    @Test
    void sendOnboardingInstructionsEmail_extractsLanguageFromToken() {
        // Arrange
        OnboardingToken token = new OnboardingToken();
        token.setDetails(Map.of("language", "de"));

        when(translationService.translate(anyString(), eq("de"))).thenAnswer(i -> new TranslationResult(i.getArgument(0) + " [de]", 1));

        // Act
        notificationService.sendOnboardingInstructionsEmail("german.customer@test.com", token, token, false);

        // Assert
        // Tarkistetaan, että käännöspalvelua kutsuttiin saksaksi useita kertoja (otsikko, runko, napit)
        verify(translationService, atLeast(5)).translate(anyString(), eq("de"));
        verify(javaMailSender, times(1)).createMimeMessage();
    }
}