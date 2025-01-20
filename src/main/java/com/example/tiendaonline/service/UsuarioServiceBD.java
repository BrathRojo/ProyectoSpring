package com.example.tiendaonline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.tiendaonline.model.Pedido;
import com.example.tiendaonline.model.Usuario;
import com.example.tiendaonline.repository.UsuarioRepository;

@Primary
@Service("usuarioServiceBD")
public class UsuarioServiceBD implements UsuarioService {
	
	@Autowired
	private UsuarioRepository repositorio;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Usuario add(Usuario u) {
		u.setContraseña(passwordEncoder.encode(u.getContraseña()));
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
	
	@Override
	public void borraUsuario(long id) {
		repositorio.deleteById(id);
	}
	
	@Override
	public Usuario findByEmail(String email) {
		return repositorio.findByEmail(email);
	}
	
	public Usuario buscador(String cadena) {
		 return repositorio.findFirstByNombreContainsIgnoreCaseOrEmailContainsIgnoreCaseOrTelefonoContainsIgnoreCase(cadena, cadena, cadena);
	}
}
