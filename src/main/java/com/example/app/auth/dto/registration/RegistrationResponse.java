package com.example.app.auth.dto.registration;


public class RegistrationResponse {
    private Error error;

    public enum Error {
        OK,
        userAlreadyExists,
        invalidEmail,
        invalidPassword,
        invalidName,
        invalidAge
    }

    // Конструктор
    public RegistrationResponse(Error error) {
        this.error = error;
    }

    // Геттер для error
    public Error getError() {
        return error;
    }

    // Сеттер для error
    public void setError(Error error) {
        this.error = error;
    }

    // Метод для створення успішного об'єкта RegistrationResponse
    public static RegistrationResponse success() {
        return new RegistrationResponse(Error.OK);
    }

    // Метод для створення невдалого об'єкта RegistrationResponse
    public static RegistrationResponse failed(Error error) {
        return new RegistrationResponse(error);
    }

    @Override
    public String toString() {
        return "RegistrationResponse{" +
                "error=" + error +
                '}';
    }
}