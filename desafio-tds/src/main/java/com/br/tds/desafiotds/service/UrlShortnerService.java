package com.br.tds.desafiotds.service;

import com.br.tds.desafiotds.domain.ShortenedUrl;
import com.br.tds.desafiotds.exception.ShortUrlNotFoundException;
import com.br.tds.desafiotds.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UrlShortnerService {

    private static final String BASE_URL = "http://localhost:8080/";
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private final UrlRepository urlRepository;

    public String createShortUrl(String url) {
        Optional<ShortenedUrl> existingShortenedUrls = urlRepository.findByUrl(url);
        if (existingShortenedUrls.isPresent()) {
            return BASE_URL + existingShortenedUrls.get().getShortUrl();
        }

        String shortCode = generateShortCode();

        ShortenedUrl shortenedUrl = new ShortenedUrl();
        shortenedUrl.setUrl(url);
        shortenedUrl.setAccessCount(0);
        shortenedUrl.setShortUrl(shortCode);

        urlRepository.save(shortenedUrl);

        return BASE_URL + shortCode;
    }

    public String acessUrl(String shortUrl) {
        ShortenedUrl shortenedUrl = urlRepository.findByShortUrl(shortUrl).orElseThrow(
                () -> new ShortUrlNotFoundException("URL curta não encontrada: " + shortUrl)
        );

        shortenedUrl.incrementAccessCount();

        urlRepository.save(shortenedUrl);

        return shortenedUrl.getUrl();
    }

    public List<ShortenedUrl> getUrlStats() {
        return urlRepository.findAll();
    }

    private String generateShortCode() {
            StringBuilder salt = new StringBuilder();
            Random rnd = new Random();
            while (salt.length() < 10 ) {
                int index = (int) (rnd.nextFloat() * ALPHABET.length());
                salt.append(ALPHABET.charAt(index));
            }
            return salt.toString();
    }
}

