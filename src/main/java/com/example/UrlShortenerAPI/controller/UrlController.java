package com.example.UrlShortenerAPI.controller;

import com.example.UrlShortenerAPI.entity.UrlLink;
import com.example.UrlShortenerAPI.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/api/url")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    // 1. Endpoint to generate the short link
    @PostMapping("/shorten")
    public ResponseEntity<UrlLink> shortenUrl(@RequestBody Map<String, String> request) {
        String originalUrl = request.get("originalUrl");
        UrlLink savedLink = urlService.shortenUrl(originalUrl);
        return ResponseEntity.ok(savedLink);
    }

    // 2. Endpoint to redirect users when they click the short link
    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirectToOriginal(@PathVariable String shortCode) {
        String originalUrl = urlService.getOriginalUrl(shortCode);

        // This tells the browser to instantly redirect to the original URL!
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(originalUrl))
                .build();
    }
}