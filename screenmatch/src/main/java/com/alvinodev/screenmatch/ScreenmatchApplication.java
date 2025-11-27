package com.alvinodev.screenmatch;

import com.alvinodev.screenmatch.model.DatosEpisodio;
import com.alvinodev.screenmatch.model.DatosSerie;
import com.alvinodev.screenmatch.model.DatosTemporada;
import com.alvinodev.screenmatch.principal.EjemploStreams;
import com.alvinodev.screenmatch.principal.Principal;
import com.alvinodev.screenmatch.service.ConsumoAPI;
import com.alvinodev.screenmatch.service.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
        SpringApplication.run(ScreenmatchApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
//        Principal principal = new Principal();
//        principal.muestraMenu();
        EjemploStreams ejeStreams = new EjemploStreams();
        ejeStreams.muestraEjemplo();
    }
}
