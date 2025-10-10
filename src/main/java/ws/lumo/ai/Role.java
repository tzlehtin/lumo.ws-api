package ws.lumo.ai;

/**
 * Defines the originator of a message in a chat conversation.
 * This enum is part of the shared API and used by both the service and clients.
 */
public enum Role {
    /**
     * A message from the end-user.
     */
    USER,

    /**
     * A message from the AI assistant.
     */
    ASSISTANT,

    /**
     * A system-level instruction that sets the context or behavior for the assistant.
     * This is typically not displayed to the user.
     */
    SYSTEM
}