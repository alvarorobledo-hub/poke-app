package com.app.pokemon.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PokemonError {
    private String message;
    private Integer pokeStatus;
}
