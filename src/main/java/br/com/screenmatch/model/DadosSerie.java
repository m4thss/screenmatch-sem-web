package br.com.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(@JsonAlias("Title") String titulo,
                         @JsonAlias("totalSeasons") Integer totalTemporadas,
                         @JsonAlias("imdbRating") String avaliacao,
                         @JsonAlias("Genre") String genero,
                         @JsonAlias("Poster") String poster,
                         @JsonAlias("Plot") String sinopse,
                         @JsonAlias("Actors") String atores) {

    @Override
    public String toString() {
        return titulo + "\n"
                + "Temporadas: " + totalTemporadas + "\n"
                + "Avaliação: " + avaliacao;
    }
}
