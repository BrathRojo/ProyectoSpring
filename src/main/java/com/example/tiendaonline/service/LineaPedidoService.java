package com.example.tiendaonline.service;

import java.util.List;

import com.example.tiendaonline.model.LineaPedido;

public interface LineaPedidoService {
	public LineaPedido add(LineaPedido u);
	public List<LineaPedido> findAll();
	public LineaPedido findById(long id);
	public LineaPedido edit(LineaPedido u);
}
