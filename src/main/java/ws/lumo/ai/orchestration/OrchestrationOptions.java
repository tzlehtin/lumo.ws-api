package ws.lumo.ai.orchestration;

import lombok.Builder;
import lombok.Data;

import java.util.Optional;

/**
 * Options to control the AI orchestration process.
 */
@Data
@Builder
public class OrchestrationOptions {

    @Builder.Default
    private boolean skipVectorSearch = false;

    private Object strategyContext;

    public Optional<Object> getStrategyContext() {
        return Optional.ofNullable(strategyContext);
    }
}