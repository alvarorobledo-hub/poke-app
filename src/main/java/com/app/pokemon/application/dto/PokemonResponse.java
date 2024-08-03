package com.app.pokemon.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonResponse {
    Integer id;
    String name;
    @JsonProperty("base_experience")
    Integer baseExperience;
    Integer height;
    Integer weight;
}
