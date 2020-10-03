package br.unisantos.livres.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.unisantos.livres.model.Usuario;
import br.unisantos.livres.repository.UsuarioRepository;
import br.unisantos.livres.security.UserDetailsimlp;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Usuario usuario = repo.findByLogin(username);
		if (usuario == null)
			throw new UsernameNotFoundException(username);
		return new UserDetailsimlp(usuario.getId(), usuario.getLogin(), usuario.getSenha(), usuario.getPerfil());
	}

}
