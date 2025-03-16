package fqbarreto.hubspotapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

@Service
public class OAuthService {

    @Value("${spring.security.oauth2.client.registration.hubspot.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.hubspot.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.registration.hubspot.redirect-uri}")
    private String redirectUri;

    @Value("${spring.security.oauth2.client.registration.hubspot.scope}")
    private String scopes;

    private final RestTemplate restTemplate = new RestTemplate();

    private final String tokenUrl = "https://api.hubapi.com/oauth/v1/token";

    public String getAuthURL() {
        try {

        String authUrl = "https://app.hubspot.com/oauth/authorize";
        return UriComponentsBuilder.fromUriString(authUrl)
                .queryParam("client_id", clientId)
                .queryParam("redirect_uri", redirectUri)
                .queryParam("scope", scopes)
                .queryParam("scope", scopes)
                .queryParam("response_type", "code")
                .toUriString();

        } catch (HttpStatusCodeException e) {
            throw new RuntimeException("Erro ao autenticar: " + e.getMessage());
        }
    }

    public String getAccessToken(String code) {
        try {

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
            requestBody.add("grant_type", "authorization_code");
            requestBody.add("client_id", clientId);
            requestBody.add("client_secret", clientSecret);
            requestBody.add("redirect_uri", redirectUri);
            requestBody.add("code", code);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestBody, headers);

            ResponseEntity<Map> response = restTemplate.exchange(tokenUrl, HttpMethod.POST, request, Map.class);

            return (String) response.getBody().get("access_token");
        } catch (HttpStatusCodeException e) {
            throw new RuntimeException("Erro ao gerar access token: " + e.getMessage());
        }
    }
}

