package com.entregable.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.entregable.models.component.UsuarioComp;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;


//@Entity dice que nuestra clase es una "entidad", es decir, que tiene relación con la base de datos
//Le indico el nombre de la tabla con la que tiene relación a través de @Table
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Indico qué atributo es la clave primaria de la base de datos usando @Id
	//Le digo que esta clave primaria se genera automáticamente con la anotación @GeneratedValue
	
	@Id
	private String email;
	
	public Usuario() {
		super();
		this.peliculas= new ArrayList<Pelicula>();

	}
	
	private String nombre;	
	
	private String apellido;
	
	private String anio_nacimiento;
	
	private String contrasenia;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Pelicula> peliculas;
	
	
	public List<Pelicula> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(List<Pelicula> peliculas) {
		this.peliculas = peliculas;
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getAnio_nacimiento() {
		return anio_nacimiento;
	}

	public void setAnio_nacimiento(String anio_nacimiento) {
		this.anio_nacimiento = anio_nacimiento;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	public void aniadirPelicula(Pelicula p) {
		this.peliculas.add(p);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void copiar(UsuarioComp usuarioComp) {
		this.nombre = usuarioComp.getNombre();
		this.apellido = usuarioComp.getApellido();
		this.contrasenia = usuarioComp.getContrasenia();
		this.email = usuarioComp.getEmail();
		this.anio_nacimiento = usuarioComp.getAnio_nacimiento();
		this.peliculas = usuarioComp.getPeliculas();
		
	}	

	
}
