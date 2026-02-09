package ws.lumo.api.ontology;

/**
 * Represents a relationship between two concepts.
 *
 * @param source The source concept's name.
 * @param verb The verb describing the relationship (e.g., "is a", "sells", "contains").
 * @param target The target concept's name.
 */
public record RelationshipDto(String source, String verb, String target) {
}