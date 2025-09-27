package ws.lumo.api;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Represents the machine-readable specification of a single tool (function)
 * that an adapter provides. This information is passed to the AI model.
 *
 * @param name The name of the function the AI should call. Must be alphanumeric and underscores.
 * @param description A clear, natural language description of what the tool does. The AI uses this to decide when to use the tool.
 * @param parameters A JSON Schema object describing the parameters the function accepts.
 */
public record ToolSpecification(
    String name,
    String description,
    JsonNode parameters
) {
    // Esimerkki parametrien JSON-rakenteesta:
    // {
    //   "type": "object",
    //   "properties": { "recipient_email": { "type": "string" }, "subject": { "type": "string" } },
    //   "required": ["recipient_email", "subject"]
    // }
}