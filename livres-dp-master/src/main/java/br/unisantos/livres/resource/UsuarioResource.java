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

import br.unisantos.livres.model.Usuario;
import br.unisantos.livres.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource implements ResourceInterface<Usuario> {

	@Autowired
	private UsuarioService service;

	@Override
	@GetMapping
	public ResponseEntity<List<Usuario>> get() {
		return ResponseEntity.ok(service.findAll());
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) {
		Usuario _usuario = service.findById(id);
		if (_usuario != null) {
			return ResponseEntity.ok(_usuario);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@GetMapping("/ativos")
	public ResponseEntity<List<Usuario>> getAllAtivos() {
		return ResponseEntity.ok(service.findAllAtivos());
	}

	@Override
	@PostMapping
	public ResponseEntity<Usuario> post(@RequestBody Usuario obj) {
		try {
			return ResponseEntity.ok(service.create(obj));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@Override
	@PutMapping
	public ResponseEntity<?> put(@RequestBody Usuario obj) {
		try {
			if (service.update(obj)) {
				return ResponseEntity.ok(obj);
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		if (service.delete(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
