package ws.lumo.api.generator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenerateDocumentRequest {
    private String correlationId;
    private Map<String, Object> fieldValues;
    private boolean saveAsPreferredPatterns;
}