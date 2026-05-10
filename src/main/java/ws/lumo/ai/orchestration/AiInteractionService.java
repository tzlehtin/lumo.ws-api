package ws.lumo.ai.orchestration;

import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.document.Document;
import ws.lumo.ai.ChatMessage;

import java.util.List;

public interface AiInteractionService {

    /**
     * A record to hold the result of an AI interaction, including the generated message
     * and any source documents that were used to generate it.
     */
    record AiInteractionResult(ChatMessage message, List<Document> sourceDocuments, String language) {}

    /**
     * Gets a response from the AI model.
     *
     * @param adapterId       The ID of the adapter making the request.
     * @param prompt          The prompt to send to the AI.
     * @param history         The conversation history.
     * @param sourceDocuments The list of source documents for context.
     * @return The result of the AI interaction.
     */
    AiInteractionResult getAiResponse(String adapterId, Prompt prompt, List<ChatMessage> history, List<Document> sourceDocuments);
}