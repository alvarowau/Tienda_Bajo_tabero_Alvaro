package com.alvarobajo.Controllers;

import com.alvarobajo.db.GestionSql;
import com.alvarobajo.interfaces.DAOTrabajadores;
import com.alvarobajo.models.Trabajadores;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alvaroBajo
 */
public class DAOTrabajadoresImpl extends GestionSql implements DAOTrabajadores {

    @Override
    public void registrar(Trabajadores t) throws Exception {
        try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("INSERT INTO trabajadores( dni, clave,nombre,email,telefono,direcion) VALUES (?,?,?,?,?,?);");
            st.setString(1, t.getDni());
            st.setString(2, t.getClave());
            st.setString(3, t.getNombre());
            st.setString(4, t.getEmail());
            st.setString(5, t.getTelefono());
            st.setString(6, t.getDirecion());
            // st.setDate(7, (Date) t.getFecha_alta());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            System.out.println("CAE EN DAOTRABAJADORESIMPL" +e);
        } finally {
            this.closeConnection();

        }
    }

    @Override
    public void modificar(Trabajadores t) throws Exception {
        try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("UPDATE trabajadores SET dni = ?, clave = ?, nombre = ?, email = ?, telefono = ?, direcion = ? WHERE id = ?");
            st.setString(1, t.getDni());
            st.setString(2, t.getClave());
            st.setString(3, t.getNombre());
            st.setString(4, t.getEmail());
            st.setString(5, t.getTelefono());
            st.setString(6, t.getDirecion());
            st.setInt(7, t.getId());     
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            System.out.println("CAE EN DAOTRABAJADORESIMPL" +e);
        } finally {
            this.closeConnection();

        }
    }

    @Override
    public void eliminar(int traId) throws Exception {
        try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("DELETE FROM trabajadores WHERE id= ?;");
            st.setInt(1, traId);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            System.out.println("CAE EN DAOTRABAJADORESIMPL" +e);
        } finally {
            this.closeConnection();

        }
    }

    @Override
    public List<Trabajadores> listar(String nombre) throws Exception {
        List<Trabajadores> lista = null;
        try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            String query = nombre.isEmpty() ? "SELECT * FROM trabajadores;" : "SELECT * FROM trabajadores WHERE nombre LIKE '%"+ nombre +"%';";
            PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement(query);
            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Trabajadores t = new Trabajadores();
                t.setId(rs.getInt("id"));
                t.setDni(rs.getString("dni"));
                t.setClave(rs.getString("clave"));
                t.setNombre(rs.getString("nombre"));
                t.setEmail(rs.getString("email"));
                t.setTelefono(rs.getString("telefono"));
                t.setDirecion(rs.getString("direcion"));
                t.setNumero_ventas(rs.getInt("numero_ventas"));
                t.setVolumen_ventas(rs.getString("volumen_ventas"));
                t.setFecha_alta(rs.getDate("fecha_alta"));
                lista.add(t);
            }
            rs.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("CAE EN DAOTRABAJADORESIMPL" +e);
        } finally {
            this.closeConnection();
        }
        return lista;
    }

    Trabajadores login(String usuario, String clave) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Trabajadores getTrabajadorID(int TraId) throws Exception {
        Trabajadores t = new Trabajadores();
        try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("SELECT * FROM trabajadores WHERE id = ? LIMIT 1;");
            st.setInt(1, TraId);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                t.setId(rs.getInt("id"));
                t.setDni(rs.getString("dni"));
                t.setClave(rs.getString("clave"));
                t.setNombre(rs.getString("nombre"));
                t.setEmail(rs.getString("email"));
                t.setTelefono(rs.getString("telefono"));
                t.setDirecion(rs.getString("direcion"));
                t.setNumero_ventas(rs.getInt("numero_ventas"));
                t.setVolumen_ventas(rs.getString("volumen_ventas"));
                t.setFecha_alta(rs.getDate("fecha_alta"));
            }
            rs.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("CAE EN DAOTRABAJADORESIMPL" +e);
        } finally {
            this.closeConnection();
        }
        return t;
    }

}
