
package com.alvarobajo.interfaces;

/**
 *
 * @author alvaroBajo
 */

import com.alvarobajo.models.Productos;
import java.util.List;


public interface DAOProductos {
    
     /*
     * este metodo nos va a hacer conectar con la base de datos para registrar un producto
     * @return: void
     */
    public void registrar(Productos productos) throws Exception;
     /*
     * este metodo nos va a hacer conectar con la base de datos para modificar un producto
     * @return: void
     */
    public void modificar(Productos productos) throws Exception;
     /*
     * este metodo nos va a hacer conectar con la base de datos para eliminar un producto
     * @return: void
     */
    public void eliminar(int prodId) throws Exception;
    
    /*
     * este metodo nos va a ayudar a conectar a la base de datos para crear una
     * lista con todos los productos en la base de datos
     *@param: ArayList desde un String 
     *@retunr ArayList
     */
    public List <Productos> listar( String nombre) throws Exception;
    
        /*
     * este metodo nos va a ayudar a conectar a la base de datos para crear una
     * lista con todos los productos en la base de datos desde la tabla de la vista
     *@param: ArayList desde un String 
     *@retunr ArayList
     */
    public List <Productos> listaAbanzada(String colum, String nombre) throws Exception;
    
    
    /*
     * Este metodo nos va a retornar un producto desde su id
     * @param: producto desde INT id
     * @return: Productos
     */
    public Productos getProductoID(int prodID) throws Exception;
      
}
