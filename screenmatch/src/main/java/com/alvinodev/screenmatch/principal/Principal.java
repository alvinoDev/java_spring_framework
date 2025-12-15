package com.alvinodev.screenmatch.principal;

import com.alvinodev.screenmatch.model.DatosEpisodio;
import com.alvinodev.screenmatch.model.DatosSerie;
import com.alvinodev.screenmatch.model.DatosTemporada;
import com.alvinodev.screenmatch.model.Episodio;
import com.alvinodev.screenmatch.service.ConsumoAPI;
import com.alvinodev.screenmatch.service.ConvierteDatos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    private final  String API_KEY = "&apikey=34353774";

    private Scanner input = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();

    private ConvierteDatos conversor = new ConvierteDatos();

    public void muestraMenu() {
        System.out.println("Escribe el nombre de la serie que buscas");
        var serieNombre = input.nextLine();

        // BUSCA DATOS GENERALES DE LA SERIE
        var json = consumoApi.obtenerDatos(URL_BASE + serieNombre.replace(" ", "+") + API_KEY);
        var datos = conversor.obtenerDatos(json, DatosSerie.class);
        System.out.println(datos);

        //BUSCA LOS DATOS DE TODAS LAS TEMPORADAS:
        List<DatosTemporada> temporadas = new ArrayList<>();
        for (int i = 1; i <= datos.totalDeTemporadas() ; i++) {
            json = consumoApi.obtenerDatos(URL_BASE + serieNombre.replace(" ", "+") + "&season=" + i + API_KEY);
            var datosTemporadaList = conversor.obtenerDatos(json, DatosTemporada.class);
            temporadas.add(datosTemporadaList);
        }

        // temporadas.forEach(System.out::println);

        // MOSTRAR TITULO DE EPISODIOS PARA TEMPORADAS
//        for (int i = 0; i < datos.totalDeTemporadas(); i++) {
//            List<DatosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
//            for (int j = 0; j < episodiosTemporada.size(); j++) {
//                System.out.println(episodiosTemporada.get(j).titulo());
//            }
//        }

        // Codigo equivalente a lo anterior LAMBDA
         temporadas.forEach(temp -> temp.episodios().forEach(episo -> System.out.println(episo.titulo())));

        // CONVERTIR INFORMACION A UNA LISTA DE TIPO DatosEpisodio
        List<DatosEpisodio> dtosEpisodio = temporadas.stream()
                .flatMap(temp -> temp.episodios().stream())
                .collect(Collectors.toList());

        //TOP 5 EPISODIOS
//        System.out.println("TOP 5 EPISODIOS:");
//        dtosEpisodio.stream()
//                .filter(episo -> !episo.evaluacion().equalsIgnoreCase("N/A"))
//                .peek(e -> System.out.println("Primer filtro (N/A)"))
//                .sorted(Comparator.comparing(DatosEpisodio::evaluacion).reversed())
//                .peek(e -> System.out.println("Segundo ordenacion (M>m)"))
//                .map(episo -> episo.titulo().toUpperCase())
//                .peek(e -> System.out.println("Tercer filtro Mayuscula (m>M)"))
//                .limit(5)
//                .forEach(System.out::println);

        // CONVERTIR DATOS A UNA LISTA DE EPISODIO
        List<Episodio> episodios = temporadas.stream()
                .flatMap( temp -> temp.episodios().stream()
                        .map(d -> new Episodio(temp.numero(), d)))
                .collect(Collectors.toList());
        episodios.forEach(System.out::println);

        // BUSQUEDA DE EPISODIOS A PARTIR DE "x" ANIO
//        System.out.println("=========| Ingresa el ANIO a partir del cual deseas ver los episodios");
//        var fecha = input.nextInt();
//        input.nextLine();
//
//        LocalDate fechaBusqueda = LocalDate.of(fecha, 1, 1);
//
//        DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        episodios.stream()
//                .filter(epi -> epi.getFechaDeLanzamiento() != null && epi.getFechaDeLanzamiento().isAfter(fechaBusqueda))
//                .forEach(epi -> System.out.println(
//                        "TEMPORADA " + epi.getTemporada() +
//                        " EPISODIO " + epi.getTitulo() +
//                        " FECHA DE LANZAMIENTO " + epi.getFechaDeLanzamiento().format(dateFormater)
//                ));
        // BUSCAR EPISODIOS POR UN "PEDAZO" DE TITULO
        System.out.println("Escribe el titulo del episodio a ver: ");
        var pedazoTitulo = input.nextLine();
        Optional<Episodio> episodioBuscado = episodios.stream()
                .filter(epi -> epi.getTitulo().toUpperCase().equals(pedazoTitulo.toUpperCase()))
                .findFirst();
        if(episodioBuscado.isPresent()) {
            System.out.println("EPISODIO ENCONTRADO!");
            System.out.println("LOS DATOS SON: " + episodioBuscado.get());
        }else{
            System.out.println("EPISODIO NO ENCONTRADO");
        }
    }
}
