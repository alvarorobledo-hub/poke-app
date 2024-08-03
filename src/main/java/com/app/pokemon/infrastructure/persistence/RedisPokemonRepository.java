package com.app.pokemon.infrastructure.persistence;

import com.app.pokemon.domain.model.Pokemon;
import com.app.pokemon.domain.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class RedisPokemonRepository implements PokemonRepository {

    private static final String POKEMON_KEY = "pokemons";

    @Value("${redis.ttl-in-days}")
    Integer ttl;

    private final RedissonClient redissonClient;

    @Override
    public void save(List<Pokemon> pokemons) {
        RMap<Integer, Pokemon> pokemonMap = redissonClient.getMap(POKEMON_KEY);
        Map<Integer, Pokemon> pokemonMapBatch = pokemons.stream()
                .collect(Collectors.toMap(Pokemon::getId, pokemon -> pokemon));

        pokemonMap.putAll(pokemonMapBatch);

        redissonClient.getKeys().expire(POKEMON_KEY, ttl, TimeUnit.DAYS);
    }

    @Override
    public List<Pokemon> findAll() {
        RMap<Integer, Pokemon> pokemonMap = redissonClient.getMap(POKEMON_KEY);
        return pokemonMap.readAllValues().stream()
                .toList();
    }

}
