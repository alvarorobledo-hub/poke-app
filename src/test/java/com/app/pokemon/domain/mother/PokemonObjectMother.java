package com.app.pokemon.domain.mother;

import com.app.pokemon.domain.model.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class PokemonObjectMother {

    public static Pokemon createPokemon() {
        return Pokemon.builder()
                .id(1)
                .name("Charizard")
                .weight(600)
                .height(123)
                .baseExperience(99)
                .build();
    }

    public static Pokemon createNonBaseExperiencePokemon() {
        return Pokemon.builder()
                .id(2)
                .name("Bulbasur")
                .weight(600)
                .height(123)
                .build();
    }

    public static List<Pokemon> createTopBaseExperiencedPokemon() {
        List<Pokemon> pokemonList = new ArrayList<>();
        pokemonList.add(
                Pokemon.builder()
                        .id(66)
                        .name("Pikachu")
                        .weight(999)
                        .height(25)
                        .baseExperience(16)
                        .build()
        );
        pokemonList.add(
                Pokemon.builder()
                        .id(12)
                        .name("Charmander")
                        .weight(872)
                        .height(27)
                        .baseExperience(59)
                        .build()
        );
        pokemonList.add(
                Pokemon.builder()
                        .id(72)
                        .name("Squirtle")
                        .weight(801)
                        .height(19)
                        .baseExperience(30)
                        .build()
        );
        pokemonList.add(
                Pokemon.builder()
                        .id(1)
                        .name("Machop")
                        .weight(777)
                        .height(46)
                        .baseExperience(88)
                        .build()
        );
        pokemonList.add(
                Pokemon.builder()
                        .id(1)
                        .name("Voltorb")
                        .weight(750)
                        .height(12)
                        .baseExperience(43)
                        .build()
        );

        return pokemonList;
    }
}
