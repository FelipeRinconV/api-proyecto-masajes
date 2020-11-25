package com.software.masajes.controladores;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.software.masajes.dto.LoginDto;
import com.software.masajes.dto.LoginOuputDto;
import com.software.masajes.model.Secretario;
import com.software.masajes.model.Terapeuta;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	EntityManager em;

	@GetMapping("/login")
	public ResponseEntity<LoginOuputDto> login(String email,String clave) {

		
		LoginOuputDto loginRespuesta= new LoginOuputDto();
		TypedQuery<Secretario> loginSecretario = em.createNamedQuery(Secretario.LOG_SECRETARIO, Secretario.class);

		loginSecretario.setParameter("email", email);
		loginSecretario.setParameter("clave", clave);

		List<Secretario> secretarios = loginSecretario.getResultList();

		if (secretarios.size() != 0) {
			
			loginRespuesta.setId((int) secretarios.get(0).getId());
			loginRespuesta.setEmail(secretarios.get(0).getEmail());
			loginRespuesta.setNombre(secretarios.get(0).getNombre());
			loginRespuesta.setIdentificador(1);
		

			return new ResponseEntity<LoginOuputDto>(loginRespuesta, HttpStatus.OK);

		} else {

			TypedQuery<Terapeuta> loginTeapreuta = em.createNamedQuery(Terapeuta.LOG_TERAPEUTA, Terapeuta.class);
			loginTeapreuta.setParameter("email", email);
			loginTeapreuta.setParameter("clave", clave);
			List<Terapeuta> terapeutas = loginTeapreuta.getResultList();

			if (terapeutas.size() > 0) {
				
				loginRespuesta.setId((int) terapeutas.get(0).getId());
				loginRespuesta.setEmail(terapeutas.get(0).getEmail());
				loginRespuesta.setNombre(terapeutas.get(0).getNombre());
				loginRespuesta.setIdentificador(0);
				
				return new ResponseEntity<LoginOuputDto>(loginRespuesta, HttpStatus.OK);

			}
		}
		
		loginRespuesta.setId(-1);
		loginRespuesta.setEmail("");
		loginRespuesta.setNombre("");
		loginRespuesta.setIdentificador(-1);
		
		return new ResponseEntity<>(loginRespuesta, HttpStatus.OK);

	}

	
	
	
	
	
}
