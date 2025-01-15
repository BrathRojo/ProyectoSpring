package com.example.tiendaonline.service;

import java.util.List;

import com.example.tiendaonline.model.Producto;

public interface ProductoService {
	public Producto add(Producto u);
	public List<Producto> findAll();
	public Producto findById(long id);
	public Producto edit(Producto u);
}
