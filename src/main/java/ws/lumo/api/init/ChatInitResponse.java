package ws.lumo.api.init;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatInitResponse {
    private String sessionId;
    private String greeting;
    private String trivialResponse;
    private String status; // UUSI
    private String unavailableMessage; // UUSI
}