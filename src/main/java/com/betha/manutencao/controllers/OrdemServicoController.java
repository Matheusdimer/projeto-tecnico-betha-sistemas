package com.betha.manutencao.controllers;

import com.betha.manutencao.domain.Avaria;
import com.betha.manutencao.domain.ItemOrdemServico;
import com.betha.manutencao.domain.OrdemServico;
import com.betha.manutencao.domain.dto.OrdemStatusDTO;
import com.betha.manutencao.services.OrdemServicoService;
import com.betha.manutencao.services.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ordens")
public class OrdemServicoController {
    @Autowired
    private OrdemServicoService ordemService;

    @GetMapping
    public List<OrdemServico> findAll() {
        return ordemService.findAll();
    }

    @GetMapping("/{id}")
    public OrdemServico findOne(@PathVariable Integer id) {
        return ordemService.findOne(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'ATENDENTE')")
    @PostMapping
    public ResponseEntity<OrdemServico> add(@RequestBody @Valid OrdemServico ordemServico) {
        OrdemServico ordem = ordemService.add(ordemServico);

        return ResponseEntity.created(URIBuilder.buildLocation(ordem.getId())).body(ordem);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'ATENDENTE')")
    @PutMapping("/{id}")
    public OrdemServico update(@PathVariable Integer id, @RequestBody @Valid OrdemServico ordemServico) {
        return ordemService.update(id, ordemServico);
    }

    @PreAuthorize("hasAnyRole('TECNICO')")
    @PutMapping("/{id}/status")
    public OrdemServico updateStatus(@PathVariable Integer id, @RequestBody OrdemStatusDTO ordemStatusDTO) {
        return ordemService.updateStatus(id, ordemStatusDTO.getStatusOrdem(), ordemStatusDTO.getObservacoes());
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        ordemService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("{ordemId}/itens")
    public List<ItemOrdemServico> findOrdemItens(@PathVariable Integer ordemId) {
        return ordemService.findItens(ordemId);
    }

    @PreAuthorize("hasAnyRole('TECNICO')")
    @PostMapping("{ordemId}/itens/{itemId}/avarias")
    public ResponseEntity<Avaria> addAvaria(@PathVariable Integer ordemId,
                                            @PathVariable Integer itemId, @RequestBody Avaria avaria) {
        Avaria avariaSalva = ordemService.addAvaria(ordemId, itemId, avaria);

        return ResponseEntity.status(HttpStatus.CREATED).body(avariaSalva);
    }
}
