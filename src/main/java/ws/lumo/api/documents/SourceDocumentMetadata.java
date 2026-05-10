package ws.lumo.api.documents;

import lombok.Data;

import java.time.Instant;

/**
 * Data Transfer Object for source document metadata, excluding the full content.
 * This is used to list available documents for an adapter.
 */
@Data
public class SourceDocumentMetadata {
    private String id;
    private String filename;
    private Instant createdAt;
}