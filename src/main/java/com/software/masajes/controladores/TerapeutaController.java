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

import com.software.masajes.dto.ClientDto;
import com.software.masajes.dto.TerapeutaDto;
import com.software.masajes.model.Cliente;
import com.software.masajes.model.Secretario;
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

	@PutMapping("/terapeuta/{id}")
	public ResponseEntity<String> updateCliente(@PathVariable("id") long id, @RequestBody TerapeutaDto terapeuta) {
		Optional<Terapeuta> terapeutaData = terapeutaRepository.findById(id);

		if (terapeutaData.isPresent()) {
			Terapeuta terapeutaActualizado = terapeutaData.get();
			
			terapeutaActualizado.setNombre(terapeuta.getNombre());
			terapeutaActualizado.setClave(terapeuta.getClave());
			terapeutaActualizado.setCedula(terapeuta.getCedula());
			terapeutaActualizado.setDireccion(terapeuta.getDireccion());
			terapeutaActualizado.setEmail(terapeuta.getEmail());
			terapeutaActualizado.setProfesion(terapeuta.getProfesion());

			terapeutaRepository.save(terapeutaActualizado);

			return new ResponseEntity<>("Terapeuta actualizado",HttpStatus.OK);
		} else {
			return new ResponseEntity<>("El terepeuta no se encuentra registrado ",HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/terapeutas")
	public ResponseEntity<String> creatTerapeuta(@RequestBody TerapeutaDto terapeuta) {
		try {

			Terapeuta terapeutaNuevo = new Terapeuta();

			terapeutaNuevo.setNombre(terapeuta.getNombre());
			terapeutaNuevo.setClave(terapeuta.getClave());
			terapeutaNuevo.setCedula(terapeuta.getCedula());
			terapeutaNuevo.setDireccion(terapeuta.getDireccion());
			terapeutaNuevo.setEmail(terapeuta.getEmail());
			terapeutaNuevo.setProfesion(terapeuta.getProfesion());

			terapeutaRepository.save(terapeutaNuevo);

			return new ResponseEntity<>("Terapeuta creado exitosamente", HttpStatus.CREATED);
		} catch (Exception e) {

			return new ResponseEntity<>("Error " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
