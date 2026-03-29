package ws.lumo.ai.orchestration;

import lombok.Builder;
import lombok.Data;

/**
 * A class to specify options for the AI orchestration process, allowing
 * certain steps like language detection or vector search to be skipped.
 */
@Data
@Builder
public class OrchestrationOptions {

    /**
     * If true, skips language detection and translation steps.
     * Defaults to false.
     */
    @Builder.Default
    private boolean skipLanguageProcessing = false;

    /**
     * If true, skips the vector store search for similar documents.
     * Defaults to false.
     */
    @Builder.Default
    private boolean skipVectorSearch = false;

}