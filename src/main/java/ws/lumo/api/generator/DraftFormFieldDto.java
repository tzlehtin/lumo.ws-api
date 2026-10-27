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
public class DraftFormFieldDto {
    public enum StatusColor {
        GREEN,   // Validoitu fakta lähdedokumenteista
        YELLOW,  // AI-ehdotus / monivalintalauseke
        RED      // Pakollinen puuttuva tieto
    }

    public enum FieldType {
        TEXT,
        NUMBER,
        CLAUSE_CHOICE,
        LOOP
    }

    private String key;
    private String label;
    private StatusColor statusColor;
    private FieldType type;
    private Object currentValue;
    private String sourceDocument;
    private String selectedOptionId;
    private List<ClauseOptionDto> options;
}