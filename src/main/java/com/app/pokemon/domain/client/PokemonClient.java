package com.app.pokemon.domain.client;

import com.app.pokemon.domain.model.Pokemon;

import java.util.List;

public interface PokemonClient {
    List<Pokemon> getAllPokemon();
}