package ws.lumo.api.generator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClauseOptionDto {
    private String id;
    private String label;
    private String templateText;
    private String resolvedText;
    private Double confidenceScore;
    private boolean selected;
}