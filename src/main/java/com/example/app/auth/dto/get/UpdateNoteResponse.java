package com.example.app.auth.dto.get;
import com.example.app.notes.Note;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdateNoteResponse {
    private Error error;
    private Note updatedNote;

    public enum Error {
        OK,
        INSUFFICIENT_PRIVILEGES,
        INVALID_NOTE_ID,
        INVALID_TITLE_LENGTH,
        INVALID_CONTENT_LENGTH
    }

    // Конструктор
    public UpdateNoteResponse(Error error, Note updatedNote) {
        this.error = error;
        this.updatedNote = updatedNote;
    }

    // Геттер для error
    public Error getError() {
        return error;
    }

    // Сеттер для error
    public void setError(Error error) {
        this.error = error;
    }

    // Геттер для updatedNote
    public Note getUpdatedNote() {
        return updatedNote;
    }

    // Сеттер для updatedNote
    public void setUpdatedNote(Note updatedNote) {
        this.updatedNote = updatedNote;
    }

    // Метод для створення успішного об'єкта UpdateNoteResponse
    public static UpdateNoteResponse success(Note updatedNote) {
        return new UpdateNoteResponse(Error.OK, updatedNote);
    }

    // Метод для створення невдалого об'єкта UpdateNoteResponse
    public static UpdateNoteResponse failed(Error error) {
        return new UpdateNoteResponse(error, null);
    }

    @Override
    public String toString() {
        return "UpdateNoteResponse{" +
                "error=" + error +
                ", updatedNote=" + updatedNote +
                '}';
    }
}
