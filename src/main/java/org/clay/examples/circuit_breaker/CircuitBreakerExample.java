package org.clay.examples.circuit_breaker;

import net.jodah.failsafe.CircuitBreaker;
import net.jodah.failsafe.Failsafe;
import net.jodah.failsafe.RetryPolicy;

import java.time.Duration;

public class CircuitBreakerExample {
    public static void main(String[] args) {
        // Create a circuit breaker with a failure threshold and delay duration
        CircuitBreaker<Object> circuitBreaker = new CircuitBreaker<>()
                .withFailureThreshold(3, 5)
                .withDelay(Duration.ofSeconds(10))
                .onOpen(() -> System.out.println("Circuit breaker opened"))
                .onHalfOpen(() -> System.out.println("Circuit breaker half-opened"))
                .onClose(() -> System.out.println("Circuit breaker closed"));

        // Wrap an operation with the circuit breaker
        Failsafe.with(circuitBreaker)
                .onFailure(e -> System.out.println("Operation failed: " + e.getFailure()))
                .run(() -> {
                    // Simulate a failing operation
                    System.out.println("Executing operation...");
                    throw new RuntimeException("Operation failed");
                });
    }
}
