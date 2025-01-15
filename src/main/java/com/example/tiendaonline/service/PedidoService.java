package com.example.tiendaonline.service;

import java.util.List;

import com.example.tiendaonline.model.Pedido;

public interface PedidoService {
	public Pedido add(Pedido u);
	public List<Pedido> findAll();
	public Pedido findById(long id);
	public Pedido edit(Pedido u);
}
