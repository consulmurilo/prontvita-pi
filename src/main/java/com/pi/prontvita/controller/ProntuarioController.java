package com.pi.prontvita.controller;

import com.pi.prontvita.model.Prontuario;
import com.pi.prontvita.model.Usuario;
import com.pi.prontvita.repository.ProntuarioRepository;
import com.pi.prontvita.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Controller
public class ProntuarioController {

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/cadastro-prontuario")
    public String exibirFormularioProntuario(Model model) {
        model.addAttribute("prontuario", new Prontuario());
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "cadastro-prontuario";
    }
    
    @PostMapping("/cadastro-prontuario")
    public String salvarProntuario(@RequestParam("dataBr") String dataBr, @ModelAttribute Prontuario prontuario, Model model) {
        try {
            DateTimeFormatter formatoBr = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataConvertida = LocalDate.parse(dataBr, formatoBr);
            prontuario.setData(dataConvertida);

            if (prontuario.getDescricao() == null || prontuario.getDescricao().trim().isEmpty()) {
                model.addAttribute("erroDesc", "O campo 'Descrição' é obrigatório.");
                model.addAttribute("usuarios", usuarioRepository.findAll());
                return "cadastro-prontuario";
            }

            prontuarioRepository.save(prontuario);
            return "redirect:/cadastro-prontuario";
        } catch (DateTimeParseException e) {
            model.addAttribute("erroData", "Data inválida. Use o formato DD/MM/AAAA.");
            model.addAttribute("usuarios", usuarioRepository.findAll());
            return "cadastro-prontuario";
        }
    }

    @GetMapping("/historico")
    public String exibirHistorico(Model model) {
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "historico";
    }

    @PostMapping("/historico")
    public String visualizarHistorico(@RequestParam("pacienteId") Integer pacienteId, Model model) {
        model.addAttribute("usuarios", usuarioRepository.findAll());

        if (pacienteId != null) {
            Usuario paciente = usuarioRepository.findById(pacienteId).orElse(null);
            if (paciente != null) {
                List<Prontuario> prontuarios = prontuarioRepository.findByPaciente(paciente);
                model.addAttribute("prontuarios", prontuarios);
            } else {
                model.addAttribute("erro", "Paciente não encontrado.");
            }
        } else {
            model.addAttribute("erro", "Selecione um paciente.");
        }

        return "historico";
    }
}