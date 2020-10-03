package br.unisantos.livres.model;

import org.dom4j.IllegalAddException;

public enum TipoPerfil {
	ADMIN(1, "ROLE_ADMIN"),
	USUARIO(2,"ROLE_USUARIO");
	
	private Integer cod;
	private String descricao;
	
	public Integer getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	private TipoPerfil(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public static TipoPerfil toEnum(Integer cod) {
		if (cod==null)
			return null;
		
		for (TipoPerfil x : TipoPerfil.values()) {
			if (cod.equals(x.getCod())) 
				return x;
		}
		throw new IllegalAddException("Código Inválido: " + cod);
	}
	
	
}
