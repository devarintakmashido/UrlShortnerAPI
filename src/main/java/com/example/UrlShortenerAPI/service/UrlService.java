package com.example.UrlShortenerAPI.service;

import com.example.UrlShortenerAPI.entity.UrlLink;
import com.example.UrlShortenerAPI.repository.UrlRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    // 1. Create a Short URL (Valid for 30 days)
    public UrlLink shortenUrl(String originalUrl) {
        // Generate a random 6-character string for our short code
        String shortCode = UUID.randomUUID().toString().substring(0, 6);
        UrlLink link = new UrlLink(originalUrl, shortCode, LocalDateTime.now().plusDays(30));

        return urlRepository.save(link);
    }

    // 2. The Redis Cache Magic!
    @Cacheable(value = "urls", key = "#shortCode")
    public String getOriginalUrl(String shortCode) {
        // If this prints in your terminal, it means Redis didn't have it and we had to ask MySQL!
        System.out.println("⚠️ Cache Miss! Fetching from the slow MySQL Database for code: " + shortCode);

        UrlLink link = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("URL not found or expired!"));

        return link.getOriginalUrl();
    }

    // 3. The Background Janitor Task
    // This cron expression means "Run at 00:00:00 (Midnight) every single day"
    @Scheduled(cron = "0 0 0 * * ?")
    public void cleanUpExpiredUrls() {
        System.out.println("🧹 Janitor Task: Cleaning up expired URLs from the database...");
        urlRepository.deleteExpiredUrls(LocalDateTime.now());
    }
}