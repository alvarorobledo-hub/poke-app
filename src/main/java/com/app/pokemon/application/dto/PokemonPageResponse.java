package com.app.pokemon.application.dto;

import lombok.Data;

import java.util.List;

@Data
public class PokemonPageResponse {
    Integer count;
    String next;
    String previous;
    List<PokemonResult> results;
}
