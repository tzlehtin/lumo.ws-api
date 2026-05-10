package ws.lumo.api.ontology;

import lombok.Data;

/**
 * Data Transfer Object for a single concept extracted by the AI.
 */
@Data
public class ConceptDto {
    private String name;
    private String type;
}