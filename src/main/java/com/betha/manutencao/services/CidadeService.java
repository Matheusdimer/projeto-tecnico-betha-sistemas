package com.betha.manutencao.services;

import com.betha.manutencao.domain.Cidade;
import com.betha.manutencao.domain.Estado;
import com.betha.manutencao.repositories.CidadeRepository;
import com.betha.manutencao.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CidadeService {
    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoService estadoService;

    public Cidade add(Integer estadoId, Cidade cidade) {
        Estado estado = estadoService.findOne(estadoId);

        cidade.setId(null);
        cidade.setEstado(estado);
        cidade = cidadeRepository.save(cidade);

        estado.getCidades().add(cidade);

        estadoService.update(estadoId, estado);
        return cidade;
    }

    public Cidade findOne(Integer cidadeId) {
        return cidadeRepository.findById(cidadeId)
                .orElseThrow(() -> new ObjectNotFoundException("Cidade não encontrada"));
    }

    public List<Cidade> findByEstado(Integer estadoId) {
        return cidadeRepository.findByEstadoId(estadoId);
    }
}
