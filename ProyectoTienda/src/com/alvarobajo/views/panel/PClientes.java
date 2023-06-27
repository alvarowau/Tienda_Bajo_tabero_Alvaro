package com.alvarobajo.views.panel;

import com.alvarobajo.Controllers.DAOClientesImpl;
import com.alvarobajo.interfaces.DAOClientes;
import com.alvarobajo.models.Clientes;
import com.alvarobajo.utilidades.Utilidades;
import com.alvarobajo.views.PanelAdmin;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alvaroBajo
 */
public class PClientes extends javax.swing.JPanel {

    /**
     * Creates new form Usuarios
     */
    boolean esEdicion = false;
    Clientes clienteEdicion;

    public PClientes() {
        initComponents();
        InitStyles();
        loadClientes();
    }

    public PClientes(Clientes c) {
        initComponents();
        esEdicion = true;
        clienteEdicion = c;
        InitStyles();
        loadClientes();
    }

    private void InitStyles() {
        //modificadores de texto
        tFDescuento.putClientProperty("JTextField.placeholderText", "Ingrese el descuento que se le aplicara al cliente");
        tFDireccion.putClientProperty("JTextField.placeholderText", "Ingrese el nombre a buscar");
        tFEmail.putClientProperty("JTextField.placeholderText", "Ingrese el email del cliente");
        tFNombre.putClientProperty("JTextField.placeholderText", "Ingrese el nombre del cliente");
        tFTelefono.putClientProperty("JTextField.placeholderText", "Ingrese el telefono del cliente");
        tFBuscar.putClientProperty("JTextField.placeholderText", "Ingrese el nombre a buscar");
        //modificadores de estilos
        tFDescuento.putClientProperty("FlatLaf.styleClass", "h4");
        tFDireccion.putClientProperty("FlatLaf.styleClass", "h4");
        tFEmail.putClientProperty("FlatLaf.styleClass", "h4");
        tFNombre.putClientProperty("FlatLaf.styleClass", "h4");
        tFTelefono.putClientProperty("FlatLaf.styleClass", "h4");
        tFBuscar.putClientProperty("FlatLaf.styleClass", "h4");
        nombreTxt.putClientProperty("FlatLaf.styleClass", "h3");
        direcionTxt.putClientProperty("FlatLaf.styleClass", "h3");
        emailTxt.putClientProperty("FlatLaf.styleClass", "h3");
        telefonoTxt.putClientProperty("FlatLaf.styleClass", "h3");
        descuentoTxt.putClientProperty("FlatLaf.styleClass", "h3");
        borrarBtn.putClientProperty("FlatLaf.styleClass", "h2");
        registrarBtn.putClientProperty("FlatLaf.styleClass", "h2");
        buscarBtn.putClientProperty("FlatLaf.styleClass", "h2");
        guardarBtn.putClientProperty("FlatLaf.styleClass", "h2");
        modificarBtn.putClientProperty("FlatLaf.styleClass", "h2");
        guardarBtn.setVisible(false);
        if (esEdicion) {

            if (clienteEdicion != null) {

                tFDescuento.setText(clienteEdicion.getDescuento());
                tFDireccion.setText(clienteEdicion.getDireccion());
                tFEmail.setText(clienteEdicion.getEmail());
                tFNombre.setText(clienteEdicion.getNombre());
                tFTelefono.setText(clienteEdicion.getTelefono());
                guardarBtn.setVisible(true);
                tFBuscar.setVisible(false);
                registrarBtn.setVisible(false);
                modificarBtn.setVisible(false);
                borrarBtn.setVisible(false);
                buscarBtn.setVisible(false);

            }

        }
    }

    private void eliminarCliente() {
        DAOClientes dao = new DAOClientesImpl();
        DefaultTableModel model = (DefaultTableModel) tablaClientes.getModel();
        for (int i : tablaClientes.getSelectedRows()) {
            try {
                dao.eliminar((int) tablaClientes.getValueAt(i, 0));
                model.removeRow(i);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ocurrió un error al borrar el usuario \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                System.out.println(e.getMessage());
            }
        }
        JOptionPane.showMessageDialog(this, "El producto fue eliminado correctamente \n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }

    private void loadClientes() {
        try {
            DAOClientes dao = new DAOClientesImpl();
            DefaultTableModel model = (DefaultTableModel) tablaClientes.getModel();
            dao.listar("").forEach((p) -> model.addRow(new Object[]{p.getId(), p.getNombre(), p.getEmail(), p.getTelefono(), p.getDireccion(), p.getNumero_compras(), p.getDinero_gastado(), p.getDescuento(), p.getFecha_alta()}));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void registrarCliente() {

        String descuento = tFDescuento.getText();
        String direccion = tFDireccion.getText();
        String email = tFEmail.getText();
        String nombre = tFNombre.getText();
        String telefono = tFTelefono.getText();

        /*
        validamos los campos
        */
        
        //lo primero que hacemos es comprobar que ningun campo este vacio
        if (descuento.isEmpty() || direccion.isEmpty() || email.isEmpty() || nombre.isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe rellenar todos los campos. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
            tFNombre.requestFocus();
            return;
        }

        /*
         * en esta comprobamos que el campo descuento sea un numero y sea menor
         * de 10 para no tener problemas mas adelante con la base de datos, ya que desde aqui podriamos tener problemas
        */
        if (Utilidades.isNumeric(descuento)) {
            if (Integer.parseInt(descuento) < 10) {
                Clientes c = new Clientes();
                c.setNombre(nombre);
                c.setDescuento(descuento);
                c.setDireccion(direccion);
                c.setEmail(email);
                c.setNombre(nombre);
                c.setTelefono(telefono);

                try {
                    DAOClientes dao = new DAOClientesImpl();
                    dao.registrar(c);
                    JOptionPane.showMessageDialog(this, "El producto fue registrado correctamente \n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    tFDescuento.setText("");
                    tFDireccion.setText("");
                    tFEmail.setText("");
                    tFNombre.setText("");
                    tFTelefono.setText("");
                    PanelAdmin.showJPanel(new PClientes());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Ocurrió un error al registrar \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(this, "El descuento tiene que ser menor a 10\n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                tFDescuento.requestFocus();
            }

        } else {
            JOptionPane.showMessageDialog(this, "El campo descuento tiene que ser un número. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
            tFDescuento.requestFocus();
        }

    }

    private void guardarmodificacion() {
        String descuento = tFDescuento.getText();
        String direccion = tFDireccion.getText();
        String email = tFEmail.getText();
        String nombre = tFNombre.getText();
        String telefono = tFTelefono.getText();

        //validamos los campos
        if (descuento.isEmpty() || direccion.isEmpty() || email.isEmpty() || nombre.isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe rellenar todos los campos. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
            tFNombre.requestFocus();
            return;
        }

        if (Utilidades.isNumeric(descuento)) {
            if (Integer.parseInt(descuento) < 10) {
                Clientes c = esEdicion ? clienteEdicion : new Clientes();
                c.setNombre(nombre);
                c.setDescuento(descuento);
                c.setDireccion(direccion);
                c.setEmail(email);
                c.setNombre(nombre);
                c.setTelefono(telefono);
                try {
                    DAOClientes dao = new DAOClientesImpl();
                    dao.modificar(c);
                    JOptionPane.showMessageDialog(this, "El usuario fue modificado correctamente \n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    tFDescuento.setText("");
                    tFDireccion.setText("");
                    tFEmail.setText("");
                    tFNombre.setText("");
                    tFTelefono.setText("");
                    PanelAdmin.showJPanel(new PClientes());

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "debe seleccionar un empleado. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(this, "El descuento tiene que ser menor a 10\n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                tFDescuento.requestFocus();
            }

        } else {
            JOptionPane.showMessageDialog(this, "El campo descuento tiene que ser un número. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
            tFDescuento.requestFocus();
        }

    }

    private void botonModificar() {
        try {
            int traId = (int) tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 0);
            DAOClientes dao = new DAOClientesImpl();
            PanelAdmin.showJPanel(new PClientes(dao.getClienteID(traId)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void botonBuscar() {

        try {
            String colum = tFBuscar.getText();
            DAOClientes dao = new DAOClientesImpl();
            DefaultTableModel model = (DefaultTableModel) tablaClientes.getModel();
            model.setRowCount(0);
            dao.listar(colum).forEach((p) -> model.addRow(new Object[]{p.getId(), p.getNombre(), p.getEmail(), p.getTelefono(), p.getDireccion(), p.getNumero_compras(), p.getDinero_gastado(), p.getDescuento(), p.getFecha_alta()}));

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
        tablaClientes = new javax.swing.JTable();
        buscarBtn = new javax.swing.JButton();
        modificarBtn = new javax.swing.JButton();
        registrarBtn = new javax.swing.JButton();
        borrarBtn = new javax.swing.JButton();
        direcionTxt = new javax.swing.JLabel();
        descuentoTxt = new javax.swing.JLabel();
        tFTelefono = new javax.swing.JTextField();
        telefonoTxt = new javax.swing.JLabel();
        emailTxt = new javax.swing.JLabel();
        tFEmail = new javax.swing.JTextField();
        tFNombre = new javax.swing.JTextField();
        nombreTxt = new javax.swing.JLabel();
        tFDireccion = new javax.swing.JTextField();
        tFDescuento = new javax.swing.JTextField();
        guardarBtn = new javax.swing.JButton();
        tFBuscar = new javax.swing.JTextField();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Email", "Telefono", "Direccion", "Numero de compras", "Dinero Gastado", "Descuento", "fecha_alta"
            }
        ));
        jScrollPane1.setViewportView(tablaClientes);

        buscarBtn.setText("BUSCAR");
        buscarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarBtnActionPerformed(evt);
            }
        });

        modificarBtn.setText("MODIFICAR");
        modificarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarBtnActionPerformed(evt);
            }
        });

        registrarBtn.setText("REGISTRAR");
        registrarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarBtnActionPerformed(evt);
            }
        });

        borrarBtn.setText("BORRAR");
        borrarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarBtnActionPerformed(evt);
            }
        });

        direcionTxt.setText("DIRECIÓN:");

        descuentoTxt.setText("DESCUENTO:");

        telefonoTxt.setText("TELEFONO:");

        emailTxt.setText("EMAIL:");

        nombreTxt.setText("NOMBRE:");

        guardarBtn.setText("GUARDAR");
        guardarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 916, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(descuentoTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(direcionTxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(telefonoTxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                                        .addComponent(emailTxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(nombreTxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(53, 53, 53)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tFEmail, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tFNombre, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tFDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tFDireccion)
                                    .addComponent(tFTelefono)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(borrarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(modificarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(guardarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(registrarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)))
                            .addComponent(tFBuscar))
                        .addGap(32, 32, 32))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(buscarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tFNombre)
                    .addComponent(nombreTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tFEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tFTelefono)
                    .addComponent(telefonoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(direcionTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tFDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(descuentoTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(tFDescuento))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(borrarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(registrarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(modificarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(guardarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(51, 51, 51)
                .addComponent(tFBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(buscarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        registrarCliente();
    }//GEN-LAST:event_registrarBtnActionPerformed

    private void borrarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarBtnActionPerformed
        if (tablaClientes.getSelectedRow() > -1) {
            eliminarCliente();
        } else {
            JOptionPane.showMessageDialog(this, "debe seleccionar un empleado. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_borrarBtnActionPerformed

    private void modificarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarBtnActionPerformed
        if (tablaClientes.getSelectedRow() > -1) {
            botonModificar();
        } else {
            JOptionPane.showMessageDialog(this, "debe seleccionar un empleado. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_modificarBtnActionPerformed

    private void guardarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarBtnActionPerformed
        guardarmodificacion();
    }//GEN-LAST:event_guardarBtnActionPerformed

    private void buscarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarBtnActionPerformed
        botonBuscar();
    }//GEN-LAST:event_buscarBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton borrarBtn;
    private javax.swing.JButton buscarBtn;
    private javax.swing.JLabel descuentoTxt;
    private javax.swing.JLabel direcionTxt;
    private javax.swing.JLabel emailTxt;
    private javax.swing.JButton guardarBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton modificarBtn;
    private javax.swing.JLabel nombreTxt;
    private javax.swing.JButton registrarBtn;
    private javax.swing.JTextField tFBuscar;
    private javax.swing.JTextField tFDescuento;
    private javax.swing.JTextField tFDireccion;
    private javax.swing.JTextField tFEmail;
    private javax.swing.JTextField tFNombre;
    private javax.swing.JTextField tFTelefono;
    private javax.swing.JTable tablaClientes;
    private javax.swing.JLabel telefonoTxt;
    // End of variables declaration//GEN-END:variables
}
