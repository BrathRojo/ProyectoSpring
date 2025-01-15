package com.example.tiendaonline.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.tiendaonline.model.LineaPedido;

@Service("lineaPedidoServiceMemory")
public class LineaPedidoServiceMemory {
	
	private List<LineaPedido> repositorio = new ArrayList<>();

}