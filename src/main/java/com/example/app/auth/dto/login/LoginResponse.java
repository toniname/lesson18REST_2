package com.example.app.auth.dto.login;

public class LoginResponse {
    private Error error;
    private String authToken;

    public enum Error {
        ok,
        invalidEmail,
        invalidPassword
    }

    // Конструктор
    public LoginResponse(Error error, String authToken) {
        this.error = error;
        this.authToken = authToken;
    }

    // Геттер для error
    public Error getError() {
        return error;
    }

    // Сеттер для error
    public void setError(Error error) {
        this.error = error;
    }

    // Геттер для authToken
    public String getAuthToken() {
        return authToken;
    }

    // Сеттер для authToken
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    // Метод для створення успішного об'єкта LoginResponse
    public static LoginResponse success(String authToken) {
        return new LoginResponse(Error.ok, authToken);
    }

    // Метод для створення невдалого об'єкта LoginResponse
    public static LoginResponse failed(Error error) {
        return new LoginResponse(error, null);
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "error=" + error +
                ", authToken='" + authToken + '\'' +
                '}';
    }
}