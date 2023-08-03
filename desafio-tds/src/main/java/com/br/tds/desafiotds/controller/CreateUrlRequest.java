package com.br.tds.desafiotds.controller;

import org.hibernate.validator.constraints.URL;

public record CreateUrlRequest(@URL String url) {
}
