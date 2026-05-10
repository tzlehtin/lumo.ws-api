package ws.lumo.ai.orchestration;

import org.springframework.ai.chat.prompt.Prompt;
import ws.lumo.ai.ChatMessage;
import ws.lumo.api.provider.AdapterConfig;

import java.util.Optional;
import java.util.List;

/**
 * Responsible for constructing the final Prompt object to be sent to the AI model.
 * This includes fetching context from the vector store and formatting the system message.
 */
public interface PromptConstructionService {
    /**
     * Constructs a prompt with context.
     *
     * @param adapterId The ID of the adapter.
     * @param history The conversation history.
     * @param config The adapter configuration.
     * @param useVectorStore Whether to perform a vector search.
     * @param ontologyContext Optional context from an ontology search.
     * @param isOntologyBuild Whether this is for building the ontology schema.
     * @return A PromptWithContext object containing the prompt and source documents.
     */
    PromptWithContext constructPrompt(String adapterId, List<ChatMessage> history, AdapterConfig config, boolean useVectorStore, Optional<String> ontologyContext, boolean isOntologyBuild);
}