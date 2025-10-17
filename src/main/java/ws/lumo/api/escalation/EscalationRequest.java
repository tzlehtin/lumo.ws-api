package ws.lumo.api.escalation;

import java.util.Map;

/**
 * A Data Transfer Object for initiating an escalation.
 *
 * @param adapterId The ID of the adapter that triggered the escalation.
 * @param originalQuery The user's original query that could not be answered.
 * @param customerContactInfo A map containing contact details (e.g., "email": "user@example.com").
 * @param originalMessageContext A map for channel-specific context (e.g., "messageId": "<...>" for email).
 * @param customerName Optional name of the customer for personalization.
 * @param customerLanguage The language of the original customer query (e.g., "en", "fi").
 */
public record EscalationRequest(String adapterId, String originalQuery, Map<String, String> customerContactInfo, Map<String, String> originalMessageContext, String customerName, String customerLanguage) {}
