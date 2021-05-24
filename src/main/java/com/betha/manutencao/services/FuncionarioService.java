package com.betha.manutencao.services;

import com.betha.manutencao.domain.Funcionario;
import com.betha.manutencao.domain.dto.AlterarSenhaDTO;
import com.betha.manutencao.domain.enums.TipoFuncionario;
import com.betha.manutencao.repositories.FuncionarioRepository;
import com.betha.manutencao.security.User;
import com.betha.manutencao.services.exceptions.AuthorizationException;
import com.betha.manutencao.services.exceptions.CredentialsException;
import com.betha.manutencao.services.exceptions.DataIntegrityException;
import com.betha.manutencao.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCrypt;

    @Transactional(propagation = Propagation.SUPPORTS)
    public Funcionario findOne(Integer id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Funcionário " + id + " não encontrado"));
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Funcionario getCurrent() {
        String username = UserService.authenticated();

        return this.findByUsername(username);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Funcionario findByUsername(String username) {
        return funcionarioRepository.findByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("Funcionário " + username + " não encontrado"));
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

    public Funcionario add(Funcionario funcionario) {
        funcionario.setId(null);
        funcionario.setSenha(bCrypt.encode(funcionario.getSenha()));
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario update(Integer id, Funcionario funcionario) {
        this.findOne(id);
        funcionario.setId(id);

        return funcionarioRepository.save(funcionario);
    }

    public Funcionario update(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario updateSenha(Integer funcionarioId, AlterarSenhaDTO alterarSenhaDTO) {
        String userAuthenticated = UserService.authenticated();
        Funcionario funcionario = this.findOne(funcionarioId);

        if (userAuthenticated == null || !userAuthenticated.equals(funcionario.getUsername())) {
            throw new AuthorizationException("Acesso negado");
        }

        if (!bCrypt.matches(alterarSenhaDTO.getSenhaAtual(), funcionario.getSenha())) {
            throw new CredentialsException("Senha atual inválida");
        }

        funcionario.setSenha(bCrypt.encode(alterarSenhaDTO.getNovaSenha()));

        return this.update(funcionario);
    }

    public void delete(Integer id) {
        Funcionario funcionario = this.findOne(id);

        if (funcionario.getTipoFuncionario() == TipoFuncionario.ADMIN) {
            throw new DataIntegrityException("Não é possível excluir o usuário administrador");
        }

        funcionarioRepository.delete(funcionario);
    }
}
