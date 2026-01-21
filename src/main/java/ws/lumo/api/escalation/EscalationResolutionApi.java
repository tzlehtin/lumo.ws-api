package ws.lumo.api.escalation;

/**
 * Defines a central service for handling the resolution of an escalated query.
 */
public interface EscalationResolutionApi {

    /**
     * Processes an expert's answer to an escalation, forwards it to the customer, and records it in the history.
     */
    void resolveEscalation(String escalationId, String expertAnswer);
}