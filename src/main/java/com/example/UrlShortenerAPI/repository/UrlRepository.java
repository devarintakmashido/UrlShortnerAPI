package com.example.UrlShortenerAPI.repository;

import com.example.UrlShortenerAPI.entity.UrlLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UrlRepository extends JpaRepository<UrlLink, Long> {

    Optional<UrlLink> findByShortCode(String shortCode);

    @Modifying
    @Transactional
    @Query("DELETE FROM UrlLink u WHERE u.expiresAt < :now")
    void deleteExpiredUrls(LocalDateTime now);
}