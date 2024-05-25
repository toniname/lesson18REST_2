package com.example.app.auth.dto.create;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateNoteResponse {
    private Error error;
    private long createdNoteId;

    public enum Error {
        OK,
        INVALID_TITLE,
        INVALID_CONTENT
    }

    public CreateNoteResponse(Error error, long createdNoteId) {
        this.error = error;
        this.createdNoteId = createdNoteId;
    }


    public static CreateNoteResponse success(long createdNoteId) {
        return new CreateNoteResponse(Error.OK, createdNoteId);
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
