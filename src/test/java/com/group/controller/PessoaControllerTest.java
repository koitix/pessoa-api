package com.group.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.group.model.Pessoa;

@SpringBootTest
@AutoConfigureMockMvc
public class PessoaControllerTest {

	@Autowired
	PessoaController pessoaController;
	
	@Autowired
	org.springframework.test.web.servlet.MockMvc MockMvc;
	
	@Test
	void buscarPessoa() {
		assertThat(pessoaController).isNotNull();		
		assertThat(pessoaController.buscarPessoa(1L).ok());
	}
	
	@Test
	void listarPorNome() {
		assertThat(pessoaController).isNotNull();	
		Pessoa pessoa = pessoaController.buscarPorNome("Haku1");
		assertThat(pessoa.getNome() == "Haku1");
	}
	
	@Test
	void listarPorCPF() {
		assertThat(pessoaController).isNotNull();	
		Pessoa pessoa = pessoaController.buscarPorCPF("78375784060");
		assertThat(pessoa.getCpf() == "78375784060");
	}
	
	@Test
	void validaData()
	{
		Pessoa pessoa = pessoaController.buscarPorNome("Haku1");
		Date date = new Date();
		
		assertThat(pessoa.getData_nascimento().before(date));
		
		Pessoa pessoa2 = pessoaController.buscarPorCPF("78375784060");
		
		assertThat(pessoa2.getData_nascimento().before(date));
	}
	
}
