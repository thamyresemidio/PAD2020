package br.unisantos.livres.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionId;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "tb_usuario")
public class Usuario extends AbstractEntity {
	private static final long serialVersionUID = 1L;
		
	@Column(name = "nm_nome", length = 60, nullable = false)
	private String nome;
	
	@Column(name = "nm_email", length = 80, unique = true, nullable = false)
	private String email;
	
	@Column(name = "nm_senha")
	private String senha;
	
	@Column(name = "st_ativo")
	private Boolean ativo;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "tb_perfil")
	private Set<Integer> perfis = new HashSet<Integer>();
	
	public Set<TipoPerfil> getPerfil(){
		return perfis.stream()
				.map(x -> TipoPerfil.toEnum(x))
				.collect(Collectors.toSet());
	}
	
	public void addPerfil(TipoPerfil perfil) {
		this.perfis.add(perfil.getCod());
	}
	
	public Usuario() { }
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return email;
	}
	
	@JsonIgnore
	public String getSenha() {
		return senha;
	}

	@JsonProperty
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}		

}
