package com.example.tiendaonline.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Producto {
	
	@Id @GeneratedValue
	private long id;
	private String nombre;
	private float precioUnitario;
	private String descripcion;
	private long categoria;
	private long stock;
	private String imagen;
}
