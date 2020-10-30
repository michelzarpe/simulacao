package com.simulacao.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.simulacao.modal.Simulacao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* 

 Os dados de entrada para uma nova simulação são: NOME DA PESSOA, CPF, VALOR
SEGURADO, NÚMERO CONTRATO EMPRÉSTIMO, DATA FIM DO CONTRATO DE
EMPRÉSTIMO E DATA NASCIMENTO.

 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimulacaoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Valores não podem ser nulos [nomePessoa]")
	@Length(min = 3, max=50, message = "3 até 50 caracteres [nomePessoa]")
	private String nomePessoa;
	
	@NotNull(message = "Valores não podem ser nulos [cpf]")
	@Length(min = 3, max=12, message = "6 até 12 caracteres [cpf]")
	private String cpf;
	
	@NotNull(message = "Valores não podem ser nulos [valorSegurado]")
	private BigDecimal valorSegurado;
	
	@NotNull(message = "Valores não podem ser nulos [numeroContratoEmprestimo]")
	private String numeroContratoEmprestimo;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone="GMT-3")
	private Date fimContratoEmprestimo;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone="GMT-3")
	private Date dataNascimento;

}
