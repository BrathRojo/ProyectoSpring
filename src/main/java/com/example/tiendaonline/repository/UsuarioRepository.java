package com.example.tiendaonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tiendaonline.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
