package com.betha.manutencao.domain.dto;

import com.betha.manutencao.domain.enums.TipoCliente;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

public class ClienteDTO {
    @NotEmpty
    @Length(max = 100)
    private String nome;

    @Email
    @NotEmpty
    @Length(max = 255)
    private String email;

    @NotNull
    private TipoCliente tipoCliente;

    @NotEmpty
    private String cpf_cnpj;

    @NotNull
    private Set<String> telefones = new HashSet<>();

    @NotEmpty
    private String endereco;
    @NotEmpty
    private String numero;
    private String complemento;
    @NotEmpty
    private String bairro;
    @NotEmpty
    private String cep;

    @NotNull
    private Integer cidadeId;

    public ClienteDTO() {
    }

    public ClienteDTO(String nome, String email, TipoCliente tipoCliente, String cpf_cnpj, Set<String> telefones,
                      String endereco, String numero, String complemento, String bairro, String cep,
                      Integer cidadeId) {
        this.nome = nome;
        this.email = email;
        this.tipoCliente = tipoCliente;
        this.cpf_cnpj = cpf_cnpj;
        this.telefones = telefones;
        this.endereco = endereco;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.cidadeId = cidadeId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public Set<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Integer cidadeId) {
        this.cidadeId = cidadeId;
    }
}
