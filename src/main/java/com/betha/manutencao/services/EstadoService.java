package com.betha.manutencao.services;

import com.betha.manutencao.domain.Estado;
import com.betha.manutencao.repositories.CidadeRepository;
import com.betha.manutencao.repositories.EstadoRepository;
import com.betha.manutencao.services.exceptions.DataIntegrityException;
import com.betha.manutencao.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EstadoService {
    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Estado> findAll() {
        return estadoRepository.findAll();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Estado findOne(Integer estadoId) {
        return estadoRepository.findById(estadoId)
                .orElseThrow(() -> new ObjectNotFoundException("Estado id " + estadoId + "não encontrado"));
    }

    public Estado add(Estado estado) {
        estado.setId(null);
        estado.setSigla(estado.getSigla().toUpperCase());
        return estadoRepository.save(estado);
    }

    public Estado update(Integer estadoId, Estado estado) {
        this.findOne(estadoId);

        estado.setId(estadoId);
        estado.setSigla(estado.getSigla().toUpperCase());
        return estadoRepository.save(estado);
    }

    public void delete(Integer estadoId) {
        Estado estado = this.findOne(estadoId);

        if (!estado.getCidades().isEmpty()) {
            throw new DataIntegrityException("Não é possível excluir o estado pois há cidades relacionadas");
        }

        estadoRepository.delete(estado);
    }
}
