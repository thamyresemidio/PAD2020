package br.unisantos.livres.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_categoria")
public class Categoria extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "nm_nome", length = 40, unique = true, nullable = false)
	private String nome;
	
	public Categoria() {}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}		

}
