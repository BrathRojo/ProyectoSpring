package com.example.tiendaonline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tiendaonline.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    void deleteById(Long id);

	List<Producto> findByCategoriasId(long id);
	
	List<Producto> findTop4ByOrderByVentasDesc();
}

