package com.simulacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simulacao.modal.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
