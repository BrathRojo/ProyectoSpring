package com.example.tiendaonline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.tiendaonline.model.Pedido;
import com.example.tiendaonline.repository.PedidoRepository;

@Primary
@Service("pedidoServiceBD")
public class PedidoServiceBD implements PedidoService {
	
	@Autowired
	private PedidoRepository repositorio;
	
	@Override
	public Pedido add(Pedido p) {
		return repositorio.save(p);
	}
	
	@Override
	public List<Pedido> findAll() {
		return repositorio.findAll();
	}
	
	@Override
	public Pedido findById(long id) {
		return repositorio.findById(id).orElse(null);
	}
	
	@Override
	public Pedido edit(Pedido p) {
		return repositorio.save(p);
	}
}
