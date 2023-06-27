
package com.alvarobajo.Controllers;

import com.alvarobajo.db.GestionSql;
import com.alvarobajo.interfaces.DAOVentas;
import com.alvarobajo.models.Clientes;
import com.alvarobajo.views.panel.Porfechas;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.jdbc.PreparedStatement;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author alvaroBajo
 */
public class DAOVentasImpl extends GestionSql implements DAOVentas {

    String nombreCliente = "";
    int idCliente = 0;

    @Override
    public List<Clientes> listar(String nombre) throws Exception {
        nombre = nombreCliente;
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
    public int getClienteID(String nombre) throws Exception {
        int idClienteSelecionado = 0;
        try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("SELECT * FROM clientes WHERE nombre = ? LIMIT 1;");
            st.setString(1, nombre);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                idClienteSelecionado = (rs.getInt("id"));
            }
            rs.close();
            st.close();

        } catch (SQLException e) {
            throw e;
        } finally {
            this.closeConnection();
        }
        return idClienteSelecionado;
    }

    @Override
    public void clienteVentas(String nombres) throws Exception {
        int IDCLIENTE = 0;
        try {
            IDCLIENTE = getClienteID(nombres);
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex);
        }
        Document documento = new Document();

        try {
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reporte_compras " + nombres + ".pdf"));
            Image header = Image.getInstance("src/com/alvarobajo/img/ventas.png");
            header.scaleToFit(650, 100);
            header.setAlignment(Chunk.ALIGN_CENTER);

            //formatear texto
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte creado por:\n\nÁlvaroAPP\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte compras de: " + nombres + "\n\n");

            documento.open();
            //agregamos los datos

            documento.add(header);
            documento.add(parrafo);
            
            float[] columnas = {2, 4, 7, 5, 5};

            PdfPTable tabla = new PdfPTable(columnas);
            tabla.addCell(new Paragraph("ID", FontFactory.getFont("Arial", 9, Font.BOLD)));
            tabla.addCell(new Paragraph("nombre", FontFactory.getFont("Arial", 9, Font.BOLD)));
            tabla.addCell(new Paragraph("Email", FontFactory.getFont("Arial", 9, Font.BOLD)));
            tabla.addCell(new Paragraph("Numero Compras", FontFactory.getFont("Arial", 9, Font.BOLD)));
            tabla.addCell(new Paragraph("Dinero gastado", FontFactory.getFont("Arial", 9, Font.BOLD)));

            try {
                this.openConnection();//Establecemos la conexion a la base de datos.
                PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("SELECT id, nombre, email, numero_compras, dinero_gastado from clientes WHERE id LIKE '%" + IDCLIENTE + "%';");
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    do {
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                        tabla.addCell(rs.getString(4));
                        tabla.addCell(rs.getString(5));
                    } while (rs.next());
                    documento.add(tabla);
                }

            } catch (SQLException e) {
                System.out.println("Error en: " + e);
            }
            Paragraph espacioBlanco = new Paragraph();
            espacioBlanco.add("\n\n");
            documento.add(espacioBlanco);

            PdfPTable tablaNom = new PdfPTable(3);
            tablaNom.addCell(new Paragraph("ID Venta", FontFactory.getFont("Arial", 9, Font.BOLD)));
            tablaNom.addCell(new Paragraph("Fecha Venta", FontFactory.getFont("Arial", 9, Font.BOLD)));
            tablaNom.addCell(new Paragraph("Valor venta", FontFactory.getFont("Arial", 9, Font.BOLD)));

            try {
                this.openConnection();//Establecemos la conexion a la base de datos.
                PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("SELECT id, Fecha_venta, precio_venta FROM ventas WHERE id_cliente LIKE '%" + IDCLIENTE + "%';");
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    do {
                        tablaNom.addCell(rs.getString(1));
                        tablaNom.addCell(rs.getString(2));
                        tablaNom.addCell(rs.getString(3));
                    } while (rs.next());
                    documento.add(tablaNom);
                }

            } catch (Exception e) {
            }

            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte creado \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);

        } catch (DocumentException e) {
            System.out.println("Error en DAOREPORTESIMPLS 1" + e.toString());
        } catch (FileNotFoundException ex) {
            System.out.println("Error en DAOREPORTESIMPLS 2" + ex.toString());
        } catch (IOException ex) {
            System.out.println("Error en DAOREPORTESIMPLS 3" + ex.toString());
        }

    }
    
    
    @Override
    public void ventasPorFecha() throws Exception {
       
        Document documento = new Document();

        try {
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reporte_compras por fecha entre " + Porfechas.fecha_inicio +" y "+ Porfechas.fecha_fin + ".pdf"));
            Image header = Image.getInstance("src/com/alvarobajo/img/ventas.png");
            header.scaleToFit(650, 100);
            header.setAlignment(Chunk.ALIGN_CENTER);

            //formatear texto
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte creado por:\n\nÁlvaroAPP\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte_compras por fecha entre " + Porfechas.fecha_inicio +" y "+ Porfechas.fecha_fin + "\n\n");

            documento.open();
            //agregamos los datos

            documento.add(header);
            documento.add(parrafo);
            
            float[] columnas = {2, 4, 7, 5, 5};

            PdfPTable tabla = new PdfPTable(columnas);
            tabla.addCell(new Paragraph("ID", FontFactory.getFont("Arial", 9, Font.BOLD)));
            tabla.addCell(new Paragraph("ID Cliente", FontFactory.getFont("Arial", 9, Font.BOLD)));
            tabla.addCell(new Paragraph("ID Trabajador", FontFactory.getFont("Arial", 9, Font.BOLD)));
            tabla.addCell(new Paragraph("Precio Venta", FontFactory.getFont("Arial", 9, Font.BOLD)));
            tabla.addCell(new Paragraph("Fecha Venta", FontFactory.getFont("Arial", 9, Font.BOLD)));

            try {
                this.openConnection();//Establecemos la conexion a la base de datos.
                PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("SELECT * FROM `ventas` WHERE fecha_venta BETWEEN CAST('" + Porfechas.fecha_inicio+ "' AS DATE) AND CAST('" + Porfechas.fecha_fin + "' AS DATE);");
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    do {
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                        tabla.addCell(rs.getString(4));
                        tabla.addCell(rs.getString(5));
                    } while (rs.next());
                    documento.add(tabla);
                }

            } catch (SQLException e) {
                System.out.println("Error en: " + e);
            }
            Paragraph espacioBlanco = new Paragraph();
            espacioBlanco.add("\n\n");
            documento.add(espacioBlanco);
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte creado \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);

        } catch (DocumentException e) {
            System.out.println("Error en DAOREPORTESIMPLS 1" + e.toString());
        } catch (FileNotFoundException ex) {
            System.out.println("Error en DAOREPORTESIMPLS 2" + ex.toString());
        } catch (IOException ex) {
            System.out.println("Error en DAOREPORTESIMPLS 3" + ex.toString());
        }

    }
    
    

}
