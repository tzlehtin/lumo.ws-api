package ws.lumo.api.generator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneratedDocumentSummaryDto {
    private String id;
    private String adapterId;
    private String correlationId;
    private String outputFileName;
    private Instant generationTimestamp;
    private String status;
    private String uncertaintyContextId;
}