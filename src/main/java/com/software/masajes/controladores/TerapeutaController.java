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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.software.masajes.model.Cliente;
import com.software.masajes.model.Terapeuta;
import com.software.masajes.repository.ClienteRepository;
import com.software.masajes.repository.TerapeutaRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class TerapeutaController {
	
	@Autowired
	TerapeutaRepository terapeutaRepository;
	
	@GetMapping("/terapeutas")
	public ResponseEntity<List<Terapeuta>> getAllTerapeutas() {
		try {
			List<Terapeuta> terapeutas = terapeutaRepository.findAll();

			if (terapeutas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(terapeutas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/terapeutas/{id}")
	public ResponseEntity<Terapeuta> getTerapeutaById(@PathVariable("id") long id) {
		Optional<Terapeuta> tutorialData = terapeutaRepository.findById(id);
		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/terapeutas/{id}")
	public ResponseEntity<HttpStatus> deleteTerapeuta(@PathVariable("id") long id) {
		try {
			terapeutaRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
