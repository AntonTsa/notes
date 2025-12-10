package org.example.notes.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.example.notes.entity.Note;
import org.example.notes.service.impl.NoteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

class NoteServiceTest {

    private NoteServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new NoteServiceImpl();
    }

    @Test
    void givenEmptyStore_whenListAll_thenReturnsEmptyList() {
        // GIVEN an empty service
        // WHEN listing all notes
        List<Note> notes = service.listAll();
        // THEN result is non-null and empty
        assertNotNull(notes);
        assertTrue(notes.isEmpty());
    }

    @Test
    void givenNewNote_whenAdd_thenReturnsNullAndIsStored() {
        // GIVEN a new Note with id=1
        Note note = createNoteInstance(1L);
        // WHEN adding the note
        Note previous = service.add(note);
        // THEN add returns null (no previous) and note is stored
        assertNull(previous);
        List<Note> notes = service.listAll();
        assertEquals(1, notes.size());
        assertSame(note, notes.getFirst());
    }

    @Test
    void givenExistingId_whenAddDuplicate_thenReturnsPreviousAndReplaces() {
        // GIVEN two Note instances with the same id=2
        Note first = createNoteInstance(2L);
        Note second = createNoteInstance(2L);
        // WHEN adding first then second
        Note prev1 = service.add(first);
        Note prev2 = service.add(second);
        // THEN first add returned null, second returned the previous instance and store contains second
        assertNull(prev1);
        assertSame(first, prev2);
        List<Note> notes = service.listAll();
        assertEquals(1, notes.size());
        assertSame(second, notes.getFirst());
    }

    @Test
    void givenStoredNote_whenDeleteById_thenIsRemoved() {
        // GIVEN a stored note with id=3
        Note note = createNoteInstance(3L);
        service.add(note);
        assertEquals(1, service.listAll().size());
        // WHEN deleting by id
        service.deleteById(3L);
        // THEN store is empty
        assertTrue(service.listAll().isEmpty());
    }

    @Test
    void givenEmptyStore_whenDeleteNonExistent_thenDoesNotThrow() {
        // GIVEN an empty service
        // WHEN deleting a non-existent id
        // THEN no exception and still empty
        assertDoesNotThrow(() -> service.deleteById(999L));
        assertTrue(service.listAll().isEmpty());
    }

    @Test
    void givenNonExisting_whenUpdate_thenAdds_thenReplaceOnSecondUpdate() {
        // GIVEN a note with id=4 not present
        Note n1 = createNoteInstance(4L);
        // WHEN updating (should add)
        service.update(n1);
        // THEN it is stored
        assertEquals(1, service.listAll().size());
        // GIVEN a different instance with same id
        Note n2 = createNoteInstance(4L);
        // WHEN updating again (should replace)
        service.update(n2);
        // THEN store contains new instance
        assertEquals(1, service.listAll().size());
        assertSame(n2, service.listAll().getFirst());
    }

    @Test
    void givenNullNote_whenAdd_thenThrowsNPE() {
        // GIVEN null note
        // WHEN adding
        // THEN expect NPE (service accesses note.getId())
        assertThrows(NullPointerException.class, () -> service.add(null));
    }

    @Test
    void givenNullNote_whenUpdate_thenThrowsNPE() {
        // GIVEN null note
        // WHEN updating
        // THEN expect NPE
        assertThrows(NullPointerException.class, () -> service.update(null));
    }

    @Test
    void givenNoteWithNullId_whenAdd_thenThrowsNPE() {
        // GIVEN a note instance whose id is null
        Note note = createNoteInstance(null);
        // WHEN adding
        // THEN ConcurrentHashMap disallows null keys -> NPE expected
        assertThrows(NullPointerException.class, () -> service.add(note));
    }

    // Helper: create Note by direct API (no reflection).
    // Assumes entity has a public no-arg constructor and a setId(Long) method.
    private Note createNoteInstance(Long id) {
        Note note = new Note();
        // setId may accept Long; if the entity uses primitive long this code must be adapted
        note.setId(id);
        return note;
    }
}

