package com.example.JavaDevModule15Boot273.controller;

import com.example.JavaDevModule15Boot273.entity.Note;
import com.example.JavaDevModule15Boot273.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {
    // GET /note/list - отримати список нотаток. Виводиться список нотаток (title та content), кожну нотатку можна видалити або редагувати
    // POST /note/delete - видалити нотатку по ID. Після видалення нотатки відбувається редирект на /note/list
    // GET /note/edit?id=xxx - сторінка редагування нотатку (відкривається по натисненню на кнопку Редагувати на списку нотаток).
    // POST /note/edit - сюди відправляється запит на редагування нотатки. Після збереження оновленого контенту нотатки відбувається редирект на /note/list

    private final NoteService noteService;

    @GetMapping("/list")
    public ModelAndView list() {
        List<Note> notesList = noteService.findAll();
        ModelAndView result = new ModelAndView("/note/list");
        result.addObject("notesList", notesList);
        return result;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        return new ModelAndView("note/note");
    }

    @PostMapping("/edit")
    public RedirectView edit(@RequestParam(value = "id") long id,
                             @RequestParam(value = "title") String title,
                             @RequestParam(value = "content") String content) {
        if (id == -1) {
            Note note = new Note();
            note.setTitle(title);
            note.setContent(content);
            noteService.save(note);
        } else {
            Note note = noteService.getById(id);
            note.setTitle(title);
            note.setContent(content);
            noteService.save(note);
        }
        return new RedirectView("/note/list");
    }

    @GetMapping("/edit")
    public ModelAndView edit(@RequestParam(value = "id") long id) {
        Note note = noteService.getById(id);
        ModelAndView result = new ModelAndView("/note/note");
        result.addObject("note", note);
        return result;
    }

    @PostMapping("/delete")
    public RedirectView delete(@RequestParam(value = "id") long id) {
        noteService.deleteById(id);
        return new RedirectView("/note/list");
    }
}
