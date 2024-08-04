package com.app.pokemon.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PokemonResult {
    String name;
    String url;
}
