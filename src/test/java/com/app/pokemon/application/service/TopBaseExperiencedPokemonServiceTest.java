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
import static com.app.pokemon.domain.mother.PokemonObjectMother.createTopBaseExperiencedPokemon;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TopBaseExperiencedPokemonServiceTest {

    private static final Integer DEFAULT_TOP = 5;

    @Mock
    private PokemonClient client;

    @Mock
    private PokemonRepository repository;

    @InjectMocks
    private TopBaseExperiencedPokemonService service;

    @Test
    void shouldGetTopBaseExperiencedPokemonFromClient() {
        List<Pokemon> pokemonList = createAllPokemon();
        List<Pokemon> topBaseExperiencedPokemonList = createTopBaseExperiencedPokemon();

        doReturn(Collections.emptyList()).when(repository).findAll();
        doReturn(pokemonList).when(client).getAllPokemon();
        doNothing().when(repository).save(pokemonList);

        List<Pokemon> response = service.getTopBaseExperiencePokemon(DEFAULT_TOP);

        assertNotNull(response);
        assertEquals(DEFAULT_TOP, response.size());
        assertEquals(topBaseExperiencedPokemonList, response);

        verify(repository).findAll();
        verify(client).getAllPokemon();
        verify(repository).save(pokemonList);
    }

    @Test
    void shouldGetTopBaseExperiencedPokemonFromCache() {
        List<Pokemon> pokemonList = createAllPokemon();
        List<Pokemon> topBaseExperiencedPokemonList = createTopBaseExperiencedPokemon();

        doReturn(pokemonList).when(repository).findAll();

        List<Pokemon> response = service.getTopBaseExperiencePokemon(DEFAULT_TOP);

        assertNotNull(response);
        assertEquals(DEFAULT_TOP, response.size());
        assertEquals(topBaseExperiencedPokemonList, response);

        verify(repository).findAll();
        verify(client, never()).getAllPokemon();
        verify(repository, never()).save(any());
    }

}