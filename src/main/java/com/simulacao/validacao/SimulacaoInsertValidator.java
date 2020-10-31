package com.simulacao.validacao;

import java.util.ArrayList;
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

		log.error("PAssei Por aqui < > <> < > < > <  ");
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessege()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}

		return list.isEmpty();
	}

}
