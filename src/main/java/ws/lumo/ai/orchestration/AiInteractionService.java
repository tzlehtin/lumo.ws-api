package ws.lumo.ai.orchestration;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;

/**
 * Responsible for the direct interaction with the AI model, including
 * tool registration and execution.
 */
public interface AiInteractionService {

    ChatResponse getAiResponse(String adapterId, Prompt prompt);

}