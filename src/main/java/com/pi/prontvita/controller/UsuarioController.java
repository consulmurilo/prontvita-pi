package com.pi.prontvita.controller;

import com.pi.prontvita.model.Usuario;
import com.pi.prontvita.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/cadastro-usuario")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro-usuario";
    }

    @PostMapping("/cadastro-usuario")
    public String cadastrarUsuario(@ModelAttribute("usuario") Usuario usuario, Model model) {
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            model.addAttribute("erro", "O campo 'Nome' é obrigatório.");
            return "cadastro-usuario";
        }
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            model.addAttribute("erro", "O campo 'E-mail' é obrigatório.");
            return "cadastro-usuario";
        }
        if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
            model.addAttribute("erro", "O campo 'Senha' é obrigatório.");
            return "cadastro-usuario";
        }

        usuarioRepository.save(usuario);

        model.addAttribute("sucesso", "Usuário cadastrado com sucesso!");

        return "redirect:/painel";
    }
}