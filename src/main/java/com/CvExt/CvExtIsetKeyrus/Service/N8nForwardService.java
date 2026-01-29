package com.CvExt.CvExtIsetKeyrus.Service;

import java.util.Map;

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

    private final WebClient webClient;

    @Value("${n8n.upload-webhook-path}")
    private String uploadWebhookPath;

    public N8nForwardService(@Value("${n8n.base-url}") String baseUrl, WebClient.Builder builder) {
        this.webClient = builder.baseUrl(baseUrl).build();
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
}
