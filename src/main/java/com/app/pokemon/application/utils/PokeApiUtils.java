package com.app.pokemon.application.utils;

public class PokeApiUtils {

    private PokeApiUtils() {}

    public static Integer extractIdFromUrl(String url) {
        String[] parts = url.split("/");
        return Integer.parseInt(parts[parts.length - 1]);
    }
}
