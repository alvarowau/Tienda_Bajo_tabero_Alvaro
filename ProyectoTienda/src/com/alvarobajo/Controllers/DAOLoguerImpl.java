package com.alvarobajo.Controllers;

import com.alvarobajo.db.GestionSql;
import com.alvarobajo.interfaces.DAOLoguer;
import com.alvarobajo.models.Trabajadores;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author alvaroBajo
 */
public class DAOLoguerImpl extends GestionSql implements DAOLoguer {

    

    public static Trabajadores us = new Trabajadores();

    
    /*
     * este metodo aparte de crear el logueo va a guardar en una variable estatica 
     * el nombre de usuario logueado para cada vez que se haga un venta le sume el dinero
     * facturado y el numero de ventas que hace este vendedor.
     * lo devuelve en la varible Trabajadores us
     *@return Trabajadores
    */
    
    @Override
    public boolean login(String usuario, String clave) {
        boolean respuesta = false;
        try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            PreparedStatement str = (PreparedStatement) this.conexion.prepareStatement("SELECT nombre, clave FROM trabajadores where nombre = '" + usuario + "' and clave = '" + clave + "' ");
            ResultSet rst = str.executeQuery();
            while (rst.next()) {
                respuesta = true;
            }
            rst.close();
            str.close();

            if (respuesta) {
                this.openConnection();//Establecemos la conexion a la base de datos.
                PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("SELECT * FROM trabajadores WHERE nombre = ? LIMIT 1;");
                st.setString(1, usuario);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    us.setId(rs.getInt("id"));
                    us.setDni(rs.getString("dni"));
                    us.setClave(rs.getString("clave"));
                    us.setNombre(rs.getString("nombre"));
                    us.setEmail(rs.getString("email"));
                    us.setTelefono(rs.getString("telefono"));
                    us.setDirecion(rs.getString("direcion"));
                    us.setNumero_ventas(rs.getInt("numero_ventas"));
                    us.setVolumen_ventas(rs.getString("volumen_ventas"));
                    us.setFecha_alta(rs.getDate("fecha_alta"));
                }
                rs.close();
                st.close();

            }
        } catch (SQLException e) {
            System.out.println("ERROR EN DAOLOGUERIMPL" + e);;
        }
        return respuesta;
    }

}
