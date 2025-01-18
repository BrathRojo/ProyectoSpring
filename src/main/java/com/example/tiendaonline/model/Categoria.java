package com.example.tiendaonline.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Categoria {

	@Id @GeneratedValue
	private Long id;
	private String nombre;

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public List<Producto> getProductos() {
		return productos;
	}



	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}



	public Categoria(String nombre) {
		this.nombre = nombre;
	}
	
	public Categoria() {

	}





	@ManyToMany(mappedBy = "categorias")
	private List<Producto> productos;
}
