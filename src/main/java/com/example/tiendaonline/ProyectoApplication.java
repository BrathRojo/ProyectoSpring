package com.example.tiendaonline;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.tiendaonline.model.Usuario;
import com.example.tiendaonline.repository.UsuarioRepository;

@SpringBootApplication
public class ProyectoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoApplication.class, args);
	}
	
	@Bean
	CommandLineRunner initData(UsuarioRepository repositorio) {
		return (args) -> {
			repositorio.saveAll(
					Arrays.asList(new Usuario(
							"admin", 
							"", 
							"admin", 
							"admin", 
							"admin@admin.com", 
							"654564654a", 
							"", 
							"987654321", 
							0L)
							)
					);
		};
	}
}
