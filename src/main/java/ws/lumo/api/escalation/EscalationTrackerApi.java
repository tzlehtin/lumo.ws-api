package ws.lumo.api.escalation;

import java.util.Optional;

/**
 * Defines a contract for managing the state of ongoing escalations.
 * This allows adapters to persist escalation information without knowing
 * the underlying storage mechanism.
 */
public interface EscalationTrackerApi {

    /**
     * Saves a new escalation record.
     * @param escalationId The unique ID for the escalation.
     * @param originalMessageId The Message-ID of the customer's original email.
     */
    void save(String escalationId, String originalMessageId);

    /**
     * Finds the original Message-ID for a given escalation ID.
     */
    Optional<String> findOriginalMessageId(String escalationId);

    /**
     * Deletes an escalation record once it has been resolved.
     */
    void deleteById(String escalationId);

}