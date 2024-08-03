package com.app.pokemon.domain.utils;

public class PokeApiUtils {
    public static Integer extractIdFromUrl(String url) {
        String[] parts = url.split("/");
        return Integer.parseInt(parts[parts.length - 1]);
    }
}
