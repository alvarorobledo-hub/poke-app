package com.app.pokemon.domain.repository;

import com.app.pokemon.domain.model.Pokemon;

import java.util.List;

public interface PokemonRepository {
    void save(List<Pokemon> pokemons);
    List<Pokemon> findAll();
}
