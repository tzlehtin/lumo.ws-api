package ws.lumo.api.init;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatInitRequest {
    private String clientVersion;
    private String language;
}