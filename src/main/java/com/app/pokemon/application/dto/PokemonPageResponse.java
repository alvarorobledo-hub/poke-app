package com.app.pokemon.application.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PokemonPageResponse {
    Integer count;
    String next;
    String previous;
    List<PokemonResult> results;
}
