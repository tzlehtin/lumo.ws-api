package ws.lumo.api;

import java.util.List;
import java.util.Map;

/**
 * Defines the contract for all external system adapters (plugins).
 * An adapter can provide tools for the AI to use.
 */
public interface Adapter {

    /**
     * Returns a unique identifier for this adapter.
     */
    String getAdapterId();

    /**
     * Returns a list of tools this adapter makes available to the AI.
     */
    List<ToolSpecification> getToolSpecifications();

    /**
     * Executes a tool with the given arguments.
     */
    String executeTool(String toolName, Map<String, Object> arguments);
}