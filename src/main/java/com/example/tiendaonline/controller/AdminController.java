package com.example.tiendaonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.tiendaonline.model.Categoria;
import com.example.tiendaonline.model.Producto;
import com.example.tiendaonline.model.Usuario;
import com.example.tiendaonline.service.CategoriaService;
import com.example.tiendaonline.service.ProductoService;
import com.example.tiendaonline.service.UsuarioService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private UsuarioService usuarioService;

    
    @GetMapping("/categorias")
    public String categorias(Model model) {
        model.addAttribute("categorias", categoriaService.findAll());
        return "admin";
    }

    @GetMapping("/categorias/nueva")
    public String nuevaCategoriaForm(Model model) {
        model.addAttribute("categoriaForm", new Categoria());
        return "admin";
    }

    
    @PostMapping("/categorias/nueva")
    public String agregarCategoria(@ModelAttribute("categoriaForm") Categoria categoria) {
        categoriaService.add(categoria);
        return "redirect:/admin/categorias";
    }


    @GetMapping("/categorias/editar/{id}")
    public String editarCategoriaForm(@PathVariable("id") long id, Model model) {
        Categoria categoria = categoriaService.findById(id);
        model.addAttribute("categoriaForm", categoria);
        return "admin";
    }


    @PostMapping("/categorias/editar")
    public String editarCategoria(@ModelAttribute("categoriaForm") Categoria categoria) {
        categoriaService.edit(categoria);
        return "redirect:/admin/categorias";
    }


    
    @GetMapping("/productos")
    public String productos(Model model) {
        model.addAttribute("productos", productoService.findAll());
        model.addAttribute("categorias", categoriaService.findAll());
        return "admin";
    }


    @GetMapping("/productos/nuevo")
    public String nuevoProductoForm(Model model) {
        model.addAttribute("productoForm", new Producto());
        model.addAttribute("categorias", categoriaService.findAll());
        return "admin";
    }


    @PostMapping("/productos/nuevo")
    public String agregarProducto(@ModelAttribute("productoForm") Producto producto) {
        productoService.add(producto);
        return "redirect:/admin/productos";
    }


    @GetMapping("/productos/editar/{id}")
    public String editarProductoForm(@PathVariable("id") long id, Model model) {
        Producto producto = productoService.findById(id);
        model.addAttribute("productoForm", producto);
        model.addAttribute("categorias", categoriaService.findAll());
        return "admin";
    }


    @PostMapping("/productos/editar")
    public String editarProducto(@ModelAttribute("productoForm") Producto producto) {
        productoService.edit(producto);
        return "redirect:/admin/productos";
    }


    @PostMapping("/productos/eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") long id) {
        productoService.borraProducto(id); 
        return "redirect:/admin/productos";
    }



    
    @GetMapping("/usuarios")
    public String usuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.findAll());
        return "admin";
    }


    @GetMapping("/usuarios/nuevo")
    public String nuevoUsuarioForm(Model model) {
        model.addAttribute("usuarioForm", new Usuario());
        return "admin";
    }


    @PostMapping("/usuarios/nuevo")
    public String agregarUsuario(@ModelAttribute("usuarioForm") Usuario usuario) {
        usuarioService.add(usuario);
        return "redirect:/admin/usuarios";
    }


    @GetMapping("/usuarios/editar/{id}")
    public String editarUsuarioForm(@PathVariable("id") long id, Model model) {
        Usuario usuario = usuarioService.findById(id);
        model.addAttribute("usuarioForm", usuario);
        return "admin";
    }


    @PostMapping("/usuarios/editar")
    public String editarUsuario(@ModelAttribute("usuarioForm") Usuario usuario) {
        usuarioService.edit(usuario);
        return "redirect:/admin/usuarios";
    }
}
