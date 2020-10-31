package com.simulacao;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.simulacao.modal.Produto;
import com.simulacao.repository.ProdutoRepository;

@SpringBootApplication
public class SimulacaoApplication implements CommandLineRunner {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	public static void main(String[] args) {
		SpringApplication.run(SimulacaoApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		Produto p1 = new Produto(1L, "Produto 1", 18, 70, BigDecimal.valueOf(0.450), BigDecimal.valueOf(5.0));
		Produto p2 = new Produto(2L, "Produto 2", 71, 75, BigDecimal.valueOf(1.8472), BigDecimal.valueOf(5.0));
		produtoRepository.saveAll(Arrays.asList(p1,p2));
	}
	 @PostConstruct
	    public void init(){
	 	      TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"));
	    }

}



