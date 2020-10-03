package br.unisantos.livres.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unisantos.livres.model.Produtor;
import br.unisantos.livres.service.ProdutorService;

@RestController
@RequestMapping("/produtores")
public class ProdutorResource implements ResourceInterface<Produtor> {

	@Autowired
	private ProdutorService service;
	
	@Override
	@GetMapping
	public ResponseEntity<List<Produtor>> get() {		
		return ResponseEntity.ok(service.findAll());
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) {
		Produtor _produtor = service.findById(id);
		if (_produtor != null) {
			return ResponseEntity.ok(_produtor);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produtor>> getByNome(@PathVariable("nome") String nome){
		return ResponseEntity.ok(service.findByNome(nome));
	}
	
	@GetMapping("/produto/nome/{nome}")
	public ResponseEntity<List<Produtor>> getByNomeProduto(@PathVariable("nome") String produto){
		return ResponseEntity.ok(service.findByNomeProduto(produto));
	}

	@GetMapping("/produto/id/{id}")
	public ResponseEntity<List<Produtor>> getByIdProduto(@PathVariable("id") Long produtoId){
		return ResponseEntity.ok(service.findByIdProduto(produtoId));
	}

	@GetMapping("/categoria/nome/{nome}")
	public ResponseEntity<List<Produtor>> getByNomeCategoriaProduto(@PathVariable("nome") String categoria){
		return ResponseEntity.ok(service.findByNomeCategoriaProduto(categoria));
	}

	@GetMapping("/categoria/id/{id}")
	public ResponseEntity<List<Produtor>> getByIdCategoriaProduto(@PathVariable("id") Long categoriaId){
		return ResponseEntity.ok(service.findByIdCategoriaProduto(categoriaId));
	}

	@Override
	@PostMapping
	public ResponseEntity<Produtor> post(@RequestBody Produtor obj) {		
		return ResponseEntity.ok(service.create(obj));
	}

	@Override
	@PutMapping
	public ResponseEntity<?> put(@RequestBody Produtor obj) {
		if (service.update(obj)) {
			return ResponseEntity.ok(obj);
		}		
		return ResponseEntity.notFound().build();
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		if (service.delete(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
