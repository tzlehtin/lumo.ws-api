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
     */
    List<DocumentMetadataDto> listDocuments(String adapterId);

    /**
     * Ingests a new document from a file upload.
     */
    IngestionResult ingestDocument(String adapterId, MultipartFile file, String description);

    /**
     * Deletes a single document from the vector store by its ID.
     */
    void deleteDocument(String documentId);

    // Tähän lisätään myöhemmin hakutoiminto.
    // List<DocumentMetadataDto> searchDocuments(String adapterId, String query);
}