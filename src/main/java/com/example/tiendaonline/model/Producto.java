package com.example.tiendaonline.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

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
	
	public Producto(String nombre, float precioUnitario, String descripcion, long categoria, long stock, String imagen) {
		this.nombre = nombre;
		this.precioUnitario = precioUnitario;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.stock = stock;
		this.imagen = imagen;
	}
	
	public Producto() {

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

	public float getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public long getCategoria() {
		return categoria;
	}

	public void setCategoria(long categoria) {
		this.categoria = categoria;
	}

	public long getStock() {
		return stock;
	}

	public void setStock(long stock) {
		this.stock = stock;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@OneToMany(mappedBy = "producto")
	private List<LineaPedido> lineapedidos;

	@ManyToMany
	@JoinTable(
			name = "producto_categoria",
			joinColumns = @JoinColumn(name = "producto_id"),
			inverseJoinColumns = @JoinColumn(name = "categoria_id")
	)
	private List<Categoria> categorias;
}
