package com.example.tiendaonline.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.tiendaonline.model.Categoria;

@Service("categoriaServiceMemory")
public class CategoriaServiceMemory {
	
	private List<Categoria> repositorio = new ArrayList<>();

}
