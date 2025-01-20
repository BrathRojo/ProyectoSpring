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

import com.example.tiendaonline.model.Producto;
import com.example.tiendaonline.model.Usuario;
import com.example.tiendaonline.service.ProductoServiceBD;
import com.example.tiendaonline.service.UsuarioServiceBD;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	
	@Autowired
	private UsuarioServiceBD servicio;
	
	@Autowired
	private ProductoServiceBD productos;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping({"/", "/index"})
	public String listado(Model model, HttpSession session) {
	    Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
	    List<Producto> productosDestacados = productos.findTop4ByOrderByVentasDesc();
	    model.addAttribute("productos", productos.findAll());
	    model.addAttribute("productosDestacados", productosDestacados);

	    if (usuarioLogueado != null) {
	        model.addAttribute("usuarioLogueado", usuarioLogueado);
	    }
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
			servicio.add(nuevoUsuario);
			return "redirect:/login";
		}
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}
	
	@PostMapping("/login/submit")
	public String iniciarSesion(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
	    Usuario user = servicio.findByEmail(username);

	    if (user != null) {
	        if (passwordEncoder.matches(password, user.getContraseña())) {
	        	session.setAttribute("usuarioLogueado", user);
	            return "redirect:/index";
	        } else {
	            model.addAttribute("error", "Nombre de usuario o contraseña incorrectos.");
	            return "login";
	        }
	    }
	    else {
            model.addAttribute("error", "Nombre de usuario o contraseña incorrectos.");
	    	return "login";
	    }
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate();
	    return "redirect:/login?logout";
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