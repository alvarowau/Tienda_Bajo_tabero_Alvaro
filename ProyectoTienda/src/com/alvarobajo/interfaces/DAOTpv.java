package com.alvarobajo.interfaces;

import com.alvarobajo.models.Clientes;
import com.alvarobajo.models.Productos;
import com.alvarobajo.models.Trabajadores;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author alvaroBajo
 */
public interface DAOTpv {

    /*
     * con este metodo creo un ArayList de los clientes y esten precargados 
     * cuando se habra la pestaña del TPV
     */
    public List<Clientes> listar(String nombre) throws Exception;

    /*
     *con este metodo creo una lista de productos por su categiria
     * para que se añadan a la tabla del tpv
     */
    public List<Productos> listarProductos(String nombre) throws Exception;

    /*
     * con este metodo desde un id de producto hago que me devuelva un producto
     * con todas sus caracteristicas registrada en la base de datos
     */
    public Productos getProductoID(int prodID) throws Exception;

    /*
     *Con este metodo hago lo mismo que el otro pero desde su nombre   
     */
    public Productos esUnProducto(String nombre) throws SQLException;

    /*
     * con este metodo desde un id de producto hago que me devuelva un cliente
     * con todas sus caracteristicas registrada en la base de datos
     */
    public Clientes esUnCliente(String nombre) throws SQLException;

    /*
     * con este metodo añado la compra al cliente en la base de datos
     */
    public void añadirCompra(Clientes c) throws Exception;

    /*
     * con este metodo modifico la cantidad de productos que quedan despues
     * de una venta en la base de datos
     */
    public void modificarCantidad(Productos p) throws Exception;

    /*
     * con este metodo modifico el volumen de ventas del trabajador 
     * en la base de datos.
    */
    public void modificarVolumenTrabajador(Trabajadores usuario) throws Exception;

}
