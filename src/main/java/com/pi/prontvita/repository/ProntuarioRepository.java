package com.pi.prontvita.repository;

import com.pi.prontvita.model.Prontuario;
import com.pi.prontvita.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProntuarioRepository extends JpaRepository<Prontuario, Integer> {
    List<Prontuario> findByPaciente(Usuario paciente);
}