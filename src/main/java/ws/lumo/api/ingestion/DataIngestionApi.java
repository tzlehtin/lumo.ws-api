package ws.lumo.api.ingestion;

import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;

/**
 * Defines a contract for ingesting data into the system's knowledge base.
 * This API is used by adapters to provide learning material, such as expert replies.
 */
public interface DataIngestionApi {
    /**
     * Ingests a single document into the vector store from an InputStream.
     *
     * @param adapterId The ID of the adapter this document belongs to.
     * @param contentStream The InputStream of the document's content.
     * @param description An optional description for the document's context.
     * @param contentType The MIME type of the content (e.g., "text/plain", "application/pdf").
     * @return The ID of the newly created document.
     */
    String ingestDocument(String adapterId, InputStream contentStream, String description, String contentType);

    /**
     * Ingests a single document from a String content.
     * This is a convenience method for backward compatibility.
     * @deprecated Use {@link #ingestDocument(String, InputStream, String, String)} instead.
     */
    default String ingestDocument(String adapterId, String content, String description) {
        return ingestDocument(adapterId, new ByteArrayInputStream(content.getBytes(java.nio.charset.StandardCharsets.UTF_8)), description, "text/plain");
    }

    /**
     * Deletes all document chunks associated with a given source document ID.
     * @param sourceDocumentId The common ID of the document to delete.
     */
    void deleteDocumentsBySourceId(String sourceDocumentId);
}