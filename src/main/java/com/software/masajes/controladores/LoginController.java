package com.software.masajes.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.software.masajes.model.Secretario;
import com.software.masajes.model.Terapeuta;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	EntityManager em;

	@GetMapping("/login")
	public ResponseEntity<Integer> login(String email, String clave) {

		TypedQuery<Secretario> loginSecretario = em.createNamedQuery(Secretario.LOG_SECRETARIO, Secretario.class);

		loginSecretario.setParameter("email", email);
		loginSecretario.setParameter("clave", clave);

		List<Secretario> secretarios = loginSecretario.getResultList();

		if (secretarios.size() != 0) {

			return new ResponseEntity<Integer>(0, HttpStatus.OK);

		} else {

			TypedQuery<Terapeuta> loginTeapreuta = em.createNamedQuery(Terapeuta.LOG_TERAPEUTA, Terapeuta.class);
			loginTeapreuta.setParameter("email", email);
			loginTeapreuta.setParameter("clave", clave);
			List<Terapeuta> terapeutas = loginTeapreuta.getResultList();

			if (terapeutas.size() > 0) {
				return new ResponseEntity<Integer>(1, HttpStatus.OK);

			}
		}

		return new ResponseEntity<Integer>(-1, HttpStatus.OK);

	}

}
