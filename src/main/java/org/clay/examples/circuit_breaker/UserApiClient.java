package org.clay.examples.circuit_breaker;

public class UserApiClient {
    public User getUserById(String userId) {
        // Make API request to retrieve user information

        // Simulate a failure for demonstration purposes
        throw new RuntimeException("Failed to retrieve user information");
    }
}
