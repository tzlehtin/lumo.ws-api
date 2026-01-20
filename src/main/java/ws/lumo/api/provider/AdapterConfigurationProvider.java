package ws.lumo.api.provider;

import java.util.List;
import java.util.Optional;
/**
 * Defines a contract for providing adapter configurations to the adapter library.
 * The implementation of this interface will reside in the main service application
 * and will be responsible for fetching configurations from the database.
 */
public interface AdapterConfigurationProvider {
    /**
     * Finds all adapter configurations of a specific type.
     * @param type The type of the adapter (e.g., "EMAIL_ADAPTER").
     * @return A list of configurations for the given type.
     */
    List<AdapterConfig> findConfigurationsByType(String type);

    /**
     * Retrieves all adapter configurations from the system.
     * @return A list of all available adapter configurations.
     */
    List<AdapterConfig> findAllConfigurations();

    /**
     * Finds a single adapter configuration by its unique ID.
     * @param adapterId The unique ID of the adapter.
     * @return An Optional containing the AdapterConfig if found.
     */
    Optional<AdapterConfig> findConfigurationById(String adapterId);

    /**
     * Updates the cron expression for a specific adapter.
     * @param adapterId The ID of the adapter to update. This will be stored in the adapter's properties.
     * @param cronExpression The new cron expression string to be stored.
     */
    void updateCronExpression(String adapterId, String cronExpression);
}