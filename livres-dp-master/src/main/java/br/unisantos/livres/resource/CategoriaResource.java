package br.unisantos.livres.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unisantos.livres.model.Categoria;
import br.unisantos.livres.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource implements ResourceInterface<Categoria> {

	@Autowired
	private CategoriaService service;

	@Override
	@GetMapping
	public ResponseEntity<List<Categoria>> get() {
		return ResponseEntity.ok(service.findAll());
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) {
		Categoria _categoria = service.findById(id);
		if (_categoria != null) {
			return ResponseEntity.ok(_categoria);
		}
		return ResponseEntity.notFound().build();
	}

	@Override
	@PostMapping
	public ResponseEntity<Categoria> post(@RequestBody Categoria obj) {
		try {
			return ResponseEntity.ok(service.create(obj));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@Override
	@PutMapping
	public ResponseEntity<?> put(@RequestBody Categoria obj) {
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
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
