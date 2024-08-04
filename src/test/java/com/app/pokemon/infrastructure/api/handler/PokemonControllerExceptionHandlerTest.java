package com.app.pokemon.infrastructure.api.handler;

import com.app.pokemon.application.service.TopHighestPokemonService;
import com.app.pokemon.domain.exceptions.PokemonNotFound;
import com.app.pokemon.domain.exceptions.PokemonServerError;
import com.app.pokemon.infrastructure.api.GetTopHighestPokemonController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PokemonControllerExceptionHandlerTest {

    private static final Integer DEFAULT_TOP = 5;

    @Mock
    private TopHighestPokemonService service;

    @InjectMocks
    private GetTopHighestPokemonController controller;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setControllerAdvice(new PokemonControllerExceptionHandler())
                .build();
    }

    @Test
    void shouldReturnInternalServerErrorWhenPokemonServerErrorIsThrown() throws Exception {
        when(service.getTopHighestPokemon(DEFAULT_TOP)).thenThrow(new PokemonServerError());

        mockMvc.perform(get("/api/v1/pokemon/highest")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value("There has been a problem with the poke-api request. Please, try again later"))
                .andExpect(jsonPath("$.pokeStatus").value(HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @Test
    void shouldReturnNotFoundWhenPokemonNotFoundIsThrown() throws Exception {
        Integer pokemonId = 51;

        when(service.getTopHighestPokemon(DEFAULT_TOP)).thenThrow(new PokemonNotFound(pokemonId));

        mockMvc.perform(get("/api/v1/pokemon/highest")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Pokemon with id 51 not found"))
                .andExpect(jsonPath("$.pokeStatus").value(HttpStatus.NOT_FOUND.value()));
    }
}