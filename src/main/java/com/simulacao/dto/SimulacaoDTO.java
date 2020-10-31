package com.simulacao.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.simulacao.modal.Simulacao;
import com.simulacao.validacao.SimulacaoInsert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(value = Include.NON_NULL)
@SimulacaoInsert
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
	
	private String produtoEscolhido;
	private Date dataSimulacao;
	private BigDecimal valorTotalPremio;

	
	public SimulacaoDTO(Simulacao simulacao) {
		this.nomePessoa = simulacao.getNomePessoa();
		this.cpf = simulacao.getCpf();
		this.valorSegurado = simulacao.getValorSegurado();
		this.numeroContratoEmprestimo = simulacao.getNumeroContratoEmprestimo();
		this.fimContratoEmprestimo = simulacao.getFimContratoEmprestimo();
		this.dataNascimento = simulacao.getDataNascimento();
		this.produtoEscolhido = simulacao.getProdutoEscolhido().getNome();
		this.dataNascimento=simulacao.getDataSimulacao();
		this.valorTotalPremio=simulacao.getValorTotalPremio();
	}
	
	
}
