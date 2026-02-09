package ws.lumo.api.ontology;

/**
 * Represents a single concept identified from the data.
 *
 * @param name The name of the concept (e.g., "Lumo.ws", "Tero Lehtinen").
 * @param type The type of the concept (e.g., "Product", "Person").
 */
public record ConceptDto(String name, String type) {
}