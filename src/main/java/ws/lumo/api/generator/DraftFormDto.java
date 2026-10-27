package ws.lumo.api.generator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DraftFormDto {
    private String correlationId;
    private String adapterId;
    private String documentName;
    private String statusSummary;
    private String uncertaintyContextId;
    private List<DraftFormFieldDto> fields;
}