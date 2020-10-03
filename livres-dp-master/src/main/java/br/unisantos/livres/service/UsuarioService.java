package br.unisantos.livres.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.unisantos.livres.model.Usuario;
import br.unisantos.livres.repository.UsuarioRepository;

@Service
public class UsuarioService implements ServiceInterface<Usuario> {

	@Autowired
	private UsuarioRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Usuario create(Usuario obj) {
		
		obj.setSenha(passwordEncoder.encode(obj.getSenha()));
		
		if (this.ValidaEmail(obj.getEmail()))
			repo.save(obj);
		
		return obj;
	}

	@Override
	public Usuario findById(Long id) {
		Optional<Usuario> _usuario = repo.findById(id);
		return _usuario.orElse(null);
	}

	@Override
	public List<Usuario> findAll() {
		return repo.findByOrderByNome();
	}
	
	public List<Usuario> findAllAtivos() {
		return repo.findByAtivoOrderByNome(true);
	}
		

	@Override
	public boolean update(Usuario obj) {
		if (repo.existsById(obj.getId()) && this.ValidaEmail(obj.getEmail())) {
			repo.save(obj);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Long id) {
		if (repo.existsById(id)) {
			repo.deleteById(id);
			return true;
		}
		return false;
	}
	
	public boolean ValidaEmail(String email) {
		List<Usuario> usuario = repo.findByEmail(email);
		if (usuario.isEmpty())
			return true;
		return false;
	}

}
