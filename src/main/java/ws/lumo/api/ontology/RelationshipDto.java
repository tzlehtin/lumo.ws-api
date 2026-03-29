package ws.lumo.api.ontology;

import lombok.Data;

/**
 * Data Transfer Object for a single relationship between two concepts.
 */
@Data
public class RelationshipDto {
    private String source;
    private String verb;
    private String target;
}