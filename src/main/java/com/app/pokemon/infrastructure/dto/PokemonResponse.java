package com.app.pokemon.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonResponse {
    Integer id;
    String name;
    @JsonProperty("base_experience")
    Integer baseExperience;
    Integer height;
    Integer weight;
}
