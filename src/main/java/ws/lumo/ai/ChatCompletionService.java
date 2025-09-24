package ws.lumo.ai;

import java.util.List;

/**
 * A generic interface for interacting with different AI chat completion models.
 * This abstraction layer allows swapping the underlying AI provider (e.g., OpenAI, Gemini)
 * without changing the core application logic.
 */
public interface ChatCompletionService {

    /**
     * Sends a list of messages to the AI model and returns the response.
     * @param messages The conversation history.
     * @return The response message from the assistant.
     */
    ChatMessage complete(List<ChatMessage> messages);

    /**
     * Returns the unique name of the AI provider (e.g., "openai", "gemini").
     * This is used by the factory to identify the service.
     * @return The provider name.
     */
    String getProviderName();
    // Tulevaisuudessa voitaisiin lisätä striimaava versio:
    // Stream<ChatMessage> stream(List<ChatMessage> messages);
}