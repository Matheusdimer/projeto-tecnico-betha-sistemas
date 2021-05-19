package com.betha.manutencao.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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

    @ManyToOne
    @JoinColumn(name = "equipamento_id")
    private Equipamento equipamento;

    @NotEmpty
    private String problema;

    @OneToMany(mappedBy = "itemOrdemServico")
    private List<Avaria> avarias = new ArrayList<>();

    public ItemOrdemServico() {
    }

    public ItemOrdemServico(OrdemServico ordem, Equipamento equipamento, String problema) {
        this.ordem = ordem;
        this.equipamento = equipamento;
        this.problema = problema;
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

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public Double getSubtotal() {
        Double soma = 0.00;
        for (Avaria item : this.getAvarias()) {
            soma += item.getCustoReparo();
        }

        return soma;
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
