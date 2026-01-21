package ws.lumo.ai.orchestration;

import org.springframework.ai.chat.prompt.Prompt;
import ws.lumo.ai.ChatMessage;
import ws.lumo.api.provider.AdapterConfig;

import java.util.List;

/**
 * Responsible for constructing the final Prompt object to be sent to the AI model.
 * This includes fetching context from the vector store and formatting the system message.
 */
public interface PromptConstructionService {

    Prompt constructPrompt(String adapterId, List<ChatMessage> history, AdapterConfig config);

}