package br.unisantos.livres.resource;

import java.math.BigDecimal;
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

import br.unisantos.livres.model.Produto;
import br.unisantos.livres.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource implements ResourceInterface<Produto> {

	@Autowired
	private ProdutoService service;

	@Override
	@GetMapping
	public ResponseEntity<List<Produto>> get() {
		return ResponseEntity.ok(service.findAll());
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) {
		Produto _produto = service.findById(id);
		if (_produto != null) {
			return ResponseEntity.ok(_produto);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/preco")
	public List<Produto> getByOrderByPreco() {
		return service.findByOrderByPreco();
	}
	
	@GetMapping("/preco_desc")
	public List<Produto> getByOrderByPrecoDesc() {
		return service.findByOrderByPrecoDesc();
	}
	
	@GetMapping("/nome/{nome}")
	public List<Produto> getByNomeContainingOrderByNome(@PathVariable("nome") String nome) {
		return service.findByNomeContainingOrderByNome(nome);
	}
	
	@GetMapping("/produtor/{id}")
	public List<Produto> getByProdutor(@PathVariable("id") Long produtorId) {
		return service.findByProdutor(produtorId);
	}
	
	@GetMapping("/categoria/{id}")
	public List<Produto> getByCategoria(@PathVariable("id") Long categoriaId) {
		return service.findByCategoria(categoriaId);
	}
	
	@GetMapping("/preco/{from}/{to}")
	public List<Produto> getByPrecoGreaterThanEqualAndPrecoLessThanEqual(@PathVariable("from") BigDecimal from, @PathVariable("to") BigDecimal to) {
		return service.findByPrecoGreaterThanEqualAndPrecoLessThanEqual(from, to);
	}

	@GetMapping("/preco_lojinha/{from}/{to}")
	public List<Produto> getByPrecoLojinhaGreaterThanEqualAndPrecoLojinhaLessThanEqual(@PathVariable("from") BigDecimal from, @PathVariable("to") BigDecimal to) {
		return service.findByPrecoLojinhaGreaterThanEqualAndPrecoLojinhaLessThanEqual(from, to);
	}

	@GetMapping("/preco_mercado/{from}/{to}")
	public List<Produto> getByPrecoMercadoGreaterThanEqualAndPrecoMercadoLessThanEqual(@PathVariable("from") BigDecimal from, @PathVariable("to") BigDecimal to) {
		return service.findByPrecoMercadoGreaterThanEqualAndPrecoMercadoLessThanEqual(from, to);
	}

	@Override
	@PostMapping
	public ResponseEntity<Produto> post(@RequestBody Produto obj) {
		try {
			return ResponseEntity.ok(service.create(obj));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@Override
	@PutMapping
	public ResponseEntity<?> put(@RequestBody Produto obj) {
		try {
			if (service.update(obj)) {
				return ResponseEntity.ok(obj);
			}
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {
			if (service.delete(id)) {
				return ResponseEntity.ok().build();
			}
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
