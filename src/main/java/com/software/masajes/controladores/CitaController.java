package com.software.masajes.controladores;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.software.masajes.dto.CitaDto;
import com.software.masajes.dto.CitaOuputDto;
import com.software.masajes.dto.ObservacionDto;
import com.software.masajes.model.Cita;
import com.software.masajes.model.Cliente;

import com.software.masajes.model.Observacion;
import com.software.masajes.model.ObservacionOuputDto;
import com.software.masajes.model.Secretario;
import com.software.masajes.model.Terapeuta;
import com.software.masajes.model.Terapia;
import com.software.masajes.repository.CitaRepository;
import com.software.masajes.repository.ClienteRepository;
import com.software.masajes.repository.ObservacionRepositoey;
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
	ObservacionRepositoey observacionRepository;
	
	@PersistenceContext
	EntityManager entityManager;
	

	@GetMapping("/citas")
	public ResponseEntity<List<CitaOuputDto>> getAllCitas() {
		try {

			List<Cita> citas = citaRepository.findAll();

			if (citas.isEmpty()) {
			
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<>(covertirListaCitaAListaCitaOuputDto(citas), HttpStatus.OK);

			}

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/cita/{id}")
	public ResponseEntity<CitaOuputDto> getCitaById(@PathVariable("id") long id) {
		Optional<Cita> cita = citaRepository.findById(id);
		
		if (cita.isPresent()) {
			Cita citaEncontrada = cita.get();
		
			return new ResponseEntity<>(convertirCitaACitaOuputDto(citaEncontrada), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/cita")
	public ResponseEntity<String> createCita(@RequestBody CitaDto citaDto) {
		
			Cita citaNuevo = new Cita();
		
			Optional<Secretario> secre2 = secreRepository.findById((long) citaDto.getIdSecretario());
			Optional<Terapia> terapia2 = terapiaRepository.findById((long) citaDto.getIdTerapia());
			Optional<Terapeuta> terapeuta2 = terapeutaRepository.findById((long) citaDto.getIdTerapeuta());
			Optional<Cliente> clienteOptional = clienteRepository.findById((long) citaDto.getIdCliente());
			
			
			if(secre2.isPresent() && terapia2.isPresent() && terapeuta2.isPresent() && clienteOptional.isPresent()) {
				
			try {

			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
			String fechaInicioCita = citaDto.getFecha().split("T")[0]+" "+ citaDto.getFecha().split("T")[1]+":00";
			
			Date fechaInicio = formato.parse(fechaInicioCita);
			Date fechaFinal = darFechaFinal(fechaInicio, terapia2.get().getDuracionMinutos());
			
			citaNuevo.setSecretario(secre2.get());
			citaNuevo.setTerapeuta(terapeuta2.get());
			citaNuevo.setTerapia(terapia2.get());
			citaNuevo.setFechaInicio(fechaInicio);
			citaNuevo.setFechaFinal(fechaFinal);
			citaNuevo.setCliente(clienteOptional.get());
			
	       citaRepository.save(citaNuevo);

			return new ResponseEntity<>("Cita creada satisfactoriamente", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Hubo un problema creando la cita", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		}else {
			return new ResponseEntity<>("Algun id provisto no corresponde a una entidad", HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	private Date darFechaFinal(Date fechaInicial,int minutos) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaInicial); 
		calendar.add(Calendar.MINUTE, minutos); 
	
		Date fechaSalida = calendar.getTime();
		
		return fechaSalida;
	}

//	@PutMapping("/citas/{idCita}")
//	public ResponseEntity<String> updateCita(@RequestBody CitaDto cita,@PathVariable("idCita") int idCita) throws ParseException {
//		Optional<Cita> citaData = citaRepository.findById((long) idCita);
//
//		if (citaData.isPresent()) {
//			Cita citaActualizado = citaData.get();
//
//			Optional<Secretario> secre2 = secreRepository.findById((long) cita.getIdSecretario());
//			Secretario secretario = (Secretario) secre2.get();
//
//			Optional<Terapia> terapia2 = terapiaRepository.findById((long) cita.getIdTerapia());
//			Terapia terapia = (Terapia) terapia2.get();
//
//			Optional<Terapeuta> terapeuta2 = terapeutaRepository.findById((long) cita.getIdTerapeuta());
//			Terapeuta terapeuta = (Terapeuta) terapeuta2.get();
//
//
//			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
//			Date fechaInicio = formato.parse(cita.getFechaInicio());
//			
//			citaActualizado.setSecretario(secretario);
//			citaActualizado.setTerapeuta(terapeuta);
//			citaActualizado.setTerapia(terapia);
//			citaActualizado.setFechaInicio(fechaInicio);
//		//	citaActualizado.setFechaFinal(fechaFinal);
//
//			citaRepository.save(citaActualizado);
//
//			return new ResponseEntity<>("Cita actualizada", HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>("La cita no se encuentra en el sistema", HttpStatus.NOT_FOUND);
//		}
//	}

	@DeleteMapping("/citas/{id}")
	public ResponseEntity<HttpStatus> deleteCita(@PathVariable("id") long id) {
		try {
			citaRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping("cita/observacion")
	private  ResponseEntity<String>  addObservacion(@RequestBody ObservacionDto observacion){
		
		
		try {
			Optional<Cita> cita = citaRepository.findById(observacion.getIdCita());
			
			if(cita.isPresent()) {
				
				Optional<Terapeuta> terapeuta = terapeutaRepository.findById(observacion.getIdTerapeuta());
				
				if(terapeuta.isPresent()) {
					
					if(observacion.getObservacion().length()>0) {
						
						Observacion nuevaObservacion = new Observacion();
						nuevaObservacion.setCita(cita.get());
						nuevaObservacion.setTerapeuta(terapeuta.get());
						nuevaObservacion.setObservacion(observacion.getObservacion());
						nuevaObservacion.setCita(cita.get());
						
						observacionRepository.save(nuevaObservacion);
						
					   return new ResponseEntity<>("La observacion se guardo correctamente", HttpStatus.CREATED);

						
					}else {
						return new ResponseEntity<>("LA OBSERVACION DEBE TENER CONTENIDO", HttpStatus.NOT_ACCEPTABLE);

					}
					
				}else {
					return new ResponseEntity<>("El terapeuta con el id:"+ observacion.getIdTerapeuta() + " no se encuentra", HttpStatus.NOT_FOUND);
				}
				
			}else {
				return new ResponseEntity<>("La cita con el id:"+ observacion.getIdCita() + " no se encuentra", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Ocurrio un error en la operacion",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	
//   @GetMapping	
//   public ResponseEntity<List<ObservacionOuputDto>>  observacionesPorCita(int idCita){
//		
//	   
//	  // TypedQuery<ObservacionOuputDto> query = entityManager.createNamedQuery(Observacion.LISTAR_OBSERVACIONES_POR_CITA, resultClass)
//			
//
//		
//	}
	
	
	/**
	 * Metodo que brinda las fechas ordenadas por fecha
	 * @return
	 */
	@GetMapping("citas/ordenadasporfecha")
	public ResponseEntity<List<CitaOuputDto>> getAllCitasOrderByStartDate(){
		
		TypedQuery<Cita> queryCitas = entityManager.createNamedQuery(Cita.LISTAR_CITAS_ORDENADAS_POR_FECHA, Cita.class);	
		List<Cita> citas= queryCitas.getResultList();
		
		if(!citas.isEmpty()) {
			return new ResponseEntity<>(covertirListaCitaAListaCitaOuputDto(citas),HttpStatus.ACCEPTED);
			
		}else {
			return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);

		}
		
	}

	
	
	@GetMapping("citas/observaciones/{id}")
   public ResponseEntity<List<ObservacionOuputDto>>  observacionesPorCita(@PathVariable("id")int idCita){
	   
	   TypedQuery<Observacion> query = entityManager.createNamedQuery(Observacion.LISTAR_OBSERVACIONES_POR_CITA, Observacion.class);
	   
	   query.setParameter(1, idCita);
	   
	   List<ObservacionOuputDto> observacionesDto = convertirListaObseracionAListaOputDto(query.getResultList());
	   
	   if(observacionesDto.isEmpty()) {
		   
		   return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
		   
	   }else {
		   
		   return new ResponseEntity<>(observacionesDto,HttpStatus.ACCEPTED);
	   }
	   
	  
		
	}
	
	
	@GetMapping("citas/terapeuta/{id}")
	public ResponseEntity<List<CitaOuputDto>> getCitasPorTerapeuta (@PathVariable("id")int idTerapeuta){
		
		TypedQuery<Cita> query = entityManager.createNamedQuery(Cita.LISTAR_CITAS_BY_TERAPEUTA, Cita.class);
		query.setParameter(1, idTerapeuta);
	
		List<CitaOuputDto> listaTerapeutas= covertirListaCitaAListaCitaOuputDto(query.getResultList());
		
		if(listaTerapeutas.isEmpty()) {
			return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
		}else {
			   return new ResponseEntity<>(listaTerapeutas,HttpStatus.OK);
 
		}
		
	}

	
	
	
	/**
	 * Metodo que convierte un cliente modelo a un clienteOuputDto el cual se usa para dar las selidas de los servicios
	 * de las citas
	 * @return
	 */
    public CitaOuputDto convertirCitaACitaOuputDto(Cita cita) {
    	
    	CitaOuputDto citaOuputDto= new CitaOuputDto();
    	
   
    	citaOuputDto.setIdCita(cita.getId());
    	citaOuputDto.setFechaFinal(cita.getFechaFinal().toString().split(" ")[0]);
    	citaOuputDto.setFechaInicial(cita.getFechaInicio().toString().split(" ")[0]);
    	citaOuputDto.setHoraInicio(cita.getFechaInicio().toString().split(" ")[1].substring(0,5));
    	citaOuputDto.setHoraFinal(cita.getFechaFinal().toString().split(" ")[1].substring(0,5));
    	citaOuputDto.setNombreProfesional(cita.getTerapeuta().getNombre());
    	citaOuputDto.setNombreCliente(cita.getCliente().getNombre());
  
    	return citaOuputDto;
    	
    	
    }
    
    
    
    public  List<CitaOuputDto> covertirListaCitaAListaCitaOuputDto(List<Cita> listaCitas){
    	
    	List<CitaOuputDto> listaCitaOuputDto= new ArrayList<CitaOuputDto>();
    	
    	for(Cita citaActual: listaCitas) {
    		
    		listaCitaOuputDto.add(convertirCitaACitaOuputDto(citaActual));
    		
    	}
  
		return listaCitaOuputDto;
    	
    }
    
    
    public ObservacionOuputDto  convertirObservacionAObservacionDto(Observacion observacion) {
    	
    	ObservacionOuputDto observacionOuputDto = new ObservacionOuputDto();
    	
    	
    	observacion.setObservacion(observacion.getObservacion());
    	
    	
    	return observacionOuputDto;
    	
    }

    public  List<ObservacionOuputDto> convertirListaObseracionAListaOputDto(List<Observacion> listaObsevaciones){
    	
    	List<ObservacionOuputDto> listaObservacionesOuputDto= new ArrayList<ObservacionOuputDto>();
    	
    	for(Observacion obser:listaObsevaciones) {
    		
    		listaObservacionesOuputDto.add(convertirObservacionAObservacionDto(obser));
    		
    	}
  
		return listaObservacionesOuputDto;
    	
    }
    
    
    
    
    
    
    
    
}
