package com.betha.manutencao.repositories;

import com.betha.manutencao.domain.ItemOrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemOrdemServicoRepository extends JpaRepository<ItemOrdemServico, Integer> {

}
