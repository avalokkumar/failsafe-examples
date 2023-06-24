package org.clay.examples.circuit_breaker;

public class User {
    private String id;
    private String name;
    private String email;
    private String phone;

    public User() {
    }

    public User(String id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
