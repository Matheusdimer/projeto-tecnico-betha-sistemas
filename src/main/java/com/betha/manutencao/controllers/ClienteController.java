package com.betha.manutencao.controllers;

import com.betha.manutencao.domain.Cliente;
import com.betha.manutencao.domain.Equipamento;
import com.betha.manutencao.domain.OrdemServico;
import com.betha.manutencao.domain.dto.ClienteDTO;
import com.betha.manutencao.domain.dto.ClienteUpdateDTO;
import com.betha.manutencao.services.ClienteService;
import com.betha.manutencao.services.EquipamentoService;
import com.betha.manutencao.services.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EquipamentoService equipamentoService;

    @GetMapping
    public List<Cliente> findAll() {
        return clienteService.findAll();
    }

    @GetMapping("/{id}")
    public Cliente findOne(@PathVariable Integer id) {
        return  clienteService.findOne(id);
    }

    @GetMapping("/{id}/ordens")
    public List<OrdemServico> findOrdensCliente(@PathVariable Integer id) {
        return clienteService.findOrdens(id);
    }

    @GetMapping("/{id}/equipamentos")
    public List<Equipamento> findEquipamentosCliente(@PathVariable Integer id) {
        return clienteService.findEquipamentos(id);
    }

    @PostMapping
    public ResponseEntity<Cliente> add(@RequestBody @Valid ClienteDTO clienteDTO) {
        Cliente cliente = clienteService.add(clienteDTO);

        return ResponseEntity.created(URIBuilder.buildLocation(cliente.getId())).body(cliente);
    }

    @PostMapping("{clienteId}/equipamentos")
    public ResponseEntity<Equipamento> addEquipamento(@PathVariable Integer clienteId,
                                                      @RequestBody Equipamento equipamento) {
        Equipamento equipamentoSalvo = equipamentoService.add(clienteId, equipamento);

        return ResponseEntity.created(URIBuilder.buildLocation(equipamentoSalvo.getId())).body(equipamentoSalvo);
    }

    @DeleteMapping("{clienteId}/equipamentos/{equipamentoId}")
    public ResponseEntity<Void> deleteEquipamento(@PathVariable Integer clienteId, @PathVariable Integer equipamentoId) {
        equipamentoService.delete(clienteId, equipamentoId);

        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    public Cliente update(@PathVariable Integer id, @RequestBody @Valid ClienteUpdateDTO cliente) {
        return clienteService.update(id, cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
