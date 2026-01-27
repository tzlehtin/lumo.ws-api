package ws.lumo.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ws.lumo.domain.CustomerAccount;
import ws.lumo.domain.OnboardingToken;
import ws.lumo.domain.User;
import ws.lumo.domain.UserRole;
import ws.lumo.persistence.CustomerAccountRepository;
import ws.lumo.persistence.OnboardingTokenRepository;
import ws.lumo.persistence.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountSetupServiceTest {

    @Mock
    private OnboardingTokenRepository onboardingTokenRepository;
    @Mock
    private CustomerAccountRepository customerAccountRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AccountSetupService accountSetupService;

    private OnboardingToken validToken;
    private CustomerAccount account;

    @BeforeEach
    void setUp() {
        validToken = new OnboardingToken();
        validToken.setId("valid-token");
        validToken.setUsed(false);
        validToken.setCustomerAccountId("account-123");
        validToken.setEmail("newuser@example.com");
        validToken.setRoles(List.of(UserRole.ADMIN));
        validToken.setDetails(Map.of("firstname", "New", "lastname", "User"));

        account = new CustomerAccount();
        account.setId("account-123");
        account.setUsers(new ArrayList<>());
    }

    @Test
    void setupAccount_withValidToken_createsUserAndLinksAccount() {
        // Arrange
        when(onboardingTokenRepository.findById("valid-token")).thenReturn(Optional.of(validToken));
        when(customerAccountRepository.findById("account-123")).thenReturn(Optional.of(account));
        when(userRepository.findByEmail("newuser@example.com")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password123")).thenReturn("hashed-password");

        // Act
        accountSetupService.setupAccount("valid-token", "password123");

        // Assert
        verify(userRepository, times(1)).save(argThat(user ->
                user.getEmail().equals("newuser@example.com") &&
                user.getPassword().equals("hashed-password")
        ));
        verify(customerAccountRepository, times(1)).save(argThat(acc ->
                !acc.getUsers().isEmpty() && acc.getUsers().get(0).getRoles().contains(UserRole.ADMIN)
        ));
        verify(onboardingTokenRepository, times(1)).save(argThat(OnboardingToken::isUsed));
    }

    @Test
    void setupAccount_withUsedToken_throwsIllegalStateException() {
        // Arrange
        validToken.setUsed(true);
        when(onboardingTokenRepository.findById("valid-token")).thenReturn(Optional.of(validToken));

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> accountSetupService.setupAccount("valid-token", "password123"));
    }
}