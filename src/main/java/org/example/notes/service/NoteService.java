package org.example.notes.service;

import org.example.notes.entity.Note;

import java.util.List;

public interface NoteService {

    List<Note> listAll();

    Note add(Note note);

    void deleteById(long id);

    void update(Note note);
}
