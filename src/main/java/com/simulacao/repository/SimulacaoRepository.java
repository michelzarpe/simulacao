package com.simulacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simulacao.modal.Simulacao;

@Repository
public interface SimulacaoRepository extends JpaRepository<Simulacao, Long> {

}
