package com.app.pokemon.application.service;

import com.app.pokemon.domain.client.PokeApiClient;
import com.app.pokemon.domain.model.Pokemon;
import com.app.pokemon.domain.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class TopBaseExperiencedPokemonService {

    private final PokeApiClient client;
    private final PokemonRepository pokemonRepository;

    public List<Pokemon> getTopBaseExperiencePokemon(Integer top)  {
        log.info("Getting top {} base experienced pokemon", top);
        List<Pokemon> pokemons = pokemonRepository.findAll();

        if (pokemons.isEmpty()) {
            pokemons = client.getAllPokemon();

            log.info("Saving {} pokemons in cache", pokemons.size());
            pokemonRepository.save(pokemons);
        }

        return pokemons.stream()
                .sorted(Comparator.comparing(Pokemon::getBaseExperience, Comparator.nullsFirst(Comparator.naturalOrder())).reversed())
                .limit(top)
                .toList();
    }
}
