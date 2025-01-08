package com.example.tiendaonline.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Categoria {

	@Id @GeneratedValue
	private Long id;
	private String nombre;
}
