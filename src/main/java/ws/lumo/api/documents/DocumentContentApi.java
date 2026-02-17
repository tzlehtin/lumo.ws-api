package ws.lumo.api.documents;

import java.util.Optional;

/**
 * An internal API for retrieving the raw content of an ingested document.
 */
public interface DocumentContentApi {

    /**
     * Finds the raw content of a document by its unique ID.
     * @param documentId The ID of the document.
     * @return An Optional containing the document content as a String, or empty if not found.
     */
    Optional<String> findDocumentContentById(String documentId);
}