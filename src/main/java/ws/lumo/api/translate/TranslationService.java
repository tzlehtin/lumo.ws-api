package ws.lumo.api.translate;

/**
 * Defines a contract for a text translation service.
 */
public interface TranslationService {

    /**
     * Translates the given text to the target language.
     * @param text The text to translate.
     * @param targetLanguage The ISO 639-1 code of the target language (e.g., "en", "sv").
     * @return The translated text.
     */
    TranslationResult translate(String text, String targetLanguage);

    /**
     * Detects the language of the given text.
     * @param text The text to analyze.
     * @return A result object containing the detected language and confidence score.
     */
    LanguageDetectionResult detectLanguage(String text);
}