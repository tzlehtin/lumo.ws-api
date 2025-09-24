package ws.lumo.ai.orchestration;

import ws.lumo.ai.ChatMessage;

/**
 * The central service interface for orchestrating AI interactions.
 * Adapters will depend on this interface, not its implementation.
 */
public interface AiOrchestratorService {
    /**
     * Generates a standard chat response for a given user query.
     */
    ChatMessage generateCooperativeResponse(String userQuery);
}