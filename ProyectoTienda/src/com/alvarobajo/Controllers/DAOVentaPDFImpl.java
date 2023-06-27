package com.alvarobajo.Controllers;

import com.alvarobajo.db.GestionSql;
import com.alvarobajo.interfaces.DAOVentaPDF;
import com.alvarobajo.utilidades.Utilidades;
import com.alvarobajo.views.panel.PTpv;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.jdbc.PreparedStatement;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author alvaroBajo
 */
public class DAOVentaPDFImpl extends GestionSql implements DAOVentaPDF {

    private String nombreCliente;
    private String emailCliente;
    private String telefonoCliente;
    private String direccionCliente;

    //para el nombre del archivo
    private String FechaActual = "";
    private String nombreArchivo = "";

    @Override
    public void datosClientes(int idCliente) throws Exception {
        
        
        if(idCliente == 0){
            
        }else{
            nombreCliente = "null";
            emailCliente = "null";
            telefonoCliente = "null";
            direccionCliente = "null";
        try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("SELECT * FROM clientes WHERE id = ? LIMIT 1;");
            st.setInt(1, idCliente);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                nombreCliente = rs.getString("nombre");
                emailCliente = rs.getString("email");
                telefonoCliente = rs.getString("telefono");
                direccionCliente = rs.getString("dirreccion");
            }
            rs.close();
            st.close();

        } catch (SQLException e)  {
            System.out.println("cae en DATOSCLIENTE() de la clase DAOVPDFIMPL" + e);
        } finally {
            this.closeConnection();
        }
        }
    }

    //Aprovechamos el dao para generar un documento pdf de factura
    @Override
    public void generarPDF()  {
        try {
            //lo priemero que hacemos es cargar la fecha
            FechaActual = Utilidades.getFechaActual();
            //cambiamos el formato de la fecha ya que el nombre del archivo no nos deja con / 
            String fechaNueva = "";
            for (int i = 0; i < FechaActual.length(); i++) {
                if (FechaActual.charAt(i) == '/') {
                    fechaNueva = FechaActual.replace("/", "_");
                }
            }

            nombreArchivo = "Venta_" + nombreCliente + "_" + fechaNueva + ".pdf";

            FileOutputStream archivo;
            File file = new File("src/pdf/" + nombreArchivo);
            archivo = new FileOutputStream(file);

            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();

            Image img = Image.getInstance("src/com/alvarobajo/img/ventas.png");
            Paragraph fecha = new Paragraph();
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
            fecha.add(Chunk.NEWLINE); //agregamos nueva linea
            fecha.add("Factura: " + "\n" + FechaActual + "\n\n");
            PdfPTable Encabezado = new PdfPTable(4);
            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0);//quito el borde de la tabla 
            //tamaño de las celdasas 
            float[] ColumnaEncabezado = new float[]{20f, 30f, 70f, 40f};
            Encabezado.setWidths(ColumnaEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
            //agregar celdas 
            Encabezado.addCell(img);
            String nif = "3245354325H";
            String nombre = "ÁlvaroAPPTIENDA";
            String direccion = "c/ Gonzalo";
            String telefono = "34234324";
            String lema = "Si puedes imaginarlo, puedes programarlo";

            Encabezado.addCell("");//celda vacia
            Encabezado.addCell("NIF: " + nif + "\nNombre: " + nombre + "\nDirección:" + direccion + "\nTeléfono" + telefono + "\nLema:" + lema);
            Encabezado.addCell(fecha);
            doc.add(Encabezado);

            //cuerpo del pdf
            Paragraph cliente = new Paragraph();
            cliente.add(Chunk.NEWLINE);
            cliente.add("Datos del cliente: \n\n");
            doc.add(cliente);

            //Datos del cliente
            PdfPTable tablaCliente = new PdfPTable(4);
            tablaCliente.setWidthPercentage(100);
            tablaCliente.getDefaultCell().setBorder(0);
            float[] ColumnaCliente = new float[]{25f, 45f, 30f, 40f};
            tablaCliente.setWidths(ColumnaCliente);
            tablaCliente.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell cliente1 = new PdfPCell(new Phrase("Nombre: ", negrita));
            PdfPCell cliente2 = new PdfPCell(new Phrase("Email: ", negrita));
            PdfPCell cliente3 = new PdfPCell(new Phrase("Teléfono: ", negrita));
            PdfPCell cliente4 = new PdfPCell(new Phrase("Dirección: ", negrita));
            cliente1.setBorder(0);
            cliente2.setBorder(0);
            cliente3.setBorder(0);
            cliente4.setBorder(0);
            tablaCliente.addCell(cliente1);
            tablaCliente.addCell(cliente2);
            tablaCliente.addCell(cliente3);
            tablaCliente.addCell(cliente4);
            tablaCliente.addCell(nombreCliente);
            tablaCliente.addCell(emailCliente);
            tablaCliente.addCell(telefonoCliente);
            tablaCliente.addCell(direccionCliente);
            //agregamos al documento
            doc.add(tablaCliente);

            //creo dos espacios para que no se vean muy juntos
            Paragraph espacio = new Paragraph();
            espacio.add(Chunk.NEWLINE);
            espacio.add("");
            espacio.setAlignment(Element.ALIGN_LEFT);
            doc.add(espacio);
            //este es el segundo espacio
            Paragraph espacio1 = new Paragraph();
            espacio.add(Chunk.NEWLINE);
            espacio.add("");
            espacio.setAlignment(Element.ALIGN_LEFT);
            doc.add(espacio1);

            //creamos la tabla de productos
            PdfPTable tablaProducto = new PdfPTable(4);
            tablaProducto.setWidthPercentage(100);
            tablaProducto.getDefaultCell().setBorder(0);
            //tamaño de celdas
            float[] ColumnaProductos = new float[]{15f, 50f, 150f, 20f};
            tablaProducto.setWidths(ColumnaProductos);
            tablaProducto.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell producto1 = new PdfPCell(new Phrase("Cantidad:", negrita));
            PdfPCell producto2 = new PdfPCell(new Phrase("Producto:", negrita));
            PdfPCell producto3 = new PdfPCell(new Phrase("Precio Unitario:", negrita));
            PdfPCell producto4 = new PdfPCell(new Phrase("Precio total;:", negrita));
            //Quitamos los bordes
            producto1.setBorder(0);
            producto2.setBorder(0);
            producto3.setBorder(0);
            producto4.setBorder(0);

            //agregamos el color al encabezado
            producto1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            producto2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            producto3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            producto4.setBackgroundColor(BaseColor.LIGHT_GRAY);

            //agregamos celdas a la tabla
            tablaProducto.addCell(producto1);
            tablaProducto.addCell(producto2);
            tablaProducto.addCell(producto3);
            tablaProducto.addCell(producto4);

            for (int i = 0; i < PTpv.tablaDetalle.getRowCount(); i++) {
                String producto = PTpv.tablaDetalle.getValueAt(i, 0).toString();
                String cantidad = PTpv.tablaDetalle.getValueAt(i, 1).toString();
                String precio = PTpv.tablaDetalle.getValueAt(i, 2).toString();
                String total = PTpv.tablaDetalle.getValueAt(i, 3).toString();
                tablaProducto.addCell(cantidad);
                tablaProducto.addCell(producto);
                tablaProducto.addCell(precio);
                tablaProducto.addCell(total);
            }

            //agregamos al documento
            doc.add(tablaProducto);

            //total a pagar
            Paragraph info = new Paragraph();
            info.add(Chunk.NEWLINE);
            info.add("Total a pagar: " + PTpv.txtTotal.getText());
            info.setAlignment(Element.ALIGN_RIGHT);
            doc.add(info);

            //Agregamos la firma
            Paragraph firma = new Paragraph();
            firma.add(Chunk.NEWLINE);
            firma.add("Firma\n\n");
            firma.add("______________");
            firma.setAlignment(Element.ALIGN_CENTER);
            doc.add(firma);

            Paragraph mensaje = new Paragraph();
            mensaje.add(Chunk.NEWLINE);
            mensaje.add("Gracias por confiar en nosotros.\n\n");
            mensaje.setAlignment(Element.ALIGN_CENTER);
            doc.add(mensaje);
            
            //cerramos el archivo
            doc.close();
            archivo.close();
            
            //abrir el documento al ser generado.
            Desktop.getDesktop().open(file);

        } catch (DocumentException | IOException e) {
            System.out.println("ERROR EN :" + e);
        }
    }
    
    
    }

