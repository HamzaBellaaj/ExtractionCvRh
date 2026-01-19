package com.CvExt.CvExtIsetKeyrus.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class KeycloakAdminService {

    private final RestTemplate restTemplate;
    private final KeycloakTokenService tokenService;

    @Value("${keycloak.admin.api-base}")
    private String apiBase;

    public KeycloakAdminService(RestTemplate restTemplate, KeycloakTokenService tokenService) {
        this.restTemplate = restTemplate;
        this.tokenService = tokenService;
    }

    // ==================== USERS ====================

    /**
     * GET /users avec pagination et recherche - retourne JSON brut
     */
    public String getUsers(int first, int max, String search) {
        String url = apiBase + "/users?first=" + first + "&max=" + max;
        if (search != null && !search.isEmpty()) {
            url += "&search=" + search;
        }
        return doGet(url);
    }

    /**
     * GET /users/{id} - retourne JSON brut
     */
    public String getUserById(String userId) {
        String url = apiBase + "/users/" + userId;
        return doGet(url);
    }

    /**
     * POST /users - Créer un utilisateur
     */
    public ResponseEntity<Void> createUser(Map<String, Object> userPayload) {
        String url = apiBase + "/users";
        return doPost(url, userPayload);
    }

    /**
     * PUT /users/{id} - Mettre à jour un utilisateur
     */
    public void updateUser(String userId, Map<String, Object> userPayload) {
        String url = apiBase + "/users/" + userId;
        doPut(url, userPayload);
    }

    /**
     * DELETE /users/{id}
     */
    public void deleteUser(String userId) {
        String url = apiBase + "/users/" + userId;
        doDelete(url);
    }

    /**
     * PUT /users/{id}/reset-password
     */
    public void setPassword(String userId, String password, boolean temporary) {
        String url = apiBase + "/users/" + userId + "/reset-password";
        Map<String, Object> credential = Map.of(
            "type", "password",
            "value", password,
            "temporary", temporary
        );
        doPut(url, credential);
    }

    // ==================== ROLES ====================

    /**
     * GET /roles - Liste tous les rôles du realm
     */
    public String getRealmRoles() {
        String url = apiBase + "/roles";
        return doGet(url);
    }

    /**
     * GET /users/{id}/role-mappings/realm - Rôles assignés à un user
     */
    public String getUserRealmRoles(String userId) {
        String url = apiBase + "/users/" + userId + "/role-mappings/realm";
        return doGet(url);
    }

    /**
     * Ajoute un rôle realm à un utilisateur
     */
    public void addRealmRoleToUser(String userId, String roleName) {
        // 1. Récupérer la RoleRepresentation en JSON String
        String roleJson = getRoleByName(roleName);
        
        // 2. POST /users/{id}/role-mappings/realm avec body = [roleRep]
        String url = apiBase + "/users/" + userId + "/role-mappings/realm";
        doPostRawJson(url, "[" + roleJson + "]");
    }

    /**
     * Retire un rôle realm d'un utilisateur
     */
    public void removeRealmRoleFromUser(String userId, String roleName) {
        // 1. Récupérer la RoleRepresentation
        String roleJson = getRoleByName(roleName);
        
        // 2. DELETE /users/{id}/role-mappings/realm avec body = [roleRep]
        String url = apiBase + "/users/" + userId + "/role-mappings/realm";
        doDeleteWithBody(url, "[" + roleJson + "]");
    }

    /**
     * GET /roles/{roleName}
     */
    private String getRoleByName(String roleName) {
        String url = apiBase + "/roles/" + roleName;
        return doGet(url);
    }

    // ==================== HTTP HELPERS ====================

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(tokenService.getAdminToken());
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private String doGet(String url) {
        try {
            HttpEntity<Void> entity = new HttpEntity<>(createHeaders());
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            handleHttpError(e);
            return null;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Erreur Keycloak: " + e.getMessage());
        }
    }

    private ResponseEntity<Void> doPost(String url, Object body) {
        try {
            HttpEntity<Object> entity = new HttpEntity<>(body, createHeaders());
            return restTemplate.exchange(url, HttpMethod.POST, entity, Void.class);
        } catch (HttpClientErrorException e) {
            handleHttpError(e);
            return null;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Erreur Keycloak: " + e.getMessage());
        }
    }

    private void doPostRawJson(String url, String jsonBody) {
        try {
            HttpEntity<String> entity = new HttpEntity<>(jsonBody, createHeaders());
            restTemplate.exchange(url, HttpMethod.POST, entity, Void.class);
        } catch (HttpClientErrorException e) {
            handleHttpError(e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Erreur Keycloak: " + e.getMessage());
        }
    }

    private void doPut(String url, Object body) {
        try {
            HttpEntity<Object> entity = new HttpEntity<>(body, createHeaders());
            restTemplate.exchange(url, HttpMethod.PUT, entity, Void.class);
        } catch (HttpClientErrorException e) {
            handleHttpError(e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Erreur Keycloak: " + e.getMessage());
        }
    }

    private void doDelete(String url) {
        try {
            HttpEntity<Void> entity = new HttpEntity<>(createHeaders());
            restTemplate.exchange(url, HttpMethod.DELETE, entity, Void.class);
        } catch (HttpClientErrorException e) {
            handleHttpError(e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Erreur Keycloak: " + e.getMessage());
        }
    }

    private void doDeleteWithBody(String url, String jsonBody) {
        try {
            HttpEntity<String> entity = new HttpEntity<>(jsonBody, createHeaders());
            restTemplate.exchange(url, HttpMethod.DELETE, entity, Void.class);
        } catch (HttpClientErrorException e) {
            handleHttpError(e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Erreur Keycloak: " + e.getMessage());
        }
    }

    private void handleHttpError(HttpClientErrorException e) {
        HttpStatus status = (HttpStatus) e.getStatusCode();
        
        if (status == HttpStatus.UNAUTHORIZED) {
            tokenService.invalidateToken();
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token Keycloak invalide, réessayez");
        }
        
        if (status == HttpStatus.FORBIDDEN) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Accès refusé par Keycloak: " + e.getResponseBodyAsString());
        }
        
        if (status == HttpStatus.NOT_FOUND) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ressource non trouvée dans Keycloak");
        }
        
        if (status == HttpStatus.CONFLICT) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Conflit Keycloak: " + e.getResponseBodyAsString());
        }
        
        throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Erreur Keycloak (" + status + "): " + e.getResponseBodyAsString());
    }
}
