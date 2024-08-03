package com.app.pokemon.infrastructure.config;

import com.app.pokemon.application.service.TopBaseExperiencedPokemonService;
import com.app.pokemon.application.service.TopHeaviestPokemonService;
import com.app.pokemon.application.service.TopHighestPokemonService;
import com.app.pokemon.infrastructure.adapter.PokeApiClientAdapter;
import com.app.pokemon.infrastructure.persistence.RedisPokemonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfig {

    private final PokeApiClientAdapter pokeApiClient;
    private final RedisPokemonRepository pokemonRepository;

    @Bean
    public TopHeaviestPokemonService topHeaviestPokemonService() {
        return new TopHeaviestPokemonService(pokeApiClient, pokemonRepository);
    }

    @Bean
    public TopHighestPokemonService topHighestPokemonService() {
        return new TopHighestPokemonService(pokeApiClient, pokemonRepository);
    }

    @Bean
    public TopBaseExperiencedPokemonService topBaseExperiencedPokemonService() {
        return new TopBaseExperiencedPokemonService(pokeApiClient, pokemonRepository);
    }

}
