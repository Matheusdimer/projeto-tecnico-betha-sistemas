package com.betha.manutencao.repositories;

import com.betha.manutencao.domain.Endereco;
import com.betha.manutencao.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}
