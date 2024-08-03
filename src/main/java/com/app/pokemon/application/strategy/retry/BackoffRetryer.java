package com.app.pokemon.application.strategy.retry;

import com.app.pokemon.domain.exceptions.PokemonServerError;
import feign.RetryableException;
import feign.Retryer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
public class BackoffRetryer implements Retryer {

    private final long initialDelay;
    private final long maxDelay;
    private final int maxAttempts;

    public BackoffRetryer() {
        this.initialDelay = 2000;
        this.maxDelay = 32000;
        this.maxAttempts = 2;
    }

    private int attempt = 1;

    @Override
    public void continueOrPropagate(RetryableException e) {
        if (attempt > maxAttempts) {
            throw new PokemonServerError();
        }

        long delay = Math.min(initialDelay * (1L << (attempt - 1)), maxDelay);

        try {
            log.info("Retry attempt {}. Retrying in {} seconds...", attempt, delay / 1000);
            TimeUnit.MILLISECONDS.sleep(delay);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted while waiting to retry", ie);
        }

        attempt++;
    }

    @Override
    public Retryer clone() {
        return new BackoffRetryer(initialDelay, maxDelay, maxAttempts);
    }
}
