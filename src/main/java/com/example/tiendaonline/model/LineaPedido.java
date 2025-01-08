package com.example.tiendaonline.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class LineaPedido {

	@Id @GeneratedValue
	private long id;
	private long pedido_id;
	private long producto_id;
	private long cantidad;
	private float precioLinea;
}
