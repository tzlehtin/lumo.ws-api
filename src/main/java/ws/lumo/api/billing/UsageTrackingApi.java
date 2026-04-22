package ws.lumo.api.billing;

/**
 * API for checking adapter usage status against budget and account status.
 * This interface decouples adapters from the service-layer implementation.
 */
public interface UsageTrackingApi {

    /**
     * Checks the usage status for a given adapter.
     * @param adapterId The ID of the adapter.
     * @return The current status (ACTIVE, BUDGET_EXCEEDED, or ACCOUNT_INACTIVE).
     */
    UsageStatus getUsageStatus(String adapterId);

    enum UsageStatus {
        ACTIVE,
        BUDGET_EXCEEDED,
        ACCOUNT_INACTIVE
    }
}
