package com.app.pokemon.infrastructure.api;

import com.app.pokemon.application.service.TopHeaviestPokemonService;
import com.app.pokemon.domain.model.Pokemon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.app.pokemon.domain.helper.ObjectMapperHelper.writeValue;
import static com.app.pokemon.domain.mother.PokemonObjectMother.createTopHeaviestPokemon;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class GetTopHeaviestPokemonControllerTest {

    private static final Integer DEFAULT_TOP = 5;

    @Mock
    private TopHeaviestPokemonService service;

    @InjectMocks
    private GetTopHeaviestPokemonController controller;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void shouldGetTopHeaviestPokemon() throws Exception {
        List<Pokemon> pokemonResponseList = createTopHeaviestPokemon();
        String pokemonResponseJson = writeValue(pokemonResponseList);

        doReturn(pokemonResponseList).when(service).getTopHeaviestPokemon(DEFAULT_TOP);

        mockMvc.perform(get("/api/v1/pokemon/heaviest")
                        .param("top", DEFAULT_TOP.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(pokemonResponseJson));
    }

}