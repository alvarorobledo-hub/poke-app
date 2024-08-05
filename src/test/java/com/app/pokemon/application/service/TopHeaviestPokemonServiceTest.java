package com.app.pokemon.application.service;

import com.app.pokemon.domain.client.PokemonClient;
import com.app.pokemon.domain.model.Pokemon;
import com.app.pokemon.domain.repository.PokemonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static com.app.pokemon.domain.mother.PokemonObjectMother.createAllPokemon;
import static com.app.pokemon.domain.mother.PokemonObjectMother.createTopHeaviestPokemon;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TopHeaviestPokemonServiceTest {

    private static final Integer DEFAULT_TOP = 5;

    @Mock
    private PokemonClient client;

    @Mock
    private PokemonRepository repository;

    @InjectMocks
    private TopHeaviestPokemonService service;

    @Test
    void shouldGetTopHeaviestPokemonFromClient() {
        List<Pokemon> pokemonList = createAllPokemon();
        List<Pokemon> topHeaviestPokemonList = createTopHeaviestPokemon();

        doReturn(Collections.emptyList()).when(repository).findAll();
        doReturn(pokemonList).when(client).getAllPokemon();
        doNothing().when(repository).save(pokemonList);

        List<Pokemon> response = service.getTopHeaviestPokemon(DEFAULT_TOP);

        assertNotNull(response);
        assertEquals(DEFAULT_TOP, response.size());
        assertEquals(topHeaviestPokemonList, response);

        verify(repository).findAll();
        verify(client).getAllPokemon();
        verify(repository).save(pokemonList);
    }

    @Test
    void shouldGetTopHeaviestPokemonFromCache() {
        List<Pokemon> pokemonList = createAllPokemon();
        List<Pokemon> topHeaviestPokemonList = createTopHeaviestPokemon();

        doReturn(pokemonList).when(repository).findAll();

        List<Pokemon> response = service.getTopHeaviestPokemon(DEFAULT_TOP);

        assertNotNull(response);
        assertEquals(DEFAULT_TOP, response.size());
        assertEquals(topHeaviestPokemonList, response);

        verify(repository).findAll();
        verify(client, never()).getAllPokemon();
        verify(repository, never()).save(any());
    }

}