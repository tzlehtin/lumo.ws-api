package ws.lumo.api.validation;

import java.util.List;

/**
 * API for managing and retrieving document validation results.
 */
public interface DocumentValidationApi {

    /**
     * Retrieves all validation results for a specific adapter.
     *
     * @param adapterId The ID of the adapter.
     * @return A list of validation result DTOs.
     */
    List<ValidationResultDto> getValidationResults(String adapterId);

    /**
     * Triggers a validation process for a specific document.
     * The process runs asynchronously in the background.
     * @param adapterId The ID of the adapter context.
     * @param documentName The name of the document/file to validate from the source (e.g., Google Drive).
     */
    void triggerValidation(String adapterId, String documentName);

    /**
     * Lists files from the Google Drive folder configured for the adapter.
     *
     * @param adapterId The ID of the adapter.
     * @return A list of Google Drive File objects.
     */
    List<DriveFileDto> getDriveFiles(String adapterId);
}