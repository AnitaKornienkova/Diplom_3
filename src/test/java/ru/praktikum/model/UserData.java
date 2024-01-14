package ru.praktikum.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserData {
    public static final String EMAIL = "anitakornienkova@praktikum.ru";
    public static final String PASSWORD = "somepassword";
    public static final String NAME = "anitakornienkova";
    public static final UserData TEST_USER = new UserData(EMAIL, PASSWORD, NAME);

    private final String email;
    private final String password;
    private final String name;

    public UserData(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public UserCredentials toCredentials() {
        return new UserCredentials(email, password);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}