package com.betha.manutencao.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Avaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "item_ordem_id")
    private ItemOrdemServico itemOrdemServico;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "equipamento_id")
    private Equipamento equipamento;

    @NotEmpty
    private String descricao;

    @NotNull
    private Double custoReparo;

    public Avaria() {
    }

    public Avaria(ItemOrdemServico itemOrdemServico, Equipamento equipamento, String descricao, Double custoReparo) {
        this.itemOrdemServico = itemOrdemServico;
        this.equipamento = equipamento;
        this.descricao = descricao;
        this.custoReparo = custoReparo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ItemOrdemServico getItemOrdemServico() {
        return itemOrdemServico;
    }

    public void setItemOrdemServico(ItemOrdemServico itemOrdemServico) {
        this.itemOrdemServico = itemOrdemServico;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getCustoReparo() {
        return custoReparo;
    }

    public void setCustoReparo(Double custoReparo) {
        this.custoReparo = custoReparo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avaria avaria = (Avaria) o;
        return id.equals(avaria.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
