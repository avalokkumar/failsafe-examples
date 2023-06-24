package org.clay.examples.circuit_breaker;

import net.jodah.failsafe.CircuitBreaker;
import net.jodah.failsafe.Failsafe;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class CircuitBreakerUserExample {
    public static void main(String[] args) {
        CircuitBreaker<Object> circuitBreaker = new CircuitBreaker<>()
                .withFailureThreshold(3)
                .withDelay(Duration.of(5, TimeUnit.SECONDS.toChronoUnit()))
                .onOpen(() -> System.out.println("Circuit is open"))
                .onClose(() -> System.out.println("Circuit is closed"))
                .onHalfOpen(() -> System.out.println("Circuit is half-open"));

        // Test case 1: Successful execution
        testCircuitBreaker(circuitBreaker, 2, true);

        // Test case 2: Failure with fallback
        testCircuitBreaker(circuitBreaker, 4, false);

        // Test case 3: Failure threshold reached
        testCircuitBreaker(circuitBreaker, 5, false);

        // Test case 4: Circuit is open
        testCircuitBreaker(circuitBreaker, 2, false);
    }

    private static void testCircuitBreaker(CircuitBreaker<Object> circuitBreaker, int executionCount, boolean shouldSucceed) {
        System.out.println("Executing operation...");
        try {
            Failsafe.with(circuitBreaker)
//                    .onFailure((result, failure, context) -> System.out.println("Operation failed: " + failure))
                    .onFailure(throwable -> System.out.println("Operation failed: " + throwable.toString()))
                    .run(() -> {
                        if (shouldSucceed) {
                            System.out.println("Operation succeeded");
                        } else {
                            throw new RuntimeException("Operation failed");
                        }
                    });
        } catch (Exception e) {
            System.out.println("Exception in thread: " + e.getMessage());
        }
        System.out.println();
    }
}
