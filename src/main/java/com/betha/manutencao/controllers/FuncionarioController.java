package com.betha.manutencao.controllers;

import com.betha.manutencao.domain.Funcionario;
import com.betha.manutencao.domain.dto.AlterarSenhaDTO;
import com.betha.manutencao.domain.dto.FuncionarioDTO;
import com.betha.manutencao.services.FuncionarioService;
import com.betha.manutencao.services.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/funcionarios")
@PreAuthorize("hasAnyRole('ADMIN')")
public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public List<FuncionarioDTO> findAll() {
        return funcionarioService.findAll().stream().map(FuncionarioDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public FuncionarioDTO findOne(@PathVariable Integer id) {
        return new FuncionarioDTO(funcionarioService.findOne(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'ATENDENTE', 'TECNICO')")
    @GetMapping("/current")
    public FuncionarioDTO getCurrent() {
        return new FuncionarioDTO(funcionarioService.getCurrent());
    }

    @PostMapping
    public ResponseEntity<FuncionarioDTO> add(@RequestBody Funcionario funcionario) {
        Funcionario funcionarioSalvo = funcionarioService.add(funcionario);

        return ResponseEntity.created(URIBuilder.buildLocation(funcionarioSalvo.getId()))
                .body(new FuncionarioDTO(funcionarioSalvo));
    }

    @PutMapping("/{id}/senha")
    @PreAuthorize("hasAnyRole('ADMIN', 'ATENDENTE', 'TECNICO')")
    public FuncionarioDTO updateSenha(@PathVariable Integer id, @RequestBody AlterarSenhaDTO alterarSenhaDTO) {
        return new FuncionarioDTO(funcionarioService.updateSenha(id, alterarSenhaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        funcionarioService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
