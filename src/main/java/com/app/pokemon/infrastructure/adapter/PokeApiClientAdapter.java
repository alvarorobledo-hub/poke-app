package com.app.pokemon.infrastructure.adapter;

import com.app.pokemon.infrastructure.dto.PokemonPageResponse;
import com.app.pokemon.infrastructure.dto.PokemonResponse;
import com.app.pokemon.domain.client.PokeApiClient;
import com.app.pokemon.domain.model.Pokemon;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.app.pokemon.infrastructure.mapper.PokemonMapper.from;
import static com.app.pokemon.domain.utils.PokeApiUtils.extractIdFromUrl;

@Slf4j
@Component
@RequiredArgsConstructor
public class PokeApiClientAdapter implements PokeApiClient {

    @Value("${poke-api.limit}")
    Integer limit;

    @Value("${poke-api.offset}")
    Integer offset;

    private final PokeApiClientDefinition client;

    @Override
    public List<Pokemon> getAllPokemon() {
        List<Pokemon> allPokemon = new ArrayList<>();

        Integer pageOffset = offset;
        boolean hasNext;

        do {
            PokemonPageResponse page = getPokemonPage(pageOffset, limit);

            allPokemon.addAll(
                    page.getResults().stream().map(result -> {
                        Integer pokemonId = extractIdFromUrl(result.getUrl());
                        return from(getPokemon(pokemonId));
                    }).toList()
            );

            pageOffset += limit;

            hasNext = page.getNext() != null;
        } while(hasNext);

        return allPokemon;
    }

    private PokemonResponse getPokemon(Integer id) {
        return client.getPokemon(id);
    }

    private PokemonPageResponse getPokemonPage(Integer offset, Integer limit) {
        return client.getPokemonPage(offset, limit);
    }
}
