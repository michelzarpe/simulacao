package com.simulacao.service;

import java.util.List;

import com.simulacao.dto.SimulacaoDTO;

public interface SimulacaoService {

	public SimulacaoDTO gerarSimulacaoSalvar(SimulacaoDTO objDTO);
	public List<SimulacaoDTO> findAllSimulacao();

}
