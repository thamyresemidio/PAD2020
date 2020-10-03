package br.unisantos.livres.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisantos.livres.model.Produtor;
import br.unisantos.livres.repository.ProdutorRepository;

@Service
public class ProdutorService implements ServiceInterface<Produtor> {
	
	@Autowired
	private ProdutorRepository repo;

	@Override
	public Produtor create(Produtor obj) {
		repo.save(obj);
		return obj;
	}

	@Override
	public Produtor findById(Long id) {
		Optional<Produtor> _produtor = repo.findById(id);
		return _produtor.orElse(null);
	}

	@Override
	public List<Produtor> findAll() {
		return repo.findByOrderByNome();
	}

	public List<Produtor> findByNome(String nome) {
		return repo.findByNomeContainingOrderByNome(nome);
	}
	
	public List<Produtor> findByNomeProduto(String produto) {
		List<Produtor> _produtores = repo.findByNomeProduto(produto);
		_produtores = _produtores.stream()
			     .distinct()
			     .collect(Collectors.toList());
		return _produtores;
	}
	
	public List<Produtor> findByIdProduto(Long produtoId) {
		List<Produtor> _produtores = repo.findByIdProduto(produtoId);
		_produtores = _produtores.stream()
			     .distinct()
			     .collect(Collectors.toList());
		return _produtores;
	}
	
	public List<Produtor> findByNomeCategoriaProduto(String categoria) {
		List<Produtor> _produtores = repo.findByNomeCategoriaProduto(categoria);
		_produtores = _produtores.stream()
			     .distinct()
			     .collect(Collectors.toList());
		return _produtores;
	}

	public List<Produtor> findByIdCategoriaProduto(Long categoriaId) {
		List<Produtor> _produtores = repo.findByIdCategoriaProduto(categoriaId);
		_produtores = _produtores.stream()
			     .distinct()
			     .collect(Collectors.toList());
		return _produtores;
	}

	@Override
	public boolean update(Produtor obj) {
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
