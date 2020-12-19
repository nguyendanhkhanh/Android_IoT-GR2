package com.khanh;

public class User {
    private String Email;
    private String Pass;

    public User() {
    }

    public User(String email, String pass) {
        Email = email;
        Pass = pass;
    }

    public String getPass() {
        return Pass;
    }

    public String getEmail() {
        return Email;
    }
}
