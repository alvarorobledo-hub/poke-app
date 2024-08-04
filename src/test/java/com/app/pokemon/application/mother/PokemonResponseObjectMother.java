package com.app.pokemon.application.mother;

import com.app.pokemon.infrastructure.dto.PokemonResponse;

import java.util.HashMap;
import java.util.Map;

public class PokemonResponseObjectMother {

    public static PokemonResponse createPokemon() {
        return PokemonResponse.builder()
                .id(1)
                .name("Charizard")
                .weight(600)
                .height(123)
                .baseExperience(99)
                .build();
    }

    public static PokemonResponse createNonBaseExperiencePokemon() {
        return PokemonResponse.builder()
                .id(2)
                .name("Bulbasur")
                .weight(600)
                .height(123)
                .build();
    }

    public static Map<Integer, PokemonResponse> createAllPokemonMap() {
        Map<Integer, PokemonResponse> pokemonMap = new HashMap<>();
        pokemonMap.put(
                686,
                PokemonResponse.builder()
                        .id(686)
                        .name("Rattata")
                        .weight(87)
                        .height(888)
                        .baseExperience(16)
                        .build()
        );
        pokemonMap.put(
                339,
                PokemonResponse.builder()
                        .id(339)
                        .name("Snorlax")
                        .weight(9)
                        .height(871)
                        .baseExperience(59)
                        .build()
        );
        pokemonMap.put(
                576,
                PokemonResponse.builder()
                        .id(576)
                        .name("Psyduck")
                        .weight(21)
                        .height(856)
                        .baseExperience(30)
                        .build()
        );
        pokemonMap.put(
                66,
                PokemonResponse.builder()
                        .id(66)
                        .name("Pikachu")
                        .weight(98)
                        .height(25)
                        .baseExperience(927)
                        .build()
        );
        pokemonMap.put(
                12,
                PokemonResponse.builder()
                        .id(12)
                        .name("Charmander")
                        .weight(12)
                        .height(27)
                        .baseExperience(678)
                        .build()
        );
        pokemonMap.put(
                72,
                PokemonResponse.builder()
                        .id(72)
                        .name("Squirtle")
                        .weight(54)
                        .height(19)
                        .baseExperience(512)
                        .build()
        );
        pokemonMap.put(
                987,
                PokemonResponse.builder()
                        .id(987)
                        .name("Machop")
                        .weight(72)
                        .height(46)
                        .baseExperience(510)
                        .build()
        );
        pokemonMap.put(
                49,
                PokemonResponse.builder()
                        .id(49)
                        .name("Voltorb")
                        .weight(12)
                        .height(12)
                        .baseExperience(499)
                        .build()
        );
        pokemonMap.put(
                6,
                PokemonResponse.builder()
                        .id(6)
                        .name("Mewtwo")
                        .weight(33)
                        .height(856)
                        .baseExperience(88)
                        .build()
        );
        pokemonMap.put(
                299,
                PokemonResponse.builder()
                        .id(299)
                        .name("Magikarp")
                        .weight(2)
                        .height(853)
                        .baseExperience(43)
                        .build()
        );
        pokemonMap.put(
                665,
                PokemonResponse.builder()
                        .id(665)
                        .name("Jigglypuff")
                        .weight(999)
                        .height(25)
                        .baseExperience(16)
                        .build()
        );
        pokemonMap.put(
                122,
                PokemonResponse.builder()
                        .id(122)
                        .name("Rayquaza")
                        .weight(872)
                        .height(27)
                        .baseExperience(59)
                        .build()
        );
        pokemonMap.put(
                723,
                PokemonResponse.builder()
                        .id(723)
                        .name("Eevee")
                        .weight(801)
                        .height(19)
                        .baseExperience(30)
                        .build()
        );
        pokemonMap.put(
                111,
                PokemonResponse.builder()
                        .id(111)
                        .name("Blaziken")
                        .weight(777)
                        .height(46)
                        .baseExperience(88)
                        .build()
        );
        pokemonMap.put(
                408,
                PokemonResponse.builder()
                        .id(408)
                        .name("Ditto")
                        .weight(750)
                        .height(12)
                        .baseExperience(43)
                        .build()
        );

        return pokemonMap;
    }
}
