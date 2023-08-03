package com.br.tds.desafiotds.integration;

import com.br.tds.desafiotds.controller.CreateUrlRequest;
import com.br.tds.desafiotds.domain.ShortenedUrl;
import com.br.tds.desafiotds.repository.UrlRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class UrlShortnerControllerTest {

    @MockBean
    private UrlRepository urlRepository;

    @Autowired
    private MockMvc mvc;

    @Test
    public void testCreateUrl_Success() throws Exception {
        String originalUrl = "http://www.example.com";

        when(urlRepository.findByUrl(originalUrl)).thenReturn(Optional.empty());

        mvc.perform(post("/url-shortner")
                        .content(asJsonString(new CreateUrlRequest(originalUrl)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("http://localhost:8080/")));

    }

    @Test
    public void testCreateUrl_DuplicateUrl() throws Exception {
        String originalUrl = "http://www.example.com";
        String shortUrl = "abc123";
        Integer id = 1;
        Integer accessCount = 0;

        when(urlRepository.findByUrl(originalUrl)).thenReturn(Optional.of(new ShortenedUrl(id, originalUrl, shortUrl, accessCount)));

        mvc.perform(post("/url-shortner")
                        .content(asJsonString(new CreateUrlRequest(originalUrl)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("http://localhost:8080/abc123")));

    }

    public String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
