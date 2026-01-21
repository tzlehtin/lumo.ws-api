package ws.lumo.ai.orchestration;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import ws.lumo.ai.ChatMessage;

import java.util.List;

/**
 * The service responsible for the direct interaction with the AI model,
 * including tool registration and execution.
 */
public interface AiInteractionService {
    /**
     * Gets a response from the AI model.
     */
    ChatResponse getAiResponse(String adapterId, Prompt prompt, List<ChatMessage> history);
}