package com.betha.manutencao.domain;

import com.betha.manutencao.domain.enums.TipoFuncionario;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Funcionario {
    @Id
    @GeneratedValue
    private Integer id;

    @NotEmpty
    private String username;
    @NotEmpty
    private String nomeCompleto;
    @NotEmpty
    private String senha;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoFuncionario tipoFuncionario;

    public Funcionario() {
    }

    public Funcionario(String username, String nomeCompleto, String senha, TipoFuncionario tipoFuncionario) {
        this.username = username;
        this.nomeCompleto = nomeCompleto;
        this.senha = senha;
        this.tipoFuncionario = tipoFuncionario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoFuncionario getTipoFuncionario() {
        return tipoFuncionario;
    }

    public void setTipoFuncionario(TipoFuncionario tipoFuncionario) {
        this.tipoFuncionario = tipoFuncionario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario that = (Funcionario) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
