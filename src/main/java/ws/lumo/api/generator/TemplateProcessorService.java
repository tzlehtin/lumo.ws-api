package ws.lumo.api.generator;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * A service for processing document templates (e.g., DOCX) by replacing placeholders with data.
 */
public interface TemplateProcessorService {

    /**
     * Fills a DOCX template with data and converts it to a PDF, writing the result to an OutputStream.
     */
    void createPdfFromTemplate(InputStream templateStream, Map<String, Object> data, OutputStream outputStream) throws Exception;
}