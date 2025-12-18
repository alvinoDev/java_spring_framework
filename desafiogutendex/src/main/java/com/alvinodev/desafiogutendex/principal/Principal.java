package com.alvinodev.desafiogutendex.principal;

import com.alvinodev.desafiogutendex.model.Datos;
import com.alvinodev.desafiogutendex.model.DatosLibros;
import com.alvinodev.desafiogutendex.service.ConvierteDatos;
import com.alvinodev.desafiogutendex.service.consumoAPI;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private consumoAPI consumoAPI = new consumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();

    public void muestraMenu() {
        var json = consumoAPI.obtenerDatos(URL_BASE);
        // System.out.println(json);

        var datos = conversor.obtenerDatos(json,Datos.class);
        // System.out.println(datos);

        // ==========| TOP 10 LIBROS MAS DESCARGADOS |==========
        AtomicInteger counter = new AtomicInteger(1);

        System.out.println("==========| TOP 10 LIBROS MAS DESCARGADOS |==========");
        datos.resultados().stream().
                sorted(Comparator.comparing( DatosLibros::numeroDeDescargas).reversed())
                .limit(10)
                .map(book -> book.titulo().toUpperCase())
                .forEach(titulo -> System.out.println(counter.getAndIncrement() + ".- " + titulo));

    }
}
