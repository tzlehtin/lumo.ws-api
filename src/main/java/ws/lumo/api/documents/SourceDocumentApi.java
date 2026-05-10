package ws.lumo.api.documents;

import java.util.List;
import java.util.Optional;

/**
 * API for managing and accessing source documents. This includes finding document
 * content and listing documents associated with a specific adapter.
 */
public interface SourceDocumentApi {

    Optional<String> findDocumentContentById(String documentId);

    /**
     * Finds all document metadata associated with a specific adapter.
     *
     * @param adapterId The ID of the adapter.
     * @return A list of document metadata.
     */
    List<SourceDocumentMetadata> findAllDocumentsByAdapterId(String adapterId);
}