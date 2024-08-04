package com.app.pokemon.infrastructure.adapter;

import com.app.pokemon.infrastructure.dto.PokemonPageResponse;
import com.app.pokemon.infrastructure.dto.PokemonResponse;
import com.app.pokemon.infrastructure.dto.PokemonResult;
import com.app.pokemon.domain.model.Pokemon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static com.app.pokemon.application.mother.PokemonPageResponseObjectMother.createPokemonPageResponse;
import static com.app.pokemon.application.mother.PokemonResponseObjectMother.createAllPokemonMap;
import static com.app.pokemon.domain.mother.PokemonObjectMother.createAllPokemon;
import static com.app.pokemon.domain.utils.PokeApiUtils.extractIdFromUrl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PokeApiClientAdapterTest {

    private static final Integer DEFAULT_LIMIT = 20;
    private static final Integer DEFAULT_OFFSET = 0;

    @Mock
    private PokeApiClientDefinition client;

    @InjectMocks
    private PokeApiClientAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter.limit = DEFAULT_LIMIT;
        adapter.offset = DEFAULT_OFFSET;
    }

    @Test
    void shouldGetAllPokemonFromClient() {
        List<Pokemon> pokemonList = createAllPokemon();
        Map<Integer, PokemonResponse> pokemonMap = createAllPokemonMap();
        PokemonPageResponse pokemonPage = createPokemonPageResponse();

        doReturn(pokemonPage).when(client).getPokemonPage(adapter.offset, adapter.limit);
        for (PokemonResult pokemonResult : pokemonPage.getResults()) {
            Integer pokemonId = extractIdFromUrl(pokemonResult.getUrl());
            doReturn(pokemonMap.get(pokemonId)).when(client).getPokemon(pokemonId);
        }

        List<Pokemon> response = adapter.getAllPokemon();

        assertNotNull(response);
        assertEquals(pokemonList, response);

        verify(client, times(1)).getPokemonPage(adapter.offset, adapter.limit);
        verify(client, times(pokemonPage.getCount())).getPokemon(any());
    }
}