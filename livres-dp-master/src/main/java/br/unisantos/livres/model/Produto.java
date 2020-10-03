package br.unisantos.livres.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_produto")
public class Produto extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Column(name = "nm_nome", length = 50, unique = true, nullable = false)
	private String nome;

	@ManyToOne
	@JoinColumn(name = "fk_categoria_id")
	private Categoria categoria;

	@Column(name = "nm_unidade", length = 20)
	private String unidade;

	@Column(name = "vl_preco")
	private BigDecimal preco;

	@Column(name = "vl_preco_mercado")
	private BigDecimal precoMercado;

	@Column(name = "vl_preco_lojinha")
	private BigDecimal precoLojinha;

	public Produto() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public BigDecimal getPrecoMercado() {
		return precoMercado;
	}

	public void setPrecoMercado(BigDecimal precoMercado) {
		this.precoMercado = precoMercado;
	}

	public BigDecimal getPrecoLojinha() {
		return precoLojinha;
	}

	public void setPrecoLojinha(BigDecimal precoLojinha) {
		this.precoLojinha = precoLojinha;
	}

}
