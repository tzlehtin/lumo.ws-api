package ws.lumo.api.provider;

import java.util.List;
import java.util.Map;

/**
 * A generic Data Transfer Object (DTO) representing a single adapter's configuration.
 * This decouples adapters from the underlying domain model (e.g., CustomerAccount).
 *
 * @param adapterId A unique identifier for this adapter instance.
 * @param type The type of the adapter (e.g., "EMAIL_ADAPTER", "JSON_CHAT_ADAPTER").
 * @param settings A map of key-value settings for the adapter.
 * @param systemPrompt A system prompt to give the AI a specific persona or context.
 * @param allowedOrigins A list of domains from which requests are allowed for this adapter.
 * @param escalationEmail The email address to which escalations from this adapter should be sent.
 */
public record AdapterConfig(String adapterId,
                            String type,
                            Map<String, String> settings,
                            String systemPrompt,
                            List<String> allowedOrigins,
                            String escalationEmail) {
}