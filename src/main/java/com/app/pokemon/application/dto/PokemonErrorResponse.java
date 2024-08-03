package com.app.pokemon.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PokemonErrorResponse {
    private String message;
    private Integer pokeStatus;
}
