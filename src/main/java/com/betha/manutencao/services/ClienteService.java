package com.betha.manutencao.services;

import com.betha.manutencao.domain.*;
import com.betha.manutencao.domain.dto.ClienteDTO;
import com.betha.manutencao.domain.dto.ClienteUpdateDTO;
import com.betha.manutencao.domain.enums.TipoCliente;
import com.betha.manutencao.repositories.ClienteRepository;
import com.betha.manutencao.repositories.EnderecoRepository;
import com.betha.manutencao.repositories.EquipamentoRepository;
import com.betha.manutencao.repositories.OrdemServicoRepository;
import com.betha.manutencao.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private OrdemServicoRepository ordemRepository;

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Cliente findOne(Integer clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ObjectNotFoundException("Cliente id " + clienteId + " não encontrado"));
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<OrdemServico> findOrdens(Integer clienteId) {
        return ordemRepository.findAllByClienteId(clienteId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Equipamento> findEquipamentos(Integer clienteId) {
        return this.findOne(clienteId).getEquipamentos();
    }

    public Cliente add(Cliente cliente) {
        cliente.setId(null);
        return clienteRepository.save(cliente);
    }

    public Cliente add(ClienteDTO clienteDTO) {
        Cliente cliente = this.fromDTO(clienteDTO);

        enderecoRepository.save(cliente.getEndereco());

        return clienteRepository.save(cliente);
    }

    public Cliente update(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente update(Integer clienteId, ClienteUpdateDTO clienteDTO) {
        Cliente cliente = this.findOne(clienteId);

        this.updateClienteData(cliente, clienteDTO);
        enderecoRepository.save(cliente.getEndereco());

        return clienteRepository.save(cliente);
    }

    public void delete(Integer clienteId) {
        Cliente cliente = this.findOne(clienteId);

        clienteRepository.delete(cliente);
    }

    public Cliente fromDTO(ClienteDTO clienteDTO) {
        Endereco endereco = new Endereco();

        endereco.setEndereco(clienteDTO.getEndereco());
        endereco.setNumero(clienteDTO.getNumero());
        endereco.setBairro(clienteDTO.getBairro());
        endereco.setComplemento(clienteDTO.getComplemento());
        endereco.setCep(clienteDTO.getCep());
        endereco.setCidade(cidadeService.findOne(clienteDTO.getCidadeId()));

        Cliente cliente = new Cliente();
        endereco.setCliente(cliente);

        cliente.setTipoCliente(clienteDTO.getTipoCliente());
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf_cnpj(clienteDTO.getCpf_cnpj());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setEndereco(endereco);
        cliente.setTelefones(clienteDTO.getTelefones());
        cliente.setId(null);

        return cliente;
    }

    public void updateClienteData(Cliente cliente, ClienteUpdateDTO clienteUpdate) {
        cliente.setNome(clienteUpdate.getNome());
        cliente.setEmail(clienteUpdate.getEmail());
        cliente.setTelefones(clienteUpdate.getTelefones());

        Endereco endereco = cliente.getEndereco();
        endereco.setEndereco(clienteUpdate.getEndereco());
        endereco.setNumero(clienteUpdate.getNumero());
        endereco.setComplemento(clienteUpdate.getComplemento());
        endereco.setBairro(clienteUpdate.getBairro());
        endereco.setCep(clienteUpdate.getCep());

        Cidade cidade = cidadeService.findOne(clienteUpdate.getCidadeId());
        endereco.setCidade(cidade);
    }
}
