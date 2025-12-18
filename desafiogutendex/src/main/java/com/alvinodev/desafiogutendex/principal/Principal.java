package com.alvinodev.desafiogutendex.principal;

import com.alvinodev.desafiogutendex.model.Datos;
import com.alvinodev.desafiogutendex.service.ConvierteDatos;
import com.alvinodev.desafiogutendex.service.consumoAPI;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private consumoAPI consumoAPI = new consumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();

    public void muestraMenu() {
        var json = consumoAPI.obtenerDatos(URL_BASE);
        System.out.println(json);

        var datos = conversor.obtenerDatos(json,Datos.class);
        System.out.println(datos);
    }
}
