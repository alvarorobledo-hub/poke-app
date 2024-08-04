package com.app.pokemon.infrastructure.mapper;

import com.app.pokemon.infrastructure.dto.PokemonResponse;
import com.app.pokemon.domain.model.Pokemon;

public class PokemonMapper {

    private PokemonMapper() {}

    public static Pokemon from(PokemonResponse pokemonResponse) {
        return Pokemon.builder()
                .id(pokemonResponse.getId())
                .name(pokemonResponse.getName())
                .baseExperience(pokemonResponse.getBaseExperience())
                .height(pokemonResponse.getHeight())
                .weight(pokemonResponse.getWeight())
                .build();
    }
}
