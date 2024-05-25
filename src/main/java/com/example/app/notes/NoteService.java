package com.example.app.notes;

import com.example.app.auth.dto.create.CreateNoteRequest;
import com.example.app.auth.dto.create.CreateNoteResponse;
import com.example.app.auth.dto.delete.DeleteNoteResponse;
import com.example.app.auth.dto.get.GetUserNotesResponse;
import com.example.app.auth.dto.get.UpdateNoteResponse;
import com.example.app.auth.dto.update.UpdateNoteRequest;
import com.example.app.users.User;
import com.example.app.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {
    private static final int MAX_TITLE_LENGTH = 100;
    private static final int MAX_CONTENT_LENGTH = 1000;

    private UserService userService;
    private NoteRepository repository;


    public CreateNoteResponse create(String username, CreateNoteRequest request) {
        Optional<CreateNoteResponse.Error> validationError = validateCreateFields(request);

        if (validationError.isPresent()) {
            return CreateNoteResponse.failed(validationError.get());
        }

        User user = userService.findByUsername(username);

        Note createdNote = repository.save(new Note(
                user,
                request.getTitle(),
                request.getContent()
        ));

        return CreateNoteResponse.success(createdNote.getId());
    }

    public GetUserNotesResponse getUserNotes(String username) {
        List<Note> userNotes = repository.getUserNotes(username);

        return GetUserNotesResponse.success(userNotes);
    }

    public UpdateNoteResponse update(String username, UpdateNoteRequest request) {
        Optional<Note> optionalNote = repository.findById(request.getId());

        if (optionalNote.isEmpty()) {
            return UpdateNoteResponse.failed(UpdateNoteResponse.Error.INVALID_NOTE_ID);
        }

        Note note = optionalNote.get();

        boolean isNotUserNote = isNotUserNote(username, note);

        if (isNotUserNote) {
            return UpdateNoteResponse.failed(UpdateNoteResponse.Error.INSUFFICIENT_PRIVILEGES);
        }

        Optional<UpdateNoteResponse.Error> validationError = validateUpdateFields(request);

        if (validationError.isPresent()) {
            return UpdateNoteResponse.failed(validationError.get());
        }

        note.setTitle(request.getTitle());
        note.setContent(request.getContent());

        repository.save(note);

        return UpdateNoteResponse.success(note);
    }

    public DeleteNoteResponse delete(String username, long id) {
        Optional<Note> optionalNote = repository.findById(id);

        if (optionalNote.isEmpty()) {
            return DeleteNoteResponse.failed(DeleteNoteResponse.Error.INVALID_NOTE_ID);
        }

        Note note = optionalNote.get();

        boolean isNotUserNote = isNotUserNote(username, note);

        if (isNotUserNote) {
            return DeleteNoteResponse.failed(DeleteNoteResponse.Error.INSUFFICIENT_PRIVILEGES);
        }

        repository.delete(note);

        return DeleteNoteResponse.success();
    }

    private Optional<CreateNoteResponse.Error> validateCreateFields(CreateNoteRequest request) {
        if (Objects.isNull(request.getTitle()) || request.getTitle().length() > MAX_TITLE_LENGTH) {
            return Optional.of(CreateNoteResponse.Error.INVALID_TITLE);
        }

        if (Objects.isNull(request.getContent()) || request.getContent().length() > MAX_CONTENT_LENGTH) {
            return Optional.of(CreateNoteResponse.Error.INVALID_TITLE);
        }

        return Optional.empty();
    }

    private Optional<UpdateNoteResponse.Error> validateUpdateFields(UpdateNoteRequest request) {
        if (Objects.nonNull(request.getTitle()) && request.getTitle().length() > MAX_TITLE_LENGTH) {
            return Optional.of(UpdateNoteResponse.Error.INVALID_TITLE_LENGTH);
        }

        if (Objects.nonNull(request.getContent()) && request.getContent().length() > MAX_CONTENT_LENGTH) {
            return Optional.of(UpdateNoteResponse.Error.INVALID_TITLE_LENGTH);
        }

        return Optional.empty();
    }

    private boolean isNotUserNote(String username, Note note) {
        return !note.getUser().getUserId().equals(username);
    }
}
