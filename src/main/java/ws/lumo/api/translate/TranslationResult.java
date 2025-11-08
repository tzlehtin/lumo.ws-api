package ws.lumo.api.translate;

/**
 * A record to hold the result of a translation operation, including the text
 * and the number of characters processed, which is used for billing.
 *
 * @param translatedText The resulting translated text.
 * @param characterCount The number of characters in the source text that were translated.
 */
public record TranslationResult(String translatedText, int characterCount) {
}
