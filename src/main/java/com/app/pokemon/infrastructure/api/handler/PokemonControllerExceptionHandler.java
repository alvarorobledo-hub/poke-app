package com.app.pokemon.infrastructure.api.handler;

import com.app.pokemon.domain.model.PokemonError;
import com.app.pokemon.domain.exceptions.PokemonNotFound;
import com.app.pokemon.domain.exceptions.PokemonServerError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PokemonControllerExceptionHandler {

    @ExceptionHandler({PokemonServerError.class, Exception.class})
    public ResponseEntity<PokemonError> handlePokemonServerError(Exception e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        PokemonError error = PokemonError.builder()
                .message(e.getMessage())
                .pokeStatus(status.value())
                .build();

        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(PokemonNotFound.class)
    public ResponseEntity<PokemonError> handlePokemonNotFound(PokemonNotFound e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        PokemonError error = PokemonError.builder()
                .message(e.getMessage())
                .pokeStatus(status.value())
                .build();

        return new ResponseEntity<>(error, status);
    }
}
