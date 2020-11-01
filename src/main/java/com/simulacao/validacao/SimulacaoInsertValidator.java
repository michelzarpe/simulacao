package com.simulacao.validacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.simulacao.controller.exception.FieldMessage;
import com.simulacao.dto.SimulacaoDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimulacaoInsertValidator implements ConstraintValidator<SimulacaoInsert, SimulacaoDTO> {

	@Override
	public void initialize(SimulacaoInsert constraintAnnotation) {

	}

	
	@Override
	public boolean isValid(SimulacaoDTO value, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (value.getFimContratoEmprestimo().getTime() < (new Date().getTime())) {
			list.add(new FieldMessage("FimContratoEmprestimo", "Confira a data de fim contrato emrpestimo, pois está menos de um mes"));
		}
		
		if (this.getNumeroMes(new Date(),value.getFimContratoEmprestimo())>120) {
			list.add(new FieldMessage("FimContratoEmprestimo", "Passou de 120 meses"));
		}


		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessege()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}

		return list.isEmpty();
	}

	protected Integer getNumeroMes(Date dataSimulacao, Date fimContratoEmprestimo) {
		log.warn("Data Atual: "+dataSimulacao);
		log.warn("Data Fim Contrato: "+fimContratoEmprestimo);
		log.warn("dataSimulacao.getTime() - fimContratoEmprestimo.getTime() "+(dataSimulacao.getTime() - fimContratoEmprestimo.getTime()));
		log.warn("Data simulacao "+dataSimulacao.getTime());
		log.warn("Fim Contrato"+fimContratoEmprestimo.getTime());
		
	    Long diferencaMeses = (fimContratoEmprestimo.getTime()-dataSimulacao.getTime()) / (1000*60*60*24) / 30;
	    return  Integer.valueOf(diferencaMeses.toString());
	}
	
	
}
