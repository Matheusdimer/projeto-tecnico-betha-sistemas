package com.betha.manutencao.domain;

import com.betha.manutencao.domain.enums.StatusOrdem;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class OrdemServico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "ordem", cascade = CascadeType.ALL)
    private List<ItemOrdemServico> itens = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    private StatusOrdem statusOrdem = StatusOrdem.ABERTA;

    private LocalDate dataInclusao = LocalDate.now();
    private LocalDate dataEncerramento;

    @Length(max = 255)
    private String observacoes;

    public OrdemServico() {
    }

    public OrdemServico(Cliente cliente, String observacoes) {
        this.cliente = cliente;
        this.observacoes = observacoes;
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

    public List<ItemOrdemServico> getItens() {
        return itens;
    }

    public void setItens(List<ItemOrdemServico> itens) {
        this.itens = itens;
    }

    public StatusOrdem getStatusOrdem() {
        return statusOrdem;
    }

    public void setStatusOrdem(StatusOrdem statusOrdem) {
        this.statusOrdem = statusOrdem;
    }

    public LocalDate getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(LocalDate dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public LocalDate getDataEncerramento() {
        return dataEncerramento;
    }

    public void setDataEncerramento(LocalDate dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Double getCustoTotal() {
        Double soma = 0.00;

        for (ItemOrdemServico item : this.getItens()) {
            soma += item.getSubtotal();
        }

        return soma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdemServico that = (OrdemServico) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
