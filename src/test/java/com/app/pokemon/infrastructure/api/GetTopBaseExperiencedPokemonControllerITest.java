package com.app.pokemon.infrastructure.api;

import com.app.pokemon.application.service.TopBaseExperiencedPokemonService;
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
import static com.app.pokemon.domain.mother.PokemonObjectMother.createTopBaseExperiencedPokemon;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class GetTopBaseExperiencedPokemonControllerITest {

    private static final Integer DEFAULT_TOP = 5;

    @Mock
    private TopBaseExperiencedPokemonService service;

    @InjectMocks
    private GetTopBaseExperiencedPokemonController controller;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void shouldGetTopBaseExperiencedPokemon() throws Exception {
        List<Pokemon> pokemonResponseList = createTopBaseExperiencedPokemon();
        String pokemonResponseJson = writeValue(pokemonResponseList);

        doReturn(pokemonResponseList).when(service).getTopBaseExperiencePokemon(DEFAULT_TOP);

        mockMvc.perform(get("/api/v1/pokemon/base-experience")
                        .param("top", DEFAULT_TOP.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(pokemonResponseJson));
    }

}