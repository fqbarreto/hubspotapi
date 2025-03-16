package fqbarreto.hubspotapi.service;

import com.google.common.util.concurrent.RateLimiter;
import fqbarreto.hubspotapi.model.ContactRequestModel;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class ContactService {

    private final String contactsUrl = "https://api.hubapi.com/crm/v3/objects/contacts/";
    private final RateLimiter rateLimiter = RateLimiter.create(11);

    public ResponseEntity<String> createContact(String accessToken, ContactRequestModel contact) {
        rateLimiter.acquire();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(accessToken.replace("Bearer ", ""));

            Map<String, Object> requestBody = Map.of("properties", contact.getProperties());

            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.exchange(
                    contactsUrl,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );
        } catch (HttpStatusCodeException e) {
            if (e.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS) {
                throw new RuntimeException("Rate limit excedido. Tente novamente mais tarde.");}
            else{
                throw new RuntimeException("Erro ao criar contato no HubSpot: " + e.getMessage());
            }
        }
    }
}