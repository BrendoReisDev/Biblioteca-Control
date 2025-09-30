package com.biblioteca.control.service;


import com.biblioteca.control.model.Aluno;
import com.biblioteca.control.model.Livros;
import com.biblioteca.control.repository.AlunoRepository;
import com.biblioteca.control.repository.LivrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivrosService {

    @Autowired
    private LivrosRepository livrosRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Livros> findAll() {
        return livrosRepository.findAll();
    }

    public Livros findById(Long id) {
        return livrosRepository.findById(id).orElse(null);
    }

    public Livros save(Livros livros) {
        return livrosRepository.save(livros);
    }

    public void deleteById(Long id) {
        livrosRepository.deleteById(id);
    }

    public Livros emprestarLivro(Long livroId, Long alunoId) {
        Livros livros = findById(livroId);
        Aluno aluno = alunoRepository.findById(alunoId).orElse(null);

        if (livros != null && !livros.isEmprestado() && aluno != null) {
            livros.getEmprestadoBy().add(aluno);
            livros.setEmprestado(true);
            return save(livros);
        }

        return null;
    }

    public Livros returnLivros(Long livrosId) {
        Livros livros = findById(livrosId);
        if (livros != null && livros.isEmprestado()) {
            livros.setEmprestadoBy(null);
            livros.setEmprestado(false);
            return save(livros);
        }

        return null;
    }
}