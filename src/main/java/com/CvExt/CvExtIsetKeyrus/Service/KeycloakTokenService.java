package com.CvExt.CvExtIsetKeyrus.Service;

import java.time.Instant;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class KeycloakTokenService {

    private final RestTemplate restTemplate;

    @Value("${keycloak.admin.token-uri}")
    private String tokenUri;

    @Value("${keycloak.admin.client-id}")
    private String clientId;

    @Value("${keycloak.admin.client-secret}")
    private String clientSecret;

    // Token cache
    private String cachedToken;
    private Instant tokenExpiry;

    public KeycloakTokenService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Récupère un token admin, avec cache automatique jusqu'à expiration
     */
    public synchronized String getAdminToken() {
        // Si le token est encore valide (avec 30s de marge), le retourner
        if (cachedToken != null && tokenExpiry != null && Instant.now().plusSeconds(30).isBefore(tokenExpiry)) {
            return cachedToken;
        }

        // Sinon, récupérer un nouveau token
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        @SuppressWarnings("unchecked")
        ResponseEntity<Map<String, Object>> response = restTemplate.postForEntity(
            tokenUri, 
            request, 
            (Class<Map<String, Object>>) (Class<?>) Map.class
        );

        Map<String, Object> responseBody = response.getBody();
        if (responseBody == null || !responseBody.containsKey("access_token")) {
            throw new RuntimeException("Impossible de récupérer le token admin Keycloak");
        }

        cachedToken = (String) responseBody.get("access_token");
        
        // expires_in est en secondes
        int expiresIn = ((Number) responseBody.get("expires_in")).intValue();
        tokenExpiry = Instant.now().plusSeconds(expiresIn);

        return cachedToken;
    }

    /**
     * Invalide le token en cache (utile si on reçoit un 401)
     */
    public synchronized void invalidateToken() {
        cachedToken = null;
        tokenExpiry = null;
    }
}
