package com.app.pokemon.domain.exceptions;

import static java.lang.String.format;

public class PokemonNotFound extends RuntimeException {
    public PokemonNotFound(Integer pokemonId) {
        super(format("Pokemon with id %d not found", pokemonId));
    }
}
