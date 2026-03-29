package ws.lumo.api.ontology;

import java.util.Optional;

/**
 * A service for querying ontologies to enrich AI prompts.
 */
public interface OntologyQueryService {

    /**
     * Prepares a request to generate a SPARQL query based on the user's query.
     * @param userQuery The natural language query from the user.
     * @param adapterId The ID of the adapter context.
     * @return An Optional containing a request object for SPARQL generation, or empty if not applicable.
     */
    Optional<SparqlQueryRequest> prepareSparqlQuery(String userQuery, String adapterId);
}