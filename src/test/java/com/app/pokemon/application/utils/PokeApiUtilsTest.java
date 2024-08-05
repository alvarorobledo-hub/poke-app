package com.app.pokemon.application.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PokeApiUtilsTest {

    private static final String POKE_API_BASE_URL = "https://pokeapi.co/api/v2/";

    @Test
    void shouldReturnPokemonIdFromUrl() {
        String firstPokemonUrl = POKE_API_BASE_URL + 34;
        String secondPokemonUrl = POKE_API_BASE_URL + 984;
        String thirdPokemonUrl = POKE_API_BASE_URL + 342;

        Integer firstPokemonId = PokeApiUtils.extractIdFromUrl(firstPokemonUrl);
        Integer secondPokemonId = PokeApiUtils.extractIdFromUrl(secondPokemonUrl);
        Integer thirdPokemonId = PokeApiUtils.extractIdFromUrl(thirdPokemonUrl);

        assertEquals(34, firstPokemonId);
        assertEquals(984, secondPokemonId);
        assertEquals(342, thirdPokemonId);
    }

}