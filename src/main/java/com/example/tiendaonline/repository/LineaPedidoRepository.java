package com.example.tiendaonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tiendaonline.model.LineaPedido;

public interface LineaPedidoRepository extends JpaRepository<LineaPedido, Long> {
	void delete(LineaPedido lineaPedidoAEliminar);
}
