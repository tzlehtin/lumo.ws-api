package ws.lumo.ai.orchestration;

import ws.lumo.ai.ChatMessage;

import java.util.List;

/**
 * A specific interface for AI response generation, used to break circular dependencies
 * where a service needs to call the AI orchestrator for a sub-task.
 */
public interface AiResponseGenerator {
    /**
     * Generates a cooperative response with specific processing options.
     * @param adapterId The unique ID of the adapter context.
     * @param history   The full conversation history.
     * @param options   Options to control the orchestration process.
     * @return An OrchestrationResult containing the response.
     */
    OrchestrationResult generateCooperativeResponse(String adapterId, List<ChatMessage> history, OrchestrationOptions options);
}