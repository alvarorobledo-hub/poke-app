package com.app.pokemon.infrastructure.api;

import com.app.pokemon.application.service.TopBaseExperiencedPokemonService;
import com.app.pokemon.domain.model.Pokemon;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GetTopBaseExperiencedPokemonController extends PokemonController {

    private final TopBaseExperiencedPokemonService service;

    @GetMapping(value = "/base-experience")
    public ResponseEntity<List<Pokemon>> getTopBaseExperiencePokemon(@RequestParam(defaultValue = "5", required = false) Integer top) {
        return ResponseEntity.ok(service.getTopBaseExperiencePokemon(top));
    }
}
