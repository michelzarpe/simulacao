package com.simulacao.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simulacao.dto.SimulacaoDTO;
import com.simulacao.modal.Produto;
import com.simulacao.modal.Simulacao;
import com.simulacao.repository.ProdutoRepository;
import com.simulacao.repository.SimulacaoRepository;
import com.simulacao.service.SimulacaoService;
import com.simulacao.service.exception.ObjectNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SimulacaoServiceImp implements SimulacaoService{

	@Autowired
	private SimulacaoRepository repository;
	
	@Autowired
	private ProdutoRepository repositoryProduto;
	
	@Override
	public SimulacaoDTO gerarSimulacaoSalvar(SimulacaoDTO objDTO) {
		Produto produtoEscolhido = null;
		Date dataSimulacao = new Date();
		BigDecimal taxaJuros = null;
		Integer numeroMeses = null;
		Double valorParaCalcTotPremio = null;
		
		Integer idade = this.idadeCalculada(objDTO.getDataNascimento());
		produtoEscolhido = repositoryProduto.findProdutoByIdade(idade);		
		
		if(produtoEscolhido== null) 
			throw new ObjectNotFoundException("Produto não existe para essa idade "+ Produto.class.getName());
		
		taxaJuros = produtoEscolhido.getTaxaJuros();
		numeroMeses = this.getNumeroMes(dataSimulacao, objDTO.getFimContratoEmprestimo());
		valorParaCalcTotPremio = this.getValorParaCalcTotPremio(taxaJuros.doubleValue(), numeroMeses);
		BigDecimal valorTotalPremio = this.getValorTotalPremio(objDTO.getValorSegurado(),valorParaCalcTotPremio,produtoEscolhido.getValorMinimoPremio());
		
		log.info("Idade Calculada: "+idade);				
		log.warn("ProdutoEscolhido: "+repositoryProduto.findProdutoByIdade(idade).toString());
		log.warn("Taxa de Juros: "+taxaJuros);
		log.warn("Numero Meses: "+numeroMeses);
		log.warn("Valor Para Calc Tot Premio: "+valorParaCalcTotPremio);
		log.warn("Valor Total Premio "+ valorTotalPremio);
		
		Simulacao simulacao = new Simulacao(null,
											objDTO.getNomePessoa(), 
											objDTO.getCpf(), 
											objDTO.getValorSegurado(), 
											objDTO.getNumeroContratoEmprestimo(), 
											objDTO.getFimContratoEmprestimo(), 
											objDTO.getDataNascimento(), 
											produtoEscolhido, 
											dataSimulacao, 
											valorTotalPremio);
		repository.save(simulacao);
		return new SimulacaoDTO(simulacao);
	}
	
	protected Integer getNumeroMes(Date dataSimulacao, Date fimContratoEmprestimo) {
		log.warn("Data Atual: "+dataSimulacao);
		log.warn("Data Fim Contrato: "+fimContratoEmprestimo);
		Long diferencaMeses = (fimContratoEmprestimo.getTime()-dataSimulacao.getTime()) / (1000*60*60*24) / 30;
	    return  Integer.valueOf(diferencaMeses.toString());
	}
	
	protected Double getValorParaCalcTotPremio(Double taxaJuros, Integer numeroMeses) {
		log.warn("Funcao taxaJuros: "+taxaJuros);
		log.warn("Funcao numeroMeses: "+numeroMeses);
		log.warn("Funcao valorParaCalcTotPremio: "+((taxaJuros/1000) * numeroMeses));
		return (taxaJuros/1000 * numeroMeses);
	}
	
	protected BigDecimal getValorTotalPremio(BigDecimal valorSegurado, Double valorParaCalcTotPremio, BigDecimal valorMinimoPremio) {
		BigDecimal retValor = valorSegurado.multiply(BigDecimal.valueOf(valorParaCalcTotPremio));
		log.warn(" Valor total Premio: "+retValor);
		if(retValor.compareTo(valorMinimoPremio)==1) {
			log.warn(" Valor total Premio: "+retValor);
			return retValor;
		}else {
			log.warn(" Valor Total Premio minimo: "+valorMinimoPremio);
			return valorMinimoPremio;
		}
	}
	
	@SuppressWarnings("deprecation")
	protected Integer idadeCalculada(Date dataNascimento) {
		log.info("Data nascimento "+ dataNascimento);
		Date dataAtual = new Date();
		log.info("Data Atual "+ dataAtual);
		return dataAtual.getYear() - dataNascimento.getYear();
	}

	@Override
	public List<SimulacaoDTO> findAllSimulacao() {
		List<Simulacao> list = repository.findAll();
		List<SimulacaoDTO> dto_list = new ArrayList<SimulacaoDTO>();
		list.forEach(i -> dto_list.add(new SimulacaoDTO(i)));
		return dto_list;
	}
	
}
