package ws.lumo.api.account;

public interface CustomerAccountApi {
    /**
     * Checks if a customer account exists for the given business ID.
     *
     * @param businessId The business ID (Y-tunnus) to check.
     * @return true if a customer exists, false otherwise.
     */
    boolean existsByBusinessId(String businessId);
}