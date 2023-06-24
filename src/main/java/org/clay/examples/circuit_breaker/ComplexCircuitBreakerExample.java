package org.clay.examples.circuit_breaker;

import net.jodah.failsafe.CircuitBreaker;
import net.jodah.failsafe.Failsafe;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * In this example, we create a custom CircuitBreaker instance with the following configuration:
 *
 * withFailureThreshold(3): The circuit breaker will open after 3 consecutive failures.
 * withSuccessThreshold(2): The circuit breaker will close after 2 consecutive successes.
 * withDelay(Duration.ofSeconds(5)): After the circuit breaker opens, there will be a delay of 5 seconds before the first attempt to execute the operation.
 * We also define callbacks for the different states of the circuit breaker:
 *
 * onOpen(() -> System.out.println("Circuit breaker opened")): This callback is executed when the circuit breaker opens.
 * onHalfOpen(() -> System.out.println("Circuit breaker half-opened")): This callback is executed when the circuit breaker transitions from the open state to the half-open state.
 * onClose(() -> System.out.println("Circuit breaker closed")): This callback is executed when the circuit breaker closes.
 *
 * Inside the performOperation method, we execute the operation using the circuit breaker. After the operation fails and the circuit breaker opens, we sleep for 10 seconds to observe the behavior. Then, we attempt to execute the operation again after the circuit breaker has opened.
 * In this case, the operation succeeds, and the circuit breaker closes.
 *
 */
public class ComplexCircuitBreakerExample {

    public static void main(String[] args) {
        CircuitBreaker<Object> circuitBreaker = new CircuitBreaker<>()
                .withFailureThreshold(3)
                .withSuccessThreshold(2)
                .withDelay(Duration.ofSeconds(5))
                .onOpen(() -> System.out.println("Circuit breaker opened"))
                .onHalfOpen(() -> System.out.println("Circuit breaker half-opened"))
                .onClose(() -> System.out.println("Circuit breaker closed"));

        performOperation(circuitBreaker);

    }

    private static void performOperation(CircuitBreaker<Object> circuitBreaker) {
        System.out.println("Performing operation...");

        Failsafe.with(circuitBreaker)
                .onFailure(e -> System.out.println("Operation failed: " + e.getFailure()))
                .onComplete(e -> System.out.println("Operation completed"))
                .run(() -> {
                    // Simulate a failing operation
                    System.out.println("Executing operation...");
                    throw new RuntimeException("Operation failed");
                });

        // Sleep for some time to observe the behavior of the circuit breaker
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Perform the operation again after the circuit breaker has opened
        System.out.println("Performing operation after circuit breaker opened...");

        Failsafe.with(circuitBreaker)
                .onFailure(e -> System.out.println("Operation failed: " + e.getFailure()))
                .run(() -> {
                    // Simulate a successful operation
                    System.out.println("Executing operation...");
                    System.out.println("Operation succeeded");
                });
    }
}
