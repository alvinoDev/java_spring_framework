package com.alvinodev.desafiogutendex.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
