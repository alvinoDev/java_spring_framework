package com.alvinodev.desafiogutendex.principal;

import com.alvinodev.desafiogutendex.model.Datos;
import com.alvinodev.desafiogutendex.model.DatosLibros;
import com.alvinodev.desafiogutendex.service.ConvierteDatos;
import com.alvinodev.desafiogutendex.service.consumoAPI;

import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private consumoAPI consumoAPI = new consumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner input = new Scanner(System.in);

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

        // ===========| BUSQUEDA DE LIBRO POR NOMBRE |==========
        System.out.println("===========| BUSQUEDA DE LIBRO POR NOMBRE |==========");
        var tituloLibro = input.nextLine();
        json = consumoAPI.obtenerDatos(URL_BASE+"?search="+tituloLibro.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                .filter(book -> book.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();
        if(libroBuscado.isPresent()){
            System.out.println("LIBRO ENCONTRADO: " + libroBuscado.get());
        }else{
            System.out.println("LIBRO NO ENCONTRADO");
        }

























    }
}
