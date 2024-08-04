package com.app.pokemon.application.strategy.retry;

import com.app.pokemon.domain.exceptions.PokemonServerError;
import feign.RetryableException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BackoffRetryerTest {

    private static final Integer DEFAULT_INITIAL_DELAY = 2000;
    private static final Integer DEFAULT_MAX_DELAY = 32000;
    private static final Integer DEFAULT_MAX_ATTEMPTS = 5;

    @Mock
    private RetryableException exception;

    @Test
    void shouldRetryerThrowExceptionWithInitialDelay() {
        BackoffRetryer retryer = new BackoffRetryer(DEFAULT_INITIAL_DELAY, DEFAULT_MAX_DELAY, 3);

        retryer.continueOrPropagate(exception); // attempt 1
        retryer.continueOrPropagate(exception); // attempt 2
        retryer.continueOrPropagate(exception); // attempt 3

        assertThrows(PokemonServerError.class, () -> retryer.continueOrPropagate(exception));
    }

    @Test
    void shouldRetryerThrowExceptionWithMaxDelay() {
        BackoffRetryer retryer = new BackoffRetryer(DEFAULT_INITIAL_DELAY, 5000, 3);
        RetryableException exception = mock(RetryableException.class);

        long startTime = System.currentTimeMillis();

        retryer.continueOrPropagate(exception); // attempt 1
        retryer.continueOrPropagate(exception); // attempt 2
        retryer.continueOrPropagate(exception); // attempt 3

        long elapsedTime = System.currentTimeMillis() - startTime;
        long minTotalDelay = 2000 + 4000 + 5000;

        assertTrue(elapsedTime >= minTotalDelay, "Elapsed time should be between 11000ms and 12000ms");
        assertThrows(PokemonServerError.class, () -> retryer.continueOrPropagate(exception));
    }

    @Test
    void shouldRetryerClone() {
        BackoffRetryer originalRetryer = new BackoffRetryer(DEFAULT_INITIAL_DELAY, DEFAULT_MAX_DELAY, DEFAULT_MAX_ATTEMPTS);
        BackoffRetryer clonedRetryer = (BackoffRetryer) originalRetryer.clone();

        assertNotNull(clonedRetryer);
        assertEquals(originalRetryer.getInitialDelay(), clonedRetryer.getInitialDelay());
        assertEquals(originalRetryer.getMaxDelay(), clonedRetryer.getMaxDelay());
        assertEquals(originalRetryer.getMaxAttempts(), clonedRetryer.getMaxAttempts());
    }

    @Test
    void shouldRetryerThrowPokemonServerErrorOnInterruptedException() throws InterruptedException {
        BackoffRetryer retryer = spy(new BackoffRetryer(DEFAULT_INITIAL_DELAY, DEFAULT_MAX_DELAY, DEFAULT_MAX_ATTEMPTS));

        doThrow(new InterruptedException()).when(retryer).sleep(anyLong());

        assertThrows(PokemonServerError.class, () -> retryer.continueOrPropagate(exception));
    }

}