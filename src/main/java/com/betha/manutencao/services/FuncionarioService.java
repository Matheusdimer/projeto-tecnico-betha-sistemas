package com.betha.manutencao.services;

import com.betha.manutencao.domain.Funcionario;
import com.betha.manutencao.repositories.FuncionarioRepository;
import com.betha.manutencao.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Funcionario findOne(Integer id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Funcionário " + id + " não encontrado"));
    }

    public Funcionario findByUsername(String username) {
        return funcionarioRepository.findByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("Funcionário " + username + " não encontrado"));
    }
}
