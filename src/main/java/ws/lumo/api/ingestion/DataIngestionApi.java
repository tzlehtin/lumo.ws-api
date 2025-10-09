package ws.lumo.api.ingestion;

/**
 * Defines a contract for ingesting data into the system's knowledge base.
 * This API is used by adapters to provide learning material, such as expert replies.
 */
public interface DataIngestionApi {

    /**
     * Ingests a single document into the vector store.
     *
     * @param adapterId The ID of the adapter this document belongs to.
     * @param content The raw text or HTML content of the document.
     * @param description An optional description for the document's context.
     * @return The ID of the newly created document.
     */
    String ingestDocument(String adapterId, String content, String description);
}