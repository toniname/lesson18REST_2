package com.example.app.auth.dto.delete;

public class DeleteNoteResponse {
    private Error error;

    public enum Error {
        OK,
        INSUFFICIENT_PRIVILEGES,
        INVALID_NOTE_ID
    }

    // Конструктор
    public DeleteNoteResponse(Error error) {
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

    // Метод для створення успішного об'єкта DeleteNoteResponse
    public static DeleteNoteResponse success() {
        return new DeleteNoteResponse(Error.OK);
    }

    // Метод для створення невдалого об'єкта DeleteNoteResponse
    public static DeleteNoteResponse failed(Error error) {
        return new DeleteNoteResponse(error);
    }

    @Override
    public String toString() {
        return "DeleteNoteResponse{" +
                "error=" + error +
                '}';
    }
}