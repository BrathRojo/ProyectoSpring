package com.example.tiendaonline.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.tiendaonline.model.Usuario;

@Service("usuarioServiceMemory")
public class UsuarioServiceMemory {
	
	private List<Usuario> repositorio = new ArrayList<>();

}