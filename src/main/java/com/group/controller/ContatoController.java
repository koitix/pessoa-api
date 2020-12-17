package com.group.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.group.model.Contato;
import com.group.model.Pessoa;
import com.group.repositorio.ContatoRepositorio;
import com.group.repositorio.PessoaRepositorio;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

	@Autowired
	private ContatoRepositorio contatoRepositorio;
	@Autowired
	private PessoaRepositorio pessoaRepositorio;
	
	 @PostMapping
		@ApiOperation(value="Adiciona novo Contato")
	    public ResponseEntity<Contato> create(@RequestBody @Valid Contato contato) {
	        Optional<Pessoa> pessoa = pessoaRepositorio.findById(contato.getPessoa().getId());
	        if (!pessoa.isPresent()) {
	            return ResponseEntity.unprocessableEntity().build();
	        }

	        contato.setPessoa(pessoa.get());

	        Contato contatoSalvo = contatoRepositorio.save(contato);
	        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
	            .buildAndExpand(contatoSalvo.getid()).toUri();

	        return ResponseEntity.created(location).body(contatoSalvo);
	    }
	
	 
	 @PutMapping("/{id}")
		@ApiOperation(value="Atualiza um Contato")
	    public ResponseEntity<Contato> update(@RequestBody @Valid Contato Contato, @PathVariable Long id) {
	        Optional<Pessoa> pessoa = pessoaRepositorio.findById(Contato.getPessoa().getId());
	        if (!pessoa.isPresent()) {
	            return ResponseEntity.unprocessableEntity().build();
	        }

	        Optional<Contato> contatoSalvo = contatoRepositorio.findById(id);
	        if (!contatoSalvo.isPresent()) {
	            return ResponseEntity.unprocessableEntity().build();
	        }

	        Contato.setPessoa(pessoa.get());
	        Contato.setid(contatoSalvo.get().getid());
	        contatoRepositorio.save(Contato);

	        return ResponseEntity.noContent().build();
	    }
	 
	 
	 @DeleteMapping("/{id}")
		@ApiOperation(value="Remove um Contato pelo id")
	    public ResponseEntity<Contato> delete(@PathVariable Long id) {
	        Optional<Contato> contato = contatoRepositorio.findById(id);
	        if (!contato.isPresent()) {
	            return ResponseEntity.unprocessableEntity().build();
	        }

	        contatoRepositorio.delete(contato.get());

	        return ResponseEntity.noContent().build();
	    }
	 
		@GetMapping
		@ApiOperation(value="Retorna lista geral de Contatos")
		public 	List<Contato> listarContatos() {
			return contatoRepositorio.findAll();
		}
	 
	 @GetMapping("/{id}")
		@ApiOperation(value="Retorna um contato pelo id")
	    public ResponseEntity<Contato> getById(@PathVariable Long id) {
	        Optional<Contato> contato = contatoRepositorio.findById(id);
	        if (!contato.isPresent()) {
	            return ResponseEntity.unprocessableEntity().build();
	        }

	        return ResponseEntity.ok(contato.get());
	    }
	 
	 
}
