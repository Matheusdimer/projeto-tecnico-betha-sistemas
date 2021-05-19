package com.betha.manutencao.domain.dto;

import com.betha.manutencao.domain.enums.StatusOrdem;

public class OrdemStatusDTO {
    private StatusOrdem statusOrdem;
    private String observacoes;

    public OrdemStatusDTO() {
    }

    public OrdemStatusDTO(StatusOrdem statusOrdem, String observacoes) {
        this.statusOrdem = statusOrdem;
        this.observacoes = observacoes;
    }

    public StatusOrdem getStatusOrdem() {
        return statusOrdem;
    }

    public void setStatusOrdem(StatusOrdem statusOrdem) {
        this.statusOrdem = statusOrdem;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
