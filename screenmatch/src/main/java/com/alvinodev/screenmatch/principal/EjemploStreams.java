package com.alvinodev.screenmatch.principal;

import java.util.Arrays;
import java.util.List;

public class EjemploStreams {
    public void muestraEjemplo() {
        List<String> nombres = Arrays.asList("Paola", "Juan", "Sandra", "Angel", "Beatriz");
        nombres.stream()
                .sorted()
                .limit(4)
                .filter(nom -> nom.startsWith("B"))
                .map(nom -> nom.toUpperCase())
                .forEach(System.out::println);
    }
}
