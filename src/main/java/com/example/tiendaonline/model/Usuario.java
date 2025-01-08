package com.example.tiendaonline.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;


@Entity
public class Usuario {
	
	@Id @GeneratedValue
	@Min(value=0)
	private long id;
	
	@NotEmpty
	private String nombre;
	private String apellidos;
	private String contraseña;
	private String rol;
	
	@Email
	private String email;
	private String dni;
	private String direccion;
	private String telefono;
	private long carrito;
}
