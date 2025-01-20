package com.example.tiendaonline.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Pedido {

	@Id @GeneratedValue
	private long id;
	private String nombre;
	private String apellidos;
	private String dni;
	private String direccion;
	private float precioTotal;
	private Date fecha;
	private Boolean pedidoActivo;
	
	public Pedido(String nombre, String apellidos, String dni, String direccion, float precioTotal, Date fecha) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.direccion = direccion;
		this.precioTotal = precioTotal;
		this.fecha = fecha;
		this.pedidoActivo = true;
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
	public float getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(float precioTotal) {
		this.precioTotal = precioTotal;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public Boolean getPedidoActivo() {
		return pedidoActivo;
	}
	public void setPedidoActivo(Boolean pedidoActivo) {
		this.pedidoActivo = pedidoActivo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<LineaPedido> getLineapedidos() {
		return lineapedidos;
	}
	public void setLineapedidos(List<LineaPedido> lineapedidos) {
		this.lineapedidos = lineapedidos;
	}

	@ManyToOne
	private Usuario usuario;
	
	@OneToMany(mappedBy = "pedido")
	private List<LineaPedido> lineapedidos;
	
	
}
