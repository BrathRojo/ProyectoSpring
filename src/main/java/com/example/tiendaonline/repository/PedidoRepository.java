package com.example.tiendaonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tiendaonline.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
