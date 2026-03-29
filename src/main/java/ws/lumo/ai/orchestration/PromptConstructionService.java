package ws.lumo.ai.orchestration;

import ws.lumo.ai.ChatMessage;
import ws.lumo.api.provider.AdapterConfig;

import java.util.List;

/**
 * Responsible for constructing the final Prompt object to be sent to the AI model. This includes fetching context from
 * the vector store and formatting the system message.
 */
public interface PromptConstructionService {
    PromptWithContext constructPrompt(String adapterId, List<ChatMessage> history, AdapterConfig config, boolean useVectorStore);
}