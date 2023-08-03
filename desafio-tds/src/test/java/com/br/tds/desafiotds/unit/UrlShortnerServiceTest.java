package com.br.tds.desafiotds.unit;

import com.br.tds.desafiotds.domain.ShortenedUrl;
import com.br.tds.desafiotds.exception.ShortUrlNotFoundException;
import com.br.tds.desafiotds.repository.UrlRepository;
import com.br.tds.desafiotds.service.UrlShortnerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UrlShortnerServiceTest {
    @Mock
    private UrlRepository urlRepository;

    @InjectMocks
    private UrlShortnerService urlShortnerService;

    @Test
    public void testCreateShortUrl_Success() {
        String longUrl = "http://www.example.com";

        when(urlRepository.findByUrl(longUrl)).thenReturn(Optional.empty());
        when(urlRepository.save(any(ShortenedUrl.class))).thenReturn(null);

        String shortUrl = urlShortnerService.createShortUrl(longUrl);

        assertNotNull(shortUrl);
    }

    @Test
    public void testCreateShortUrl_DuplicateUrl() {
        String longUrl = "http://www.example.com";
        ShortenedUrl existingUrl = new ShortenedUrl();
        existingUrl.setUrl(longUrl);
        existingUrl.setShortUrl("abc123");

        when(urlRepository.findByUrl(longUrl)).thenReturn(Optional.of(existingUrl));

        String shortUrl = urlShortnerService.createShortUrl(longUrl);

        assertEquals("http://localhost:8080/abc123", shortUrl);
    }

    @Test
    public void testAcessUrl_Success() {
        String shortUrl = "abc123";
        ShortenedUrl shortenedUrl = new ShortenedUrl();
        shortenedUrl.setUrl("http://www.example.com");
        shortenedUrl.setAccessCount(0);

        when(urlRepository.findByShortUrl(shortUrl)).thenReturn(Optional.of(shortenedUrl));

        String result = urlShortnerService.acessUrl(shortUrl);

        assertEquals(shortenedUrl.getUrl(), result);
        verify(urlRepository, times(1)).save(shortenedUrl);
    }

    @Test
    public void testGetUrl_NotFound() {
        String shortUrl = "nonExistentShortUrl";

        when(urlRepository.findByShortUrl(shortUrl)).thenReturn(Optional.empty());

        assertThrows(ShortUrlNotFoundException.class, () -> urlShortnerService.acessUrl(shortUrl));
    }

    @Test
    public void testGetUrlStats() {
        List<ShortenedUrl> shortenedUrls = List.of(
                new ShortenedUrl(),
                new ShortenedUrl()
        );

        when(urlRepository.findAll()).thenReturn(shortenedUrls);

        List<ShortenedUrl> result = urlShortnerService.getUrlStats();

        assertEquals(shortenedUrls, result);
        verify(urlRepository, times(1)).findAll();
    }

}
