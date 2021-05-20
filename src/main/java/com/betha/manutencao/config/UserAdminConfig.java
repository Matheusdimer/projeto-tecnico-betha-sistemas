package com.betha.manutencao.config;

import com.betha.manutencao.domain.Funcionario;
import com.betha.manutencao.domain.enums.TipoFuncionario;
import com.betha.manutencao.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class UserAdminConfig {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Bean
    public boolean verifyRootUser() {
        if (!funcionarioRepository.findByUsername("admin").isPresent()) {
            Funcionario admin = new Funcionario("admin",
                    "Administrador", encoder.encode("senhaadmin"), TipoFuncionario.ADMIN);

            funcionarioRepository.save(admin);
        }

        return true;
    }
}
