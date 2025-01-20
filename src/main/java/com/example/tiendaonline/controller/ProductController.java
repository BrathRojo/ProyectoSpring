package com.example.tiendaonline.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.tiendaonline.model.LineaPedido;
import com.example.tiendaonline.model.Pedido;
import com.example.tiendaonline.model.Producto;
import com.example.tiendaonline.model.Usuario;
import com.example.tiendaonline.service.CategoriaServiceBD;
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
	
	@GetMapping("/productoDetalles/{id}")
	public String Detalles(@PathVariable long id, Model model) {
		Producto producto = productoService.findById(id);
        model.addAttribute("producto", producto);
        
        model.addAttribute("listaCategorias", categoriaService.findAll());
        return "detalles";
	}
	
	@GetMapping("/carrito")
	public String Carrito() {
		return "carrito";
	}
	
	@Transactional
	@PostMapping("/carrito/{id}")
	public String MeterProductoCarrito(@PathVariable Long id, Model model, HttpSession session) {
	    Usuario usuarioActual = (Usuario) session.getAttribute("usuarioLogueado");
	    Producto productoAMeter = productoService.findById(id);

	    if (usuarioActual == null) {
	        return "redirect:/login";
	    }

	    if (productoAMeter == null) {
	        model.addAttribute("error", "Producto no encontrado");
	        return "error";
	    }

	    Pedido carrito = usuarioActual.getPedidos().stream()
	        .filter(Pedido::getPedidoActivo)
	        .findFirst()
	        .orElseGet(() -> {
	            Pedido nuevoPedido = new Pedido(
	                usuarioActual.getNombre(),
	                usuarioActual.getApellidos(),
	                usuarioActual.getDni(),
	                usuarioActual.getDireccion(),
	                0L,
	                new Date()
	            );
	            nuevoPedido.setPedidoActivo(true);
	            usuarioActual.getPedidos().add(nuevoPedido);
	            return nuevoPedido;
	        });

	    LineaPedido lineaExistente = carrito.getLineapedidos().stream()
	            .filter(linea -> linea.getProducto().equals(productoAMeter))
	            .findFirst()
	            .orElse(null);

	    if (lineaExistente != null) {
	        lineaExistente.setCantidad(lineaExistente.getCantidad() + 1);
	        lineaExistente.setPrecioLinea(lineaExistente.getCantidad() * productoAMeter.getPrecioUnitario());
	    } else {
	        LineaPedido nuevaLinea = new LineaPedido();
	        nuevaLinea.setProducto(productoAMeter);
	        nuevaLinea.setCantidad(1);
	        nuevaLinea.setPrecioLinea(productoAMeter.getPrecioUnitario());
	        carrito.getLineapedidos().add(nuevaLinea);
	    }

	    carrito.setPrecioTotal((float) carrito.getLineapedidos().stream()
	        .mapToDouble(LineaPedido::getPrecioLinea)
	        .sum());

	    pedidoService.add(carrito);

	    model.addAttribute("pedido", carrito);

	    return "carrito";
	}

}
