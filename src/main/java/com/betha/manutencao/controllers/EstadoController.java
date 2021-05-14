package com.betha.manutencao.controllers;

import com.betha.manutencao.domain.Cidade;
import com.betha.manutencao.domain.Estado;
import com.betha.manutencao.services.CidadeService;
import com.betha.manutencao.services.EstadoService;
import com.betha.manutencao.services.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {
    @Autowired
    private EstadoService estadoService;

    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public List<Estado> findAll() {
        return estadoService.findAll();
    }

    @GetMapping("/{id}")
    public Estado findOne(@PathVariable Integer id) {
        return estadoService.findOne(id);
    }

    @PostMapping
    public ResponseEntity<Estado> add(@RequestBody Estado estado) {
        Estado novoEstado = estadoService.add(estado);

        return ResponseEntity.created(URIBuilder.buildLocation(novoEstado.getId())).body(novoEstado);
    }

    @PutMapping("/{id}")
    public Estado update(@PathVariable Integer id, @RequestBody Estado estado) {
        return estadoService.update(id, estado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer estadoId) {
        estadoService.delete(estadoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/cidades")
    public List<Cidade> findCidades(@PathVariable(name = "id") Integer estadoId) {
        return cidadeService.findByEstado(estadoId);
    }

    @PostMapping("/{id}/cidades")
    public ResponseEntity<Cidade> addCidade(@PathVariable(name = "id") Integer estadoId,
                                            @RequestBody Cidade cidade) {
        Cidade novaCidade = cidadeService.add(estadoId, cidade);

        return ResponseEntity.created(URIBuilder.buildLocation(novaCidade.getId())).body(novaCidade);
    }
}
