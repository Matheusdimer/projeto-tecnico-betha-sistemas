package com.betha.manutencao.domain.enums;

public enum StatusOrdem {
    ABERTA(1, "Aberta"),
    NAO_ATENDIDA(2, "Não atendida"),
    ATENDIDA(3, "Atendida");

    private Integer codigo;
    private String descricao;

    StatusOrdem(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static StatusOrdem toEnum(Integer cod) {
        for (StatusOrdem x : StatusOrdem.values()) {
            if (x.getCodigo().equals(cod)) {
                return x;
            }
        }

        throw new IllegalArgumentException("Código inválido");
    }
}
