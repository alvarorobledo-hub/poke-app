package com.app.pokemon.domain.mother;

import com.app.pokemon.domain.model.Pokemon;

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
}
