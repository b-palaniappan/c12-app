package io.c12.bala.c12app.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/api/v1")
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("status", "ok");
        return ResponseEntity.ok(responseMap);
    }

    @GetMapping("/ping")
    public ResponseEntity<Map<String, String>> pingCheck() {
        return healthCheck();
    }

}
