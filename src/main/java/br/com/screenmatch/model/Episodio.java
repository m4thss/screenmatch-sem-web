package br.com.screenmatch.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episodio {
    private Integer temporada;
    private String titulo;
    private Integer numeroEpisodio;
    private Double avaliacao;
    private LocalDate dataLancamento;

    public Episodio(Integer temporada, DadosEpisodio dados){
        this.temporada = temporada;
        this.titulo = dados.titulo();
        this.numeroEpisodio = dados.numeroEpisodio();

        try{
            this.avaliacao = Double.valueOf(dados.avaliacao());
        } catch (NumberFormatException e){
            this.avaliacao = 0.0;
        }
        try {
            this.dataLancamento = LocalDate.parse(dados.dataLancamento());
        } catch (DateTimeParseException e){
            this.dataLancamento = null;
        }

    }

    @Override
    public String toString() {
        return "temporada=" + temporada +
                ", titulo='" + titulo + '\'' +
                ", numeroEpisodio=" + numeroEpisodio +
                ", avaliacao=" + avaliacao +
                ", dataLancamento=" + dataLancamento;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public Integer getTemporada() {
        return temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }
}
