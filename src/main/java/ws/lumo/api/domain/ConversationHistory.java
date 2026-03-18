package ws.lumo.api.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import ws.lumo.ai.ChatMessage;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ConversationHistory {
    private String id;
    private String adapterId;
    private List<ChatMessage> messages = new ArrayList<>();
    private Instant lastUpdated;
}