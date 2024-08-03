package com.app.pokemon.infrastructure.api;

import com.app.pokemon.application.service.TopHeaviestPokemonService;
import com.app.pokemon.domain.model.Pokemon;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GetTopHeaviestPokemonController extends PokemonController {

    private final TopHeaviestPokemonService service;

    @GetMapping(value = "/heaviest")
    public ResponseEntity<List<Pokemon>> getTopHeaviestPokemon(@RequestParam(defaultValue = "5", required = false) Integer top) {
        return ResponseEntity.ok(service.getTopHeaviestPokemon(top));
    }
}
