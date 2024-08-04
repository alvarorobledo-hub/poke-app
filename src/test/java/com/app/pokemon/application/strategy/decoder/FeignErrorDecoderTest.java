package com.app.pokemon.application.strategy.decoder;

import com.app.pokemon.domain.exceptions.PokemonNotFound;
import com.app.pokemon.domain.exceptions.PokemonServerError;
import feign.Request;
import feign.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FeignErrorDecoderTest {

    private static final String POKE_API_POKEMON_URL = "https://pokeapi.co/api/v2/pokemon/";

    @Mock
    private Response response;

    @Mock
    private Request request;

    @InjectMocks
    private FeignErrorDecoder decoder;

    @Test
    void shouldReturnPokemonNotFoundOn404StatusCode() {
        String url = POKE_API_POKEMON_URL + 30;
        String expectedMessage = "Pokemon with id 30 not found";

        doReturn(HttpStatus.NOT_FOUND.value()).when(response).status();
        doReturn(request).when(response).request();
        doReturn(url).when(request).url();

        Exception exception = decoder.decode("testMethod", response);

        assertNotNull(exception);
        assertEquals(expectedMessage, exception.getMessage());
        assertEquals(PokemonNotFound.class, exception.getClass());

        verify(response).request();
        verify(request).url();
    }

    @Test
    void shouldReturnPokemonServerErrorOnOtherStatusCode() {
        String expectedMessage = "There has been a problem with the poke-api request. Please, try again later";

        doReturn(HttpStatus.BAD_GATEWAY.value()).when(response).status();

        Exception exception = decoder.decode("testMethod", response);

        assertNotNull(exception);
        assertEquals(expectedMessage, exception.getMessage());
        assertEquals(PokemonServerError.class, exception.getClass());

        verify(response, never()).request();
        verify(request, never()).url();
    }

}