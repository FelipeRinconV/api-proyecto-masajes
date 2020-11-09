package com.software.masajes.controladores;

import java.util.Date;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.software.masajes.dto.CitaDto;
import com.software.masajes.dto.ClientDto;
import com.software.masajes.model.Cita;
import com.software.masajes.model.Cliente;
import com.software.masajes.model.Factura;
import com.software.masajes.model.Secretario;
import com.software.masajes.model.Terapeuta;
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
public class CitaController {
	
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
	
	@GetMapping("/citas")
	public ResponseEntity<List<Cita>> getAllCitas() {
		try {

			List<Cita> citas = citaRepository.findAll();

			if (citas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(citas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/cita/{id}")
	public ResponseEntity<Cita> getCById(@PathVariable("id") long id) {
		Optional<Cita> tutorialData = citaRepository.findById(id);
		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/cita/{id}")
	public ResponseEntity<Cita> createCita(@RequestBody CitaDto cita) {
		try {

			Cita citaNuevo = new Cita();

			Optional<Secretario> secre2 = secreRepository.findById((long) cita.getIdSecretario());
			Secretario secretario = (Secretario) secre2.get();
			
			Optional<Terapia> terapia2 = terapiaRepository.findById((long) cita.getIdTerapia());
			Terapia terapia = (Terapia) terapia2.get();
			
			Optional<Terapeuta> terapeuta2 = terapeutaRepository.findById((long) cita.getIdTerapeuta());
			Terapeuta terapeuta = (Terapeuta) terapeuta2.get();
			
			Optional<Factura> factura2 = facturaRepository.findById((long) cita.getIdFactura());
			Factura factura = (Factura) factura2.get();
			
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	        Date fechaInicio = formato.parse(cita.getFechaInicio());;
	        Date fechaFinal = formato.parse(cita.getFechaFinal());
	        
			citaNuevo.setFactura(factura);
			citaNuevo.setId((long) cita.getId());
			citaNuevo.setSecretario(secretario);
			citaNuevo.setTerapeuta(terapeuta);
			citaNuevo.setTerapia(terapia);
			citaNuevo.setFechaInicio(fechaInicio);
			citaNuevo.setFechaFinal(fechaFinal);

			Cita _tutorial = citaRepository.save(citaNuevo);

			return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/citas")
	public ResponseEntity<String> updateCita(@RequestBody CitaDto cita) throws ParseException {
		Optional<Cita> citaData = citaRepository.findById((long) cita.getId());

		if (citaData.isPresent()) {
			Cita citaActualizado = citaData.get();

			Optional<Secretario> secre2 = secreRepository.findById((long) cita.getIdSecretario());
			Secretario secretario = (Secretario) secre2.get();
			
			Optional<Terapia> terapia2 = terapiaRepository.findById((long) cita.getIdTerapia());
			Terapia terapia = (Terapia) terapia2.get();
			
			Optional<Terapeuta> terapeuta2 = terapeutaRepository.findById((long) cita.getIdTerapeuta());
			Terapeuta terapeuta = (Terapeuta) terapeuta2.get();
			
			Optional<Factura> factura2 = facturaRepository.findById((long) cita.getIdFactura());
			Factura factura = (Factura) factura2.get();
			
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	        Date fechaInicio = formato.parse(cita.getFechaInicio());;
	        Date fechaFinal = formato.parse(cita.getFechaFinal());
	        
			citaActualizado.setFactura(factura);
			citaActualizado.setId((long) cita.getId());
			citaActualizado.setSecretario(secretario);
			citaActualizado.setTerapeuta(terapeuta);
			citaActualizado.setTerapia(terapia);
			citaActualizado.setFechaInicio(fechaInicio);
			citaActualizado.setFechaFinal(fechaFinal);

			citaRepository.save(citaActualizado);

			return new ResponseEntity<>("Cita actualizada", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("La cita no se encuentra en el sistema", HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/citas/{id}")
	public ResponseEntity<HttpStatus> deleteCita(@PathVariable("id") long id) {
		try {
			citaRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
