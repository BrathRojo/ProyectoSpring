package com.example.tiendaonline.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Pedido {

	@Id @GeneratedValue
	private long id;
	private long usuario_id;
	private String nombre;
	private String apellidos;
	private String dni;
	private String direccion;
	private float precioTotal;
	private String fecha;
}
