package com.example.tiendaonline.service;

import java.util.List;

import com.example.tiendaonline.model.Categoria;

public interface CategoriaService {
	public Categoria add(Categoria c);
	public List<Categoria> findAll();
	public Categoria findById(long id);
	public Categoria edit(Categoria c);
}
