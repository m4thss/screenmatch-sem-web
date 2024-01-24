package br.com.screenmatch.principal;

import br.com.screenmatch.model.DadosEpisodio;
import br.com.screenmatch.model.DadosSerie;
import br.com.screenmatch.model.DadosTemporada;
import br.com.screenmatch.model.Episodio;
import br.com.screenmatch.service.ConsumoAPI;
import br.com.screenmatch.service.ConverteDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private Scanner scanner = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();

    private final String URL = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=828a5de2";

    public void menu(){
        System.out.println("Digite no nome da serie: ");
        String nomeSerie = scanner.nextLine();

        String json = consumo.obterDados(URL
                + nomeSerie.replace(" ", "+")
                + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);

        List<DadosTemporada> temporadas = new ArrayList<>();

		for(int i = 1; i < dados.totalTemporadas(); i++){
			json = consumo.obterDados(URL
                    + nomeSerie.replace(" ", "+")
                    + "&Season="
					+ i
                    + "&apikey=828a5de2");
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}

        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(e -> new Episodio(t.numero(), e)))
                .collect(Collectors.toList());

        episodios.forEach(System.out::println);

        System.out.println("A partir de qual data você gostaria de ver os episodios?");
        int ano = scanner.nextInt();
        scanner.nextLine();

        LocalDate dataBusca = LocalDate.of(ano, 1, 1);
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        episodios.stream()
                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
                .forEach(e -> System.out.println(
                        " | Temporada: " + e.getTemporada()
                        + " | Episodio: " + e.getNumeroEpisodio()
                        + " | Titulo: " + e.getTitulo()
                        + " | Data lançamento: " + e.getDataLancamento().format(formatador)
                ));
    }
}
