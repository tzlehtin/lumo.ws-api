package ws.lumo.api.generator;

import org.springframework.scheduling.annotation.Async;

public interface DocumentGenerationService {

    @Async
    void generateDocument(String adapterId, String correlationId);
}