package ws.lumo.api.documents;

import java.util.Optional;

/**
 * API for accessing raw source documents that are not part of the vector store.
 * This is used for data sources like Vainu files for the SalesAdapter.
 */
public interface SourceDocumentApi {

    Optional<String> findDocumentContentById(String documentId);

}