package ws.lumo.api.provider;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdapterConfigDto {
    private String adapterId;
    private String customerAccountId;
    private String adapterType;
    private String adapterKey;
    private List<String> allowedOrigins;
    private Integer trivialQueryWordThreshold;
    private String trivialQueryResponse;
    private Map<String, String> properties;
    private String escalationEmail;
    private Integer contextLength;
    private String greeting;
    private Double languageDetectionConfidenceThreshold;
    private Integer languageDetectionWordLimit;
    private String escalationMessage;
    private String expertLanguage;
    private Boolean includeGreetingInEscalation;
    private Boolean includeClosingInEscalation;
    private Integer contextDocumentLimit;
}