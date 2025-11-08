package ws.lumo.api.history;

import ws.lumo.ai.ChatMessage;

import java.util.List;

/**
 * An interface for services that handle the storage of conversation histories.
 */
public interface ConversationHistoryApi {

    /**
     * Appends a list of messages to a conversation history, creating a new
     * history document if one does not exist for the given session ID.
     */
    void recordMessages(String sessionId, String adapterId, List<ChatMessage> messages);

    /**
     * Records an escalation event for a specific conversation session.
     */
    void recordEscalation(String sessionId, String escalationId);

}