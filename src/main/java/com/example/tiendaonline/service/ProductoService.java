package com.example.tiendaonline.service;

import java.util.List;

import com.example.tiendaonline.model.Producto;

public interface ProductoService {
	public Producto add(Producto u);
	public List<Producto> findAll();
	public Producto findById(long id);
	public Producto edit(Producto u);
	public void borraProducto(long id);
	public List<Producto> findTop4ByOrderByVentasDesc();
    List<Producto> findByNombreContaining(String query);

    List<Producto> findByNombreContainingAndCategoriasId(String query, Long categoriaId);
}
