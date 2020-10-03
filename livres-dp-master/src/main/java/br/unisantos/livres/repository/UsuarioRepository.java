package br.unisantos.livres.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.unisantos.livres.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	List<Usuario> findByOrderByNome();
	
	List<Usuario> findByAtivoOrderByNome(Boolean ativo);

	List<Usuario> findByEmail(String email);
	
	Usuario findByLogin(String login);
		
}
