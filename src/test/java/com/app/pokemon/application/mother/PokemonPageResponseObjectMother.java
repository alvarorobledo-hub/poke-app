package com.app.pokemon.application.mother;

import com.app.pokemon.application.dto.PokemonPageResponse;
import com.app.pokemon.application.dto.PokemonResult;

import java.util.List;

public class PokemonPageResponseObjectMother {

    public static PokemonPageResponse createPokemonPageResponse() {
        List<PokemonResult> results = PokemonResultObjectMother.createAllPokemon();
        return PokemonPageResponse.builder()
                .count(results.size())
                .results(results)
                .build();
    }
}
