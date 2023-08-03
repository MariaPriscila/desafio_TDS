package com.br.tds.desafiotds.controller;

import com.br.tds.desafiotds.service.UrlShortnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class UrlShortnerAcessorController {

    final UrlShortnerService urlShortnerService;

    @GetMapping(value = "/{shortUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortUrl) {
        String urlOriginal = urlShortnerService.acessUrl(shortUrl);

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(urlOriginal)).build();
    }

}
