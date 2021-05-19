package com.betha.manutencao.domain;

import com.betha.manutencao.domain.enums.TipoCliente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Length(min = 5, max = 100)
    private String nome;

    @OneToOne
    @JoinColumn(name = "endereco_id")
    @NotNull
    private Endereco endereco;
    @Email
    @NotEmpty
    @Length(min = 5, max = 255)
    private String email;

    @NotNull
    @Enumerated(value = EnumType.ORDINAL)
    private TipoCliente tipoCliente;

    @NotEmpty
    private String cpf_cnpj;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "telefones")
    private Set<String> telefones = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<OrdemServico> ordens = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Equipamento> equipamentos = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(String nome, Endereco endereco, String email, TipoCliente tipoCliente, String cpf_cnpj) {
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.tipoCliente = tipoCliente;
        this.cpf_cnpj = cpf_cnpj;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
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

    public List<OrdemServico> getOrdens() {
        return ordens;
    }

    public void setOrdens(List<OrdemServico> ordens) {
        this.ordens = ordens;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id.equals(cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
