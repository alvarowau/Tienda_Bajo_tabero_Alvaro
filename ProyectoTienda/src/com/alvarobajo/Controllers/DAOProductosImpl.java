package com.alvarobajo.Controllers;

import com.alvarobajo.db.GestionSql;
import com.alvarobajo.interfaces.DAOProductos;
import com.alvarobajo.models.Productos;
import com.mysql.jdbc.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author alvaroBajo
 */
public class DAOProductosImpl extends GestionSql implements DAOProductos {

    @Override
    public void registrar(Productos p) throws Exception {
        try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("INSERT INTO productos( codigo,producto,precio_compra,precio_venta,cantidad,estado,descripcion,categoria) VALUES (?,?,?,?,?,?,?,?);");
            st.setString(1, p.getCodigo());
            st.setString(2, p.getProducto());
            st.setString(3, p.getPrecio_compra());
            st.setString(4, p.getPrecio_venta());
            st.setString(5, p.getCantidad());
            st.setString(6, p.getEstado());
            st.setString(7, p.getDescripcion());
            st.setString(8, p.getCategoria());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            System.out.println("ERROR EN DAOPRODUCTOSIML" + e);
        } finally {
            this.closeConnection();

        }
    }

    @Override
    public void modificar(Productos p) throws Exception {
        try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("UPDATE productos SET codigo = ?, producto = ?, precio_compra = ?, precio_venta = ?, cantidad = ?, estado = ?, descripcion = ?, categoria = ? WHERE id = ?");
            st.setString(1, p.getCodigo());
            st.setString(2, p.getProducto());
            st.setString(3, p.getPrecio_compra());
            st.setString(4, p.getPrecio_venta());
            st.setString(5, p.getCantidad());
            st.setString(6, p.getEstado());
            st.setString(7, p.getDescripcion());
            st.setString(8, p.getCategoria());
            st.setInt(9, p.getId());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            System.out.println("ERROR EN DAOPRODUCTOSIML" + e);
        } finally {
            this.closeConnection();

        }
    }

    @Override
    public void eliminar(int prodId) throws Exception {
        try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("DELETE FROM productos WHERE id= ?;");
            st.setInt(1, prodId);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            System.out.println("ERROR EN DAOPRODUCTOSIML" + e);
        } finally {
            this.closeConnection();

        }
    }

    @Override
    public List<Productos> listar(String nombre) throws Exception {
        List<Productos> lista = null;
        try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            String query = nombre.isEmpty() ? "SELECT * FROM productos;" : "SELECT * FROM productos WHERE nombre LIKE '%" + nombre + "%';";
            PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement(query);
            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Productos p = new Productos();
                p.setId(rs.getInt("id"));
                p.setCodigo(rs.getString("codigo"));
                p.setProducto(rs.getString("producto"));
                p.setPrecio_compra(rs.getString("precio_compra"));
                p.setPrecio_venta(rs.getString("precio_venta"));
                p.setCantidad(rs.getString("cantidad"));
                p.setEstado(rs.getString("estado"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setCategoria(rs.getString("categoria"));
                p.setVendidos(rs.getString("vendidos"));
                p.setFecha_venta(rs.getDate("fecha_venta"));
                lista.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("ERROR EN DAOPRODUCTOSIML" + e);
        } finally {
            this.closeConnection();
        }
        return lista;
    }

    @Override
    public Productos getProductoID(int prodID) throws Exception {
        Productos p = new Productos();
        try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("SELECT * FROM productos WHERE id = ? LIMIT 1;");
            st.setInt(1, prodID);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt("id"));
                p.setCodigo(rs.getString("codigo"));
                p.setProducto(rs.getString("producto"));
                p.setPrecio_compra(rs.getString("precio_compra"));
                p.setPrecio_venta(rs.getString("precio_venta"));
                p.setCantidad(rs.getString("cantidad"));
                p.setEstado(rs.getString("estado"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setCategoria(rs.getString("categoria"));
                p.setVendidos(rs.getString("vendidos"));
                p.setFecha_venta(rs.getDate("fecha_venta"));
            }
            rs.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("ERROR EN DAOPRODUCTOSIML" + e);
        } finally {
            this.closeConnection();
        }
        return p;
    }

    @Override
    public List<Productos> listaAbanzada(String colum, String nombre) throws Exception {
         List<Productos> lista = null;
        try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("SELECT * FROM productos WHERE "+colum+" LIKE '%" + nombre + "%';");
            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Productos p = new Productos();
                p.setId(rs.getInt("id"));
                p.setCodigo(rs.getString("codigo"));
                p.setProducto(rs.getString("producto"));
                p.setPrecio_compra(rs.getString("precio_compra"));
                p.setPrecio_venta(rs.getString("precio_venta"));
                p.setCantidad(rs.getString("cantidad"));
                p.setEstado(rs.getString("estado"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setCategoria(rs.getString("categoria"));
                p.setVendidos(rs.getString("vendidos"));
                p.setFecha_venta(rs.getDate("fecha_venta"));
                lista.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("ERROR EN DAOPRODUCTOSIML" + e);
        } finally {
            this.closeConnection();
        }
        return lista;
    }

}
