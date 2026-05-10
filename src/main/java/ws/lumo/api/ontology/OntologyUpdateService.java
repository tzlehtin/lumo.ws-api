package ws.lumo.api.ontology;

/**
 * A service for queueing incremental updates to an adapter's ontology.
 */
public interface OntologyUpdateService {

    /**
     * Queues a task to add a document's concepts and relationships to the ontology.
     */
    void queueDocumentAddition(String adapterId, String documentId);

    /**
     * Queues a task to remove a document's concepts and relationships from the ontology.
     */
    void queueDocumentDeletion(String adapterId, String documentId);
}