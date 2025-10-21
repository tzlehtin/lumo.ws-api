package ws.lumo.ai;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Defines the possible roles in a chat conversation, following the convention
 * used by many large language models.
 */
public enum Role {
    USER,
    ASSISTANT,
    SYSTEM;

    @JsonCreator
    public static Role fromString(String value) {
        if (value == null) {
            return null;
        }
        return Role.valueOf(value.toUpperCase());
    }
}