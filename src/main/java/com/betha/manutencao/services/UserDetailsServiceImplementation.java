package com.betha.manutencao.services;

import com.betha.manutencao.domain.Funcionario;
import com.betha.manutencao.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
    @Autowired
    private FuncionarioService funcionarioService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Funcionario funcionario = funcionarioService.findByUsername(s);

        return new User(funcionario.getId(), funcionario.getUsername(),
                funcionario.getSenha(), funcionario.getTipoFuncionario());
    }
}
