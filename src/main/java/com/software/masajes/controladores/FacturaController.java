package com.software.masajes.controladores;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.software.masajes.model.Factura;

import com.software.masajes.repository.FacturaRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class FacturaController {

	
	@Autowired
	private FacturaRepository facturaRepository;
	
	
	@GetMapping("/factura/{id}")
	public ResponseEntity<Factura> getAllFactura(@PathVariable("id") long id) {
		Optional<Factura> factura = facturaRepository.findById(id);
		if (factura.isPresent()) {
			return new ResponseEntity<>(factura.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/factura")
	public ResponseEntity<String> crearFactura() {
		try {

			Factura factura = new Factura();
			
            facturaRepository.save(factura);

			return new ResponseEntity<>(factura.toString(), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	
	

}
