package com.biblioteca.control.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Livros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", length = 100)
    private String titulo;

    @Column(name = "autor", length = 100)
    private String autor;

    @Column(name = "emprestado")
    private boolean emprestado;

    @ManyToMany
    @JoinTable(
            name = "livros_alunos",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id")
    )
    private List<Aluno> emprestadoBy = new ArrayList<>();
}
