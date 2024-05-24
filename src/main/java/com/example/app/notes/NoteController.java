package com.example.app.notes;

import com.example.app.auth.dto.create.CreateNoteRequest;
import com.example.app.auth.dto.create.CreateNoteResponse;
import com.example.app.auth.dto.delete.DeleteNoteResponse;
import com.example.app.auth.dto.get.GetUserNotesResponse;
import com.example.app.auth.dto.get.UpdateNoteResponse;
import com.example.app.auth.dto.update.UpdateNoteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notes")
public class NoteController {
    private NoteService noteService;

    @PostMapping
    public CreateNoteResponse create(Principal principal, @RequestBody CreateNoteRequest request) {
        return noteService.create(principal.getName(), request);
    }

    @GetMapping
    public GetUserNotesResponse getUserNotes(Principal principal) {
        return noteService.getUserNotes(principal.getName());
    }

    @PatchMapping
    public UpdateNoteResponse update(Principal principal, @RequestBody UpdateNoteRequest request) {
        return noteService.update(principal.getName(), request);
    }

    @DeleteMapping
    public DeleteNoteResponse delete(Principal principal, @RequestParam(name = "id") long id) {
        return noteService.delete(principal.getName(), id);
    }
}