package ws.lumo.api.validation;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

/**
 * Data Transfer Object for a single validation result.
 */
@Data
@Builder
public class ValidationResultDto {

    private String id;
    private String adapterId;
    private String documentName;
    private Instant validationTimestamp;
    private String status;
    private String strategy;
    private String details;

}