package com.pi.prontvita.controller;

import com.pi.prontvita.model.Usuario;
import com.pi.prontvita.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @GetMapping("/login")
    public String exibirLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("senha") String senha, Model model) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        
        if (usuario != null && usuario.getSenha().equals(senha)) {
            return "redirect:/painel";
        } else {
            model.addAttribute("erro", "E-mail ou senha inválidos.");
            return "login";
        }
    }
}