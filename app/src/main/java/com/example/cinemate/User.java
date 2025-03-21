package com.example.cinemate;


public abstract class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private String role; // ADMIN / CUSTOMER

    public User(String id, String name, String email, String password, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}
