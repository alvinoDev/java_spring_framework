package com.alvinodev.screenmatch;

import com.alvinodev.screenmatch.model.DatosEpisodio;
import com.alvinodev.screenmatch.model.DatosSerie;
import com.alvinodev.screenmatch.service.ConsumoAPI;
import com.alvinodev.screenmatch.service.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
        SpringApplication.run(ScreenmatchApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        var consumoApi = new ConsumoAPI();

        var json = consumoApi.obtenerDatos("https://www.omdbapi.com/?t=game+of+thrones&apikey=34353774");
        // var json = consumoApi.obtenerDatos("https://coffee.alexflipnote.dev/random.json");

        System.out.println(json);

        ConvierteDatos conversor = new ConvierteDatos();
        var datos = conversor.obtenerDatos(json, DatosSerie.class);
        System.out.println(datos);

        json = consumoApi.obtenerDatos("https://www.omdbapi.com/?t=game+of+thrones&season=1&episode=1&apikey=34353774");
        DatosEpisodio episodios = conversor.obtenerDatos(json, DatosEpisodio.class);
        System.out.println(episodios);
    }
}
