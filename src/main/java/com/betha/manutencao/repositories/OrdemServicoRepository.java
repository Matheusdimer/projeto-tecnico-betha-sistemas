package com.betha.manutencao.repositories;

import com.betha.manutencao.domain.ItemOrdemServico;
import com.betha.manutencao.domain.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Integer> {
    List<OrdemServico> findAllByClienteId(Integer clienteId);

    @Query("SELECT item FROM OrdemServico ordem " +
            "INNER JOIN ItemOrdemServico item ON item.id = :itemId " +
            "WHERE ordem.id = :ordemId")
    Optional<ItemOrdemServico> findItemInOrdemById(@Param("ordemId") Integer ordemId, @Param("itemId") Integer itemId);
}
