package ch.fhnw.madnessevent.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Health check passed");
    }

    @GetMapping("/")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("Welcome to MadnessEvent API");
    }
}
