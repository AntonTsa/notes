package org.example.notes.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.notes.entity.Note;
import org.example.notes.repository.NoteRepository;
import org.example.notes.service.NoteService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;

    @Override
    public List<Note> listAll() {
        return noteRepository.findAll();
    }

    @Override
    public Note getById(Long id) {
        return noteRepository.findById(id).orElse(null);
    }

    @Override
    public Note add(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public void deleteById(long id) {
        noteRepository.deleteById(id);
    }

    @Override
    public void update(Note note) {
        noteRepository.save(note);
    }
}
