package com.example.tiendaonline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.tiendaonline.model.LineaPedido;
import com.example.tiendaonline.repository.LineaPedidoRepository;

@Primary
@Service("lineaPedidoServiceBD")
public class LineaPedidoServiceBD implements LineaPedidoService {
	
	@Autowired
	private LineaPedidoRepository repositorio;
	
	@Override
	public LineaPedido add(LineaPedido lp) {
		return repositorio.save(lp);
	}
	
	@Override
	public List<LineaPedido> findAll() {
		return repositorio.findAll();
	}
	
	@Override
	public LineaPedido findById(long id) {
		return repositorio.findById(id).orElse(null);
	}
	
	@Override
	public LineaPedido edit(LineaPedido lp) {
		return repositorio.save(lp);
	}
}
