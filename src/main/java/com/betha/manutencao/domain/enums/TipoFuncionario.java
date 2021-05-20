package com.betha.manutencao.domain.enums;

public enum TipoFuncionario {
    ADMIN(1, "ROLE_ADMIN"),
    ATENDENTE(2, "ROLE_ATENDENTE"),
    TECNICO(3, "ROLE_TECNICO");

    private Integer codigo;
    private String role;

    TipoFuncionario(Integer codigo, String role) {
        this.codigo = codigo;
        this.role = role;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getRole() {
        return role;
    }
}
