package com.app.pokemon.application.mother;

import com.app.pokemon.infrastructure.dto.PokemonResult;

import java.util.ArrayList;
import java.util.List;

public class PokemonResultObjectMother {

    private static final String POKE_API_POKEMON_URL = "https://pokeapi.co/api/v2/pokemon/";

    public static List<PokemonResult> createAllPokemon() {
        List<PokemonResult> pokemonList = new ArrayList<>();
        pokemonList.add(
                PokemonResult.builder()
                        .name("Rattata")
                        .url(POKE_API_POKEMON_URL + 686)
                        .build()
        );
        pokemonList.add(
                PokemonResult.builder()
                        .name("Snorlax")
                        .url(POKE_API_POKEMON_URL + 339)
                        .build()
        );
        pokemonList.add(
                PokemonResult.builder()
                        .name("Psyduck")
                        .url(POKE_API_POKEMON_URL + 576)
                        .build()
        );
        pokemonList.add(
                PokemonResult.builder()
                        .name("Pikachu")
                        .url(POKE_API_POKEMON_URL + 66)
                        .build()
        );
        pokemonList.add(
                PokemonResult.builder()
                        .name("Charmander")
                        .url(POKE_API_POKEMON_URL + 12)
                        .build()
        );
        pokemonList.add(
                PokemonResult.builder()
                        .name("Squirtle")
                        .url(POKE_API_POKEMON_URL + 72)
                        .build()
        );
        pokemonList.add(
                PokemonResult.builder()
                        .name("Machop")
                        .url(POKE_API_POKEMON_URL + 987)
                        .build()
        );
        pokemonList.add(
                PokemonResult.builder()
                        .name("Voltorb")
                        .url(POKE_API_POKEMON_URL + 49)
                        .build()
        );
        pokemonList.add(
                PokemonResult.builder()
                        .name("Mewtwo")
                        .url(POKE_API_POKEMON_URL + 6)
                        .build()
        );
        pokemonList.add(
                PokemonResult.builder()
                        .name("Magikarp")
                        .url(POKE_API_POKEMON_URL + 299)
                        .build()
        );
        pokemonList.add(
                PokemonResult.builder()
                        .name("Jigglypuff")
                        .url(POKE_API_POKEMON_URL + 665)
                        .build()
        );
        pokemonList.add(
                PokemonResult.builder()
                        .name("Rayquaza")
                        .url(POKE_API_POKEMON_URL + 122)
                        .build()
        );
        pokemonList.add(
                PokemonResult.builder()
                        .name("Eevee")
                        .url(POKE_API_POKEMON_URL + 723)
                        .build()
        );
        pokemonList.add(
                PokemonResult.builder()
                        .name("Blaziken")
                        .url(POKE_API_POKEMON_URL + 111)
                        .build()
        );
        pokemonList.add(
                PokemonResult.builder()
                        .name("Ditto")
                        .url(POKE_API_POKEMON_URL + 408)
                        .build()
        );

        return pokemonList;
    }
}
