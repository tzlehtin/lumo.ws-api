package ws.lumo.api.documents;

import java.util.Optional;

/**
 * API for managing and accessing source documents. This includes finding document
 * content and listing documents associated with a specific adapter.
 */
public interface SourceDocumentApi {

    Optional<String> findDocumentContentById(String documentId);

}