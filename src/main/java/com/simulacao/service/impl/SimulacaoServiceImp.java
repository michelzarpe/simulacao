package com.simulacao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simulacao.dto.SimulacaoDTO;
import com.simulacao.repository.SimulacaoRepository;
import com.simulacao.service.SimulacaoService;

@Service
public class SimulacaoServiceImp implements SimulacaoService{

	@Autowired
	private SimulacaoRepository repository;
	
	@Override
	public SimulacaoDTO gerarSimulacaoSalvar(SimulacaoDTO objDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}


/*
 ModelMapper modelMapper = new ModelMapper();
modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);
*/


/*
 
 
 Os dados de entrada para uma nova simulação são: NOME DA PESSOA, CPF, VALOR
SEGURADO, NÚMERO CONTRATO EMPRÉSTIMO, DATA FIM DO CONTRATO DE
EMPRÉSTIMO E DATA NASCIMENTO.

Com os dados de entrada o Back-end deve simular a contratação de seguro prestamista e
gerar as informações:

VALOR TOTAL DO PRÊMIO: O valor do prêmio é calculado usando a fórmula
“valorSegurado * taxaJuros / 1000 * numeroMeses”. O valor em “taxaJuros” é encontrado na
entidade Produto, com base na idade da pessoa. O valor “numeroMeses” é encontrado pela
diferença em meses da data da simulação (Data atual) e Data fim do contrato de empréstimo.
DATA SIMULAÇÃO: Data atual
PRODUTO ESCOLHIDO: Produto utilizado para realizar a simulação do seguro
prestamista.

O Back-end deve gravar os dados da entidade Simulacao após uma simulação de seguro
prestamista com sucesso. Esses dados serão consultados posteriormente pelo Front-end.
 
 
 
 
 */