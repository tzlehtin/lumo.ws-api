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
    Optional<AdapterConfig> findConfigurationById(String adapterId);

}