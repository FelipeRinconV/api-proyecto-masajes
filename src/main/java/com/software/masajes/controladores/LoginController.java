package com.software.masajes.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.software.masajes.repository.SecretarioRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class LoginController {

	
	@Autowired
	SecretarioRepository secreRepository;
	
	
	
	
	
}
