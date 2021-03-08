package com.software.masajes.model;

import java.io.Serializable;
import java.lang.annotation.Native;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

import com.software.masajes.dto.TerapeutaOuputDto;
import com.software.masajes.model.consultas.personalizadas.ClienteByTerapeuta;

@NamedQueries({

		@NamedQuery(name = Terapeuta.LOG_TERAPEUTA, query = "select u from Terapeuta u where u.email=:email and u.clave=:clave")

})

@NamedNativeQueries({
	@NamedNativeQuery(name = Terapeuta.GET_TERAPEUTAS_DOSPONIBLES,query = " SELECT  terapeutas.id_terapeuta,cedula,email,nombre,profesion  FROM" + 
			" terapeutas INNER JOIN terapia_terapeuta " + 
			" ON terapeutas.id_terapeuta = terapia_terapeuta.id_terapeuta" + 
			" WHERE NOT EXISTS " + 
			" (SELECT nombre  FROM citas  WHERE  fecha_final <= ? AND  fecha >= ? )"
			+ "AND id_terapia=?;",resultSetMapping = "GET_TERAPEUTAS_DOSPONIBLES_RESULT" ),
	
	@NamedNativeQuery(name = Terapeuta.GET_TERAPEUTAS_DISPONIBLES_POR_TERAPIA,query = " SELECT  terapeutas.id_terapeuta,cedula,email,nombre,profesion  FROM" + 
			" terapeutas INNER JOIN terapia_terapeuta " + 
			" ON terapeutas.id_terapeuta = terapia_terapeuta.id_terapeuta" + 
			" WHERE id_terapia=?;",resultSetMapping = "GET_TERAPEUTAS_DOSPONIBLES_RESULT" )
	
})


@SqlResultSetMapping(
		  name="GET_TERAPEUTAS_DOSPONIBLES_RESULT",
		  classes = @ConstructorResult(
				  targetClass = TerapeutaOuputDto.class,
				  columns = {
						  @ColumnResult(name = "id_terapeuta",type = Long.class),
						  @ColumnResult(name = "cedula",type = String.class),
						  @ColumnResult(name = "email",type = String.class),
						  @ColumnResult(name = "nombre",type = String.class),
						  @ColumnResult(name = "profesion",type = String.class)
						  
				  }
				  )
		)


@Entity
@Table(name = "terapeutas")
public class Terapeuta implements Serializable {

	public static final String LOG_TERAPEUTA = "login_terapeuta";
	public static final String GET_TERAPEUTAS_DOSPONIBLES = "terapeutas_disponibles";
	public static final String GET_TERAPEUTAS_DISPONIBLES_POR_TERAPIA = "terapeutas_disponibles";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_terapeuta")
	private long id;

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "terapeuta", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<TerapiaTerapeuta> terapiaTerapeutas;

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "terapeuta")
	private List<Cita> citas;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "terapeuta")
	private List<Observacion> observaciones;

	@Column(nullable = false, name = "cedula", length = 10)
	private String cedula;

	@Column(nullable = false, name = "clave")
	private String clave;

	@Column(nullable = true, name = "direccion")
	private String direccion;

	@Column(nullable = false, name = "email")
	private String email;

	@Column(nullable = false, name = "nombre")
	private String nombre;

	@Column(nullable = false, name = "profesion")
	private String profesion;

	public Terapeuta() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public List<Cita> getCitas() {
		return citas;
	}

	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}

	public List<TerapiaTerapeuta> getTerapiaTerapeutas() {
		return terapiaTerapeutas;
	}

	public void setTerapiaTerapeutas(List<TerapiaTerapeuta> terapiaTerapeutas) {
		this.terapiaTerapeutas = terapiaTerapeutas;
	}

}
