package com.betha.manutencao.controllers;

import com.betha.manutencao.domain.Cliente;
import com.betha.manutencao.domain.dto.ClienteNovoDTO;
import com.betha.manutencao.services.ClienteService;
import com.betha.manutencao.services.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> findAll() {
        return clienteService.findAll();
    }

    @GetMapping("/{id}")
    public Cliente findOne(@PathVariable Integer id) {
        return  clienteService.findOne(id);
    }

    @PostMapping
    public ResponseEntity<Cliente> add(@RequestBody ClienteNovoDTO clienteDTO) {
        Cliente cliente = clienteService.add(clienteDTO);

        return ResponseEntity.created(URIBuilder.buildLocation(cliente.getId())).body(cliente);
    }
}
