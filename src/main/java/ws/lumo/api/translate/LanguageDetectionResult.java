package ws.lumo.api.translate;

/**
 * Represents the result of a language detection operation.
 * @param language The detected language as an ISO 639-1 code.
 * @param confidence The confidence score of the detection (0.0 to 1.0).
 */
public record LanguageDetectionResult(String language, float confidence) {}