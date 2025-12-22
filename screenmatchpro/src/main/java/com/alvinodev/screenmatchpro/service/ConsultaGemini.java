package com.alvinodev.screenmatchpro.service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

public class ConsultaGemini {

    private static String API_KEY = "pegar-clave-aqui";
    public static String obtenerTraduccion(String texto) {
        String modelo = "gemini-3-flash-preview";
        String prompt = "Traduce al español el siguiente texto. " +
                "Devuelve ÚNICAMENTE el texto traducido, sin explicaciones, sin comillas, " +
                "sin introducciones ni formato adicional: " + texto;

        Client cliente = new Client.Builder().apiKey(API_KEY).build();

        try {
            GenerateContentResponse respuesta = cliente.models.generateContent(
                    modelo,
                    prompt,
                    null // Parámetro para configuraciones adicionales
            );

            if (!respuesta.text().isEmpty()) {
                return respuesta.text().trim();
            }
        } catch (Exception e) {
            System.err.println("Error al llamar a la API de Gemini para traducción: " + e.getMessage());
        }

        return null;
    }
}
