
package com.alvarobajo.interfaces;

import com.alvarobajo.models.Trabajadores;
import java.util.List;

/**
 *
 * @author alvaroBajo
 */
public interface DAOTrabajadores {
    /*
    * con este metodo registro un Trabajador para que pueda usar la aplicacion
    */
    public void registrar(Trabajadores t) throws Exception;
    
    /*
    * con este metodo modifico a un Trabajador ya registrado
    */
    public void modificar(Trabajadores t) throws Exception;
    
    /*
    * con este metodo podemos eliminar un Trabajador 
    */
    public void eliminar(int traId) throws Exception;
    
    /*
    *con este metodo podemoa listar todos los trabajadores que tenemos registrados
    */
    public List <Trabajadores> listar(String nombre) throws Exception;
    
    /*
    *con este metodo podemos buscar un trabajador por su id
    */
    public Trabajadores getTrabajadorID(int TraId) throws Exception;
    
    
    
    
}
