package com.app.pokemon.infrastructure.mapper;

import com.app.pokemon.application.mother.PokemonResponseObjectMother;
import com.app.pokemon.domain.model.Pokemon;
import com.app.pokemon.domain.mother.PokemonObjectMother;
import com.app.pokemon.infrastructure.dto.PokemonResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class PokemonMapperTest {

    @Test
    void shouldMapPokemonResponseToPokemon() {
        Pokemon expectedResponse = PokemonObjectMother.createPokemon();
        PokemonResponse pokemonResponse = PokemonResponseObjectMother.createPokemon();

        Pokemon response = PokemonMapper.from(pokemonResponse);

        assertNotNull(response);
        assertEquals(expectedResponse, response);
    }

    @Test
    void shouldMapPokemonResponseToPokemonWithoutBaseExperience() {
        Pokemon expectedResponse = PokemonObjectMother.createNonBaseExperiencePokemon();
        PokemonResponse pokemonResponse = PokemonResponseObjectMother.createNonBaseExperiencePokemon();

        Pokemon response = PokemonMapper.from(pokemonResponse);

        assertNotNull(response);
        assertEquals(expectedResponse, response);
    }

}