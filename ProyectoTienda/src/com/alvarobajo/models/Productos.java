
package com.alvarobajo.models;

import java.util.Date;

/**
 *
 * @author alvaroBajo
 */
public class Productos {
    
    private int id;
    private String codigo;
    private String producto;
    private String precio_compra;
    private String precio_venta;
    private String cantidad;
    private String estado;
    private String descripcion;
    private String categoria;
    private String vendidos;
    private Date fecha_venta;

    public Date getFecha_venta() {
        return fecha_venta;
    }

    @Override
    public String toString() {
        return "Productos{" + "id=" + id + ", codigo=" + codigo + ", producto=" + producto + ", precio_compra=" + precio_compra + ", precio_venta=" + precio_venta + ", cantidad=" + cantidad + ", estado=" + estado + ", descripcion=" + descripcion + ", categoria=" + categoria + ", vendidos=" + vendidos + ", fecha_venta=" + fecha_venta + '}';
    }

    public void setFecha_venta(Date fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(String precio_compra) {
        this.precio_compra = precio_compra;
    }

    public String getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(String precio_venta) {
        this.precio_venta = precio_venta;
    }


    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getVendidos() {
        return vendidos;
    }

    public void setVendidos(String vendidos) {
        this.vendidos = vendidos;
    }
    
    
    
}
