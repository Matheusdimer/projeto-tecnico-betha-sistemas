package com.betha.manutencao.domain.dto;

import javax.validation.constraints.NotEmpty;

public class AlterarSenhaDTO {
    @NotEmpty
    private String senhaAtual;
    @NotEmpty
    private String novaSenha;

    public AlterarSenhaDTO() {
    }

    public AlterarSenhaDTO(String senhaAtual, String novaSenha) {
        this.senhaAtual = senhaAtual;
        this.novaSenha = novaSenha;
    }

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }
}
