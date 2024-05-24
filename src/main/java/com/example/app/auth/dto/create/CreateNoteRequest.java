package com.example.app.auth.dto.create;

import lombok.Data;

@Data
public class CreateNoteRequest {
    private final String title;
    private final String content;

    public CreateNoteRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
