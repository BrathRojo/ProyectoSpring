package com.example.tiendaonline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.tiendaonline.model.Usuario;
import com.example.tiendaonline.repository.UsuarioRepository;

@Primary
@Service("usuarioServiceBD")
public class UsuarioServiceBD implements UsuarioService {
	
	@Autowired
	private UsuarioRepository repositorio;
	
	@Override
	public Usuario add(Usuario u) {
		return repositorio.save(u);
	}
	
	@Override
	public List<Usuario> findAll() {
		return repositorio.findAll();
	}
	
	@Override
	public Usuario findById(long id) {
		return repositorio.findById(id).orElse(null);
	}
	
	@Override
	public Usuario edit(Usuario u) {
		return repositorio.save(u);
	}
	
	public List<Usuario> buscador(String cadena) {
		 return repositorio.findByNombreContainsIgnoreCaseOrEmailContainsIgnoreCaseOrTelefonoContainsIgnoreCase(cadena, cadena, cadena);
	}
}
