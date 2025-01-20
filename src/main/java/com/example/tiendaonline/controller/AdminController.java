package com.example.tiendaonline.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.tiendaonline.model.Categoria;
import com.example.tiendaonline.model.Producto;
import com.example.tiendaonline.model.Usuario;
import com.example.tiendaonline.service.CategoriaServiceBD;
import com.example.tiendaonline.service.ProductoServiceBD;
import com.example.tiendaonline.service.UsuarioServiceBD;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/zonaAdmin")
public class AdminController {
	
	private final String uploadDir = "src/images";

    @Autowired
    private CategoriaServiceBD categoriaService;
    @Autowired
    private ProductoServiceBD productoService;
    @Autowired
    private UsuarioServiceBD usuarioService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping({"/", "/zonaAdmin"})
	public String zonaAdmin() {
		return "admin";
	}
    
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("productoForm", new Producto());
        model.addAttribute("categoriaForm", new Categoria());
        model.addAttribute("usuarioForm", new Usuario());
        
        model.addAttribute("listaCategorias", categoriaService.findAll());
        
        return "nuevo";
    }

    @PostMapping("/nuevo/usuario/submit")
    public String nuevoUsuario(@Valid @ModelAttribute("usuarioForm") Usuario nuevoUsuario,
			BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
			return "registro";
		} else {
			usuarioService.add(nuevoUsuario);
			return "redirect:/zonaAdmin/nuevo#headingUsuario";
		}
    }
    
    @PostMapping("/nuevo/categoria/submit")
    public String nuevoCategoria(@Valid @ModelAttribute("categoriaForm") Categoria nuevaCategoria,
			BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
			return "registro";
		} else {
			categoriaService.add(nuevaCategoria);
			return "redirect:/zonaAdmin/nuevo#headingCategoria";
		}
    }
    
    @PostMapping("/nuevo/producto/submit")
    public String nuevoProducto(@Valid @ModelAttribute("productoForm") Producto nuevoProducto,
                            @RequestParam(value = "categorias", required = false) List<Long> categoriasIds,
                            BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
        return "nuevo";
    } else {
        if (categoriasIds != null) {
            List<Categoria> categorias = categoriasIds.stream()
                    .map(id -> categoriaService.findById(id))
                    .collect(Collectors.toList());
            nuevoProducto.setCategorias(categorias);
        }

        productoService.add(nuevoProducto);
        return "redirect:/zonaAdmin/nuevo#headingProducto";
    }
}
    

    @GetMapping("/categorias/editar/{id}")
    public String editarCategoriaForm(@PathVariable long id, Model model) {
        Categoria categoria = categoriaService.findById(id);
        model.addAttribute("categoriaForm", categoria);
        return "editar";
    }


    @PostMapping("/categorias/editar")
    public String editarCategoria(@ModelAttribute("categoriaForm") Categoria categoria) {
        categoriaService.edit(categoria);
        return "redirect:/editar";
    }
    
    @GetMapping("/editar")
    public String editar(Model model) {
        model.addAttribute("productos", productoService.findAll());
        model.addAttribute("categorias", categoriaService.findAll());
        model.addAttribute("usuarios", usuarioService.findAll());
        return "editar";
    }
    
    @PostMapping("/eliminar/categoria/{id}")
    public String eliminarCategoria(@PathVariable long id) {
    	categoriaService.borraCategoria(id);
    	return "redirect:/zonaAdmin/editar#categorias";
    }

    @PostMapping("/eliminar/producto/{id}")
    public String eliminarProduct(@PathVariable long id) {
    	productoService.borraProducto(id);
    	return "redirect:/zonaAdmin/editar#productos";
    }
    
    @PostMapping("/eliminar/usuario/{id}")
    public String eliminarUsuario(@PathVariable long id) {
    	usuarioService.borraUsuario(id);
    	return "redirect:/zonaAdmin/editar#usuarios";
    }
    
    @GetMapping("/editar/usuario/{id}")
    public String editarUsuario(@PathVariable long id, Model model) {
        Usuario usuario = usuarioService.findById(id);
        model.addAttribute("usuarioAEditar", usuario);
        return "usuario";
    }
    
    @PostMapping("/editar/usuario/submit")
    public String editarUsuarioSubmit(@ModelAttribute("usuarioAEditar") Usuario usuario) {
    	String contraseña = usuario.getContraseña();
    	usuario.setContraseña(passwordEncoder.encode(contraseña));
    	usuarioService.edit(usuario);
    	return "redirect:/zonaAdmin/editar"; 
    }
    
    @GetMapping("/editar/categoria/{id}")
    public String editarCategoria(@PathVariable long id, Model model) {
    	Categoria categoria = categoriaService.findById(id);
        model.addAttribute("categoriaAEditar", categoria);
        return "categoria";
    }
    
    @PostMapping("/editar/categoria/submit")
    public String editarCategoriaSubmit(@ModelAttribute("categoriaAEditar") Categoria categoria) {
    	categoriaService.edit(categoria);
    	return "redirect:/zonaAdmin/editar"; 
    }
    
    @GetMapping("/editar/producto/{id}")
    public String editarProducto(@PathVariable long id, Model model) {
    	Producto producto = productoService.findById(id);
        model.addAttribute("productoAEditar", producto);
        
        model.addAttribute("listaCategorias", categoriaService.findAll());
        return "producto";
    }
    
    @PostMapping("/editar/producto/submit")
    public String editarProductoSubmit(@ModelAttribute("productoAEditar") Producto producto) {
    	productoService.edit(producto);
    	return "redirect:/zonaAdmin/editar"; 
    }
}
