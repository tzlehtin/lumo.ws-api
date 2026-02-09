package ws.lumo.api.ontology;

import java.util.List;

/**
 * A Data Transfer Object representing a complete ontology generated from a set of documents.
 *
 * @param concepts A list of identified concepts.
 * @param relationships A list of identified relationships between the concepts.
 */
public record OntologyDto(List<ConceptDto> concepts, List<RelationshipDto> relationships) {
}