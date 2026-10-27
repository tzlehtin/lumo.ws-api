package ws.lumo.api.generator;

import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface DocumentGenerationService {

    @Async
    void generateDocument(String adapterId, String correlationId);

    DraftFormDto prepareDraftForm(String adapterId, String correlationId);

    void generateDocumentFromDraft(String adapterId, String correlationId, GenerateDocumentRequest request);

    List<GeneratedDocumentSummaryDto> listGenerationHistory(String adapterId);

    void deleteGenerationRecord(String id);
}