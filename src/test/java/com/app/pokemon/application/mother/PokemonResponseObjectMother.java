package com.app.pokemon.application.mother;

import com.app.pokemon.application.dto.PokemonResponse;
import com.app.pokemon.domain.model.Pokemon;

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
}
