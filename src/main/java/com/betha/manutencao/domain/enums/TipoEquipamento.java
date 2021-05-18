package com.betha.manutencao.domain.enums;

public enum TipoEquipamento {
    COMPUTADOR(1, "Computador/Notebook"),
    IMPRESSORA(2, "Impressora"),
    CELULAR(3, "Celular"),
    ELETRODOMESTICO(4, "Eletrodoméstico"),
    OUTRO(5, "Outros");

    private Integer codigo;
    private String descricao;

    TipoEquipamento(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoEquipamento toEnum(Integer cod) {
        for (TipoEquipamento x : TipoEquipamento.values()) {
            if (x.getCodigo().equals(cod)) {
                return x;
            }
        }

        throw new IllegalArgumentException("Código inválido");
    }
}
