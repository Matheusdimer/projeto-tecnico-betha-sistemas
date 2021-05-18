package com.betha.manutencao.repositories;

import com.betha.manutencao.domain.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Integer> {
    List<OrdemServico> findAllByClienteId(Integer clienteId);
}
