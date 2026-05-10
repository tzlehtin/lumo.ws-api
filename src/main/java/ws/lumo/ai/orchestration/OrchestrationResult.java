package ws.lumo.ai.orchestration;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.document.Document;
import ws.lumo.ai.ChatMessage;

import java.util.List;

public record OrchestrationResult(
    ChatMessage message,
    String language,
    List<Document> sourceDocuments,
    ChatResponse chatResponse
) {}