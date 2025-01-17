package com.example.tiendaonline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tiendaonline.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	List<Usuario> findByNombreContainsIgnoreCaseOrEmailContainsIgnoreCaseOrTelefonoContainsIgnoreCase(String nombre, String email, String telefono);

}
