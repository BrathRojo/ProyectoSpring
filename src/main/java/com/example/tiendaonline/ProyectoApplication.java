package com.example.tiendaonline;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.tiendaonline.model.Categoria;
import com.example.tiendaonline.model.Producto;
import com.example.tiendaonline.model.Usuario;
import com.example.tiendaonline.repository.CategoriaRepository;
import com.example.tiendaonline.repository.ProductoRepository;
import com.example.tiendaonline.repository.UsuarioRepository;

@SpringBootApplication
public class ProyectoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProyectoApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(UsuarioRepository usuarioRepositorio, PasswordEncoder passwordEncoder, ProductoRepository productoRepositorio, CategoriaRepository categoriasRepositorio) {
        return (args) -> {
            if (usuarioRepositorio.count() == 0) {
                String encodedPassword = passwordEncoder.encode("admin");
                usuarioRepositorio.saveAll(
                    Arrays.asList(
                        new Usuario(
                            "admin", 
                            "", 
                            encodedPassword,
                            "ADMIN",
                            "admin@admin.com", 
                            "654564654a", 
                            "", 
                            "987654321"
                        )
                    )
                );
            }

            if (categoriasRepositorio.count() == 0) {
                List<Categoria> listaCategorias = Arrays.asList(
                    new Categoria("Guitarras"),
                    new Categoria("Cuerda")
                );
                categoriasRepositorio.saveAll(listaCategorias);

                if (productoRepositorio.count() == 0) {
                    productoRepositorio.saveAll(
                        Arrays.asList(
                            new Producto(
                                "Producto de prueba",
                                10L,
                                "Este es un producto de prueba con categorías",
                                100L,
                                listaCategorias
                            )
                        )
                    );
                }
            }
        };
    }
}
