package org.clay.examples.failure_handling;

import java.io.IOException;

public class RemoteService {
    public User getUser(String userId) throws IOException {
        // Simulate a remote service call
        if (Math.random() < 0.5) {
            throw new IOException("Failed to connect to the remote service");
        }

        // Simulate a timeout
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Simulate a successful response
        return new User(userId, "John Doe");
    }
}

class User {
    private String id;
    private String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        System.out.println("User object created");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
