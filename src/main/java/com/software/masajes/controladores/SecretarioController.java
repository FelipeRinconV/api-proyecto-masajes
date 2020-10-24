package com.software.masajes.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.software.masajes.dto.SecretarioDto;
import com.software.masajes.model.Secretario;
import com.software.masajes.repository.SecretarioRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class SecretarioController {

	@Autowired
	SecretarioRepository secreRepository;

	@GetMapping("/secreatarios")
	public ResponseEntity<List<Secretario>> getAllSecretarios() {
		try {
			List<Secretario> secretarios = secreRepository.findAll();

			if (secretarios.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(secretarios, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/secretarios/{id}")
	public ResponseEntity<Secretario> getSecretariolById(@PathVariable("id") long id) {
		Optional<Secretario> tutorialData = secreRepository.findById(id);
		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/secretarios")
	public ResponseEntity<String> createSecretario(@RequestBody SecretarioDto secretario) {
		try {

			Secretario secre = new Secretario();
			secre.setCedula(secretario.getCedula());
			secre.setEmail(secretario.getEmail());
			secre.setNombre(secretario.getNombre());
			secre.setSueldo(secretario.getSueldo());
			secre.setClave(secretario.getClave());

			secreRepository.save(secre);

			return new ResponseEntity<>("Secretario creado satisfactoriamente", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage()+secretario.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/secretarios/{id}")
	public ResponseEntity<Secretario> updateSecretario(@PathVariable("id") long id,
			@RequestBody Secretario secretario) {
		Optional<Secretario> secretarioData = secreRepository.findById(id);

		if (secretarioData.isPresent()) {
			Secretario secre = secretarioData.get();
			secre.setCedula(secretario.getCedula());
			secre.setNombre(secretario.getNombre());
			secre.setSueldo(secretario.getSueldo());
			return new ResponseEntity<>(secreRepository.save(secre), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/secretarios/{id}")
	public ResponseEntity<HttpStatus> deleteSecretario(@PathVariable("id") long id) {
		try {
			secreRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
