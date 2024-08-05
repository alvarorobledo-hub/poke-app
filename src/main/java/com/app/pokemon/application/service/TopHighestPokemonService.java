package com.app.pokemon.application.service;

import com.app.pokemon.domain.client.PokemonClient;
import com.app.pokemon.domain.model.Pokemon;
import com.app.pokemon.domain.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class TopHighestPokemonService {

    private final PokemonClient client;
    private final PokemonRepository pokemonRepository;

    public List<Pokemon> getTopHighestPokemon(Integer top) {
        log.info("Getting top {} highest pokemon", top);
        List<Pokemon> pokemons = pokemonRepository.findAll();

        if (pokemons.isEmpty()) {
            pokemons = client.getAllPokemon();

            log.info("Saving {} pokemons in cache", pokemons.size());
            pokemonRepository.save(pokemons);
        }

        return pokemons.stream()
                .sorted((firstPokemon, secondPokemon) -> Integer.compare(secondPokemon.getHeight(), firstPokemon.getHeight()))
                .limit(top)
                .toList();
    }
}
