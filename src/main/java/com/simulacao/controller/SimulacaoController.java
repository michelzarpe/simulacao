package com.simulacao.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simulacao.dto.SimulacaoDTO;
import com.simulacao.service.SimulacaoService;
import com.simulacao.util.Response;


@RestController
@RequestMapping("/simulacao")
public class SimulacaoController {

	
	@Autowired
	public SimulacaoService service;
	
	
	@PostMapping
	public ResponseEntity<Response<SimulacaoDTO>> create(@Valid @RequestBody SimulacaoDTO objDTO, BindingResult result){
		Response<SimulacaoDTO> response = new Response<SimulacaoDTO>();
		if(result.hasErrors()) {
			result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		SimulacaoDTO objSaveDTO = service.gerarSimulacaoSalvar(objDTO);
		response.setData(objSaveDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
}


