
package com.alvarobajo.interfaces;



/**
 *
 * @author alvaroBajo
 */
public interface DAOVentaPDF {
    
    
    /*
    * con este metodo quiero conseguir traer todos los datos del cliente que hace 
    * la compra para poder añadirle en la factura
    */
    public void datosClientes(int idCliente) throws Exception;
    
    /*
    *con este metodo vamos a generar la factura con los datos o no obtenidos del
    *metodo anterior
    */
    public void generarPDF();
        
    
    
}
