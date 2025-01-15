package com.example.tiendaonline.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.tiendaonline.model.Pedido;

@Service("pedidoServiceMemory")
public class PedidoServiceMemory {
	
	private List<Pedido> repositorio = new ArrayList<>();

}