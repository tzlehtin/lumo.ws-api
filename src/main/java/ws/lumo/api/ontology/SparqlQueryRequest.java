package ws.lumo.api.ontology;

import lombok.Data;

/**
 * A data transfer object representing a request to generate a SPARQL query.
 * This is used to pass information from the ontology service to the orchestrator
 * without creating a circular dependency.
 */
@Data
public class SparqlQueryRequest {
    private final String promptForSparql;
    private final OntologyDto ontologyDto;
}