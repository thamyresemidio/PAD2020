package br.unisantos.livres.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "tb_produtor")
public class Produtor extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "nm_nome", length = 80, nullable = false)
	private String nome;
	
	@Column(name = "nm_cpf", length = 14)
	private String cpf;
	
	@Column(name = "nm_email", length = 80)
	private String email;
	
	@Column(name = "nm_telefone", length = 16)
	private String telefone;
	
	@ManyToMany
	@JoinTable(name = "tb_produtor_produto", 
		joinColumns = @JoinColumn(name = "fk_produtor_id"), 
		inverseJoinColumns = @JoinColumn(name = "fk_produto_id"))
	private List<Produto> produtos; 
	
	public Produtor() {}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@JsonIgnore
	public List<Produto> getProdutos() {
		return produtos;
	}

	@JsonProperty
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
		
}
