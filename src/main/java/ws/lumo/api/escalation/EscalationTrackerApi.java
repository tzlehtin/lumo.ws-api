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
     * @param tracker The escalation tracker object to save.
     */
    void save(EscalationTrackerDto tracker);

    /**
     * Finds an escalation record by its ID.
     * @param escalationId The unique ID of the escalation.
     * @return An Optional containing the EscalationTrackerDto if found.
     */
    Optional<EscalationTrackerDto> findById(String escalationId);

    /**
     * Deletes an escalation record once it has been resolved.
     */
       /**
     * Deletes an escalation record once it has been resolved.
     */
    void deleteById(String escalationId);    

    /**
     * Creates a temporary tracker for a conversation that is waiting for user input (e.g., an email address).
     */
    void createPendingEscalation(String sessionId, String adapterId, String originalQuery, String customerLanguage);

    /**
     * Finds a pending escalation tracker by its session ID and status.
     */
    Optional<EscalationTrackerDto> findBySessionIdAndStatus(String sessionId, String status);

    /**
     * Updates the status of an existing escalation tracker.
     */
    void updateStatus(String id, String newStatus);
}
