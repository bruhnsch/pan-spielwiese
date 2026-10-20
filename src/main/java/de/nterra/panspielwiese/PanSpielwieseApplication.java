package de.nterra.panspielwiese;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

@SpringBootApplication
@RestController
public class PanSpielwieseApplication {

    public static void main(String[] args) {
        SpringApplication.run(PanSpielwieseApplication.class, args);
    }

    @GetMapping("/")
    public Map<String, Object> index() {
        return Map.of(
            "status", "UP",
            "application", "pan-spielwiese",
            "message", "Hello from STACKIT & Spring Boot!",
            "timestamp", Instant.now().toString()
        );
    }
}
