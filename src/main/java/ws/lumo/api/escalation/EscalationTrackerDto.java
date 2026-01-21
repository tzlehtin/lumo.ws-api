package ws.lumo.api.escalation;

import java.util.Map;
/**
 * A Data Transfer Object representing the state of an ongoing escalation.
 * This is a pure data carrier with no persistence-layer annotations.
 *
 * @param escalationId The unique ID for this escalation process.
 * @param originatingAdapterId The ID of the adapter where the escalation started.
 * @param originalQuery The initial user query that triggered the escalation.
 * @param customerContactInfo A map containing the customer's contact details (e.g., email).
 * @param originalMessageContext Context from the original message channel (e.g., email Message-ID).
 * @param customerLanguage The detected language of the customer.
 * @param sessionId The session ID of the conversation, used to link the escalation back to the chat history.
 */
public record EscalationTrackerDto(
    String escalationId,
    String originatingAdapterId,
    String originalQuery,
    Map<String, String> customerContactInfo,
    Map<String, String> originalMessageContext,
    String customerLanguage,
    String sessionId
) {}