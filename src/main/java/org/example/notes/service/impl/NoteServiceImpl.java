package org.example.notes.service.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.example.notes.entity.Note;
import org.example.notes.service.NoteService;
import org.springframework.stereotype.Service;

@Service
public class NoteServiceImpl implements NoteService {
    private final Map<Long, Note> notes = new ConcurrentHashMap<>();
    private final AtomicLong id = new AtomicLong();

    @Override
    public List<Note> listAll() {
        return notes.values().stream().toList();
    }

    @Override
    public Note getById(Long id) {
        return notes.get(id);
    }

    @Override
    public Note add(Note note) {
        note.setId(id.incrementAndGet());
        return notes.put(note.getId(), note);
    }

    @Override
    public void deleteById(long id) {
        notes.remove(id);
    }

    @Override
    public void update(Note note) {
        notes.put(note.getId(), note);
    }
}
