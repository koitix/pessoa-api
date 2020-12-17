package com.group.repositorio;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.group.model.Contato;

@Repository
public interface ContatoRepositorio extends  JpaRepository<Contato, Long>{ 

    @Modifying
    @Transactional
    @Query("DELETE FROM Contato contato WHERE contato.pessoa.id = ?1")
    void deletarPorIdPessoa(Long pessoaId);
    
}
