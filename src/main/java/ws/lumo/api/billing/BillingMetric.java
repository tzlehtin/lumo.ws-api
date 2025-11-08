package ws.lumo.api.billing;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

/**
 * Tracks the number of billable responses for a specific adapter on a specific day.
 */
@Document("billing_metrics")
@Data
@CompoundIndex(name = "adapter_date_idx", def = "{'adapterId' : 1, 'date': 1}", unique = true)
public class BillingMetric {

    @Id
    private String id;
    private String adapterId;
    private LocalDate date; // Date in UTC
    private long responseCount = 0;
    private long geminiInputTokens = 0;
    private long geminiOutputTokens = 0;
    private long translationInputTokens = 0;
    private long translationOutputTokens = 0;
}