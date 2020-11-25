package com.software.masajes.controladores;

import java.util.ArrayList;
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

import com.software.masajes.dto.TerapeutaDto;
import com.software.masajes.dto.TerapeutaOuputDto;
import com.software.masajes.model.Cliente;
import com.software.masajes.model.Terapeuta;
import com.software.masajes.model.TerapiaOuputDto;
import com.software.masajes.model.consultas.personalizadas.ClienteByTerapeuta;
import com.software.masajes.repository.TerapeutaRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class TerapeutaController {

	@Autowired
	TerapeutaRepository terapeutaRepository;
	
	@PersistenceContext
	private EntityManager entityManager;


	@GetMapping("/terapeutas")
	public ResponseEntity<List<TerapeutaOuputDto>> getAllTerapeutas() {
		try {
			List<Terapeuta> terapeutas = terapeutaRepository.findAll();

			if (terapeutas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(convertirListaTerapeutaAListaTerapeutaOuputDto(terapeutas), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/terapeutas/{id}")
	public ResponseEntity<TerapeutaOuputDto> getTerapeutaById(@PathVariable("id") long id) {
		Optional<Terapeuta> terapeuta = terapeutaRepository.findById(id);
		if (terapeuta.isPresent()) {
			return new ResponseEntity<>(convertirTerapeutaATerapeutaDto(terapeuta.get()), HttpStatus.OK);
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

			return new ResponseEntity<>("Terapeuta actualizado", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("El terepeuta no se encuentra registrado ", HttpStatus.NOT_FOUND);
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
	
	
	@GetMapping("/terapeutas/clientes/{id}")
	public ResponseEntity<List<ClienteByTerapeuta>> getClientesAsociados(@PathVariable("id") int idTerapeuta ){
		
				
		TypedQuery<ClienteByTerapeuta> query= entityManager.createNamedQuery(Cliente.CLIENTES_BY_TERAPEUTA, ClienteByTerapeuta.class);
	
		query.setParameter(1,idTerapeuta);
		
		List<ClienteByTerapeuta> listaClientes = query.getResultList();
		
		if(!listaClientes.isEmpty()) {
			
			return new ResponseEntity<>(listaClientes, HttpStatus.ACCEPTED);

		}else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		
	}
	
	
	
	public  TerapeutaOuputDto convertirTerapeutaATerapeutaDto(Terapeuta terapeuta) {
				
		TerapeutaOuputDto terapeutaDto = new TerapeutaOuputDto();
		
		terapeutaDto.setId(terapeuta.getId());
		terapeutaDto.setCedula(terapeuta.getCedula());
		terapeutaDto.setClave(terapeuta.getClave());
		terapeutaDto.setDireccion(terapeuta.getDireccion());
		terapeutaDto.setEmail(terapeuta.getEmail());
		terapeutaDto.setNombre(terapeuta.getNombre());
		terapeutaDto.setProfesion(terapeuta.getProfesion());
		
		
		return terapeutaDto;		
	}
	
	
	public List<TerapeutaOuputDto> convertirListaTerapeutaAListaTerapeutaOuputDto(List<Terapeuta> listaTerapeutas){
		
		 
		List<TerapeutaOuputDto> terapeutasDto= new ArrayList<TerapeutaOuputDto>();
		
		for(Terapeuta terapeuta: listaTerapeutas) {
			
			terapeutasDto.add(convertirTerapeutaATerapeutaDto(terapeuta));
			
		}
		
		return terapeutasDto;
		
	}
	
	
	
	

}
