package org.clay.examples.retry_strategy;

import net.jodah.failsafe.Failsafe;
import net.jodah.failsafe.RetryPolicy;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static java.lang.System.out;
import static java.time.Duration.*;

public class RetryExample {
    public static void main(String[] args) {
        // Retry with fixed delay strategy
        RetryPolicy<Object> fixedDelayRetryPolicy = new RetryPolicy<>()
                .handle(RuntimeException.class)
                .withDelay(Duration.ofSeconds(4))
                .withMaxRetries(3)
                .onRetry(e -> out.println("Retrying..."));

        // Retry with exponential backoff strategy
        RetryPolicy<Object> exponentialBackoffRetryPolicy = new RetryPolicy<>()
                .withMaxRetries(5)
                .withBackoff(200L, 2000, ChronoUnit.MILLIS)
                .onRetry(e -> out.println("Retrying..."));


        // Custom retry strategy
        RetryPolicy<Object> customRetryPolicy = new RetryPolicy<>()
                .withMaxRetries(4)
                .withDelay(ofSeconds(3))
                .onRetry(e -> out.println("Retrying..."));

        // Perform operations with different retry strategies
        performOperation("Operation 1", fixedDelayRetryPolicy);
        performOperation("Operation 2", exponentialBackoffRetryPolicy);
        performOperation("Operation 3", customRetryPolicy);
    }

    private static void performOperation(String operationName, RetryPolicy<Object> retryPolicy) {
        out.println("Performing " + operationName);

        Failsafe.with(retryPolicy)
                .onFailure(e -> out.println("Operation " + operationName + " failed: " + e.getFailure()))
                .run(() -> {
                    // Simulate a failing operation
                    out.println("Executing " + operationName);
                    throw new RuntimeException("Operation " + operationName + " failed");
                });
    }
}


