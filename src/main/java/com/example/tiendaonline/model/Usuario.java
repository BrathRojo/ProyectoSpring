package com.example.tiendaonline.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	
	public Usuario(@NotEmpty String nombre, String apellidos, String contraseña, String rol,
			@Email String email, String dni, String direccion, String telefono, long carrito) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.contraseña = contraseña;
		this.rol = rol;
		this.email = email;
		this.dni = dni;
		this.direccion = direccion;
		this.telefono = telefono;
		this.carrito = carrito;
	}

	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public long getCarrito() {
		return carrito;
	}

	public void setCarrito(long carrito) {
		this.carrito = carrito;
	}
	
	@OneToMany(mappedBy = "usuario")
	private List<Pedido> pedidos;
}
