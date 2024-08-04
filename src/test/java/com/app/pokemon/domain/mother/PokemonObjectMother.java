package com.app.pokemon.domain.mother;

import com.app.pokemon.domain.model.Pokemon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
                        .weight(98)
                        .height(25)
                        .baseExperience(927)
                        .build()
        );
        pokemonList.add(
                Pokemon.builder()
                        .id(12)
                        .name("Charmander")
                        .weight(12)
                        .height(27)
                        .baseExperience(678)
                        .build()
        );
        pokemonList.add(
                Pokemon.builder()
                        .id(72)
                        .name("Squirtle")
                        .weight(54)
                        .height(19)
                        .baseExperience(512)
                        .build()
        );
        pokemonList.add(
                Pokemon.builder()
                        .id(987)
                        .name("Machop")
                        .weight(72)
                        .height(46)
                        .baseExperience(510)
                        .build()
        );
        pokemonList.add(
                Pokemon.builder()
                        .id(49)
                        .name("Voltorb")
                        .weight(12)
                        .height(12)
                        .baseExperience(499)
                        .build()
        );

        return pokemonList;
    }

    public static List<Pokemon> createTopHeaviestPokemon() {
        List<Pokemon> pokemonList = new ArrayList<>();
        pokemonList.add(
                Pokemon.builder()
                        .id(665)
                        .name("Jigglypuff")
                        .weight(999)
                        .height(25)
                        .baseExperience(16)
                        .build()
        );
        pokemonList.add(
                Pokemon.builder()
                        .id(122)
                        .name("Rayquaza")
                        .weight(872)
                        .height(27)
                        .baseExperience(59)
                        .build()
        );
        pokemonList.add(
                Pokemon.builder()
                        .id(723)
                        .name("Eevee")
                        .weight(801)
                        .height(19)
                        .baseExperience(30)
                        .build()
        );
        pokemonList.add(
                Pokemon.builder()
                        .id(111)
                        .name("Blaziken")
                        .weight(777)
                        .height(46)
                        .baseExperience(88)
                        .build()
        );
        pokemonList.add(
                Pokemon.builder()
                        .id(408)
                        .name("Ditto")
                        .weight(750)
                        .height(12)
                        .baseExperience(43)
                        .build()
        );

        return pokemonList;
    }

    public static List<Pokemon> createTopHighestPokemon() {
        List<Pokemon> pokemonList = new ArrayList<>();
        pokemonList.add(
                Pokemon.builder()
                        .id(686)
                        .name("Rattata")
                        .weight(87)
                        .height(888)
                        .baseExperience(16)
                        .build()
        );
        pokemonList.add(
                Pokemon.builder()
                        .id(339)
                        .name("Snorlax")
                        .weight(9)
                        .height(871)
                        .baseExperience(59)
                        .build()
        );
        pokemonList.add(
                Pokemon.builder()
                        .id(576)
                        .name("Psyduck")
                        .weight(21)
                        .height(856)
                        .baseExperience(30)
                        .build()
        );
        pokemonList.add(
                Pokemon.builder()
                        .id(6)
                        .name("Mewtwo")
                        .weight(33)
                        .height(856)
                        .baseExperience(88)
                        .build()
        );
        pokemonList.add(
                Pokemon.builder()
                        .id(299)
                        .name("Magikarp")
                        .weight(2)
                        .height(853)
                        .baseExperience(43)
                        .build()
        );

        return pokemonList;
    }

    public static List<Pokemon> createAllPokemon() {
        List<Pokemon> pokemonList = new ArrayList<>();
        pokemonList.add(
                Pokemon.builder()
                        .id(686)
                        .name("Rattata")
                        .weight(87)
                        .height(888)
                        .baseExperience(16)
                        .build()
        );
        pokemonList.add(
                Pokemon.builder()
                        .id(339)
                        .name("Snorlax")
                        .weight(9)
                        .height(871)
                        .baseExperience(59)
                        .build()
        );
        pokemonList.add(
                Pokemon.builder()
                        .id(576)
                        .name("Psyduck")
                        .weight(21)
                        .height(856)
                        .baseExperience(30)
                        .build()
        );
        pokemonList.add(
                Pokemon.builder()
                        .id(66)
                        .name("Pikachu")
                        .weight(98)
                        .height(25)
                        .baseExperience(927)
                        .build()
        );
        pokemonList.add(
                Pokemon.builder()
                        .id(12)
                        .name("Charmander")
                        .weight(12)
                        .height(27)
                        .baseExperience(678)
                        .build()
        );
        pokemonList.add(
                Pokemon.builder()
                        .id(72)
                        .name("Squirtle")
                        .weight(54)
                        .height(19)
                        .baseExperience(512)
                        .build()
        );
        pokemonList.add(
                Pokemon.builder()
                        .id(987)
                        .name("Machop")
                        .weight(72)
                        .height(46)
                        .baseExperience(510)
                        .build()
        );
        pokemonList.add(
                Pokemon.builder()
                        .id(49)
                        .name("Voltorb")
                        .weight(12)
                        .height(12)
                        .baseExperience(499)
                        .build()
        );
        pokemonList.add(
                Pokemon.builder()
                        .id(6)
                        .name("Mewtwo")
                        .weight(33)
                        .height(856)
                        .baseExperience(88)
                        .build()
        );
        pokemonList.add(
                Pokemon.builder()
                        .id(299)
                        .name("Magikarp")
                        .weight(2)
                        .height(853)
                        .baseExperience(43)
                        .build()
        );
        pokemonList.add(
                Pokemon.builder()
                        .id(665)
                        .name("Jigglypuff")
                        .weight(999)
                        .height(25)
                        .baseExperience(16)
                        .build()
        );
        pokemonList.add(
                Pokemon.builder()
                        .id(122)
                        .name("Rayquaza")
                        .weight(872)
                        .height(27)
                        .baseExperience(59)
                        .build()
        );
        pokemonList.add(
                Pokemon.builder()
                        .id(723)
                        .name("Eevee")
                        .weight(801)
                        .height(19)
                        .baseExperience(30)
                        .build()
        );
        pokemonList.add(
                Pokemon.builder()
                        .id(111)
                        .name("Blaziken")
                        .weight(777)
                        .height(46)
                        .baseExperience(88)
                        .build()
        );
        pokemonList.add(
                Pokemon.builder()
                        .id(408)
                        .name("Ditto")
                        .weight(750)
                        .height(12)
                        .baseExperience(43)
                        .build()
        );

        return pokemonList;
    }

    public static Map<Integer, Pokemon> createAllPokemonMap() {
        return createAllPokemon().stream().collect(Collectors.toMap(Pokemon::getId, pokemon -> pokemon));
    }
}
