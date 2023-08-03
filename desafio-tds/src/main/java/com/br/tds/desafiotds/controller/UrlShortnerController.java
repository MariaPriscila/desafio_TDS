package com.br.tds.desafiotds.controller;

import com.br.tds.desafiotds.service.UrlShortnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("url-shortner")
@RequiredArgsConstructor
public class UrlShortnerController {

    final UrlShortnerService urlShortnerService;

    @PostMapping(value = "", produces = "application/json")
    public String createShortUrl(@Valid @RequestBody CreateUrlRequest request) {
        return urlShortnerService.createShortUrl(request.url());
    }
}
