package br.unisantos.livres.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisantos.livres.model.Categoria;
import br.unisantos.livres.repository.CategoriaRepository;

@Service
public class CategoriaService implements ServiceInterface<Categoria> {
	
	@Autowired
	private CategoriaRepository repo;

	@Override
	public Categoria create(Categoria obj) {
		repo.save(obj);
		return obj;
	}

	@Override
	public Categoria findById(Long id) {
		Optional<Categoria> _categoria = repo.findById(id);
		return _categoria.orElse(null);
	}

	@Override
	public List<Categoria> findAll() {
		return repo.findByOrderByNome();
	}

	@Override
	public boolean update(Categoria obj) {
		if (repo.existsById(obj.getId())) {
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

}
