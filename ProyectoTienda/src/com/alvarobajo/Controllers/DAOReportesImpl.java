package com.alvarobajo.Controllers;

import com.alvarobajo.db.GestionSql;
import com.alvarobajo.interfaces.DAOReportes;
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
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author alvaroBajo
 */
public class DAOReportesImpl extends GestionSql implements DAOReportes {

    @Override
    public void reportesClientes() {
        Document documento = new Document();
        try {
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reportes_Clientes.pdf"));
            Image header = Image.getInstance("src/com/alvarobajo/img/ventas.png");
            header.scaleToFit(650, 100);
            header.setAlignment(Chunk.ALIGN_CENTER);

            //formatear texto
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte creado por:\n\nÁlvaroAPP\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte Cliente\n\n");

            documento.open();
            //agregamos los datos

            documento.add(header);
            documento.add(parrafo);

            PdfPTable tabla = new PdfPTable(7);
            tabla.addCell(new Paragraph("ID", FontFactory.getFont("Arial", 9, Font.BOLD)));
            tabla.addCell(new Paragraph("nombre", FontFactory.getFont("Arial", 9, Font.BOLD)));
            tabla.addCell(new Paragraph("Email", FontFactory.getFont("Arial", 9, Font.BOLD)));
            tabla.addCell(new Paragraph("Compras", FontFactory.getFont("Arial", 9, Font.BOLD)));
            tabla.addCell(new Paragraph("Dinero gastado", FontFactory.getFont("Arial", 9, Font.BOLD)));
            tabla.addCell(new Paragraph("Descuento", FontFactory.getFont("Arial", 9, Font.BOLD)));
            tabla.addCell(new Paragraph("Fecha de alta", FontFactory.getFont("Arial", 9, Font.BOLD)));

            try {
                this.openConnection();//Establecemos la conexion a la base de datos.
                PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("SELECT id, nombre, email, numero_compras, dinero_gastado, descuento, fecha_alta from clientes");
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    do {
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                        tabla.addCell(rs.getString(4));
                        tabla.addCell(rs.getString(5));
                        tabla.addCell(rs.getString(6));
                        tabla.addCell(rs.getString(7));
                    } while (rs.next());
                    documento.add(tabla);
                }

            } catch (SQLException e) {
                System.out.println("Error en: " + e);
            }
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte creado \n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        } catch (DocumentException e) {
            System.out.println("Error en DAOREPORTESIMPLS 1" + e.toString());
        } catch (FileNotFoundException ex) {
            System.out.println("Error en DAOREPORTESIMPLS 2" + ex.toString());
        } catch (IOException ex) {
            System.out.println("Error en DAOREPORTESIMPLS 3" + ex.toString());
        }

    }

    @Override
    public void reportesTrabajadores() {
        Document documento = new Document();
        try {
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reportes_Trabajadores.pdf"));
            Image header = Image.getInstance("src/com/alvarobajo/img/ventas.png");
            header.scaleToFit(650, 100);
            header.setAlignment(Chunk.ALIGN_CENTER);

            //formatear texto
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte creado por:\n\nÁlvaroAPP\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte Trabajadores\n\n");

            documento.open();
            //agregamos los datos

            documento.add(header);
            documento.add(parrafo);

            PdfPTable tabla = new PdfPTable(7);
            tabla.addCell(new Paragraph("ID", FontFactory.getFont("Arial", 9, Font.BOLD)));
            tabla.addCell(new Paragraph("Dni", FontFactory.getFont("Arial", 9, Font.BOLD)));
            tabla.addCell(new Paragraph("Nombre", FontFactory.getFont("Arial", 9, Font.BOLD)));
            tabla.addCell(new Paragraph("Email", FontFactory.getFont("Arial", 9, Font.BOLD)));
            tabla.addCell(new Paragraph("numero Ventas", FontFactory.getFont("Arial", 9, Font.BOLD)));
            tabla.addCell(new Paragraph("Volumen Ventas", FontFactory.getFont("Arial", 9, Font.BOLD)));
            tabla.addCell(new Paragraph("Fecha de alta", FontFactory.getFont("Arial", 9, Font.BOLD)));

            try {
                this.openConnection();//Establecemos la conexion a la base de datos.
                PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("SELECT id, dni, nombre, email, numero_ventas, volumen_ventas, fecha_alta from trabajadores");
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    do {
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                        tabla.addCell(rs.getString(4));
                        tabla.addCell(rs.getString(5));
                        tabla.addCell(rs.getString(6));
                        tabla.addCell(rs.getString(7));
                    } while (rs.next());
                    documento.add(tabla);
                }

            } catch (SQLException e) {
                System.out.println("Error en: " + e);
            }
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte creado \n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        } catch (DocumentException e) {
            System.out.println("Error en DAOREPORTESIMPLS 1" + e.toString());
        } catch (FileNotFoundException ex) {
            System.out.println("Error en DAOREPORTESIMPLS 2" + ex.toString());
        } catch (IOException ex) {
            System.out.println("Error en DAOREPORTESIMPLS 3" + ex.toString());
        }

    }

    @Override
    public void reportesProductos() {
        Document documento = new Document();
        try {
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reportes_Productos.pdf"));
            Image header = Image.getInstance("src/com/alvarobajo/img/ventas.png");
            header.scaleToFit(650, 100);
            header.setAlignment(Chunk.ALIGN_CENTER);

            //formatear texto
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte creado por:\n\nÁlvaroAPP\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte Productos\n\n");

            documento.open();
            //agregamos los datos

            documento.add(header);
            documento.add(parrafo);

            float[] columnas = {2, 5, 6, 3, 2, 2, 4, 2};

            PdfPTable tabla = new PdfPTable(columnas);
            tabla.setWidthPercentage(100);
            tabla.addCell(new Paragraph("ID", FontFactory.getFont("Arial", 9, Font.BOLD)));
            tabla.addCell(new Paragraph("Codigo", FontFactory.getFont("Arial", 9, Font.BOLD)));
            tabla.addCell(new Paragraph("Nom Producto", FontFactory.getFont("Arial", 9, Font.BOLD)));
            tabla.addCell(new Paragraph("Precio Compra", FontFactory.getFont("Arial", 9, Font.BOLD)));
            tabla.addCell(new Paragraph("Precio Venta", FontFactory.getFont("Arial", 9, Font.BOLD)));
            tabla.addCell(new Paragraph("Cantidad", FontFactory.getFont("Arial", 9, Font.BOLD)));
            tabla.addCell(new Paragraph("Categoria", FontFactory.getFont("Arial", 9, Font.BOLD)));
            tabla.addCell(new Paragraph("Vendidos", FontFactory.getFont("Arial", 9, Font.BOLD)));

            try {
                this.openConnection();//Establecemos la conexion a la base de datos.
                PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("SELECT id, codigo, producto, precio_compra, precio_venta, cantidad, categoria, vendidos from productos");
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    do {
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                        tabla.addCell(rs.getString(4));
                        tabla.addCell(rs.getString(5));
                        tabla.addCell(rs.getString(6));
                        tabla.addCell(rs.getString(7));
                        tabla.addCell(rs.getString(8));
                    } while (rs.next());
                    documento.add(tabla);
                }

            } catch (SQLException e) {
                System.out.println("Error en: " + e);
            }
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte creado \n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        } catch (DocumentException e) {
            System.out.println("Error en DAOREPORTESIMPLS 1" + e.toString());
        } catch (FileNotFoundException ex) {
            System.out.println("Error en DAOREPORTESIMPLS 2" + ex.toString());
        } catch (IOException ex) {
            System.out.println("Error en DAOREPORTESIMPLS 3" + ex.toString());
        }

    }
    
    @Override
    public void reportesVentas() {
        Document documento = new Document();
        try {
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reportes_Ventas.pdf"));
            Image header = Image.getInstance("src/com/alvarobajo/img/ventas.png");
            header.scaleToFit(650, 100);
            header.setAlignment(Chunk.ALIGN_CENTER);

            //formatear texto
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte creado por:\n\nÁlvaroAPP\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte Ventas\n\n");

            documento.open();
            //agregamos los datos

            documento.add(header);
            documento.add(parrafo);

            float[] columnas = {3, 9, 4, 5, 3};

            PdfPTable tabla = new PdfPTable(columnas);
            tabla.setWidthPercentage(100);
            tabla.addCell(new Paragraph("ID", FontFactory.getFont("Arial", 9, Font.BOLD)));
            tabla.addCell(new Paragraph("ID Cliente", FontFactory.getFont("Arial", 9, Font.BOLD)));
            tabla.addCell(new Paragraph("ID Trabajador", FontFactory.getFont("Arial", 9, Font.BOLD)));
            tabla.addCell(new Paragraph("Precio Venta", FontFactory.getFont("Arial", 9, Font.BOLD)));
            tabla.addCell(new Paragraph("Fecha Venta", FontFactory.getFont("Arial", 9, Font.BOLD)));

            try {
                this.openConnection();//Establecemos la conexion a la base de datos.
                PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("SELECT id, id_cliente, id_trabajador, precio_venta, fecha_venta from ventas");
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
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte creado \n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        } catch (DocumentException e) {
            System.out.println("Error en DAOREPORTESIMPLS 1" + e.toString());
        } catch (FileNotFoundException ex) {
            System.out.println("Error en DAOREPORTESIMPLS 2" + ex.toString());
        } catch (IOException ex) {
            System.out.println("Error en DAOREPORTESIMPLS 3" + ex.toString());
        }

    }

}
