package com.br.tds.desafiotds.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.br.tds.desafiotds.domain.ShortenedUrl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Repository
public interface UrlRepository extends MongoRepository<ShortenedUrl, String>{
    Optional<ShortenedUrl> findByUrl(String url);
    Optional<ShortenedUrl> findByShortUrl(String shortUrl);
}
