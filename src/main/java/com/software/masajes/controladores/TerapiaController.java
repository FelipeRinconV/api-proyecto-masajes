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

import com.software.masajes.model.Cita;
import com.software.masajes.model.Terapia;
import com.software.masajes.repository.CitaRepository;
import com.software.masajes.repository.ClienteRepository;
import com.software.masajes.repository.FacturaRepository;
import com.software.masajes.repository.SecretarioRepository;
import com.software.masajes.repository.TerapeutaRepository;
import com.software.masajes.repository.TerapiaRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class TerapiaController {
	
	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	SecretarioRepository secreRepository;
	
	@Autowired
	CitaRepository citaRepository;
	
	@Autowired
	TerapiaRepository terapiaRepository;
	
	@Autowired
	TerapeutaRepository terapeutaRepository;
	
	@Autowired
	FacturaRepository facturaRepository;
	
	@GetMapping("/terapias")
	public ResponseEntity<List<Terapia>> getAllTerapias() {
		try {

			List<Terapia> terapias = terapiaRepository.findAll();

			if (terapias.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(terapias, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/terapia/{id}")
	public ResponseEntity<Terapia> getTerapiaById(@PathVariable("id") long id) {
		Optional<Terapia> tutorialData = terapiaRepository.findById(id);
		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/terapias/{id}")
	public ResponseEntity<HttpStatus> deleteTerapia(@PathVariable("id") long id) {
		try {
			terapiaRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
