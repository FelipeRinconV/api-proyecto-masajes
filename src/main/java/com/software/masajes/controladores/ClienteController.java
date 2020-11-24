package com.software.masajes.controladores;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
import com.software.masajes.model.Cliente;
import com.software.masajes.model.Secretario;
import com.software.masajes.repository.ClienteRepository;
import com.software.masajes.repository.SecretarioRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ClienteController  {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	SecretarioRepository secreRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	

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
	public ResponseEntity<Cliente> createCliente(@RequestBody ClientDto cliente) {
		try {

			Cliente clienteNuevo = new Cliente();

			Optional<Secretario> secre2 = secreRepository.findById((long) cliente.getIdSectretario());
			Secretario secre = (Secretario) secre2.get();

			clienteNuevo.setNombre(cliente.getNombre());
			clienteNuevo.setSecretario(secre);
			clienteNuevo.setCedula(cliente.getCedula());
			clienteNuevo.setDireccion(cliente.getDireccion());
			clienteNuevo.setEmail(cliente.getEmail());
			clienteNuevo.setOcupacion(cliente.getOcupacion());
			clienteNuevo.setTelefono(cliente.getTelefono());
			clienteNuevo.setFechaNacimiento(cliente.getFecha_nacimiento());

			Cliente _tutorial = clienteRepository.save(clienteNuevo);

			return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/clientes")
	public ResponseEntity<String> updateCliente(@RequestBody ClientDto cliente, @PathVariable long idCleinte) {
		Optional<Cliente> clienteData = clienteRepository.findById(idCleinte);

		if (clienteData.isPresent()) {
			Cliente clienteActualizado = clienteData.get();

			clienteActualizado.setNombre(cliente.getNombre());
			clienteActualizado.setCedula(cliente.getCedula());
			clienteActualizado.setDireccion(cliente.getDireccion());
			clienteActualizado.setEmail(cliente.getEmail());
			clienteActualizado.setOcupacion(cliente.getOcupacion());
			clienteActualizado.setTelefono(cliente.getTelefono());
			clienteActualizado.setFechaNacimiento(cliente.getFecha_nacimiento());

			clienteRepository.save(clienteActualizado);

			return new ResponseEntity<>("Cliente actualizado", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("El cliente no se encuetra registrado ", HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<HttpStatus> deleteCliente(@PathVariable("id") long id) {
		try {
			clienteRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/cliente/cedula/{cedula}")
	public ResponseEntity<Cliente>  getClienteByCedula(@PathVariable("cedula") String cedula ){
		
		TypedQuery<Cliente> queryClienteByCedula= entityManager.createNamedQuery(Cliente.CLIENTE_BY_CEDULA, Cliente.class);
		queryClienteByCedula.setParameter("ce", cedula);
		
		Cliente cliente= queryClienteByCedula.getSingleResult();
		
		if(cliente!=null) {
			return new ResponseEntity<Cliente>(cliente,HttpStatus.FOUND);
		}else {
			return new ResponseEntity<Cliente>(HttpStatus.NO_CONTENT);
		}
		
		
	}
}
