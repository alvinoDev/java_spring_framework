package com.alvinodev.springai_ecomarket.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.ai.google.genai.GoogleGenAiChatOptions;
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
        this.chatClient = chatClientBuilder
                .defaultOptions(
                        GoogleGenAiChatOptions.builder()
                        .model(GoogleGenAiChatModel.ChatModel.GEMINI_3_PRO_PREVIEW)
                        .build())
                .build();;
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
                        .model("gemini-3-flash-preview")
                        .temperature(0.5)
                        .build())
                .call()
                .content(); // Obtenemos solo el texto de la respuesta

        return Map.of("categoria", response);
    }

    @GetMapping("/contartokens")
    public Map<String, Object> categorizarConTokens(
            @RequestParam(value = "producto") String producto
    ) {
        var pregunta = "Genera 5 productos ecológicos";
        // 1. Realizamos la llamada completa
        var response = this.chatClient.prompt(pregunta)
                .user(producto)
                .options(GoogleGenAiChatOptions.builder()
                        .model("gemini-3-flash-preview")
                        .temperature(0.5)
                        .build())
                .call()
                .chatResponse(); // Obtenemos el objeto de respuesta completo

        // 2. Extraemos los metadatos de uso
        var metadata = response.getMetadata().getUsage();

        return Map.of(
                "respuesta", response.getResult().getOutput().getText(),
                "uso", Map.of(
                        "tokens_entrada", metadata.getPromptTokens(),
                        "tokens_salida", metadata.getCompletionTokens(),
                        "tokens_totales", metadata.getTotalTokens()
                )
        );
    }
}