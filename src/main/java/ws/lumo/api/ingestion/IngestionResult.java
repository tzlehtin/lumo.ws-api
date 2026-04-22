package ws.lumo.api.ingestion;

/**
 * A simple DTO to return the result of a data ingestion operation.
 * A simple DTO to return the result of a data ingestion operation.
 * @param documentId The unique ID of the ingested document.
 */
public record IngestionResult(String documentId) {
}