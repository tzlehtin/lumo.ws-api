package ws.lumo.api.processed;

/**
 * A generic API for managing the state of processed items to prevent duplicates.
 */
public interface ProcessedItemApi {

    /**
     * Checks if an item has already been processed for a specific adapter.
     * @param adapterId The ID of the adapter.
     * @param itemKey A unique key for the item (e.g., a URL, an email Message-ID).
     * @return true if the item has been processed, false otherwise.
     */
    default boolean isItemProcessed(String adapterId, String itemKey) {
        // KORJAUS: Varmistetaan, että itemKey ei ole null ennen käsittelyä.
        if (itemKey == null) return false;
        String normalizedKey = itemKey.trim().replaceFirst("^(https?://)?(www\\.)?", "").replaceAll("/$", "");
        return isItemProcessedNormalized(adapterId, normalizedKey);
    }

    /**
     * Marks an item as processed for a specific adapter.
     * @param adapterId The ID of the adapter.
     * @param itemKey A unique key for the item.
     */
    void markItemAsProcessed(String adapterId, String itemKey);

    // Sisäinen metodi, jota toteutuksen tulee käyttää.
    boolean isItemProcessedNormalized(String adapterId, String normalizedKey);

    /**
     * Checks if a specific contact (person) within a company has already been processed.
     * This is used for multi-contact leads like from Vainu.
     * @param adapterId The ID of the adapter.
     * @param businessId The company's unique business ID (Y-tunnus).
     * @param email The contact person's email.
     * @return true if the contact has been processed, false otherwise.
     */
    boolean isContactProcessed(String adapterId, String businessId, String email);

    /**
     * Marks a specific contact as processed.
     * @param adapterId The ID of the adapter.
     * @param businessId The company's unique business ID.
     * @param email The contact person's email.
     */
    void markContactAsProcessed(String adapterId, String businessId, String email);
}