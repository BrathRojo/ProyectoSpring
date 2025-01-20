package com.example.tiendaonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tiendaonline.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	void deleteById(Long id);
}
