package ws.lumo.api.provider;

import java.util.List;
import java.util.Map;
/**
 * A generic Data Transfer Object (DTO) representing a single adapter's configuration.
 * This decouples adapters from the underlying domain model (e.g., CustomerAccount).
 *
 * @param customerId The ID of the customer account this adapter belongs to.
 * @param adapterId A unique identifier for this adapter instance.
 * @param type The type of the adapter (e.g., "EMAIL_ADAPTER", "JSON_CHAT_ADAPTER").
 * @param settings A flexible map of key-value settings specific to the adapter type.
 * @param systemPrompt A system prompt to give the AI a specific persona or context.
 * @param allowedOrigins A list of domains from which requests are allowed for this adapter.
 * @param greeting The initial greeting message for the chat.
 * @param escalationEmail The email address to which escalations from this adapter should be sent.
 * @param escalationMessage The message template shown to the user when escalation is triggered.
 * @param trivialQueryWordThreshold The minimum number of words a query must have to be processed by the AI.
 * @param trivialQueryResponse The canned response to send for queries that are too short.
 * @param contextLength The number of recent messages to include in the context for the AI.
 * @param languageDetectionConfidenceThreshold The confidence threshold (0.0-1.0) for language detection.
 * @param languageDetectionWordLimit The number of words to use for language detection.
 * @param includeGreetingInEscalation Whether to include a "Hi [Name]," greeting in the escalation acknowledgement.
 * @param includeClosingInEscalation Whether to include a "Best regards," closing in the escalation acknowledgement.
 * @param expertLanguage The language in which the expert should receive the escalation email.
 * @param adapterKey A secret key for authenticating server-to-server API calls (e.g., for ingestion).
 * @param contextDocumentLimit The maximum number of documents to fetch from the vector store for context.
 * @param forceTranslation If true, the AI's response will always be translated to the target language.
 */
public record AdapterConfig(String customerId,
                            String adapterId,
                            String type,
                            Map<String, Object> settings, // KORJAUS: Muutetaan tyyppi joustavammaksi
                            String systemPrompt,
                            List<String> allowedOrigins,
                            String greeting,
                            String escalationEmail,
                            String escalationMessage,
                            Integer trivialQueryWordThreshold,
                            String trivialQueryResponse,
                            Integer contextLength,
                            Double languageDetectionConfidenceThreshold,
                            Integer languageDetectionWordLimit,
                            Boolean includeGreetingInEscalation,
                            Boolean includeClosingInEscalation,
                            String expertLanguage,
                            String adapterKey,
                            Integer contextDocumentLimit,
                            Boolean forceTranslation) {
}