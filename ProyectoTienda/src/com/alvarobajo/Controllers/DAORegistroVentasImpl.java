package com.alvarobajo.Controllers;

import com.alvarobajo.db.GestionSql;
import com.alvarobajo.interfaces.DAORegistroVentas;
import com.alvarobajo.models.DetalleVenta;
import com.alvarobajo.models.Ventas;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author alvaroBajo
 */
public class DAORegistroVentasImpl extends GestionSql implements DAORegistroVentas {

    //ultimo Id de la venta
    public static int idVentaRegistrada;
    java.math.BigDecimal iDColVar;
    private static ResultSet resultado;
    
    @Override
    public void registrar(Ventas v) throws Exception {
        try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("INSERT INTO ventas( id_cliente, id_trabajador,precio_venta) VALUES (?,?,?);",Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, v.getIdCliente());
            st.setInt(2, v.getIdTrabajaor());
            st.setDouble(3, v.getPrecioVenta());
            st.executeUpdate();
            resultado = (ResultSet) st.getGeneratedKeys();
            if(resultado.next()){
                idVentaRegistrada = resultado.getInt(1);
            }
            st.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar venta" +e.toString());
        } finally {
            this.closeConnection();

        }
    }

    @Override
    public void registrarDetalle(DetalleVenta d) throws Exception {
        try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("INSERT INTO detalle_venta( id_venta, id_producto, cantidad, precio_unitario,sub_total, descuento, iva, total_pagar) VALUES (?,?,?,?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, d.getId_venta());
            st.setInt(2, d.getId_producto());
            st.setInt(3, d.getCantidad());
            st.setDouble(4, d.getPrecio_unitario());
            st.setDouble(5, d.getSub_total());
            st.setDouble(6, d.getDescuento());
            st.setDouble(7, d.getIva());
            st.setDouble(8, d.getTotal_pagar());

            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar detalle de venta" + e.toString());
        } finally {
            this.closeConnection();

        }
    }
    
    
    
    @Override
    public List<Ventas> listar() throws Exception {
        List<Ventas> lista = null;
        try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            String query =  "SELECT * FROM ventas;";
            PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement(query);
            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Ventas v = new Ventas();
                v.setId(rs.getInt("id"));
                v.setId(rs.getInt("id_cliente"));
                v.setIdTrabajaor(rs.getInt("id_trabajador"));
                v.setPrecioVenta(rs.getDouble("precio_venta"));
                v.setFechaVenta(rs.getDate("fecha_venta"));
                lista.add(v);
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
}
