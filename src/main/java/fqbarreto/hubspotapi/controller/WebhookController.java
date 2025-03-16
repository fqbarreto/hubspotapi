package fqbarreto.hubspotapi.controller;

import fqbarreto.hubspotapi.model.WebhookContactPayloadModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    @PostMapping("/contact-creation")
    public ResponseEntity<String> webhookContactCreation(
            @RequestHeader("X-HubSpot-Signature") String signature,
            @RequestBody List<WebhookContactPayloadModel> payloads
    ) {
        try {

            for (WebhookContactPayloadModel payload : payloads) {

                System.out.println("Tipo de Inscrição do webhook: " + payload.getSubscriptionType() + " contato: " + payload.getObjectId());
            }

            return ResponseEntity.ok("Webhook recebido");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar webhook");
        }
    }
}
