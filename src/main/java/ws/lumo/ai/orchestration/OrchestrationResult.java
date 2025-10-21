package ws.lumo.ai.orchestration;

import ws.lumo.ai.ChatMessage;

public record OrchestrationResult(
    ChatMessage message,
    String detectedLanguage
) {}