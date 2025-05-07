package com.pi.prontvita.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PainelController {

    @GetMapping("/painel")
    public String exibirPainel() {
        return "painel";
    }
}