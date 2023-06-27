/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.alvarobajo.interfaces;

/**
 *
 * @author alvaroBajo
 */
public interface DAOReportes {
    
    /*
     * con este metodo creo un reporte en PDF de los Clientes
    */
    public void reportesClientes();
    /*
     * con este metodo creo un reporte en PDF de los Trabajadores
    */
    public void reportesTrabajadores();
    /*
     * con este metodo creo un reporte en PDF de los Productos
    */
    public void reportesProductos();
    /*
     * con este metodo creo un reporte en PDF de las Ventas
    */
    public void reportesVentas();
    
}
