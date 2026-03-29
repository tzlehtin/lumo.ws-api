package ws.lumo.ai.orchestration;

import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.document.Document;

import java.util.List;

/**
 * A record to hold the constructed prompt along with the source documents used to create it.
 * This allows passing both pieces of information together through the orchestration process.
 */
public record PromptWithContext(Prompt prompt, List<Document> sourceDocuments) {
}