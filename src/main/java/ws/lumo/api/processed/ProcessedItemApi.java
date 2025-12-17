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
    boolean isItemProcessed(String adapterId, String itemKey);

    /**
     * Marks an item as processed for a specific adapter.
     * @param adapterId The ID of the adapter.
     * @param itemKey A unique key for the item.
     */
    void markItemAsProcessed(String adapterId, String itemKey);

}