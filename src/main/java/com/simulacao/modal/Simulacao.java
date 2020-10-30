package com.simulacao.modal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class Simulacao implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nomePessoa;
	private String cpf;
	private BigDecimal valorSegurado;
	private String numeroContratoEmprestimo;
	private Date fimContratoEmprestimo;
	private Date dataNascimento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "produtoEscolhido", referencedColumnName = "id")
	private Produto produtoEscolhido;
	private Date dataSimulacao;
	private BigDecimal valorTotalPremio;
}
