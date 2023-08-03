package com.br.tds.desafiotds.integration;

import com.br.tds.desafiotds.controller.UrlStatsResponse;
import com.br.tds.desafiotds.domain.ShortenedUrl;
import com.br.tds.desafiotds.repository.UrlRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UrlStatisticsControllerTest {

    @MockBean
    private UrlRepository urlRepository;

    @Autowired
    private MockMvc mvc;

    @Test
    public void testGetStatistics_Success() throws Exception {
        List<UrlStatsResponse> urlStatsResponses = List.of(
                new UrlStatsResponse("http://www.abc.com", 1),
                new UrlStatsResponse("http://www.cba.com", 2)
        );

        List<ShortenedUrl> shortenedUrls = List.of(
                new ShortenedUrl(1, "http://www.abc.com", "abc123", 1),
                new ShortenedUrl(2, "http://www.cba.com", "cba123", 2)
        );

        when(urlRepository.findAll()).thenReturn(shortenedUrls);

        mvc.perform(get("/stats"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(asJsonString(urlStatsResponses))));

    }
    public String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
