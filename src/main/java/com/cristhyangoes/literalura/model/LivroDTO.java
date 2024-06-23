package com.cristhyangoes.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivroDTO(@JsonAlias("title") String titulo,
                       @JsonAlias("languages") List<String> idioma,
                       @JsonAlias("authors") List<AutorDTO> autor,
                       @JsonAlias("download_count") Integer downloads) {
}
