package br.com.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosTemporada(@JsonAlias("Season") Integer numero,
                             @JsonAlias("Episodes") List<DadosEpisodio> episodios) {

    @Override
    public String toString() {
        return "Temporada " +
                numero +
                ", episodios = " + episodios;
    }
}
