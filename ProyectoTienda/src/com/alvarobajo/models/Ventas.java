
package com.alvarobajo.models;

import java.util.Date;

/**
 *
 * @author alvaroBajo
 */
public class Ventas {
    
    private int id;
    private int idCliente;
    private int idTrabajaor;
    private double precioVenta;
    private Date fechaVenta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdTrabajaor() {
        return idTrabajaor;
    }

    public void setIdTrabajaor(int idTrabajaor) {
        this.idTrabajaor = idTrabajaor;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }    
}
