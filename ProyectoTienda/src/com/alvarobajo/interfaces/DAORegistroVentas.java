
package com.alvarobajo.interfaces;

import com.alvarobajo.models.DetalleVenta;
import com.alvarobajo.models.Ventas;
import java.util.List;

/**
 *
 * @author alvaroBajo
 */
public interface DAORegistroVentas {
    
    /*
     * con este metodo registro la venta en la base de datos
    */
    public void registrar(Ventas v) throws Exception ;
    
    /*
     * con este metodo registro el detalle de la venta en la base de datos
    */
    public void registrarDetalle(DetalleVenta d) throws Exception ;
    
    public List<Ventas> listar() throws Exception ;
    
}
