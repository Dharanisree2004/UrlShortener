package dev.url.UrlShortener.Links;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class LinkService {

    private final LinkRepository linkRepository;
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_CODE_LENGTH = 6;

    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public Link shortenUrl(String originalUrl) {
        String shortCode = generateUniqueShortCode();
        Link link = new Link(originalUrl, shortCode, LocalDateTime.now(), 0);
        return linkRepository.save(link);
    }

    public Link getOriginalUrl(String shortCode) {
        Link link = linkRepository.findByShortCode(shortCode);
        if (link != null) {
            link.setClicks(link.getClicks() + 1);
            linkRepository.save(link);
        }
        return link;
    }

    public Link getStats(String shortCode) {
        return linkRepository.findByShortCode(shortCode);
    }

    public List<Link> getAllLinks() {
        return linkRepository.findAll();
    }

    private String generateUniqueShortCode() {
        String code;
        do {
            code = generateRandomCode();
        } while (linkRepository.findByShortCode(code) != null);
        return code;
    }

    private String generateRandomCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < SHORT_CODE_LENGTH; i++) {
            code.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return code.toString();
    }
}
