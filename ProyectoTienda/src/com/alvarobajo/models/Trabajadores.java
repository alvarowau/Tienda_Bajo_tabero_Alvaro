
package com.alvarobajo.models;

import java.util.Date;

/**
 *
 * @author alvaroBajo
 */
public class Trabajadores {
    private int id;
    private String dni;
    private String clave;
    private String nombre;
    private String email;
    private String telefono;
    private String direcion;
    private int numero_ventas;
    private String volumen_ventas;
    private Date fecha_alta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
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

    public String getDirecion() {
        return direcion;
    }

    public void setDirecion(String direcion) {
        this.direcion = direcion;
    }

    public int getNumero_ventas() {
        return numero_ventas;
    }

    public void setNumero_ventas(int numero_ventas) {
        this.numero_ventas = numero_ventas;
    }

    public String getVolumen_ventas() {
        return volumen_ventas;
    }

    public void setVolumen_ventas(String volumen_ventas) {
        this.volumen_ventas = volumen_ventas;
    }

    public Date getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(Date fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    @Override
    public String toString() {
        return "Trabajadores{" + "id=" + id + ", dni=" + dni + ", clave=" + clave + ", nombre=" + nombre + ", email=" + email + ", telefono=" + telefono + ", direcion=" + direcion + ", numero_ventas=" + numero_ventas + ", volumen_ventas=" + volumen_ventas + ", fecha_alta=" + fecha_alta + '}';
    }
    
    
    
    
    
}
