package com.group.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Contato")
public class Contato {
	@Id // Id Annotation
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AutoIncrement
	private Long id;
	

	 @ManyToOne(fetch = FetchType.LAZY, optional = true)
	 @JoinColumn(name="id_pessoa")
	 @OnDelete(action = OnDeleteAction.CASCADE)
//	 @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	 @JsonIgnore
	private Pessoa pessoa;
	 
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String telefone;
	
	@Column(nullable = false)
	private String email;

	public Long getid() {
		return id;
	}

	public void setid(Long id) {
		this.id = id;
	}

	@NotEmpty(message="Campo Nome do Contato não pode ser vazio")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@NotEmpty(message="Campo Telefone do Contato não pode ser vazio")
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@NotEmpty(message="Campo Email do Contato não pode ser vazio")
	@Email
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contato other = (Contato) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	
	
	
}
