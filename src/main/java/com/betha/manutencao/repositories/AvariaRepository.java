package com.betha.manutencao.repositories;

import com.betha.manutencao.domain.Avaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvariaRepository extends JpaRepository<Avaria, Integer> {

}
