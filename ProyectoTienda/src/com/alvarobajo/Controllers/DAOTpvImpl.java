package com.alvarobajo.Controllers;

import com.alvarobajo.db.GestionSql;
import com.alvarobajo.interfaces.DAOTpv;
import com.alvarobajo.models.Clientes;
import com.alvarobajo.models.Productos;
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
public class DAOTpvImpl extends GestionSql implements DAOTpv {

    
  
    @Override
    public List<Clientes> listar(String nombre) throws Exception {
        List<Clientes> lista = null;
        try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            String query = nombre.isEmpty() ? "SELECT * FROM clientes;" : "SELECT * FROM clientes WHERE telefono LIKE '%" + nombre + "%';";
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
            throw e;
        } finally {
            this.closeConnection();
        }
        return lista;

    }

        
    
    @Override
    public List<Productos> listarProductos(String nombre) throws Exception {
        List<Productos> lista = null;
        try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("SELECT * FROM productos WHERE categoria LIKE '" + nombre + "';");
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
            System.out.println("ERROR EN DAOTPVIMPL" +e );
        } finally {
            this.closeConnection();
        }
        return lista;

    }

    
    
    @Override
    public Productos esUnProducto(String nombre) throws SQLException {
        Productos p = new Productos();
        try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("SELECT * FROM productos WHERE producto = ? LIMIT 1;");
            st.setString(1, nombre);

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
            System.out.println("ERROR EN DAOTPVIMPL" +e );
        } finally {
            this.closeConnection();
        }
        return p;
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
            System.out.println("ERROR EN DAOTPVIMPL" +e );
        } finally {
            this.closeConnection();
        }
        return p;
    }

  
    @Override
    public Clientes esUnCliente(String nombre) throws SQLException {
        Clientes c = new Clientes();
        try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("SELECT * FROM clientes WHERE nombre = ? LIMIT 1;");
            st.setString(1, nombre);

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
            System.out.println("ERROR EN DAOTPVIMPL" +e );
        } finally {
            this.closeConnection();
        }
        return c;
    }

  
    @Override
    public void añadirCompra(Clientes c) throws SQLException {
        try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("UPDATE clientes SET numero_compras = ?, dinero_gastado = ?, descuento = ? WHERE id = ?");
            st.setInt(1, c.getNumero_compras());
            st.setString(2, c.getDinero_gastado());
            st.setString(3, c.getDescuento());
            st.setInt(4, c.getId());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {

            System.out.println("ERROR EN DAOTPVIMPL" +e );
        } finally {
            this.closeConnection();

        }
    }
    
  
    @Override
    public void modificarCantidad(Productos p) throws Exception{
      try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("UPDATE productos SET cantidad = ? WHERE id = ?");
            st.setString(1, p.getCantidad());
            st.setInt(2, p.getId());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            System.out.println("ERROR EN DAOTPVIMPL" +e );
        } finally {
            this.closeConnection();

        }
    }
    
    @Override
    public void modificarVolumenTrabajador(Trabajadores usuario)throws Exception{
        try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("UPDATE trabajadores SET volumen_ventas = ?, SET numero_ventas = ? WHERE id = ?");
            st.setString(1, usuario.getVolumen_ventas());
            st.setInt(2, usuario.getNumero_ventas());
            st.setInt(3, usuario.getId());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            System.out.println("ERROR EN DAOTPVIMPL" +e );
        } finally {
            this.closeConnection();

        }
    }


}
