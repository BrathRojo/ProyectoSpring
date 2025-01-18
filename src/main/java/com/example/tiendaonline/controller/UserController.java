package com.example.tiendaonline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tiendaonline.model.Usuario;
import com.example.tiendaonline.repository.UsuarioRepository;
import com.example.tiendaonline.service.UsuarioServiceBD;

import jakarta.validation.Valid;

@Controller
public class UserController {
	
	private String uploadDir = "src/imagenes";
	
	@Autowired
	private UsuarioServiceBD servicio;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping({"/", "/index"})
	public String listado(Model model, @RequestParam(name="q", required=false) String query) {
		List<Usuario> resultado = (query == null) ? servicio.findAll() : servicio.buscador(query);
		model.addAttribute("listaUsuarios", resultado);
		return "index";
	}
	
	@GetMapping("/usuario/new")
	public String registro(Model model) {
		model.addAttribute("usuarioForm", new Usuario());
		return "registro";
	}
	
	@PostMapping("/usuario/new/submit")
	public String registrarUsuario(@Valid @ModelAttribute("usuarioForm") Usuario nuevoUsuario,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "registro";
		} else {
			nuevoUsuario.setContraseña(passwordEncoder.encode(nuevoUsuario.getContraseña()));
			servicio.add(nuevoUsuario);
			return "redirect:/login";
		}
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		
		return "login";
	}
	
	@PostMapping("/login/submit")
	public String iniciarSesion(@RequestParam String nombre, @RequestParam String contraseña, Model model) {
	    Usuario usuario = new Usuario();
	    System.out.println(usuario);

	    if (usuario != null) {
	        // Comprobación de la contraseña codificada
	        if (passwordEncoder.matches(contraseña, usuario.getContraseña())) {
	            return "redirect:/index"; // Redirige al índice si la autenticación es exitosa
	        } else {
	            // Si la contraseña no es correcta
	            model.addAttribute("error", "Nombre de usuario o contraseña incorrectos.");
	            return "login"; // Muestra la vista de login con un mensaje de error
	        }
	    } else {
	        // Si no se encuentra el usuario
	        model.addAttribute("error", "Nombre de usuario o contraseña incorrectos.");
	        return "login"; // Muestra la vista de login con un mensaje de error
	    }
	}
	
	@GetMapping("/zonaAdmin")
	public String zonaAdmin() {
		return "admin";
	}
	
	@GetMapping("/usuario/edit/{id}")
	public String editarUsuarioForm(@PathVariable long id, Model model) {
		Usuario usuario = servicio.findById(id);
		if(usuario != null) {
			model.addAttribute("usuarioForm", usuario);
			return "form";
		} else {
			return "redirect:/usuario/new";
		}
	}
	
	@PostMapping("/usuario/edit/submit")
	public String editarUsuarioSubmit(@Valid @ModelAttribute("usuarioForm") Usuario usuario, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "form";
		} else {			
			servicio.edit(usuario);
			return "redirect:/usuario/list";
		}
	}
}