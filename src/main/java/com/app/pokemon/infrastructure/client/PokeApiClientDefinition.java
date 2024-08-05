package com.app.pokemon.infrastructure.client;

import com.app.pokemon.infrastructure.dto.PokemonPageResponse;
import com.app.pokemon.infrastructure.dto.PokemonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "poke-api-client", url = "${poke-api.base-url}")
public interface PokeApiClientDefinition {

    @GetMapping(value = "/pokemon/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    PokemonResponse getPokemon(@PathVariable("id") Integer id);

    @GetMapping(value = "/pokemon", consumes = MediaType.APPLICATION_JSON_VALUE)
    PokemonPageResponse getPokemonPage(@RequestParam(name = "offset") Integer offset, @RequestParam(name = "limit") Integer limit);
}
