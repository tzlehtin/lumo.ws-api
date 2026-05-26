package ws.lumo.api.vector;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ws.lumo.api.ingestion.DocumentMetadataDto;

/**
 * A DTO representing the document stored in the vector store.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VectorDocumentDto {
    private String id;
    private String content;
    private DocumentMetadataDto metadata;
}