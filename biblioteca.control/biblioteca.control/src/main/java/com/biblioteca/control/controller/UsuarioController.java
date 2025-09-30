package com.biblioteca.control.controller;


import com.biblioteca.control.model.Usuario;
import com.biblioteca.control.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;


    @PostMapping
    public String salvarUsuario(Usuario usuario) {
        usuarioService.salvarUsuario(usuario);
        return "redirect:/index.html";
    }


    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String senha,
                        RedirectAttributes redirectAttributes) {
        Usuario usuario = usuarioService.buscarUsuarioPorEmail(email);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            return "redirect:/home";
        }

        redirectAttributes.addAttribute("error", "true");
        return "redirect:/index.html";
    }


    @PostMapping("/api")
    @ResponseBody
    public void salvarUsuarioJson(@RequestBody Usuario usuario) {
        usuarioService.salvarUsuario(usuario);
    }


    @GetMapping
    @ResponseBody
    public Usuario buscarUsuarioPorEmail(@RequestParam String email) {
        return usuarioService.buscarUsuarioPorEmail(email);
    }

    @DeleteMapping
    @ResponseBody
    public void deletarUsuarioPorEmail(@RequestParam String email) {
        usuarioService.deletarUuarioPorEmail(email);
    }


    @PutMapping
    @ResponseBody
    public void atualizarUsuarioPorId(@RequestParam long id,
                                      @RequestBody Usuario usuario) {
        usuarioService.atualizarUsuarioPorId(id, usuario);
    }
}