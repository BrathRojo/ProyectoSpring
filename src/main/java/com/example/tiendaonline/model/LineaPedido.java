package com.example.tiendaonline.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class LineaPedido {

	@Id @GeneratedValue
	private long id;
	private long cantidad;
	private float precioLinea;
	public LineaPedido(long cantidad, float precioLinea) {
		this.cantidad = cantidad;
		this.precioLinea = precioLinea;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCantidad() {
		return cantidad;
	}
	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
	}
	public float getPrecioLinea() {
		return precioLinea;
	}
	public void setPrecioLinea(float precioLinea) {
		this.precioLinea = precioLinea;
	}
	
	@ManyToOne
	private Pedido pedido;
	
	@ManyToOne 
	private Producto producto; 
}
