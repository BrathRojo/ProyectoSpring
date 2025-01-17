package com.example.tiendaonline.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.tiendaonline.model.Usuario;
import com.example.tiendaonline.service.UsuarioServiceBD;

import jakarta.validation.Valid;

@Controller
public class UserController {
	
	private String uploadDir = "src/main/resources/static/imagenes";
	
	@Autowired
	private UsuarioServiceBD servicio;
	
	@GetMapping({"/", "usuario/list"})
	public String listado(Model model, @RequestParam(name="q", required=false) String query) {
		List<Usuario> resultado = (query == null) ? servicio.findAll() : servicio.buscador(query);
		model.addAttribute("listaUsuarios", resultado);
		return "list";
	}
	
	@GetMapping("/usuario/new")
	public String nuevoUsuarioForm(Model model) {
		model.addAttribute("usuarioForm", new Usuario());
		return "form";
	}
	
	@PostMapping("/usuario/new/submit")
	public String nuevoUsuarioSubmit(@Valid @ModelAttribute("usuarioForm") Usuario nuevoUsuario,
			BindingResult bindingResult,
			@RequestParam("file") MultipartFile file) {
		System.out.println('H');
		if(bindingResult.hasErrors()) {
			return "form";
		} else {	
			if(!file.isEmpty()) {
				String fileName = StringUtils.cleanPath(file.getOriginalFilename());
				
				Path uploadPath = Paths.get(uploadDir);
				Path filePath = uploadPath.resolve(fileName);
				System.out.print(filePath);
				
				try {
					Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
			servicio.add(nuevoUsuario);
			return "redirect:/usuario/list";
		}
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