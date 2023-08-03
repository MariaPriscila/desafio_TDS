package com.br.tds.desafiotds.controller;

import com.br.tds.desafiotds.domain.ShortenedUrl;
import com.br.tds.desafiotds.service.UrlShortnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("stats")
@RequiredArgsConstructor
public class UrlStatisticsController {

    final UrlShortnerService urlShortnerService;

    @GetMapping(value = "")
    public List<UrlStatsResponse> getStats() {
        List<ShortenedUrl> shortenedUrl = urlShortnerService.getUrlStats();

        return shortenedUrl.stream().map(url -> new UrlStatsResponse(url.getUrl(), url.getAccessCount())).toList();

    }
}
