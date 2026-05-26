package ws.lumo.api.ingestion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * A DTO representing the metadata of a document stored in the vector store.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentMetadataDto {
    private String id;
    private String description;
    private Instant ingestedAt;
    private String adapterId;
    private String sourceUrl;

}