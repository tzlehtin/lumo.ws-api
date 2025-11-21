package ws.lumo.api;

import java.util.Optional;

/**
 * A central registry for discovering and accessing active Adapter instances.
 * The implementation of this interface will be provided by the main application
 * and injected into services that need it.
 */
public interface AdapterRegistry {

    /**
     * Retrieves an active adapter instance by its unique ID.
     */
    Optional<Adapter> getAdapter(String adapterId);

    /**
     * Registers a new adapter instance with the registry.
     */
    void registerAdapter(Adapter adapter);

    /**
     * Removes an adapter instance from the registry, effectively stopping it.
     * @param adapterId The ID of the adapter to unregister.
     */
    void unregisterAdapter(String adapterId);

    /**
     * Reloads a specific adapter's configuration and restarts it.
     */
    void reloadAdapter(String adapterId);
}