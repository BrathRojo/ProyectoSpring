package com.example.tiendaonline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.tiendaonline.model.Producto;
import com.example.tiendaonline.repository.ProductoRepository;

@Primary
@Service("productoServiceBD")
public class ProductoServiceBD implements ProductoService {
	
	@Autowired
	private ProductoRepository repositorio;
	
	@Override
	public Producto add(Producto p) {
		return repositorio.save(p);
	}
	
	@Override
	public List<Producto> findAll() {
		return repositorio.findAll();
	}
	
	@Override
	public Producto findById(long id) {
		return repositorio.findById(id).orElse(null);
	}
	
	@Override
	public void borraProducto(long id) {
		repositorio.deleteById(id);
	}
	
	@Override
	public Producto edit(Producto p) {
		return repositorio.save(p);
	}

	@Override
	public List<Producto> findTop4ByOrderByVentasDesc() {
		return repositorio.findTop4ByOrderByVentasDesc();
	}
}
