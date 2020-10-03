package br.unisantos.livres.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisantos.livres.model.Produto;
import br.unisantos.livres.repository.ProdutoRepository;

@Service
public class ProdutoService implements ServiceInterface<Produto> {
	
	@Autowired
	private ProdutoRepository repo;

	@Override
	public Produto create(Produto obj) {
		repo.save(obj);
		return obj;
	}

	@Override
	public Produto findById(Long id) {
		Optional<Produto> _produto = repo.findById(id);
		return _produto.orElse(null);
	}

	@Override
	public List<Produto> findAll() {
		return repo.findByOrderByNome();
	}

	public List<Produto> findByOrderByPreco() {
		return repo.findByOrderByPreco();
	}
	
	public List<Produto> findByOrderByPrecoDesc() {
		return repo.findByOrderByPrecoDesc();
	}
	
	public List<Produto> findByNomeContainingOrderByNome(String nome) {
		return repo.findByNomeContainingOrderByNome(nome);
	}
		
	public List<Produto> findByProdutor(Long produtorId) {
		return repo.findByProdutor(produtorId);
	}
		
	public List<Produto> findByCategoria(Long categoriaId) {
		return repo.findByCategoria(categoriaId);
	}
	
	public List<Produto> findByPrecoGreaterThanEqualAndPrecoLessThanEqual(BigDecimal from, BigDecimal to) {
		return repo.findByPrecoGreaterThanEqualAndPrecoLessThanEqualOrderByPreco(from, to);
	}

	public List<Produto> findByPrecoLojinhaGreaterThanEqualAndPrecoLojinhaLessThanEqual(BigDecimal from, BigDecimal to) {
		return repo.findByPrecoLojinhaGreaterThanEqualAndPrecoLojinhaLessThanEqualOrderByPrecoLojinha(from, to);
	}

	public List<Produto> findByPrecoMercadoGreaterThanEqualAndPrecoMercadoLessThanEqual(BigDecimal from, BigDecimal to) {
		return repo.findByPrecoMercadoGreaterThanEqualAndPrecoMercadoLessThanEqualOrderByPrecoMercado(from, to);
	}

	@Override
	public boolean update(Produto obj) {
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
