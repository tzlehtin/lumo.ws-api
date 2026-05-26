package ws.lumo.api.validation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A simple DTO to represent a file from Google Drive.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriveFileDto {
    private String id;
    private String name;
}