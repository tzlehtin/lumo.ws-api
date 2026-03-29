package ws.lumo.api.ontology;

import lombok.Data;

import java.util.List;

/**
 * Data Transfer Object for the complete set of concepts and relationships
 * extracted from a single piece of text.
 */
@Data
public class OntologyDto {
    private List<ConceptDto> concepts;
    private List<RelationshipDto> relationships;
    private String language;
}