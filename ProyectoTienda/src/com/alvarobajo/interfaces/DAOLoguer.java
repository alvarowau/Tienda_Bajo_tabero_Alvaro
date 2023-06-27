
package com.alvarobajo.interfaces;

/**
 *
 * @author alvaroBajo
 */
public interface DAOLoguer {
    
    /*
     * Desde este metodo quiero conseguir obtener un boolean en el que 
     * si esta el y coincide el nombre con la clave nos devuelva un true
     */
    public boolean login(String usuario, String clave) ;
    
}
