package com.br.tds.desafiotds.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("shortnedurl")
public class ShortenedUrl {

    @Id
    private Integer id;
    private String url;
    private String shortUrl;
    private Integer accessCount;
    public void incrementAccessCount() { this.accessCount++; }
}
