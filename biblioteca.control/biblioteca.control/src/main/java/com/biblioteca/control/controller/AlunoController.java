package com.biblioteca.control.controller;

import com.biblioteca.control.model.Aluno;
import com.biblioteca.control.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public List<Aluno> getAllAluno() {
        return alunoService.findAll();
    }

    @PostMapping
    public Aluno addUser(@RequestBody Aluno aluno) {
        return alunoService.save(aluno);
    }
}