package com.example.tiendaonline.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class LineaPedido {

    @Id @GeneratedValue
    private long id;
    private long cantidad;
    private float precioLinea;

    @ManyToOne
    private Pedido pedido;

    @ManyToOne 
    private Producto producto;

    public LineaPedido(long cantidad, Producto producto, Pedido pedido) {
        this.cantidad = cantidad;
        this.producto = producto;
        this.precioLinea = producto.getPrecioUnitario() * cantidad;
        this.pedido = pedido;
    }

    public LineaPedido() {
    }

    // Getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecioLinea() {
        return precioLinea;
    }

    public void setPrecioLinea(float precioLinea) {
        this.precioLinea = precioLinea;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
