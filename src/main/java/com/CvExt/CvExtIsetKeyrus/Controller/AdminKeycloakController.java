package com.CvExt.CvExtIsetKeyrus.Controller;

import java.net.URI;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CvExt.CvExtIsetKeyrus.Service.KeycloakAdminService;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminKeycloakController {

    private final KeycloakAdminService keycloakAdminService;

    public AdminKeycloakController(KeycloakAdminService keycloakAdminService) {
        this.keycloakAdminService = keycloakAdminService;
    }

    // ==================== USERS ====================

    /**
     * GET /api/admin/users?first=0&max=50&search=
     */
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUsers(
            @RequestParam(defaultValue = "0") int first,
            @RequestParam(defaultValue = "50") int max,
            @RequestParam(required = false) String search) {
        String users = keycloakAdminService.getUsers(first, max, search);
        return ResponseEntity.ok(users);
    }

    /**
     * GET /api/admin/users/{id}
     */
    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUserById(@PathVariable String id) {
        String user = keycloakAdminService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * POST /api/admin/users
     * Body: UserRepresentation JSON de Keycloak
     */
    @PostMapping("/users")
    public ResponseEntity<Void> createUser(@RequestBody Map<String, Object> userPayload) {
        ResponseEntity<Void> response = keycloakAdminService.createUser(userPayload);
        
        // Keycloak retourne 201 avec Location header
        if (response != null && response.getHeaders().getLocation() != null) {
            URI location = response.getHeaders().getLocation();
            return ResponseEntity.created(location).build();
        }
        return ResponseEntity.status(201).build();
    }

    /**
     * PUT /api/admin/users/{id}
     * Body: UserRepresentation JSON partiel
     */
    @PutMapping("/users/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable String id, @RequestBody Map<String, Object> userPayload) {
        keycloakAdminService.updateUser(id, userPayload);
        return ResponseEntity.noContent().build();
    }

    /**
     * DELETE /api/admin/users/{id}
     */
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        keycloakAdminService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * PUT /api/admin/users/{id}/password
     * Body: { "password": "xxx", "temporary": false }
     */
    @PutMapping(value = "/users/{id}/password", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> setPassword(@PathVariable String id, @RequestBody Map<String, Object> body) {
        String password = (String) body.get("password");
        if (password == null || password.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Object tempObj = body.get("temporary");
        boolean temporary = tempObj != null && Boolean.parseBoolean(tempObj.toString());
        keycloakAdminService.setPassword(id, password, temporary);
        return ResponseEntity.noContent().build();
    }

    // ==================== ROLES ====================

    /**
     * GET /api/admin/roles
     */
    @GetMapping(value = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getRealmRoles() {
        String roles = keycloakAdminService.getRealmRoles();
        return ResponseEntity.ok(roles);
    }

    /**
     * GET /api/admin/users/{id}/roles
     */
    @GetMapping(value = "/users/{id}/roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUserRoles(@PathVariable String id) {
        String roles = keycloakAdminService.getUserRealmRoles(id);
        return ResponseEntity.ok(roles);
    }

    /**
     * POST /api/admin/users/{id}/roles
     * Body: { "roleName": "CV_READ" }
     */
    @PostMapping(value = "/users/{id}/roles", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addRoleToUser(@PathVariable String id, @RequestBody Map<String, String> body) {
        String roleName = body.get("roleName");
        if (roleName == null || roleName.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        keycloakAdminService.addRealmRoleToUser(id, roleName);
        return ResponseEntity.noContent().build();
    }

    /**
     * DELETE /api/admin/users/{id}/roles
     * Body: { "roleName": "CV_READ" }
     */
    @DeleteMapping(value = "/users/{id}/roles", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> removeRoleFromUser(@PathVariable String id, @RequestBody Map<String, String> body) {
        String roleName = body.get("roleName");
        if (roleName == null || roleName.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        keycloakAdminService.removeRealmRoleFromUser(id, roleName);
        return ResponseEntity.noContent().build();
    }
}
