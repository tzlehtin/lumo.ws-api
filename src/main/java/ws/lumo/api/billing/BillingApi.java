package ws.lumo.api.billing;

import org.springframework.ai.chat.metadata.Usage;

/**
 * An interface for services that handle billing-related operations.
 */
public interface BillingApi {
    /**
     * Increments the count of billable AI responses for the adapter.
     */
    void incrementBillableResponseCount(String adapterId);

    /**
     * Records the token usage from a Gemini API call.
     */
    void recordGeminiUsage(String adapterId, Usage usage);

    /**
     * Records the character count from a Translation API call.
     */
    void recordTranslationUsage(String adapterId, long characterCount);
}