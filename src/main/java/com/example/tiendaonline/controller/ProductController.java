package com.example.tiendaonline.controller;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tiendaonline.model.LineaPedido;
import com.example.tiendaonline.model.Pedido;
import com.example.tiendaonline.model.Producto;
import com.example.tiendaonline.model.Usuario;
import com.example.tiendaonline.service.CategoriaServiceBD;
import com.example.tiendaonline.service.LineaPedidoServiceBD;
import com.example.tiendaonline.service.PedidoServiceBD;
import com.example.tiendaonline.service.ProductoServiceBD;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Controller
public class ProductController {

    @Autowired
    private CategoriaServiceBD categoriaService;
    @Autowired
    private ProductoServiceBD productoService;
    @Autowired
    private PedidoServiceBD pedidoService;
    @Autowired
    private LineaPedidoServiceBD lineaPedidoService;

    @GetMapping("/productoDetalles/{id}")
    public String Detalles(@PathVariable long id, Model model, HttpSession session) {
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado != null) {
            model.addAttribute("usuarioLogueado", usuarioLogueado);
        }
        Producto producto = productoService.findById(id);
        model.addAttribute("producto", producto);

        model.addAttribute("listaCategorias", categoriaService.findAll());
        return "detalles";
    }

    @GetMapping("/carrito")
    public String Carrito(Model model, HttpSession session) {
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado != null) {
            model.addAttribute("usuarioLogueado", usuarioLogueado);
        }

        Pedido pedidoActual = null;
        List<Pedido> pedidos = usuarioLogueado.getPedidos();
        if (!pedidos.isEmpty() && pedidos.get(0).getPedidoActivo()) {
            pedidoActual = pedidos.get(0);
        }

        if (pedidoActual != null) {
            model.addAttribute("lineaPedidos", pedidoActual.getLineapedidos());
            model.addAttribute("carritoTotal", pedidoActual.getPrecioTotal());
            model.addAttribute("pedidoactual", pedidoActual);
        } else {
            model.addAttribute("lineaPedidos", new ArrayList<LineaPedido>());
            model.addAttribute("carritoTotal", 0);
        }

        return "carrito";
    }

    @Transactional
    @PostMapping("/carrito/{id}")
    public String MeterProductoCarrito(@PathVariable Long id, @RequestParam("cantidad") int cantidad, Model model, HttpSession session) {
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado != null) {
            model.addAttribute("usuarioLogueado", usuarioLogueado);
        }
        Usuario usuarioActual = (Usuario) session.getAttribute("usuarioLogueado");
        Producto productoAMeter = productoService.findById(id);

        if (usuarioActual == null) {
            return "redirect:/login";
        }

        if (productoAMeter == null) {
            model.addAttribute("error", "Producto no encontrado");
            return "error";
        }
        
        Pedido pedidoActual;
        List<Pedido> pedidos = usuarioActual.getPedidos();
        if (!pedidos.isEmpty() && pedidos.get(0).getPedidoActivo()) {
            pedidoActual = pedidos.get(0);
        } else {
            pedidoActual = new Pedido(
                usuarioActual.getNombre(),
                usuarioActual.getApellidos(),
                usuarioActual.getDni(),
                usuarioActual.getDireccion(),
                usuarioActual
            );
            pedidoService.add(pedidoActual);
        }

        LineaPedido productoRepetido = pedidoActual.getLineapedidos().stream()
            .filter(lineaPedido -> lineaPedido.getProducto().getId() == id)
            .findFirst()
            .orElse(null);

        if (productoRepetido != null) {
            productoRepetido.setCantidad(productoRepetido.getCantidad() + cantidad);
            lineaPedidoService.edit(productoRepetido);
        } else {
            LineaPedido lineaPedidoAMeter = new LineaPedido(cantidad, productoAMeter, pedidoActual);
            pedidoActual.getLineapedidos().add(lineaPedidoAMeter);
            lineaPedidoService.add(lineaPedidoAMeter);
        }

        productoAMeter.setVentas(productoAMeter.getVentas() + cantidad);

        pedidoActual.setPrecioTotal((float) pedidoActual.getLineapedidos().stream()
            .mapToDouble(LineaPedido::getPrecioLinea)
            .sum());

        pedidoService.add(pedidoActual);

        model.addAttribute("lineaPedidos", pedidoActual.getLineapedidos());
        model.addAttribute("carritoTotal", pedidoActual.getPrecioTotal());

        return "carrito";
    }

    @Transactional
    @PostMapping("/carrito/eliminar/{id}")
    public String EliminarProductoCarrito(@PathVariable Long id, Model model, HttpSession session) {
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado != null) {
            model.addAttribute("usuarioLogueado", usuarioLogueado);
        }
        Usuario usuarioActual = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioActual == null) {
            return "redirect:/login";
        }

        Pedido pedidoActual;
        List<Pedido> pedidos = usuarioActual.getPedidos();
        if (!pedidos.isEmpty() && pedidos.get(0).getPedidoActivo()) {
            pedidoActual = pedidos.get(0);
        } else {
            return "redirect:/carrito";
        }

        LineaPedido lineaPedidoAEliminar = pedidoActual.getLineapedidos().stream()
            .filter(lineaPedido -> lineaPedido.getId() == id)
            .findFirst()
            .orElse(null);

        if (lineaPedidoAEliminar != null) {
            pedidoActual.getLineapedidos().remove(lineaPedidoAEliminar);
            lineaPedidoService.delete(lineaPedidoAEliminar);
        }

        pedidoActual.setPrecioTotal((float) pedidoActual.getLineapedidos().stream()
            .mapToDouble(LineaPedido::getPrecioLinea)
            .sum());

        pedidoService.add(pedidoActual);

        model.addAttribute("lineaPedidos", pedidoActual.getLineapedidos());
        model.addAttribute("carritoTotal", pedidoActual.getPrecioTotal());

        return "carrito";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam String query, @RequestParam(required = false) Long categoria, Model model) {
        List<Producto> productos;
        if (categoria != null) {
            productos = productoService.findByNombreContainingAndCategoriasId(query, categoria);
        } else {
            productos = productoService.findByNombreContaining(query);
        }
        model.addAttribute("productos", productos);
        model.addAttribute("listaCategorias", categoriaService.findAll());
        return "index";
    }
    
    @PostMapping("/carrito/submit")
    public String pagoRealizado(Model model, HttpSession session) {
        Usuario usuarioActual = (Usuario) session.getAttribute("usuarioLogueado");

    	Pedido pedidoActual = usuarioActual.getPedidos().get(0);
    	pedidoActual.setPedidoActivo(false);
        model.addAttribute("carritoTotal", pedidoActual.getPrecioTotal());
    	model.addAttribute("mensajePedido", "Pedido Realizado");
    	
    	return "carrito";
    }

}
