package ws.lumo.api.escalation;

import java.util.Map;

/**
 * A Data Transfer Object representing the state of an ongoing escalation.
 * This is a pure data carrier with no persistence-layer annotations.
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