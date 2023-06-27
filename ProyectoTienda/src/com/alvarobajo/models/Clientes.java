
package com.alvarobajo.models;

import java.util.Date;

/**
 *
 * @author alvaroBajo
 */
public class Clientes {
    private int id;
    private String nombre;
    private String email;
    private String telefono;
    private String direccion;
    private int numero_compras;
    private String dinero_gastado;
    private String descuento;
    private Date fecha_alta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getNumero_compras() {
        return numero_compras;
    }

    public void setNumero_compras(int numero_compras) {
        this.numero_compras = numero_compras;
    }

    public String getDinero_gastado() {
        return dinero_gastado;
    }

    public void setDinero_gastado(String dinero_gastado) {
        this.dinero_gastado = dinero_gastado;
    }

    public String getDescuento() {
        return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    public Date getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(Date fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    @Override
    public String toString() {
        return "Clientes{" + "id=" + id + ", nombre=" + nombre + ", email=" + email + ", telefono=" + telefono + ", direccion=" + direccion + ", numero_compras=" + numero_compras + ", dinero_gastado=" + dinero_gastado + ", descuento=" + descuento + ", fecha_alta=" + fecha_alta + '}';
    }
    
    
    
    
}
