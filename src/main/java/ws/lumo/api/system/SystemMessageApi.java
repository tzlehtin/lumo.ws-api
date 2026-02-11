package ws.lumo.api.system;

/**
 * An API for retrieving standardized, translated system messages.
 * The implementation handles translation and any related billing logic internally.
 */
public interface SystemMessageApi {

    /**
     * Retrieves a translated warning message for when the AI attempts to send an email to a third party.
     * The template includes a placeholder (%s) for the original recipient.
     *
     * @param adapterId The ID of the adapter requesting the message, for billing purposes.
     * @param language  The target language code (e.g., "fi", "en").
     * @return The translated warning message.
     */
    String getThirdPartyEmailWarning(String adapterId, String language);
}