package com.alvinodev.screenmatchpro.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}