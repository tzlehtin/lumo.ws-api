package ws.lumo.ai;

import java.util.Map;
/**
 * A data transfer object representing a single message in a conversation.
 * This is an immutable record, which is a good practice for DTOs.
 *
 * @param role The role of the message sender (e.g., USER, ASSISTANT).
 * @param content The text content of the message.
 * @param metadata A map of additional, non-visible data associated with the message (e.g., sessionId, tool call info).
 */
public record ChatMessage(Role role, String content, Map<String, Object> metadata) {
}