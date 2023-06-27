package com.alvarobajo.Controllers;

import com.alvarobajo.db.GestionSql;
import com.alvarobajo.interfaces.DAOClientes;
import com.alvarobajo.models.Clientes;
import java.sql.SQLException;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author alvaroBajo
 */
public class DAOClientesImpl extends GestionSql implements DAOClientes {

    

    @Override
    public void registrar(Clientes c) throws Exception {
        try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("INSERT INTO clientes(nombre, email, telefono, dirreccion, descuento ) VALUES (?,?,?,?,?);");
            st.setString(1, c.getNombre());
            st.setString(2, c.getEmail());
            st.setString(3, c.getTelefono());
            st.setString(4, c.getDireccion());
            st.setString(5, c.getDescuento());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        } finally {
            this.closeConnection();

        }
    }

    @Override
    public void modificar(Clientes c) throws Exception {
        try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("UPDATE clientes SET nombre = ?, email = ?, telefono = ?, dirreccion = ?, descuento = ? WHERE id = ?");
            st.setString(1, c.getNombre());
            st.setString(2, c.getEmail());
            st.setString(3, c.getTelefono());
            st.setString(4, c.getDireccion());
            st.setString(5, c.getDescuento());
            st.setInt(6, c.getId());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        } finally {
            this.closeConnection();

        }
    }

    @Override
    public void eliminar(int cliId) throws Exception {
        try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("DELETE FROM clientes WHERE id= ?;");
            st.setInt(1, cliId);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        } finally {
            this.closeConnection();

        }
    }

    @Override
    public List<Clientes> listar( String nombre) throws Exception {
        List<Clientes> lista = null;
        try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            String query = nombre.isEmpty() ? "SELECT * FROM clientes;" : "SELECT * FROM clientes WHERE nombre LIKE '%" + nombre + "%';";
            PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement(query);
            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Clientes c = new Clientes();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setEmail(rs.getString("email"));
                c.setTelefono(rs.getString("telefono"));
                c.setDireccion(rs.getString("dirreccion"));
                c.setNumero_compras(rs.getInt("numero_compras"));
                c.setDinero_gastado(rs.getString("dinero_gastado"));
                c.setDescuento(rs.getString("descuento"));
                c.setFecha_alta(rs.getDate("fecha_alta"));
                lista.add(c);
            }
            rs.close();
            st.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        } finally {
            this.closeConnection();
        }
        return lista;
    }

    @Override
    public Clientes getClienteID(int prodID) throws Exception {
        Clientes c = new Clientes();
        try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("SELECT * FROM clientes WHERE id = ? LIMIT 1;");
            st.setInt(1, prodID);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setEmail(rs.getString("email"));
                c.setTelefono(rs.getString("telefono"));
                c.setDireccion(rs.getString("dirreccion"));
                c.setNumero_compras(rs.getInt("numero_compras"));
                c.setDinero_gastado(rs.getString("dinero_gastado"));
                c.setDescuento(rs.getString("descuento"));
                c.setFecha_alta(rs.getDate("fecha_alta"));
            }
            rs.close();
            st.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        } finally {
            this.closeConnection();
        }
        return c;
    }

}
