package org.example.notes.service;

import java.util.List;
import org.example.notes.entity.Note;

public interface NoteService {

    List<Note> listAll();

    Note getById(Long id);

    Note add(Note note);

    void deleteById(long id);

    void update(Note note);
}
