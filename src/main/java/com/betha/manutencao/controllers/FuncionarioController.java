package com.betha.manutencao.controllers;

import com.betha.manutencao.domain.Funcionario;
import com.betha.manutencao.services.FuncionarioService;
import com.betha.manutencao.services.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
@PreAuthorize("hasAnyRole('ADMIN')")
public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public List<Funcionario> findAll() {
        return funcionarioService.findAll();
    }

    @GetMapping("/{id}")
    public Funcionario findOne(@PathVariable Integer id) {
        return funcionarioService.findOne(id);
    }

    @PostMapping
    public ResponseEntity<Funcionario> add(@RequestBody Funcionario funcionario) {
        Funcionario funcionarioSalvo = funcionarioService.add(funcionario);

        return ResponseEntity.created(URIBuilder.buildLocation(funcionarioSalvo.getId())).body(funcionarioSalvo);
    }

    @PutMapping("/{id}")
    public Funcionario update(@PathVariable Integer id, @RequestBody Funcionario funcionario) {
        return funcionarioService.update(id, funcionario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        funcionarioService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
