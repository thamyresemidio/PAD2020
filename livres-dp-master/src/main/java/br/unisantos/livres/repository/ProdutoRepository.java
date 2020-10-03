package br.unisantos.livres.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.unisantos.livres.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	List<Produto> findByOrderByNome();
	
	List<Produto> findByOrderByPreco();
	
	List<Produto> findByOrderByPrecoDesc();
	
	List<Produto> findByNomeContainingOrderByNome(String nome);
	
	
	@Query("select pr from Produtor p join p.produtos pr on p.id = ?1")
	List<Produto> findByProdutor(Long produtorId);
	
	
	@Query("select p from Produto p where p.categoria.id = ?1")
	List<Produto> findByCategoria(Long categoriaId);
	
	List<Produto> findByPrecoGreaterThanEqualAndPrecoLessThanEqualOrderByPreco(BigDecimal from, BigDecimal to);

	List<Produto> findByPrecoMercadoGreaterThanEqualAndPrecoMercadoLessThanEqualOrderByPrecoMercado(BigDecimal from, BigDecimal to);

	List<Produto> findByPrecoLojinhaGreaterThanEqualAndPrecoLojinhaLessThanEqualOrderByPrecoLojinha(BigDecimal from, BigDecimal to);
}
