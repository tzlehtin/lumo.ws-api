package ws.lumo.api.provider;

/**
 * An API for creating onboarding tokens, used by adapters that don't have
 * direct access to the database persistence layer.
 */
public interface OnboardingTokenApi {

    String createSalesLeadToken(String email, String leadName, String leadUrl);
    boolean isTokenUsed(String token);
    String findOrCreateGenericToken();


}