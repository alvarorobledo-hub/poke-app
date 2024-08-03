package com.app.pokemon.domain.exceptions;

public class PokemonServerError extends RuntimeException {
    public PokemonServerError() {
        super("There has been a problem with the poke-api request. Please, try again later");
    }
}
