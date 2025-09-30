package com.biblioteca.control.controller;

import com.biblioteca.control.model.Aluno;
import com.biblioteca.control.model.Livros;
import com.biblioteca.control.service.AlunoService;
import com.biblioteca.control.service.LivrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private LivrosService livrosService;

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("livros", livrosService.findAll());
        model.addAttribute("alunos", alunoService.findAll());
        model.addAttribute("novoLivro", new Livros());
        return "home";
    }

    @PostMapping("/add")
    public String addLivro(
            @ModelAttribute Livros novoLivro,
            @RequestParam(required = false) String nomeAluno,
            @RequestParam(required = false) String emailAluno,
            @RequestParam(required = false, defaultValue = "false") boolean emprestado
    ) {

        novoLivro.setEmprestado(emprestado);


        if (nomeAluno != null && !nomeAluno.isBlank() && emailAluno != null && !emailAluno.isBlank()) {
            Aluno aluno = new Aluno();
            aluno.setNome(nomeAluno);
            aluno.setEmail(emailAluno);
            alunoService.save(aluno);

            if (emprestado) {
                novoLivro.getEmprestadoBy().add(aluno);
            }
        }

        livrosService.save(novoLivro);
        return "redirect:/home";
    }

    @PostMapping("/emprestar/{id}")
    public String emprestar(@PathVariable Long id, @RequestParam Long alunoId) {
        livrosService.emprestarLivro(id, alunoId);
        return "redirect:/home";
    }

    @PostMapping("/devolver/{id}")
    public String devolver(@PathVariable Long id) {
        livrosService.returnLivros(id);
        return "redirect:/home";
    }

    @PostMapping("/remover/{id}")
    public String remover(@PathVariable Long id) {
        livrosService.deleteById(id);
        return "redirect:/home";
    }
}
