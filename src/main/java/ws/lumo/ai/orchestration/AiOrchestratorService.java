package ws.lumo.ai.orchestration;

import ws.lumo.ai.ChatMessage;

/**
 * The central service interface for orchestrating AI interactions.
 * Adapters will depend on this interface, not its implementation.
 */
public interface AiOrchestratorService {
    /**
     * Generates a response to a user query within a specific context.
     *
     * @param adapterId The unique ID of the adapter that received the message.
     * @param query The user's input query.
     * @return The generated ChatMessage response.
     */
    ChatMessage generateCooperativeResponse(String adapterId, String query);
}