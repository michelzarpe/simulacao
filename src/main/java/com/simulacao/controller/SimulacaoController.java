package com.simulacao.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simulacao.dto.SimulacaoDTO;
import com.simulacao.service.SimulacaoService;
import com.simulacao.util.Response;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/simulacao")
public class SimulacaoController {

	@Autowired
	public SimulacaoService service;
	
	@PostMapping
	public ResponseEntity<Response<SimulacaoDTO>> create(@Valid @RequestBody SimulacaoDTO objDTO, BindingResult result){
		Response<SimulacaoDTO> response = new Response<SimulacaoDTO>();
		log.info("0 Chegamos até aqui..... "+objDTO.toString());
		if(result.hasErrors()) {
			result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		SimulacaoDTO objSaveDTO = service.gerarSimulacaoSalvar(objDTO);
		log.info("1 Chegamos até aqui..... "+objSaveDTO.toString());
		response.setData(objSaveDTO);
		log.info("2 Chegamos até aqui..... "+objSaveDTO.toString());
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping()
	public ResponseEntity<Response<List<SimulacaoDTO>>> getSimulation(){
		Response<List<SimulacaoDTO>> response = new Response<List<SimulacaoDTO>>();
		response.setData(service.findAllSimulacao());
		return ResponseEntity.ok().body(response);	
	}
	
	
}


