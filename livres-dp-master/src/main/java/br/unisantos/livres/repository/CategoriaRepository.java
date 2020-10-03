package br.unisantos.livres.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.unisantos.livres.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	List<Categoria> findByOrderByNome();
}
