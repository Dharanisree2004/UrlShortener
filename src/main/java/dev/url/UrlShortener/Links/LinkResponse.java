package dev.url.UrlShortener.Links;

import java.time.LocalDateTime;

public class LinkResponse {

    private Long id;
    private String originalUrl;
    private String shortCode;
    private String qrCodeUrl;
    private LocalDateTime createdAt;

    public LinkResponse() {
        // Default constructor for deserialization
    }

    public LinkResponse(Long id, String originalUrl, String shortCode, String qrCodeUrl, LocalDateTime createdAt) {
        this.id = id;
        this.originalUrl = originalUrl;
        this.shortCode = shortCode;
        this.qrCodeUrl = qrCodeUrl;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
