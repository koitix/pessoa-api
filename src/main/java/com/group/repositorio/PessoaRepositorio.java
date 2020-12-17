package com.group.repositorio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.group.model.Pessoa;

@Repository
public interface PessoaRepositorio extends  PagingAndSortingRepository<Pessoa, Long>, JpaSpecificationExecutor<Pessoa>{
	
	@Query("select pessoa from Pessoa pessoa where nome = :nome")
	Page<Pessoa> buscarPorNome(Pageable pageable, @Param("nome") String nome);
	
	@Query("select pessoa from Pessoa pessoa where cpf = :cpf")
	Page<Pessoa> buscarPorCPF(Pageable pageable, @Param("cpf") String cpf);
	
}
