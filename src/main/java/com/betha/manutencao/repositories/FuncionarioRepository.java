package com.betha.manutencao.repositories;

import com.betha.manutencao.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    Optional<Funcionario> findByUsername(String username);
}
