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
	public ResponseEntity<?> create(@Valid @RequestBody SimulacaoDTO objDTO){
		Response<SimulacaoDTO> response = new Response<SimulacaoDTO>();
		SimulacaoDTO objSaveDTO = service.gerarSimulacaoSalvar(objDTO);
		response.setData(objSaveDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping()
	public ResponseEntity<Response<List<SimulacaoDTO>>> getSimulation(){
		Response<List<SimulacaoDTO>> response = new Response<List<SimulacaoDTO>>();
		response.setData(service.findAllSimulacao());
		return ResponseEntity.ok().body(response);	
	}
	
	
}


