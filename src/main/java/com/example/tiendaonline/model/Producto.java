package com.example.tiendaonline.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
	
	@Column(unique = true)
	private String nombre;
	private float precioUnitario;
	private String descripcion;
	private long stock;
	private long ventas;
	private String imagen;
	
	public Producto(String nombre, float precioUnitario, String descripcion, long stock, List<Categoria> categorias) {
		this.nombre = nombre;
		this.precioUnitario = precioUnitario;
		this.descripcion = descripcion;
		this.stock = stock;
		this.ventas = 0L;
		this.imagen =  "/images/producto.webp";
		this.categorias = categorias;
	}
	
	public long getVentas() {
		return ventas;
	}

	public void setVentas(long ventas) {
		this.ventas = ventas;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Producto() {

	}
	
	public Producto(String nombre, float precioUnitario, String descripcion, long stock) {
		this.nombre = nombre;
		this.precioUnitario = precioUnitario;
		this.descripcion = descripcion;
		this.stock = stock;
		this.ventas = 0L;
		this.categorias = new ArrayList<Categoria>();
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

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(
			name = "producto_categoria",
			joinColumns = @JoinColumn(name = "producto_id"),
			inverseJoinColumns = @JoinColumn(name = "categoria_id", nullable = true)
	)
	private List<Categoria> categorias;
}
