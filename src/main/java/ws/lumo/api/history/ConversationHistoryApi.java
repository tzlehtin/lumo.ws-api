package ws.lumo.api.history;

import ws.lumo.ai.ChatMessage;

import java.util.List;

/**
 * An API for services that manage conversation history persistence.
 */
public interface ConversationHistoryApi {
    /**
     * Records one or more messages to the history of a specific session.
     */
    void recordMessages(String sessionId, String adapterId, List<ChatMessage> messages);
}