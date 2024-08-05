package com.app.pokemon.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class PokemonError {
    private String message;
    private Integer pokeStatus;
}
