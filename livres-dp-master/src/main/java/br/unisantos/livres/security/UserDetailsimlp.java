package br.unisantos.livres.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.unisantos.livres.model.TipoPerfil;

public class UserDetailsimlp implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	private String login;
	private String senha;
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserDetailsimlp(){}
	
	public UserDetailsimlp(Long id, String login, String senha, Set<TipoPerfil> perfil){
		super();
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.authorities = perfil.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao()))
				 .collect(Collectors.toList());
	}
	
	public Long getId() { return id; }
	
	@Override
	 public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	 }
	 @Override
	 public String getPassword() { return senha; }
	 @Override
	 public String getUsername() { return login; }
	 @Override
	 public boolean isAccountNonExpired() { return true; }
	 @Override
	 public boolean isAccountNonLocked() { return true; }
	 @Override
	 public boolean isCredentialsNonExpired() { return true; }
	 @Override
	 public boolean isEnabled() { return true; }


}
