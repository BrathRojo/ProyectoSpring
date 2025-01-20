package com.example.tiendaonline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.tiendaonline.model.Categoria;
import com.example.tiendaonline.model.Producto;
import com.example.tiendaonline.repository.CategoriaRepository;
import com.example.tiendaonline.repository.ProductoRepository;

@Primary
@Service("categoriaServiceBD")
public class CategoriaServiceBD implements CategoriaService {
	
	@Autowired
	private CategoriaRepository repositorio;
	
	@Autowired
	private ProductoRepository productoRepository;

	
	@Override
	public Categoria add(Categoria c) {
		return repositorio.save(c);
	}
	
	@Override
	public List<Categoria> findAll() {
		return repositorio.findAll();
	}
	
	@Override
	public Categoria findById(long id) {
		return repositorio.findById(id).orElse(null);
	}
	
	@Override
	public Categoria edit(Categoria c) {
		return repositorio.save(c);
	}
	
	@Override
	public void borraCategoria(long id) {
		Categoria categoriaAEliminar = repositorio.findById(id).orElse(null);
		List<Producto> productos = productoRepository.findByCategoriasId(id);
		
		for (Producto producto : productos) {
			producto.getCategorias().removeIf(categoria -> categoria == categoriaAEliminar);
			productoRepository.save(producto);
		}
		repositorio.deleteById(id);
	}
}
