package com.example.tiendaonline.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.tiendaonline.model.Producto;

@Service("productoServiceMemory")
public class ProductoServiceMemory {
	
	private List<Producto> repositorio = new ArrayList<>();

}