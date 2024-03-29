package com.mitocode.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
@Entity
@Table(name="paciente")
public class Paciente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//autogenerable
	private Integer idPaciente;//clase wrapper
	//private int edad;//clase primitivo
	//@NotNull
	@ApiModelProperty(notes="Nombres debe tener minimo 3 caracteres")
	@Size(min = 3,message = "Nombres debe tener un minimo de 3 caracteres")
	@Column(name="nombres",nullable = false, length = 70)
	private String nombres;
	@ApiModelProperty(notes="apellidos debe tener minimo 3 caracteres")
	@Size(min = 3,message = "apellidos debe tener un minimo de 3 caracteres")
	@Column(name="apellidos",nullable = false, length = 70)
	private String apellidos;
	@ApiModelProperty(notes="dni debe tener 8 caracteres")
	@Size(min = 3, max = 8 ,message = "dni debe tener 8 caracteres")
	@Column(name="dni",nullable = false, length = 8)
	private String dni;
	@ApiModelProperty(notes="direccion debe tener minimo 3 caracteres")
	@Size(min = 3,max = 150 ,message = "direccion debe tener un minimo de 3 caracteres")
	@Column(name="direccion",nullable = true, length = 150)
	private String direccion;
	@ApiModelProperty(notes="telefono debe tener minimo 3 caracteres")
	@Size(min = 3,max= 9, message = "telefono debe tener 9 caracteres")
	@Column(name="telefono",nullable = true, length = 9)
	private String telefono;
	
	@Column(name="email",nullable = false, length = 55)
	private String email;
	
	public Integer getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}

///https://chrome.google.com/webstore/detail/restful-stress/lljgneahfmgjmpglpbhmkangancgdgeb
