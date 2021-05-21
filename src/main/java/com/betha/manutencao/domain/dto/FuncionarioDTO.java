package com.betha.manutencao.domain.dto;

import com.betha.manutencao.domain.Funcionario;
import com.betha.manutencao.domain.enums.TipoFuncionario;

public class FuncionarioDTO {
    private Integer id;
    private String username;
    private String nomeCompleto;
    private TipoFuncionario tipoFuncionario;

    public FuncionarioDTO() {
    }

    public FuncionarioDTO(Integer id, String username, String nomeCompleto, TipoFuncionario tipoFuncionario) {
        this.id = id;
        this.username = username;
        this.nomeCompleto = nomeCompleto;
        this.tipoFuncionario = tipoFuncionario;
    }

    public FuncionarioDTO(Funcionario funcionario) {
        this.id = funcionario.getId();
        this.username = funcionario.getUsername();
        this.nomeCompleto = funcionario.getNomeCompleto();
        this.tipoFuncionario = funcionario.getTipoFuncionario();
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

    public TipoFuncionario getTipoFuncionario() {
        return tipoFuncionario;
    }

    public void setTipoFuncionario(TipoFuncionario tipoFuncionario) {
        this.tipoFuncionario = tipoFuncionario;
    }
}
