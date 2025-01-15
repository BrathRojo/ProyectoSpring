package com.example.tiendaonline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.tiendaonline.model.Categoria;
import com.example.tiendaonline.repository.CategoriaRepository;

@Primary
@Service("categoriaServiceBD")
public class CategoriaServiceBD implements CategoriaService {
	
	@Autowired
	private CategoriaRepository repositorio;
	
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
}
