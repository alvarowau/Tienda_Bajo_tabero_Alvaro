package com.alvarobajo.views.panel;

import com.alvarobajo.Controllers.DAOProductosImpl;
import com.alvarobajo.interfaces.DAOProductos;
import com.alvarobajo.models.Productos;
import com.alvarobajo.views.PanelAdmin;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.alvarobajo.utilidades.Utilidades;

/**
 *
 * @author alvaroBajo
 */

/*
* En este punto del trabajo decido crear una clase secundaria para poder evaluar 
*si los datos que pudiesen crear conflicto más adelante cuando tengamos que 
*trabajar con ellos son números INT Y DOUBLE, ya que debido a que no lo fuese 
*podría el programa causar errores inesperados
*/
public class PProductos extends javax.swing.JPanel {

    boolean esEdicion = false;
    Productos productoEdicion;

    /**
     * Creates new form Productos
     */
    public PProductos() {
        initComponents();
        InitStyles();
        loadProductos();
    }

    public PProductos(Productos p) {
        initComponents();
        esEdicion = true;
        productoEdicion = p;
        InitStyles();
        loadProductos();
    }

    private void InitStyles() {
        cantidadTxt.putClientProperty("FlatLaf.styleClass", "h2");
        categoriaTxt.putClientProperty("FlatLaf.styleClass", "h2");
        cbxCategoria.putClientProperty("FlatLaf.styleClass", "h4");
        cbxEstado.putClientProperty("FlatLaf.styleClass", "h4");
        codigoTxt.putClientProperty("FlatLaf.styleClass", "h2");
        descrTxt.putClientProperty("FlatLaf.styleClass", "h2");
        estadoTxt.putClientProperty("FlatLaf.styleClass", "h2");
        jPanel1.putClientProperty("FlatLaf.styleClass", "h2");
        jScrollPane1.putClientProperty("FlatLaf.styleClass", "h2");

        modificarBtn.putClientProperty("FlatLaf.styleClass", "h2");
        borrarBtn.putClientProperty("FlatLaf.styleClass", "h2");
        precioCTxt.putClientProperty("FlatLaf.styleClass", "h2");
        precioVTxt.putClientProperty("FlatLaf.styleClass", "h2");
        productoTxt.putClientProperty("FlatLaf.styleClass", "h2");
        registrarBtn.putClientProperty("FlatLaf.styleClass", "h2");
        tFCantidad.putClientProperty("FlatLaf.styleClass", "h4");
        tFCantidad.putClientProperty("JTextField.placeholderText", "Ingrese la cantidad");
        tFCodigo.putClientProperty("FlatLaf.styleClass", "h4");
        tFCodigo.putClientProperty("JTextField.placeholderText", "Ingrese el codigo de barras del producto");
        tFDesc.putClientProperty("FlatLaf.styleClass", "h4");
        tFDesc.putClientProperty("JTextField.placeholderText", "Ingrese el descuento");
        tFPrecioC.putClientProperty("FlatLaf.styleClass", "h4");
        tFPrecioC.putClientProperty("JTextField.placeholderText", "Ingrese el precio de compra");
        tFPrecioV.putClientProperty("FlatLaf.styleClass", "h4");
        tFPrecioV.putClientProperty("JTextField.placeholderText", "Ingrese el precio de venta");
        tFDesc.putClientProperty("FlatLaf.styleClass", "h4");
        tFproducto.putClientProperty("FlatLaf.styleClass", "h4");
        tFproducto.putClientProperty("JTextField.placeholderText", "Ingrese el nombre del producto");
        buscarBtn.putClientProperty("FlatLaf.styleClass", "h2");
        guardarBtn.putClientProperty("FlatLaf.styleClass", "h2");
        guardarBtn.setVisible(false);

        if (esEdicion) {

            if (productoEdicion != null) {
                String categoria = cbxCategoria.getSelectedItem().toString();
                String estado = cbxEstado.getSelectedItem().toString();
                tFCantidad.setText(productoEdicion.getCantidad());
                tFCodigo.setText(productoEdicion.getCodigo());
                tFDesc.setText(productoEdicion.getDescripcion());
                tFPrecioC.setText(productoEdicion.getPrecio_compra());
                tFPrecioV.setText(productoEdicion.getPrecio_venta());
                tFproducto.setText(productoEdicion.getProducto());
                categoria = productoEdicion.getCategoria();
                estado = productoEdicion.getEstado();
                guardarBtn.setVisible(true);
                tFBuscar.setVisible(false);
                registrarBtn.setVisible(false);
                modificarBtn.setVisible(false);
                borrarBtn.setVisible(false);
                buscarBtn.setVisible(false);
            }
        }

    }

    private void eliminarProducto() {
        DAOProductos dao = new DAOProductosImpl();
        DefaultTableModel model = (DefaultTableModel) tablaProductos.getModel();
        for (int i : tablaProductos.getSelectedRows()) {
            try {
                dao.eliminar((int) tablaProductos.getValueAt(i, 0));
                model.removeRow(i);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ocurrió un error al borrar el usuario \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                System.out.println(e.getMessage());
            }
        }
        JOptionPane.showMessageDialog(this, "El producto fue eliminado correctamente \n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }

    private void loadProductos() {
        try {
            DAOProductos dao = new DAOProductosImpl();
            DefaultTableModel model = (DefaultTableModel) tablaProductos.getModel();
            dao.listar("").forEach((p) -> model.addRow(new Object[]{p.getId(), p.getCodigo(), p.getProducto(), p.getPrecio_compra(), p.getPrecio_venta(), p.getCantidad(), p.getEstado(), p.getDescripcion(), p.getCategoria(), p.getVendidos(), p.getFecha_venta()}));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void registrarProducto() {
        String cantidad = tFCantidad.getText();
        String codigo = tFCodigo.getText();
        String descripcion = tFDesc.getText();
        String precio_compra = tFPrecioC.getText();
        String precio_venta = tFPrecioV.getText();
        String producto = tFproducto.getText();
        String categoria = cbxCategoria.getSelectedItem().toString();
        String estado = cbxEstado.getSelectedItem().toString();

        //validamos los campos
        if (cantidad.isEmpty() || codigo.isEmpty() || precio_compra.isEmpty() || precio_venta.isEmpty() || producto.isEmpty() || categoria.isEmpty() || estado.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe rellenar todos los campos. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
            tFCodigo.requestFocus();
            return;
        }

        if (Utilidades.isNumericDouble(precio_compra)) {
            if (Utilidades.isNumericDouble(precio_venta)) {
                if (Utilidades.isNumeric(cantidad)) {
                    if (Integer.parseInt(cantidad)>0) {
                        Productos p = new Productos();
                        p.setCodigo(codigo);
                        p.setProducto(producto);
                        p.setPrecio_compra(precio_compra);
                        p.setPrecio_venta(precio_venta);
                        p.setCantidad(cantidad);
                        p.setEstado(estado);
                        p.setDescripcion(descripcion);
                        p.setCategoria(categoria);

                        try {
                            DAOProductos dao = new DAOProductosImpl();
                            dao.registrar(p);
                            JOptionPane.showMessageDialog(this, "El producto fue registrado correctamente \n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                            tFCantidad.setText("");
                            tFCodigo.setText("");
                            tFPrecioC.setText("");
                            tFPrecioV.setText("");
                            tFproducto.setText("");
                            PanelAdmin.showJPanel(new PProductos());
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(this, "Ocurrió un error al registrar \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                        }

                    } else {
                        JOptionPane.showMessageDialog(this, "la cantidad tiene que ser un numero mayor a 0. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                        tFCantidad.requestFocus();
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "la cantidad tiene que ser un numero mayor a 0. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                    tFCantidad.requestFocus();
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "El precio venta tiene que ser un valor DOUBLE, separado con '.'. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                tFPrecioV.requestFocus();
                return;
            }
        } else {
            JOptionPane.showMessageDialog(this, "El precio compra tiene que ser un valor DOUBLE, separado con '.'. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
            tFPrecioC.requestFocus();
            return;
        }

    }

    private void guardarmodificacion() {
        String cantidad = tFCantidad.getText();
        String codigo = tFCodigo.getText();
        //tFDesc;
        String descripcion = tFDesc.getText();
        String precio_compra = tFPrecioC.getText();
        String precio_venta = tFPrecioV.getText();
        String producto = tFproducto.getText();
        String categoria = cbxCategoria.getSelectedItem().toString();
        String estado = cbxEstado.getSelectedItem().toString();

        //validamos los campos
        if (cantidad.isEmpty() || codigo.isEmpty() || precio_compra.isEmpty() || precio_venta.isEmpty() || producto.isEmpty() || categoria.isEmpty() || estado.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe rellenar todos los campos. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
            tFCodigo.requestFocus();
            return;
        }

        if (Utilidades.isNumericDouble(precio_compra)) {
            if (Utilidades.isNumericDouble(precio_venta)) {
                if (Utilidades.isNumeric(cantidad)) {
                    if (Integer.parseInt(cantidad)>0) {
                        Productos p = esEdicion ? productoEdicion : new Productos();
                        p.setCodigo(codigo);
                        p.setProducto(producto);
                        p.setPrecio_compra(precio_compra);
                        p.setPrecio_venta(precio_venta);
                        p.setCantidad(cantidad);
                        p.setEstado(estado);
                        p.setDescripcion(descripcion);
                        p.setCategoria(categoria);
                        try {
                            DAOProductos dao = new DAOProductosImpl();
                            dao.modificar(p);
                            JOptionPane.showMessageDialog(this, "El usuario fue modificado correctamente \n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                            tFCantidad.setText("");
                            tFCodigo.setText("");
                            tFPrecioC.setText("");
                            tFPrecioV.setText("");
                            tFproducto.setText("");
                            PanelAdmin.showJPanel(new PProductos());

                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(this, "debe seleccionar un empleado. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                        }

                    } else {
                        JOptionPane.showMessageDialog(this, "la cantidad tiene que ser un numero mayor a 0. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                        tFCantidad.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "la cantidad tiene que ser un numero mayor a 0. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                    tFCantidad.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(this, "El precio venta tiene que ser un valor DOUBLE, separado con '.'. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                tFPrecioV.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(this, "El precio compra tiene que ser un valor DOUBLE, separado con '.'. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
            tFPrecioC.requestFocus();
        }

    }

    private void botonModificar() {
        try {
            int traId = (int) tablaProductos.getValueAt(tablaProductos.getSelectedRow(), 0);
            DAOProductos dao = new DAOProductosImpl();
            PanelAdmin.showJPanel(new PProductos(dao.getProductoID(traId)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void botonBuscar() {

        try {
            String colum = cbxbusqueda.getSelectedItem().toString();
            String nombre = tFBuscar.getText();
            DAOProductos dao = new DAOProductosImpl();
            DefaultTableModel model = (DefaultTableModel) tablaProductos.getModel();
            model.setRowCount(0);
            dao.listaAbanzada(colum, nombre).forEach((p) -> model.addRow(new Object[]{p.getId(), p.getCodigo(), p.getProducto(), p.getPrecio_compra(), p.getPrecio_venta(), p.getCantidad(), p.getEstado(), p.getDescripcion(), p.getCategoria(), p.getVendidos(), p.getFecha_venta()}));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        codigoTxt = new javax.swing.JLabel();
        tFCodigo = new javax.swing.JTextField();
        productoTxt = new javax.swing.JLabel();
        precioCTxt = new javax.swing.JLabel();
        precioVTxt = new javax.swing.JLabel();
        cantidadTxt = new javax.swing.JLabel();
        categoriaTxt = new javax.swing.JLabel();
        tFproducto = new javax.swing.JTextField();
        tFPrecioC = new javax.swing.JTextField();
        tFPrecioV = new javax.swing.JTextField();
        tFCantidad = new javax.swing.JTextField();
        estadoTxt = new javax.swing.JLabel();
        descrTxt = new javax.swing.JLabel();
        cbxCategoria = new javax.swing.JComboBox<>();
        borrarBtn = new javax.swing.JButton();
        registrarBtn = new javax.swing.JButton();
        modificarBtn = new javax.swing.JButton();
        buscarBtn = new javax.swing.JButton();
        cbxEstado = new javax.swing.JComboBox<>();
        tFDesc = new javax.swing.JTextField();
        guardarBtn = new javax.swing.JButton();
        tFBuscar = new javax.swing.JTextField();
        cbxbusqueda = new javax.swing.JComboBox<>();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Codigo", "Producto", "Precio Compra", "Precio Venta", "Cantidad", "Estado", "Descripcion", "Categoria", "Vendidos", "Fecha Venta"
            }
        ));
        jScrollPane1.setViewportView(tablaProductos);

        codigoTxt.setText("Codigo:");

        productoTxt.setText("Producto:");

        precioCTxt.setText("Precio compra:");

        precioVTxt.setText("Precio venta:");

        cantidadTxt.setText("cantidad");

        categoriaTxt.setText("Categoria:");

        estadoTxt.setText("Estado:");

        descrTxt.setText("Descipción:");

        cbxCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BEBIDA", "COMIDA", "POSTRE" }));

        borrarBtn.setText("BORRAR");
        borrarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarBtnActionPerformed(evt);
            }
        });

        registrarBtn.setText("REGISTRAR");
        registrarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarBtnActionPerformed(evt);
            }
        });

        modificarBtn.setText("MODIFICAR");
        modificarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarBtnActionPerformed(evt);
            }
        });

        buscarBtn.setText("BUSCAR");
        buscarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarBtnActionPerformed(evt);
            }
        });

        cbxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "OPERATIVO", "NO OPERATIVO" }));

        guardarBtn.setText("GUARDAR");
        guardarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarBtnActionPerformed(evt);
            }
        });

        tFBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tFBuscarActionPerformed(evt);
            }
        });

        cbxbusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "codigo", "producto", "estado", "categoria" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 875, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(estadoTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(categoriaTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(descrTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cantidadTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(precioVTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(precioCTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(productoTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(codigoTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tFCantidad, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tFPrecioV, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tFPrecioC, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tFproducto, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tFCodigo, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbxCategoria, 0, 226, Short.MAX_VALUE)
                            .addComponent(cbxEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tFDesc)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(borrarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                        .addComponent(registrarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(guardarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(modificarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buscarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbxbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tFBuscar)))
                .addGap(78, 78, 78))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tFCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(codigoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(productoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tFproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(precioCTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tFPrecioC, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tFPrecioV, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(precioVTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tFCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cantidadTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(estadoTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                            .addComponent(cbxEstado))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(categoriaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(descrTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                            .addComponent(tFDesc))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(registrarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(borrarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(modificarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(guardarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbxbusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                            .addComponent(tFBuscar))
                        .addGap(18, 18, 18)
                        .addComponent(buscarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 33, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void registrarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarBtnActionPerformed

        registrarProducto();
    }//GEN-LAST:event_registrarBtnActionPerformed

    private void borrarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarBtnActionPerformed
        if (tablaProductos.getSelectedRow() > -1) {
            eliminarProducto();
        } else {
            JOptionPane.showMessageDialog(this, "debe seleccionar un empleado. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_borrarBtnActionPerformed

    private void modificarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarBtnActionPerformed
        if (tablaProductos.getSelectedRow() > -1) {
            botonModificar();
        } else {
            JOptionPane.showMessageDialog(this, "debe seleccionar un empleado. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_modificarBtnActionPerformed

    private void guardarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarBtnActionPerformed
        guardarmodificacion();
    }//GEN-LAST:event_guardarBtnActionPerformed

    private void tFBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tFBuscarActionPerformed

    }//GEN-LAST:event_tFBuscarActionPerformed

    private void buscarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarBtnActionPerformed
        botonBuscar();
    }//GEN-LAST:event_buscarBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton borrarBtn;
    private javax.swing.JButton buscarBtn;
    private javax.swing.JLabel cantidadTxt;
    private javax.swing.JLabel categoriaTxt;
    private javax.swing.JComboBox<String> cbxCategoria;
    private javax.swing.JComboBox<String> cbxEstado;
    private javax.swing.JComboBox<String> cbxbusqueda;
    private javax.swing.JLabel codigoTxt;
    private javax.swing.JLabel descrTxt;
    private javax.swing.JLabel estadoTxt;
    private javax.swing.JButton guardarBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton modificarBtn;
    private javax.swing.JLabel precioCTxt;
    private javax.swing.JLabel precioVTxt;
    private javax.swing.JLabel productoTxt;
    private javax.swing.JButton registrarBtn;
    private javax.swing.JTextField tFBuscar;
    private javax.swing.JTextField tFCantidad;
    private javax.swing.JTextField tFCodigo;
    private javax.swing.JTextField tFDesc;
    private javax.swing.JTextField tFPrecioC;
    private javax.swing.JTextField tFPrecioV;
    private javax.swing.JTextField tFproducto;
    private javax.swing.JTable tablaProductos;
    // End of variables declaration//GEN-END:variables
}
