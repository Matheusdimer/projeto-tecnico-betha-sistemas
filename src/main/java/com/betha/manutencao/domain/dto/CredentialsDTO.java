package com.betha.manutencao.domain.dto;

public class CredentialsDTO {
    private String username;
    private String senha;

    public CredentialsDTO() {
    }

    public CredentialsDTO(String username, String senha) {
        this.username = username;
        this.senha = senha;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
