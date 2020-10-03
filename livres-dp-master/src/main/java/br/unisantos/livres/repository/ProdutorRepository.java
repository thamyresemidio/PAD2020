package br.unisantos.livres.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.unisantos.livres.model.Produtor;

@Repository
public interface ProdutorRepository extends JpaRepository<Produtor, Long> {

	List<Produtor> findByOrderByNome();
	
	List<Produtor> findByNomeContainingOrderByNome(String nome);
	
	@Query("select p from Produtor p join p.produtos pr where pr.nome like %?1% order by p.nome")
	List<Produtor> findByNomeProduto(String produto);
	
	@Query("select p from Produtor p join p.produtos pr where pr.id = ?1 order by p.nome")
	List<Produtor> findByIdProduto(Long produtoId);

	@Query("select p from Produtor p join p.produtos pr where pr.categoria.nome like %?1% order by p.nome")
	List<Produtor> findByNomeCategoriaProduto(String categoria);

	@Query("select p from Produtor p join p.produtos pr where pr.categoria.id = ?1 order by p.nome")
	List<Produtor> findByIdCategoriaProduto(Long id);

}
