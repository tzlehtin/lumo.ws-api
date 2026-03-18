package ws.lumo.api.history;

import ws.lumo.api.domain.ConversationHistory;
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

    /**
     * Finds conversation histories that are candidates for a follow-up message.
     * @param adapterId The ID of the adapter (e.g., a specific SalesAdapter).
     * @param sequence The sequence number of the last message sent (e.g., 1 for finding candidates for the 2nd message).
     * @return A list of conversation histories that match the criteria.
     */
    List<ConversationHistory> findFollowUpCandidates(String adapterId, int sequence);

}