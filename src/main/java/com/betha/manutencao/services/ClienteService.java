package com.betha.manutencao.services;

import com.betha.manutencao.domain.Cliente;
import com.betha.manutencao.domain.Endereco;
import com.betha.manutencao.domain.dto.ClienteNovoDTO;
import com.betha.manutencao.domain.enums.TipoCliente;
import com.betha.manutencao.repositories.ClienteRepository;
import com.betha.manutencao.repositories.EnderecoRepository;
import com.betha.manutencao.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente findOne(Integer clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ObjectNotFoundException("Cliente id " + clienteId + " não encontrado"));
    }

    public Cliente add(Cliente cliente) {
        cliente.setId(null);
        return clienteRepository.save(cliente);
    }

    public Cliente add(ClienteNovoDTO clienteNovoDTO) {
        Cliente cliente = this.fromDTO(clienteNovoDTO);

        return clienteRepository.save(cliente);
    }

    public Cliente update(Integer clienteId, Cliente cliente) {
        this.findOne(clienteId);

        cliente.setId(clienteId);
        return clienteRepository.save(cliente);
    }

    public void delete(Integer clienteId) {
        Cliente cliente = this.findOne(clienteId);

        clienteRepository.delete(cliente);
    }

    public Endereco updateEndereco(Integer clienteId, Endereco endereco) {
        Cliente cliente = this.findOne(clienteId);

        endereco.setCliente(cliente);
        cliente.setEndereco(endereco);

        clienteRepository.save(cliente);
        return enderecoRepository.save(endereco);
    }

    public Cliente fromDTO(ClienteNovoDTO clienteNovoDTO) {
        Endereco endereco = new Endereco();

        endereco.setEndereco(clienteNovoDTO.getEndereco());
        endereco.setNumero(clienteNovoDTO.getNumero());
        endereco.setBairro(clienteNovoDTO.getBairro());
        endereco.setComplemento(clienteNovoDTO.getComplemento());
        endereco.setCep(clienteNovoDTO.getCep());
        endereco.setCidade(cidadeService.findOne(clienteNovoDTO.getCidadeId()));

        Cliente cliente = new Cliente();
        endereco.setCliente(cliente);

        cliente.setTipoCliente(TipoCliente.toEnum(clienteNovoDTO.getTipo()));
        cliente.setNome(clienteNovoDTO.getNome());
        cliente.setCpf_cnpj(clienteNovoDTO.getCpf_cnpj());
        cliente.setEmail(clienteNovoDTO.getEmail());
        cliente.setEndereco(endereco);
        cliente.setTelefones(clienteNovoDTO.getTelefones());
        cliente.setId(null);

        enderecoRepository.save(endereco);
        return clienteRepository.save(cliente);
    }

}
