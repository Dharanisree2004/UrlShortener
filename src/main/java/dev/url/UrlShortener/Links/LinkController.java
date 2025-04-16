package dev.url.UrlShortener.Links;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LinkController {

    private final LinkService linkService;

    @Value("${app.base-url}")
    private String baseUrl;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<Map<String, String>> shortenUrl(@RequestBody Map<String, String> request) {
        String originalUrl = request.get("url");
        Link link = linkService.shortenUrl(originalUrl);

        Map<String, String> response = new HashMap<>();
        response.put("shortCode", link.getShortCode());
        response.put("shortUrl", baseUrl + "/" + link.getShortCode());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{shortCode}")
    public void redirect(@PathVariable String shortCode, HttpServletResponse response) throws IOException {
        Link link = linkService.getOriginalUrl(shortCode);
        if (link != null) {
            response.sendRedirect(link.getUrlOriginal());
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @GetMapping("/stats/{shortCode}")
    public ResponseEntity<Map<String, Object>> getStats(@PathVariable String shortCode) {
        Link link = linkService.getStats(shortCode);
        if (link == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("clicks", link.getClicks());
        response.put("created_at", link.getCreatedAt());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/urls")
    public ResponseEntity<List<Map<String, String>>> getAllLinks() {
        List<Link> links = linkService.getAllLinks();
        List<Map<String, String>> response = links.stream().map(link -> {
            Map<String, String> item = new HashMap<>();
            item.put("shortCode", link.getShortCode());
            item.put("url", link.getUrlOriginal());
            return item;
        }).toList();

        return ResponseEntity.ok(response);
    }
}
