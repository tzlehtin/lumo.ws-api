package ws.lumo.api.vector;

import java.util.Optional;

/**
 * Defines a contract for high-level management operations on the vector store,
 * such as reconstructing documents from their chunks.
 */
public interface VectorStoreManagementApi {

    /**
     * Reconstructs the original document content by fetching all its chunks from the vector store and joining them in the correct order.
     * @param sourceDocumentId The common ID of the source document.
     * @return An Optional containing the reconstructed document content, or empty if not found.
     */
    Optional<String> findAndReconstructDocument(String sourceDocumentId);

}