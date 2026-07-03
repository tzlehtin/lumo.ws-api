package ws.lumo.validation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ws.lumo.api.validation.DocumentValidationApi;
import ws.lumo.api.validation.DriveFileDto;
import ws.lumo.api.validation.ValidationResultDto;

import java.util.List;

@RestController
@RequestMapping("/v1/validation")
public class ValidationController {

    private final DocumentValidationApi documentValidationApi;

    public ValidationController(DocumentValidationApi documentValidationApi) {
        this.documentValidationApi = documentValidationApi;
    }

    @GetMapping("/{adapterId}/results")
    public ResponseEntity<List<ValidationResultDto>> getResults(@PathVariable String adapterId) {
        List<ValidationResultDto> results = documentValidationApi.getValidationResults(adapterId);
        return ResponseEntity.ok(results);
    }

    @PostMapping("/{adapterId}/trigger")
    public ResponseEntity<Void> triggerValidation(
            @PathVariable String adapterId,
            @RequestParam String fileId,
            @RequestParam String documentName) {
        
        // Käynnistetään validointi asynkronisesti, jotta UI ei jää odottamaan.
        // Itse logiikka on palvelun sisällä.
        documentValidationApi.triggerValidation(adapterId, fileId, documentName);

        // Palautetaan 202 Accepted, joka kertoo, että pyyntö on otettu vastaan.
        return ResponseEntity.accepted().build();
    }
}