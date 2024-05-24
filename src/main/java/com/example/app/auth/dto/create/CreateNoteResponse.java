package com.example.app.auth.dto.create;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateNoteResponse {
    private Error error;
    private long createdNoteId;

    public enum Error {
        ok,
        invalidTitle,
        invalidContent
    }

    public CreateNoteResponse(Error error, long createdNoteId) {
        this.error = error;
        this.createdNoteId = createdNoteId;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public long getCreatedNoteId() {
        return createdNoteId;
    }

    public void setCreatedNoteId(long createdNoteId) {
        this.createdNoteId = createdNoteId;
    }

    public static CreateNoteResponse success(long createdNoteId) {
        return new CreateNoteResponse(Error.ok, createdNoteId);
    }

    public static CreateNoteResponse failed(Error error) {
        return new CreateNoteResponse(error, -1L);
    }

    @Override
    public String toString() {
        return "CreateNoteResponse{" +
                "error=" + error +
                ", createdNoteId=" + createdNoteId +
                '}';
    }
}
