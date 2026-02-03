package com.alvinodev.springai_ecomarket.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.ai.google.genai.GoogleGenAiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/categorizador")
public class CategorizadorDeProductosController {
    private final ChatClient chatClient;

    public CategorizadorDeProductosController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping
    public Map CategorizarProductos(
            @RequestParam(value = "producto", defaultValue = "Cepillo de dientes de bambú") String producto
    ) {
        var systemPrompt = """
                Actúa como un categorizador de productos y debes responder solo el nombre de la categoría del producto informado
                                
                Escoge una categoría de la siguiente lista:
                                
                1. Higiene Personal
                2. Electrónicos
                3. Deportes
                4 Otros
                                
                Ejemplo de uso:
                                
                Producto: Pelota de fútbol
                Respuesta: Deportes
                """;

        String response = this.chatClient.prompt()
                .system(systemPrompt) // Configura el rol del sistema
                .user(producto)       // Envía la entrada del usuario
                .options(GoogleGenAiChatOptions.builder()
                        .temperature(0.5)
                        .build())
                .call()
                .content(); // Obtenemos solo el texto de la respuesta

        return Map.of("categoria", response);
    }
}