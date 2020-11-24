package com.software.masajes.controladores;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.software.masajes.dto.TerapiaDto;
import com.software.masajes.dto.TerapiaTerapeutaDto;
import com.software.masajes.model.Secretario;
import com.software.masajes.model.Terapeuta;
import com.software.masajes.model.Terapia;
import com.software.masajes.model.TerapiaOuputDto;
import com.software.masajes.model.TerapiaTerapeuta;
import com.software.masajes.repository.CitaRepository;
import com.software.masajes.repository.ClienteRepository;
import com.software.masajes.repository.SecretarioRepository;
import com.software.masajes.repository.TerapeutaRepository;
import com.software.masajes.repository.TerapiaRepository;
import com.software.masajes.repository.TerapiaTerapeutaRepository;

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
	TerapiaTerapeutaRepository terapiaTerapeutaRepository;
	
	@Autowired
	TerapiaRepository terapiaRepository;
	
	@Autowired
	TerapeutaRepository terapeutaRepository;
	
	
	@GetMapping("/terapias")
	public ResponseEntity<List<TerapiaOuputDto> > getAllTerapias() {
		try {

			List<Terapia> terapias = terapiaRepository.findAll();

			if (terapias.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(convertirListaTerapiaAListaTerapiaOuputDto(terapias), HttpStatus.OK);
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
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	@PostMapping("/terapias")
	public ResponseEntity<HttpStatus> saveTerapia(@RequestBody TerapiaDto terapia  ) {
		try {
		
			Optional<Secretario> secretario = secreRepository.findById((long) terapia.getIdSecretario());
			
			if(secretario ==null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}else {
				Terapia terapiaNueva = new Terapia();
			
				terapiaNueva.setNombre(terapia.getNombre());
				terapiaNueva.setSecretario(secretario.get());
				terapiaNueva.setPrecio(terapia.getPrecio());
				terapiaNueva.setDuracionMinutos(terapia.getDuracionMinutos());	
				
				terapiaRepository.save(terapiaNueva);
				return new ResponseEntity<>(HttpStatus.OK);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@PostMapping("/terapias/asignacion")
	public ResponseEntity<HttpStatus> asignarTerapia(@RequestBody TerapiaTerapeutaDto terapiaTerapeuta  ) {
		try {
		
			Optional<Terapeuta> terapeuta = terapeutaRepository.findById((long) terapiaTerapeuta.getIdTerapeuta());
			
			Optional<Terapia> terapia = terapiaRepository.findById((long) terapiaTerapeuta.getIdTerapia());
			
			if(terapeuta ==null || terapia ==null ) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}else {
				TerapiaTerapeuta terapiaTerapeutaNuevo = new TerapiaTerapeuta();
			
				terapiaTerapeutaNuevo.setTerapeuta(terapeuta.get());
				terapiaTerapeutaNuevo.setTerapia(terapia.get());
				
				terapiaTerapeutaRepository.save(terapiaTerapeutaNuevo);
				return new ResponseEntity<>(HttpStatus.CREATED);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	
	public  TerapiaOuputDto convertirTerapiaATerapiaOuputDto(Terapia terapia) {
		
		TerapiaOuputDto terapiaOuputDto = new TerapiaOuputDto();
		
		terapiaOuputDto.setDuracionMinutos(terapia.getDuracionMinutos());
		terapiaOuputDto.setId(terapia.getId());
		terapiaOuputDto.setNombre(terapia.getNombre());
		terapiaOuputDto.setPrecio(terapia.getPrecio());
		
		
		
		return terapiaOuputDto;
		
	}
	
	
	
	public  List<TerapiaOuputDto> convertirListaTerapiaAListaTerapiaOuputDto(List<Terapia> terapias){
		
		
		List<TerapiaOuputDto> listaTerapiaOuputDtos= new ArrayList<TerapiaOuputDto>();
		
		
		for(Terapia tera:terapias) {
			listaTerapiaOuputDtos.add(convertirTerapiaATerapiaOuputDto(tera));
		}
		
		return listaTerapiaOuputDtos;
		
	}
	
	
	
	

}
