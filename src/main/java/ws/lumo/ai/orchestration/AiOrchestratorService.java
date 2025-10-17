package ws.lumo.ai.orchestration;

import java.util.List;

import ws.lumo.ai.ChatMessage;

/**
 * The central service interface for orchestrating AI interactions.
 * Adapters will depend on this interface, not its implementation.
 */
public interface AiOrchestratorService {
    /**
     * Generates a response to a user query within a specific context.
     *
     * @param adapterId The unique ID of the adapter context.
     * @param history   The full conversation history, with the latest user message at the end.
     *                  The service will extract the latest query from this list.
     * @return The generated ChatMessage response.
     */
    ChatMessage generateCooperativeResponse(String adapterId, List<ChatMessage> history);

    /**
     * Generates a standard escalation message when the AI cannot answer.
     * The message is fetched from the adapter's configuration and translated to the target language.
     *
     * @param adapterId The unique ID of the adapter context.
     * @param targetLanguage The language to translate the escalation message into.
     * @return A ChatMessage configured for escalation.
     */
    ChatMessage generateEscalationResponse(String adapterId, String targetLanguage);
}