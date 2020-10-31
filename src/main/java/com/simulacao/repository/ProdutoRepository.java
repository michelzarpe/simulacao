package com.simulacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.simulacao.modal.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Produto obj WHERE obj.idadeMinima <= :idade and obj.idadeMaxima >= :idade")
	public Produto findProdutoByIdade(@Param("idade") Integer idade);
}
