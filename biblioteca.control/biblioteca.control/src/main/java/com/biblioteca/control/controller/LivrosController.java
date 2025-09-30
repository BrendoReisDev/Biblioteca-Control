package com.biblioteca.control.controller;


import com.biblioteca.control.model.Livros;
import com.biblioteca.control.service.LivrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class LivrosController {

    @Autowired
    private LivrosService livrosService;

    @GetMapping
    public List<Livros> getAllLivros() {
        return livrosService.findAll();
    }

    @GetMapping("/{id}")
    public Livros getLivros(@PathVariable Long id) {
        return livrosService.findById(id);
    }

    @PostMapping
    public Livros addLivros(@RequestBody Livros livros) {
        return livrosService.save(livros);
    }

    @PutMapping("/{id}")
    public Livros updateLivros(@PathVariable Long id, @RequestBody Livros livros) {
        return livrosService.save(livros);
    }

    @DeleteMapping("/{id}")
    public void deleteLivros(@PathVariable Long id) {
        livrosService.deleteById(id);
    }

    @PostMapping("/{bookId}/borrow/{userId}")
    public ResponseEntity<Livros> borrowBook(@PathVariable Long livroId, @PathVariable Long alunoId) {
        Livros emprestarLivro = livrosService.emprestarLivro(livroId, alunoId);
        if (emprestarLivro != null) {
            return ResponseEntity.ok(emprestarLivro);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{bookId}/return")
    public ResponseEntity<Livros> returnBook(@PathVariable Long bookId) {
        Livros returnedBook = livrosService.returnLivros(bookId);
        if (returnedBook != null) {
            return ResponseEntity.ok(returnedBook);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}