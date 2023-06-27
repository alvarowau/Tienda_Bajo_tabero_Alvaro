
package com.alvarobajo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author alvaroBajo
 */

/*
*con esta clase podremos hacer que todas las implementaciones
*hereden de ella asi no tendremos que crear en todas una conexion, esta clase
*nos facilitara mucho el trabajo a lo largo del proyecto
*/
public class GestionSql {
        protected Connection conexion;
        private final String DB_URL = "jdbc:mysql://localhost:3306/proyectotienda";
        private final String USER = "root";
        private final String PASS = "";
                
    public Connection openConnection()   {
        //Conectamos a la BDD con las credenciales indicadas antes arriba    
        try{ 
            conexion = DriverManager.getConnection(DB_URL,USER, PASS);
            return conexion;
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return null;
    }
    
    public void closeConnection() throws SQLException{
        if (conexion != null){ //comprobamos que conexion sea diferente de nula y si es diferente de nula:
            if (conexion.isClosed()){//comprobamos que no este cerrada y si no esta cerrada, la cerramos.
                conexion.close();
            }
        }
    }
    
    
}
