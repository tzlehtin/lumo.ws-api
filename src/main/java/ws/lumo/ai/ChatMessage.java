package ws.lumo.ai;

import java.util.Map;

/**
 * A data transfer object representing a single message in a conversation.
 * This is an immutable record, which is a good practice for DTOs.
 */
public record ChatMessage(Role role, String content, Map<String, Object> metadata) {
}