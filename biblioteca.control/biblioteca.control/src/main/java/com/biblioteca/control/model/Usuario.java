package com.biblioteca.control.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "email", unique = true, length = 200)
    private String email;

    @Column(name = "nome", length = 100)
    private String nome;

    @Column(name = "senha", length = 100)
    private String senha;
}