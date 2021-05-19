package com.betha.manutencao.services;

import com.betha.manutencao.domain.Cliente;
import com.betha.manutencao.domain.Equipamento;
import com.betha.manutencao.repositories.EquipamentoRepository;
import com.betha.manutencao.services.exceptions.DataIntegrityException;
import com.betha.manutencao.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EquipamentoService {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @Transactional(propagation = Propagation.SUPPORTS)
    public Equipamento find(Integer id) {
        return equipamentoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Equipamento id " + id + " não encontrado"));
    }

    public Equipamento add(Integer clienteId, Equipamento equipamento) {
        Cliente cliente = clienteService.findOne(clienteId);

        equipamento.setId(null);
        equipamento.setCliente(cliente);
        cliente.getEquipamentos().add(equipamento);

        clienteService.update(cliente);

        return equipamentoRepository.save(equipamento);
    }

    public void delete(Integer clienteId, Integer equipamentoId) {
        Equipamento equipamento = this.find(equipamentoId);

        if (!equipamento.getItensOrdem().isEmpty()) {
            throw new DataIntegrityException("Não é possível excluir o equipamento pois há ordens relacionadas");
        }

        Cliente cliente = clienteService.findOne(clienteId);

        if (cliente.getEquipamentos().contains(equipamento)) {
            cliente.getEquipamentos().remove(equipamento);
            clienteService.update(cliente);
            equipamentoRepository.delete(equipamento);
        } else {
            throw new ObjectNotFoundException("Equipamento id " + equipamentoId + " não encontrado");
        }
    }
}
