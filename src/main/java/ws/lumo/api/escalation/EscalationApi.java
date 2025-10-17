package ws.lumo.api.escalation;

/**
 * Defines a centralized contract for handling escalations from any adapter.
 */
public interface EscalationApi {

    /**
     * Initiates the escalation process for a query that could not be answered.
     * This service is responsible for notifying internal support and acknowledging the
     * customer.
     *
     * @param request The escalation request containing all necessary context.
     * @return An acknowledgement message to be sent back to the original user.
     */
    String initiateEscalation(EscalationRequest request, String language);
}