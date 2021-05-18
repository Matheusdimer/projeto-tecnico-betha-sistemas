package com.betha.manutencao.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class ItemOrdemServico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ordem_id")
    private OrdemServico ordem;

    @OneToOne
    @JoinColumn(name = "equipamento_id")
    private Equipamento equipamento;

    @OneToMany(mappedBy = "itemOrdemServico")
    private List<Avaria> avarias = new ArrayList<>();

    public ItemOrdemServico() {
    }

    public ItemOrdemServico(OrdemServico ordem, Equipamento equipamento) {
        this.ordem = ordem;
        this.equipamento = equipamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrdemServico getOrdem() {
        return ordem;
    }

    public void setOrdem(OrdemServico ordem) {
        this.ordem = ordem;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public List<Avaria> getAvarias() {
        return avarias;
    }

    public void setAvarias(List<Avaria> avarias) {
        this.avarias = avarias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemOrdemServico that = (ItemOrdemServico) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
