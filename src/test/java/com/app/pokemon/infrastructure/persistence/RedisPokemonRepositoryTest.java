package com.app.pokemon.infrastructure.persistence;

import com.app.pokemon.domain.model.Pokemon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.redisson.api.RKeys;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.app.pokemon.domain.mother.PokemonObjectMother.createAllPokemon;
import static com.app.pokemon.domain.mother.PokemonObjectMother.createAllPokemonMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RedisPokemonRepositoryTest {

    private static final Integer DEFAULT_TTL = 7;
    private static final String DEFAULT_POKEMON_KEY = "pokemons";

    @Mock
    private RedissonClient client;

    @Mock
    private RMap<Integer, Pokemon> pokemonRMap;

    @Mock
    private RKeys rKeys;

    @InjectMocks
    private RedisPokemonRepository repository;

    @BeforeEach
    void setUp() {
        repository.ttl = DEFAULT_TTL;
    }

    @Test
    void shouldSavePokemonListOnRedis() {
        List<Pokemon> pokemonList = createAllPokemon();
        Map<Integer, Pokemon> pokemonMap = createAllPokemonMap();

        doReturn(pokemonRMap).when(client).getMap(DEFAULT_POKEMON_KEY);
        doReturn(rKeys).when(client).getKeys();

        repository.save(pokemonList);

        verify(rKeys).expire(DEFAULT_POKEMON_KEY, DEFAULT_TTL, TimeUnit.DAYS);
        verify(pokemonRMap).putAll(pokemonMap);
    }

    @Test
    void shouldFindAllPokemonListOnRedis() {
        List<Pokemon> expectedPokemonList = createAllPokemon();

        doReturn(pokemonRMap).when(client).getMap(DEFAULT_POKEMON_KEY);
        doReturn(expectedPokemonList).when(pokemonRMap).readAllValues();

        List<Pokemon> response = repository.findAll();

        assertNotNull(response);
        assertEquals(expectedPokemonList, response);
    }
}