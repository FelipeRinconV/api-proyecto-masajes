package com.software.masajes.controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.software.masajes.model.Cliente;
import com.software.masajes.model.Secretario;
import com.software.masajes.repository.ClienteRepository;
import com.software.masajes.repository.SecretarioRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ClienteController {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	SecretarioRepository secreRepository;
	
	@GetMapping("/clientes")
	public ResponseEntity<List<Cliente>> getAllClientes() {
		try {
			List<Cliente> clientes = clienteRepository.findAll();

			if (clientes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(clientes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/clientes/{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable("id") long id) {
		Optional<Cliente> tutorialData = clienteRepository.findById(id);
		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/clientes")
	public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente, @PathVariable("id")
			long id) {
		try {

			Cliente clienteNuevo = new Cliente();
			
			Optional<Secretario> secre2 = secreRepository.findById(id); 
			Secretario secre = (Secretario) secre2.get();

			clienteNuevo.setId(cliente.getId());
			clienteNuevo.setNombre(cliente.getNombre());
			clienteNuevo.setSecretario(secre);
			clienteNuevo.setCedula(cliente.getCedula());
			clienteNuevo.setDireccion(cliente.getDireccion());
			clienteNuevo.setEmail(cliente.getEmail());
			clienteNuevo.setOcupacion(cliente.getOcupacion());
			clienteNuevo.setTelefono(cliente.getTelefono());
			cliente.setFechaNacimiento(cliente.getFechaNacimiento());

			Cliente _tutorial = clienteRepository.save(clienteNuevo);

			return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/clientes/{id}")
	public ResponseEntity<Cliente> updateCliente(@PathVariable("id") long id,
			@RequestBody Cliente cliente) {
		Optional<Cliente> clienteData = clienteRepository.findById(id);

		if (clienteData.isPresent()) {
			Cliente clienteActualizado = clienteData.get();
			
			clienteActualizado.setCedula();
			
			return new ResponseEntity<>(clienteRepository.save(clienteActualizado), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
