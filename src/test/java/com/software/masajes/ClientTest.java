package com.software.masajes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import com.software.masajes.controladores.ClienteController;
import com.software.masajes.dto.ClientDto;
import com.software.masajes.model.Cliente;
import com.software.masajes.model.Secretario;

@SpringBootTest
@DataJpaTest
public class ClientTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ClienteController clienteController;

	@Test
	public void saveCustomer() {

		Secretario secretario = new Secretario();

		secretario.setCedula("1007376425");
		secretario.setNombre("secretario1");
		secretario.setSueldo(200);

		Cliente cliente = new Cliente((long) (1), secretario, "100", "Quimbaya", "Recreo", "feliperv04@gmail.com",
				"2020/02", "felipe", "3108457581");
		/*
		 * Cliente(Long id, Secretario secretario, String cedula, String direccion,
		 * String email, String fechaNacimiento, String nombre, String ocupacion, String
		 * telefono)
		 */

		assertThat(clienteController.getClienteById((long) 1)).isNotNull();
	}
	
	

}
