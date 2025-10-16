package ws.lumo.api.init;

/**
 * Defines the contract for initializing a chat session.
 * This service handles validation, language negotiation, and providing
 * initial data to the client.
 */
public interface ChatInitService {

    /**
     * Initializes a chat session for a given adapter.
     *
     * @param adapterId The ID of the adapter being initialized.
     * @param clientVersion The version of the client widget.
     * @param language The preferred language from the client's browser.
     * @return A response object containing the session ID and localized strings.
     */
    ChatInitResponse initializeChat(String adapterId, String clientVersion, String language);
}