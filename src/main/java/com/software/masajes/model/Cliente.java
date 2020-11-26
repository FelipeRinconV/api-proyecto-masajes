package com.software.masajes.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.software.masajes.model.consultas.personalizadas.ClienteByTerapeuta;

@NamedQueries({

	@NamedQuery(name = Cliente.CLIENTE_BY_CEDULA, query = "select u from Cliente u where u.cedula=:ce")

})

@NamedNativeQueries({
	@NamedNativeQuery(name = Cliente.CLIENTES_BY_TERAPEUTA, 
			query = "SELECT  clientes.id_cliente,telefono,cedula,nombre,direccion,email,ocupacion,fecha_nacimiento  FROM clientes  INNER JOIN citas ON clientes.id_cliente=citas.id_cliente WHERE id_terapeuta = ?  group by clientes.id_cliente",resultSetMapping = "CLIENTES_BY_TERAPEUTA_RESULT")
	
})

@SqlResultSetMapping(
		  name="CLIENTES_BY_TERAPEUTA_RESULT",
		  classes = @ConstructorResult(
				  targetClass = ClienteByTerapeuta.class,
				  columns = {
						  @ColumnResult(name = "clientes.id_cliente",type = Long.class),
						  @ColumnResult(name = "telefono",type = String.class),
						  @ColumnResult(name = "cedula",type = String.class),
						  @ColumnResult(name = "nombre",type = String.class),
						  @ColumnResult(name = "direccion",type = String.class),
						  @ColumnResult(name = "email",type = String.class),
						  @ColumnResult(name = "ocupacion",type = String.class),
						  @ColumnResult(name = "fecha_nacimiento",type = Date.class)
				  }
				  )
		)

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

	
	
	public static final String CLIENTE_BY_CEDULA="cliente_por_cedula";
	public static final String CLIENTES_BY_TERAPEUTA="CLIENTES_ASOCIADOS_TERAPEUTA";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_cliente")
	private Long id;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_secretario")
	private Secretario secretario;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
	private List<Cita> citas;
	

	@Column(name = "cedula", nullable = false)
	private String cedula;

	@Column(name = "direccion", nullable = false)
	private String direccion;

	@Column(name = "email", nullable = false)
	private String email;

	@Basic
	private String fechaNacimiento;

	@Column(name = "nombre", nullable = false)
	private String nombre;

	@Basic
	private String ocupacion;

	@Column(name = "telefono", nullable = false)
	private String telefono;

	public Cliente() {
		super();

	}

	public Cliente(Long id, Secretario secretario, String cedula, String direccion, String email,
			String fechaNacimiento, String nombre, String ocupacion, String telefono) {
		super();
		this.id = id;
		this.secretario = secretario;
		this.cedula = cedula;
		this.direccion = direccion;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.nombre = nombre;
		this.ocupacion = ocupacion;
		this.telefono = telefono;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Secretario getSecretario() {
		return secretario;
	}

	public void setSecretario(Secretario secretario) {
		this.secretario = secretario;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Cita> getCitas() {
		return citas;
	}

	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}
	
	

}
