
package com.alvarobajo.interfaces;

import com.alvarobajo.models.Clientes;
import java.util.List;

/**
 *
 * @author alvaroBajo
 */


public interface DAOClientes {
    
    /*
     * este metodo nos va a hacer conectar con la base de datos para refgistrar un cliente
     * @return: void
     */
    public void registrar(Clientes clientes) throws Exception;
    /*
     * este metodo nos va a hacer conectar con la base de datos para modificar un cliente
     * @return: void
     */
    public void modificar(Clientes clientes) throws Exception;
    /*
     * este metodo nos va a hacer conectar con la base de datos para eliminar un cliente
     * @return: void
     */
    public void eliminar(int cliId) throws Exception;
    
    /*
     * este metodo nos va a ayudar a conectar a la base de datos para crear una
     * lista con todos los clientes en la base de datos
     *@param: ArayList desde un String 
     *@retunr ArayList
     */
    public List <Clientes> listar( String nombre) throws Exception;
    
    /*
     * Este metodo nos va a retornar un cliente desde su id
     * @param: cliente desde INT id
     * @return: cliente
     */
    public Clientes getClienteID(int prodID) throws Exception;
}
