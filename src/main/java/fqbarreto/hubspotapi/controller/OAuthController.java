package fqbarreto.hubspotapi.controller;

import fqbarreto.hubspotapi.service.OAuthService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/oauth")
public class OAuthController {

    @Autowired
    private final OAuthService _authService;

    public OAuthController(OAuthService AuthService) {
        this._authService = AuthService;
    }
    @GetMapping("/authorize")
    public ResponseEntity<String> authorize() {
        String authorizationUrl = _authService.getAuthURL();
        return ResponseEntity.ok(authorizationUrl);
    }

    @GetMapping("/callback")
    public ResponseEntity<String> callback(@RequestParam("code") String code) {
        String accessToken = _authService.getAccessToken(code);
        System.out.println(accessToken);
        return ResponseEntity.ok("Access Token: " + accessToken);
    }
}
