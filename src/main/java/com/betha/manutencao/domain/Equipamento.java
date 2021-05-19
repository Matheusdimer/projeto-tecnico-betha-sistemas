package com.betha.manutencao.domain;

import com.betha.manutencao.domain.enums.TipoEquipamento;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
public class Equipamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @NotNull
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoEquipamento tipoEquipamento;
    private String marca;
    private String modelo;

    @JsonIgnore
    @OneToMany(mappedBy = "equipamento")
    List<ItemOrdemServico> itensOrdem;

    public Equipamento() {
    }

    public Equipamento(Cliente cliente, TipoEquipamento tipoEquipamento, String marca, String modelo) {
        this.cliente = cliente;
        this.tipoEquipamento = tipoEquipamento;
        this.marca = marca;
        this.modelo = modelo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public TipoEquipamento getTipoEquipamento() {
        return tipoEquipamento;
    }

    public void setTipoEquipamento(TipoEquipamento tipoEquipamento) {
        this.tipoEquipamento = tipoEquipamento;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public List<ItemOrdemServico> getItensOrdem() {
        return itensOrdem;
    }

    public void setItensOrdem(List<ItemOrdemServico> itensOrdem) {
        this.itensOrdem = itensOrdem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipamento that = (Equipamento) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
