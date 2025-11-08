package ws.lumo.ai.orchestration;

import ws.lumo.ai.ChatMessage;

import java.util.List;
import java.util.Optional;

/**
 * Handles pre-processing steps of the orchestration, such as routing
 * based on conversation state (e.g., pending escalation) or simple checks
 * (e.g., trivial queries).
 */
public interface OrchestrationPreProcessingService {

    Optional<OrchestrationResult> preProcess(String adapterId, List<ChatMessage> history);

}