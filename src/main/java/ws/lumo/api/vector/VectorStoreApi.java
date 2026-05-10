package ws.lumo.api.vector;

import org.springframework.web.multipart.MultipartFile;
import ws.lumo.api.ingestion.IngestionResult;
import ws.lumo.api.ingestion.DocumentMetadataDto;

import java.util.List;

/**
 * An API for managing documents within the vector store.
 */
public interface VectorStoreApi {

    /**
     * Lists all documents associated with a specific adapter.
     * @param adapterId The ID of the adapter.
     * @param searchTerm An optional term to filter documents by description or content.
     * @param page The page number to retrieve.
     * @param size The number of documents per page.
     * @return A list of document metadata DTOs.
     */
    List<DocumentMetadataDto> listDocuments(String adapterId, String searchTerm, int page, int size);

    /**
     * Ingests a new document from a file upload.
     * @param adapterId The ID of the adapter.
     * @param file The file to ingest.
     * @param description A description for the document.
     * @return The result of the ingestion, containing the document ID.
     */
    IngestionResult ingestDocument(String adapterId, MultipartFile file, String description);

    /**
     * Deletes a single document from the vector store by its ID.
     * @param documentId The ID of the document to delete.
     */
    void deleteDocument(String documentId);

    /**
     * Finds multiple documents from the vector store by their specific IDs.
     * This is used for targeted lookups where the document IDs are already known.
     *
     * @param documentIds A list of document IDs.
     * @return A list of found documents.
     */
    List<org.springframework.ai.document.Document> findDocumentsByIds(List<String> documentIds);

    // Tähän lisätään myöhemmin hakutoiminto.
    // List<DocumentMetadataDto> searchDocuments(String adapterId, String query);
}