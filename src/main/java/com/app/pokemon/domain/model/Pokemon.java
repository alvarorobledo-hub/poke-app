package com.app.pokemon.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pokemon {
    Integer id;
    String name;
    @JsonProperty("base_experience")
    Integer baseExperience;
    Integer height;
    Integer weight;
}
