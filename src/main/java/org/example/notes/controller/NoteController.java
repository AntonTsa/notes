package org.example.notes.controller;

import lombok.RequiredArgsConstructor;
import org.example.notes.entity.Note;
import org.example.notes.service.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping
    public ModelAndView listNotes(Authentication authentication) {
        ModelAndView mav = new ModelAndView("list");
        mav.addObject("notes", noteService.listAll());
        mav.addObject(
                "username",
                authentication != null ? authentication.getName() : "Guest"
        );
        return mav;
    }

    @GetMapping("/new")
    public ModelAndView createNewNote() {
        Note note = new Note();
        ModelAndView modelAndView = new ModelAndView("new");
        modelAndView.addObject("note", note);
        return modelAndView;
    }

    @PostMapping("/new")
    public String addNote(@ModelAttribute Note note) {
        noteService.add(note);
        return "redirect:/api/notes";
    }

    @GetMapping("/{id}/edit")
    public ModelAndView viewNote(@PathVariable Long id) {
        Note note = noteService.getById(id);
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("note", note);
        return modelAndView;
    }

    @PostMapping("/edit")
    public String updateNote(@ModelAttribute Note note) {
        noteService.update(note);
        return "redirect:/api/notes";
    }

    @PostMapping("/{id}/delete")
    public String deleteNote(@PathVariable Long id) {
        noteService.deleteById(id);
        return "redirect:/api/notes";
    }

}
