package ws.lumo.api.ontology;

import ws.lumo.ai.ChatMessage;
import ws.lumo.api.provider.AdapterConfig;

import java.util.List;
import java.util.Optional;

/**
 * A service for querying ontologies to enrich AI prompts.
 */
public interface OntologyQueryService {
    /**
     * Prepares a request to generate a SPARQL query based on the user's query.
     *
     * @param userQuery The natural language query from the user.
     * @param history   The full conversation history.
     * @param config    The configuration of the active adapter.
     * @return An Optional containing a request object for SPARQL generation, or empty if not applicable.
     */
    Optional<SparqlQueryRequest> prepareSparqlQuery(String userQuery, List<ChatMessage> history, AdapterConfig config);
}