
package com.alvarobajo.models;

/**
 *
 * @author alvaroBajo
 */
public class DetalleVenta {
    private int id;
    private int id_venta;
    private int id_producto;
    //esta de mas
    private String nombre;
    private int cantidad;
    private double precio_unitario;
    private double sub_total;
    private double descuento;
    private double iva;
    private double total_pagar;

    
    public DetalleVenta(){
        this.id = 0;
        this.id_venta = 0;
        this.id_producto = 0;
        this.nombre = "";
        this.cantidad = 0;
        this.precio_unitario = 0.0;
        this.sub_total = 0.0;
        this.total_pagar = 0.0;
    }

    public DetalleVenta(int id, int id_venta, int id_producto, String nombre, int cantidad, double precio_unitario, double sub_total, double descuento, double total_pagar) {
        this.id = id;
        this.id_venta = id_venta;
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
        this.sub_total = sub_total;
        this.descuento = descuento;
        this.total_pagar = total_pagar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public double getSub_total() {
        return sub_total;
    }

    public void setSub_total(double sub_total) {
        this.sub_total = sub_total;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotal_pagar() {
        return total_pagar;
    }

    public void setTotal_pagar(double total_pagar) {
        this.total_pagar = total_pagar;
    }
    
    



    
}
