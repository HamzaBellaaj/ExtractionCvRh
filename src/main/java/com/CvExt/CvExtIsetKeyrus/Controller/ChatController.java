package com.CvExt.CvExtIsetKeyrus.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CvExt.CvExtIsetKeyrus.Service.N8nForwardService;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private N8nForwardService n8nForwardService;

    // Envoyer un message au chat
    @PostMapping
    public Map<String, Object> chat(@RequestBody ChatRequest request) {
        return n8nForwardService.forwardChatMessage(request.sessionId, request.chatInput);
    }

    // Classe pour la requête
    public static class ChatRequest {
        public String sessionId;
        public String chatInput;
    }
}
