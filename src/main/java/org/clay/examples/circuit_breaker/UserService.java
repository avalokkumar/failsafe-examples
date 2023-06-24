package org.clay.examples.circuit_breaker;

import net.jodah.failsafe.CircuitBreaker;
import net.jodah.failsafe.Failsafe;

import java.time.Duration;

public class UserService {
    private UserApiClient userApiClient;
    private CircuitBreaker<Object> circuitBreaker;

    public UserService() {
        this.userApiClient = new UserApiClient();

        // Create a circuit breaker with custom configuration
        this.circuitBreaker = new CircuitBreaker<>()
                .withFailureThreshold(3)
                .withSuccessThreshold(2)
                .withDelay(Duration.ofSeconds(5))
                .onOpen(() -> System.out.println("Circuit breaker opened"))
                .onHalfOpen(() -> System.out.println("Circuit breaker half-opened"))
                .onClose(() -> System.out.println("Circuit breaker closed"));
    }

    public User getUserById(String userId) {
        return Failsafe.with(circuitBreaker)
                .onFailure(e -> System.out.println("Failed to retrieve user: " + e.getFailure()))
                .get(() -> userApiClient.getUserById(userId));
    }
}
