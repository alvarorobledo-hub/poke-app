package com.app.pokemon.application.strategy.decoder;

import com.app.pokemon.domain.exceptions.PokemonNotFound;
import com.app.pokemon.domain.exceptions.PokemonServerError;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import static com.app.pokemon.application.utils.PokeApiUtils.extractIdFromUrl;

@Slf4j
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String method, Response response) {
        log.error("Error occurred during method: {}, status: {}, reason: {}", method, response.status(), response.reason());

        if (response.status() == 404) {
            Integer pokemonId = extractIdFromUrl(response.request().url());
            return new PokemonNotFound(pokemonId);
        }

        return new PokemonServerError();
    }
}
