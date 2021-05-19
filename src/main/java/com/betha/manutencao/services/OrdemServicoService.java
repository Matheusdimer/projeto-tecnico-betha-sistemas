package com.betha.manutencao.services;

import com.betha.manutencao.domain.*;
import com.betha.manutencao.domain.enums.StatusOrdem;
import com.betha.manutencao.repositories.AvariaRepository;
import com.betha.manutencao.repositories.EquipamentoRepository;
import com.betha.manutencao.repositories.ItemOrdemServicoRepository;
import com.betha.manutencao.repositories.OrdemServicoRepository;
import com.betha.manutencao.services.exceptions.DataIntegrityException;
import com.betha.manutencao.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class OrdemServicoService {
    @Autowired
    private OrdemServicoRepository ordemRepository;

    @Autowired
    private ItemOrdemServicoRepository itemRepository;

    @Autowired
    private AvariaRepository avariaRepository;

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @Autowired
    private ClienteService clienteService;

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<OrdemServico> findAll() {
        return ordemRepository.findAll();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public OrdemServico findOne(Integer id) {
        return ordemRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Ordem id " + id + " não encontrada"));
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ItemOrdemServico> findItens(Integer ordemId) {
        return this.findOne(ordemId).getItens();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public ItemOrdemServico findOneItem(Integer ordemId, Integer itemId) {
        return ordemRepository.findItemInOrdemById(ordemId, itemId)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Item relacionado a ordem " + ordemId + " não encontrado"));
    }

    public OrdemServico add(OrdemServico ordemServico) {
        Cliente cliente = clienteService.findOne(ordemServico.getCliente().getId());
        cliente.getOrdens().add(ordemServico);

        ordemServico.setId(null);
        ordemServico.setCliente(cliente);

        for (ItemOrdemServico item : ordemServico.getItens()) {
            Equipamento equipamento = equipamentoRepository.findById(item.getEquipamento().getId())
                    .orElseThrow(() -> new ObjectNotFoundException("Equipamento id "
                            + item.getEquipamento().getId() + " não encontrado"));

            item.setEquipamento(equipamento);
            item.setAvarias(Collections.emptyList());
            item.setOrdem(ordemServico);

            equipamento.getItensOrdem().add(item);
        }

        OrdemServico ordemSalva = ordemRepository.save(ordemServico);
        clienteService.update(cliente);

        return ordemSalva;
    }

    public OrdemServico update(Integer ordemId, OrdemServico ordemServico) {
        this.findOne(ordemId);

        ordemServico.setId(ordemId);

        return ordemRepository.save(ordemServico);
    }

    public OrdemServico updateStatus(Integer ordemId, StatusOrdem statusOrdem, String obs) {
        OrdemServico ordemServico = this.findOne(ordemId);

        ordemServico.setStatusOrdem(statusOrdem);
        ordemServico.setObservacoes(obs);

        if (!(statusOrdem == StatusOrdem.ABERTA)) {
            ordemServico.setDataEncerramento(LocalDate.now());
        } else {
            ordemServico.setDataEncerramento(null);
        }

        return ordemRepository.save(ordemServico);
    }

    public void delete(Integer ordemId) {
        OrdemServico ordemServico = this.findOne(ordemId);

        if (ordemServico.getStatusOrdem() != StatusOrdem.ABERTA) {
            throw new DataIntegrityException("Não é possível excluir uma ordem já finalizada");
        }

        for (ItemOrdemServico item : ordemServico.getItens()) {
            if (!item.getAvarias().isEmpty()) {
                throw new DataIntegrityException("Não é possível excluir a ordem pois já existem avarias analizadas");
            }
        }

        ordemRepository.delete(ordemServico);
    }

    public Avaria addAvaria(Integer ordemId, Integer itemOrdemId, Avaria avaria) {
        ItemOrdemServico itemOrdemServico = this.findOneItem(ordemId, itemOrdemId);

        avaria.setEquipamento(itemOrdemServico.getEquipamento());
        avaria.setItemOrdemServico(itemOrdemServico);

        itemOrdemServico.getAvarias().add(avaria);
        avariaRepository.save(avaria);
        itemRepository.save(itemOrdemServico);

        return avaria;
    }
}
