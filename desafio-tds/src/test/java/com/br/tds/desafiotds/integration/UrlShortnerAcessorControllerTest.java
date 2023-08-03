package com.br.tds.desafiotds.integration;
import com.br.tds.desafiotds.domain.ShortenedUrl;
import com.br.tds.desafiotds.repository.UrlRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UrlShortnerAcessorControllerTest {

    @MockBean
    private UrlRepository urlRepository;

    @Autowired
    private MockMvc mvc;

    @Test
    public void testRedirect_Success() throws Exception {
        String shortUrl = "abc123";
        String originalUrl = "http://www.example.com";
        Integer id = 1;
        Integer accessCount = 0;

        when(urlRepository.findByShortUrl(shortUrl))
                .thenReturn(Optional.of(new ShortenedUrl(id, originalUrl, shortUrl, accessCount)));

        mvc.perform(get("/" + shortUrl))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", containsString(originalUrl)));

    }

    @Test
    public void testRedirect_InvalidShortUrl() throws Exception {
        String shortUrl = "abc123";

        when(urlRepository.findByShortUrl(shortUrl))
                .thenReturn(Optional.empty());

        mvc.perform(get("/" + shortUrl))
                .andExpect(status().isNotFound());

    }
}
