package com.group.controller;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.group.model.Pessoa;
import com.group.repositorio.ContatoRepositorio;
import com.group.repositorio.PessoaRepositorio;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	private PessoaRepositorio pessoaRepositorio;
	
	@Autowired
	private ContatoRepositorio contatoRepositorio;
	

	
	@GetMapping
	public 	Page<Pessoa> ListarPessoas(Pageable pageable) {
		return pessoaRepositorio.findAll(pageable);
	}
	
	@GetMapping(value="/listar-nome")
	public Page<Pessoa> listarPorNome(Pageable pageable,String nome) {
		return pessoaRepositorio.buscarPorNome(pageable,nome);
	}
	
	@GetMapping(value="/listar-cpf")
	public Page<Pessoa> listaPorCPF(Pageable pageable,String cpf) {
		return pessoaRepositorio.buscarPorCPF(pageable, cpf);
	}
			
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Pessoa> adicionarPessoa(@Validated @RequestBody Pessoa pessoa) {
		
		if( pessoa.getContato().size() ==0) {
			return ResponseEntity.notFound().build();
		}
		 pessoaRepositorio.save(pessoa);
		return ResponseEntity.ok(pessoa);
	}
		
	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> alterarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
		Pessoa pessoaExistente = pessoaRepositorio.findById(id).get();
		if(pessoaExistente == null ) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(pessoa, pessoaExistente,"id");
		
		pessoaExistente = pessoaRepositorio.save(pessoaExistente);
		
		return ResponseEntity.ok(pessoaExistente);
	}
	
	@DeleteMapping(path= "/{id}")
	public ResponseEntity<Void> deletarPessoa(@PathVariable(name="id") Long id ){
		Pessoa pessoaExistente = pessoaRepositorio.findById(id).get();
		
        deleteTransaction(pessoaExistente);

        return ResponseEntity.noContent().build();
    }
 
  @Transactional
    public void deleteTransaction(Pessoa pessoa) {
        contatoRepositorio.deletarPorIdPessoa(pessoa.getId());
        pessoaRepositorio.delete(pessoa);
    }
 
	
	@GetMapping(path= "/{id}")
	public ResponseEntity<Pessoa> buscarPessoa(@PathVariable(name="id") Long id) {
		Pessoa pessoaExistente = pessoaRepositorio.findById(id).get();
		if(pessoaExistente == null ) {
			return ResponseEntity.notFound().build();
		}
		pessoaExistente = pessoaRepositorio.save(pessoaExistente);
		
		return ResponseEntity.ok(pessoaExistente);
	}
	
}
