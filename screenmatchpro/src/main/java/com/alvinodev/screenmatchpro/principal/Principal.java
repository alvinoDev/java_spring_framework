package com.alvinodev.screenmatchpro.principal;

import com.alvinodev.screenmatchpro.model.*;
import com.alvinodev.screenmatchpro.repository.SerieRepository;
import com.alvinodev.screenmatchpro.service.ConsumoAPI;
import com.alvinodev.screenmatchpro.service.ConvierteDatos;
import com.fasterxml.jackson.annotation.OptBoolean;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=" + System.getenv("API_KEY_OMDB");
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<DatosSerie> datosSeries = new ArrayList<>();

    private SerieRepository repositorio;

    private List<Serie> series;

    public Principal(SerieRepository repository) {
        this.repositorio = repository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar series 
                    2 - Buscar episodios
                    3 - Mostrar series buscadas
                    4 - Buscar Serie por titulo
                    5 - Buscar Top 5 Series
                    6 - Buscar Series por Categoria
                    7 - Filtrar Series por Temporada y Evaluacion
                                  
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;
                case 3:
                    mostrarSerieBuscada();
                    break;
                case 4:
                    buscarSeriePorTitulo();
                    break;
                case 5:
                    buscarTop5Series();
                case 6:
                    buscarSeriesPorCategoria();
                    break;
                case 7:
                    filtrarSeriesPorTemporadaYEvaluacion();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private DatosSerie getDatosSerie() {
        System.out.println("Escribe el nombre de la serie que deseas buscar");
        var nombreSerie = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + API_KEY);
        System.out.println(json);
        DatosSerie datos = conversor.obtenerDatos(json, DatosSerie.class);
        return datos;
    }
    private void buscarEpisodioPorSerie() {
        mostrarSerieBuscada();
        System.out.println("Escribe nombre de la Serie para ver los Episodios: ");
        var nombreSerie = teclado.nextLine();

        Optional<Serie> serie = series.stream()
                .filter(s -> s.getTitulo().toLowerCase().contains(nombreSerie.toLowerCase()))
                .findFirst();

        if(serie.isPresent()) {
            var serieEncontrada = serie.get();

            List<DatosTemporadas> temporadas = new ArrayList<>();

            for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {
                var json = consumoApi.obtenerDatos(URL_BASE + serieEncontrada.getTitulo().replace(" ", "+") + "&season=" + i + API_KEY);
                DatosTemporadas datosTemporada = conversor.obtenerDatos(json, DatosTemporadas.class);
                temporadas.add(datosTemporada);
            }
            temporadas.forEach(System.out::println);

            List<Episodio> episodios = temporadas.stream()
                    .flatMap(d -> d.episodios().stream()
                            .map(e -> new Episodio(d.numero(), e)))
                    .collect(Collectors.toList());

            serieEncontrada.setEpisodios(episodios);
            repositorio.save(serieEncontrada);
        }

    }

    private void buscarSerieWeb() {
        DatosSerie datos = getDatosSerie();
        Serie serie = new Serie(datos);
        repositorio.save(serie);

        //datosSeries.add(datos);
        System.out.println(datos);
    }

    private void mostrarSerieBuscada() {
        series = repositorio.findAll();

        series.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
    }

    private void buscarSeriePorTitulo() {
        System.out.println("Escribe nombre de la Serie a buscar: ");
        var nombreSerie = teclado.nextLine();

        Optional<Serie> serieBuscada = repositorio.findByTituloContainsIgnoreCase(nombreSerie);
        if(serieBuscada.isPresent()) {
            System.out.println("La Serie es: " + serieBuscada.get());
        } else {
            System.out.println("Serie no encontrada !");
        }
    }

    private void buscarTop5Series() {
        List<Serie> topSeries = repositorio.findTop5ByOrderByEvaluacionDesc();
        topSeries.forEach(s -> System.out.println("SERIE: " + s.getTitulo() + " EVALUACION: " + s.getEvaluacion()));
    }

    private void buscarSeriesPorCategoria() {
        System.out.println("Escribe el Genero/Categoria de la Serie a buscar: ");
        var genero = teclado.nextLine();

        var categoria = Categoria.fromStringEspanol(genero);
        List<Serie> seriePorCategoria = repositorio.findByGenero(categoria);

        System.out.println("Las series de la categoria: " + genero);
        seriePorCategoria.forEach(System.out::println);
    }

    private void filtrarSeriesPorTemporadaYEvaluacion() {
        System.out.println("Ingresa el numero de Temporadas: ");
        var temporadas = teclado.nextInt();
        teclado.nextLine();
        System.out.println("Ingresa un valor para Evaluacion: ");
        var evaluacion = teclado.nextDouble();
        teclado.nextLine();

        List<Serie> filtroSerie = repositorio.findByTotalTemporadasLessThanEqualAndEvaluacionGreaterThanEqual(temporadas, evaluacion);

        System.out.println("LISTA DE FILTRO POR TEMPORADAS Y EVALUACION: ");
        filtroSerie.forEach( s -> System.out.println("SERIE: " + s.getTitulo() + " EVALUACION: " + s.getEvaluacion()));

    }
}
