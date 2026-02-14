package com.CvExt.CvExtIsetKeyrus.Service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class N8nForwardService {

    private static final Logger log = LoggerFactory.getLogger(N8nForwardService.class);

    private final WebClient webClient;

    @Value("${n8n.upload-webhook-path}")
    private String uploadWebhookPath;

    @Value("${n8n.chat-webhook-path}")
    private String chatWebhookPath;

    public N8nForwardService(@Value("${n8n.base-url}") String baseUrl, WebClient.Builder builder) {
        this.webClient = builder.baseUrl(baseUrl).build();
        log.info("N8nForwardService initialized with base URL: {}", baseUrl);
    }

    public Object forwardUpload(String authHeader, MultipartFile file) {
        try {
            MultipartBodyBuilder body = new MultipartBodyBuilder();
            body.part("file", file.getResource()).filename(file.getOriginalFilename());

            return webClient.post()
                    .uri(uploadWebhookPath)
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .header("Authorization", authHeader)
                    .body(BodyInserters.fromMultipartData(body.build()))
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();

        } catch (WebClientResponseException e) {
            return Map.of("error", "N8N error", "status", e.getStatusCode().value(), "message", e.getResponseBodyAsString());
        } catch (Exception e) {
            return Map.of("error", "Request failed", "message", e.getMessage());
        }
    }

    /**
     * Envoie un message au workflow n8n Chat et extrait la réponse.
     */
    public Map<String, Object> forwardChatMessage(String sessionId, String chatInput) {
        try {
            // Utiliser les noms de paramètres attendus par n8n Chat Trigger
            Map<String, String> payload = Map.of(
                "sessionId", sessionId,
                "chatInput", chatInput,
                "action", "sendMessage"
            );

            log.info("Sending chat message to n8n: sessionId={}, chatInput={}", sessionId, chatInput);
            log.info("Chat webhook path: {}", chatWebhookPath);

            Object response = webClient.post()
                    .uri(chatWebhookPath)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(payload)
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();

            log.info("Raw response from n8n: {}", response);

            String reply = extractReplyFromN8nResponse(response);
            log.info("Extracted reply: {}", reply);
            
            return Map.of("reply", reply);

        } catch (WebClientResponseException e) {
            log.error("N8N error: status={}, body={}", e.getStatusCode().value(), e.getResponseBodyAsString());
            return Map.of("error", "N8N error", "status", e.getStatusCode().value(), "message", e.getResponseBodyAsString());
        } catch (Exception e) {
            log.error("Request failed: {}", e.getMessage(), e);
            return Map.of("error", "Request failed", "message", e.getMessage());
        }
    }

    /**
     * Extrait le texte de réponse depuis différents formats n8n possibles:
     * 1) { "output": "..." } ou { "text": "..." } ou { "reply": "..." }
     * 2) [ { "json": { "output": "..." } } ]
     * 3) [ { "output": "..." } ]
     * 4) String direct
     */
    @SuppressWarnings("unchecked")
    private String extractReplyFromN8nResponse(Object response) {
        if (response == null) {
            return "Désolé, je n'ai pas pu obtenir de réponse.";
        }

        log.debug("Extracting reply from response type: {}", response.getClass().getName());

        // Cas: réponse directe en String
        if (response instanceof String) {
            return (String) response;
        }

        // Cas: réponse directe en objet { "output": "...", "text": "...", "reply": "...", "message": "..." }
        if (response instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) response;
            log.debug("Response map keys: {}", map.keySet());
            
            if (map.containsKey("output")) return String.valueOf(map.get("output"));
            if (map.containsKey("text")) return String.valueOf(map.get("text"));
            if (map.containsKey("reply")) return String.valueOf(map.get("reply"));
            if (map.containsKey("message")) return String.valueOf(map.get("message"));
            if (map.containsKey("response")) return String.valueOf(map.get("response"));
            if (map.containsKey("content")) return String.valueOf(map.get("content"));
            
            // Cas n8n chat: chercher dans les clés imbriquées
            if (map.containsKey("data") && map.get("data") instanceof Map) {
                Map<String, Object> data = (Map<String, Object>) map.get("data");
                if (data.containsKey("output")) return String.valueOf(data.get("output"));
                if (data.containsKey("text")) return String.valueOf(data.get("text"));
            }
        }

        // Cas: réponse en tableau [ { ... } ]
        if (response instanceof java.util.List) {
            java.util.List<?> list = (java.util.List<?>) response;
            if (!list.isEmpty()) {
                Object first = list.get(0);
                if (first instanceof Map) {
                    Map<String, Object> item = (Map<String, Object>) first;
                    // Format n8n standard: [ { "json": { "output": "..." } } ]
                    if (item.containsKey("json") && item.get("json") instanceof Map) {
                        Map<String, Object> json = (Map<String, Object>) item.get("json");
                        if (json.containsKey("output")) return String.valueOf(json.get("output"));
                        if (json.containsKey("text")) return String.valueOf(json.get("text"));
                        if (json.containsKey("reply")) return String.valueOf(json.get("reply"));
                    }
                    // Format simplifié: [ { "output": "..." } ]
                    if (item.containsKey("output")) return String.valueOf(item.get("output"));
                    if (item.containsKey("text")) return String.valueOf(item.get("text"));
                    if (item.containsKey("reply")) return String.valueOf(item.get("reply"));
                    if (item.containsKey("message")) return String.valueOf(item.get("message"));
                    if (item.containsKey("response")) return String.valueOf(item.get("response"));
                }
            }
        }

        log.warn("Could not extract reply from response: {}", response);
        return "Désolé, je n'ai pas pu interpréter la réponse.";
    }
}
