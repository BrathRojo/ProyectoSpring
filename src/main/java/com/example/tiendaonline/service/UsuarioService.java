package com.example.tiendaonline.service;

import java.util.List;

import com.example.tiendaonline.model.Usuario;

public interface UsuarioService {
	public Usuario add(Usuario u);
	public List<Usuario> findAll();
	public Usuario findById(long id);
	public Usuario edit(Usuario u);
}
