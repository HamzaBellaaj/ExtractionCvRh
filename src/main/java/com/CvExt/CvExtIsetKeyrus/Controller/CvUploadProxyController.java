package com.CvExt.CvExtIsetKeyrus.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.CvExt.CvExtIsetKeyrus.Service.N8nForwardService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/cv/upload")
public class CvUploadProxyController {

    private final N8nForwardService n8nForwardService;

    public CvUploadProxyController(N8nForwardService n8nForwardService) {
        this.n8nForwardService = n8nForwardService;
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Object> uploadCv(
            @RequestPart("file") MultipartFile file,
            HttpServletRequest request) {

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Missing Authorization Bearer token");
        }

        Object result = n8nForwardService.forwardUpload(authorizationHeader, file);
        return ResponseEntity.ok(result);
    }
}
