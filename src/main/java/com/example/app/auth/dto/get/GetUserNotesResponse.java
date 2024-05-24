package com.example.app.auth.dto.get;
import com.example.app.notes.Note;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data

public class GetUserNotesResponse {
    private Error error;
    private List<Note> userNotes;

    public enum Error {
        ok
    }

    // Конструктор
    public GetUserNotesResponse(Error error, List<Note> userNotes) {
        this.error = error;
        this.userNotes = userNotes;
    }

    // Геттер для error
    public Error getError() {
        return error;
    }

    // Сеттер для error
    public void setError(Error error) {
        this.error = error;
    }

    // Геттер для userNotes
    public List<Note> getUserNotes() {
        return userNotes;
    }

    // Сеттер для userNotes
    public void setUserNotes(List<Note> userNotes) {
        this.userNotes = userNotes;
    }

    // Метод для створення успішного об'єкта GetUserNotesResponse
    public static GetUserNotesResponse success(List<Note> userNotes) {
        return new GetUserNotesResponse(Error.ok, userNotes);
    }

    // Метод для створення невдалого об'єкта GetUserNotesResponse
    public static GetUserNotesResponse failed(Error error) {
        return new GetUserNotesResponse(error, null);
    }

    @Override
    public String toString() {
        return "GetUserNotesResponse{" +
                "error=" + error +
                ", userNotes=" + userNotes +
                '}';
    }
}