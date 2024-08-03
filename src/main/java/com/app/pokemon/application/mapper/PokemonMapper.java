package com.app.pokemon.application.mapper;

import com.app.pokemon.application.dto.PokemonResponse;
import com.app.pokemon.domain.model.Pokemon;

public class PokemonMapper {
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
