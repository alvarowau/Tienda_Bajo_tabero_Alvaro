
package com.alvarobajo.interfaces;

import com.alvarobajo.models.Clientes;
import java.util.List;

/**
 *
 * @author alvaroBajo
 */
public interface DAOVentas {
    
    /*
    *con este metodo hacemos una lista para los clientes
    */
    public List<Clientes> listar(String nombre) throws Exception;
    /*
    *con este metodo por el nombre de un cliente obtenemos su id
    * este metodo lo usare para poder hacer la factura
    */
    public int getClienteID(String nombre) throws Exception;
    
    public void clienteVentas(String nombres)throws Exception;
    /*
    *con este metodo puedo obtener ventas por la fecha
    */
    public void ventasPorFecha() throws Exception;
    
}
