package ws.lumo.api.provider;

import java.util.Map;

/**
 * A generic Data Transfer Object (DTO) representing a single adapter's configuration.
 * This decouples adapters from the underlying domain model (e.g., CustomerAccount).
 *
 * @param adapterId A unique identifier for this adapter instance.
 * @param settings A map of key-value settings for the adapter.
 */
public record AdapterConfig(String adapterId, Map<String, String> settings) {
}