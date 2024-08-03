package com.app.pokemon.infrastructure.api;

import com.app.pokemon.application.service.TopHighestPokemonService;
import com.app.pokemon.domain.model.Pokemon;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GetTopHighestPokemonController extends PokemonController {

    private final TopHighestPokemonService service;

    @GetMapping(value = "/highest")
    public ResponseEntity<List<Pokemon>> getTopHighestPokemon(@RequestParam(defaultValue = "5", required = false) Integer top) {
        return ResponseEntity.ok(service.getTopHighestPokemon(top));
    }
}
