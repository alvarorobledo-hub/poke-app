package com.app.pokemon.application.strategy.retry;

import com.app.pokemon.domain.exceptions.PokemonServerError;
import feign.RetryableException;
import feign.Retryer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
public class BackoffRetryer implements Retryer, Cloneable {

    private final long initialDelay;
    private final long maxDelay;
    private final int maxAttempts;

    public static List<BackoffRetryer> children;

    BackoffRetryer() {
        this.initialDelay = 2000;
        this.maxDelay = 32000;
        this.maxAttempts = 2;
    }

    BackoffRetryer(BackoffRetryer retryer) {
        this.initialDelay = retryer.initialDelay;
        this.maxDelay = retryer.maxDelay;
        this.maxAttempts = retryer.maxAttempts;
        this.attempt = retryer.attempt;

        children = retryer.children.stream().map(child -> (BackoffRetryer) child.clone()).toList();
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
            throw new PokemonServerError();
        }

        attempt++;
    }

    @Override
    public Retryer clone() {
        try {
            BackoffRetryer copy = (BackoffRetryer) super.clone();
            copy.children = children.stream().map(child -> (BackoffRetryer) child.clone()).toList();
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new PokemonServerError();
        }
    }
}
