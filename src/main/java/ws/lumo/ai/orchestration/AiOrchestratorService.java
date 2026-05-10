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
     * @return An OrchestrationResult containing the response and the detected language.
     */
    OrchestrationResult generateCooperativeResponse(String adapterId, List<ChatMessage> history);

    /**
     * Generates a cooperative response with specific processing options.
     * @param adapterId The unique ID of the adapter context.
     * @param history   The full conversation history.
     * @param options   Options to control the orchestration process.
     * @return An OrchestrationResult containing the response.
     */
    OrchestrationResult generateCooperativeResponse(String adapterId, List<ChatMessage> history, OrchestrationOptions options);
}