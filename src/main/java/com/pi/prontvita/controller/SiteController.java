package com.pi.prontvita.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SiteController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/cadastro-usuario")
    public String cadastroUsuario() {
        return "cadastro-usuario";
    }

    @GetMapping("/cadastro-prontuario")
    public String cadastroProntuario() {
        return "cadastro-prontuario";
    }

    @GetMapping("/historico")
    public String historico() {
        return "historico";
    }
    
    @GetMapping("/painel")
    public String painel() {
        return "painel";
    }
}