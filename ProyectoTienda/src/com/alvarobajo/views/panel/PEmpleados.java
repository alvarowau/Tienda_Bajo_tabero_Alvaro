
package com.alvarobajo.views.panel;

import com.alvarobajo.Controllers.DAOTrabajadoresImpl;
import com.alvarobajo.interfaces.DAOTrabajadores;
import com.alvarobajo.models.Trabajadores;
import com.alvarobajo.views.PanelAdmin;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alvaroBajo
 */
public class PEmpleados extends javax.swing.JPanel {

    boolean esEdicion = false;
    Trabajadores trabajadorEdicion;

    /**
     * Creates new form Usuarios
     */
    public PEmpleados() {
        initComponents();
        initStyles();
        loadTrabajadores();
    }

    public PEmpleados(Trabajadores trabajador) {
        initComponents();
        esEdicion = true;
        trabajadorEdicion = trabajador;
        initStyles();
        loadTrabajadores();
    }

    private void initStyles() {
        nombreTxt.putClientProperty("FlatLaf.styleClass", "h2");
        emailTxt.putClientProperty("FlatLaf.styleClass", "h2");
        telefonoTxt.putClientProperty("FlatLaf.styleClass", "h2");
        dniTxt.putClientProperty("FlatLaf.styleClass", "h2");
        direcionTxt.putClientProperty("FlatLaf.styleClass", "h2");
        claveTxt.putClientProperty("FlatLaf.styleClass", "h2");
        TableEmpleados.putClientProperty("FlatLaf.styleClass", "h4");
        tFClave.putClientProperty("FlatLaf.styleClass", "h4");
        tFClave.putClientProperty("JTextField.placeholderText", "Ingrese la clave");
        tFDireccion.putClientProperty("FlatLaf.styleClass", "h4");
        tFDireccion.putClientProperty("JTextField.placeholderText", "Ingrese la dirección");
        tFDni.putClientProperty("FlatLaf.styleClass", "h4");
        tFDni.putClientProperty("JTextField.placeholderText", "Ingrese el DNI");
        tFEmail.putClientProperty("FlatLaf.styleClass", "h4");
        tFEmail.putClientProperty("JTextField.placeholderText", "Ingrese el email");
        tFNombre.putClientProperty("FlatLaf.styleClass", "h4");
        tFNombre.putClientProperty("JTextField.placeholderText", "Ingrese el nombre");
        tFTelefono.putClientProperty("FlatLaf.styleClass", "h4");
        tFTelefono.putClientProperty("JTextField.placeholderText", "Ingrese el teléfono");
        tFBuscar.putClientProperty("FlatLaf.styleClass", "h4");
        tFBuscar.putClientProperty("JTextField.placeholderText", "Ingrese el nombre a buscar");
        registrarBtn.putClientProperty("FlatLaf.styleClass", "h2");
        modificarBtn.putClientProperty("FlatLaf.styleClass", "h2");
        borrarBtn.putClientProperty("FlatLaf.styleClass", "h2");
        guardarBtn.putClientProperty("FlatLaf.styleClass", "h2");
        buscarBtn.putClientProperty("FlatLaf.styleClass", "h2");
        guardarBtn.setVisible(false);

        if (esEdicion) {

            if (trabajadorEdicion != null) {
                tFNombre.setText(trabajadorEdicion.getNombre());
                tFEmail.setText(trabajadorEdicion.getEmail());
                tFTelefono.setText(trabajadorEdicion.getTelefono());
                tFDni.setText(trabajadorEdicion.getDni());
                tFDireccion.setText(trabajadorEdicion.getDirecion());
                tFClave.setText(trabajadorEdicion.getClave());
                guardarBtn.setVisible(true);
                tFBuscar.setVisible(false);
                registrarBtn.setVisible(false);
                modificarBtn.setVisible(false);
                borrarBtn.setVisible(false);
                buscarBtn.setVisible(false);
            }
        }

    }

    private void loadTrabajadores() {
        try {
            DAOTrabajadores dao = new DAOTrabajadoresImpl();
            DefaultTableModel model = (DefaultTableModel) TableEmpleados.getModel();
            dao.listar("").forEach((t) -> model.addRow(new Object[]{t.getId(), t.getDni(), t.getNombre(), t.getEmail(), t.getTelefono(), t.getDirecion(), t.getNumero_ventas(), t.getVolumen_ventas(), t.getFecha_alta()}));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void eliminarEmpleado() {
        DAOTrabajadores dao = new DAOTrabajadoresImpl();
        DefaultTableModel model = (DefaultTableModel) TableEmpleados.getModel();
        for (int i : TableEmpleados.getSelectedRows()) {
            try {
                dao.eliminar((int) TableEmpleados.getValueAt(i, 0));
                model.removeRow(i);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ocurrió un error al borrar el usuario \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                System.out.println(e.getMessage());
            }
        }
        JOptionPane.showMessageDialog(this, "El usuario fue Borrado correctamente \n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }

    private void registrarEmpleado() {
        String nombre = tFNombre.getText();
        String dni = tFDni.getText();
        String clave = tFClave.getText();
        String email = tFEmail.getText();
        String telefono = tFTelefono.getText();
        String direc = tFDireccion.getText();
        //validamos los campos
        if (nombre.isEmpty() || dni.isEmpty() || clave.isEmpty() || email.isEmpty() || telefono.isEmpty() || direc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe rellenar todos los campos. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
            tFNombre.requestFocus();
            return;
        }
        Trabajadores t = new Trabajadores();
        t.setDni(dni);
        t.setClave(clave);
        t.setNombre(nombre);
        t.setEmail(email);
        t.setTelefono(telefono);
        t.setDirecion(direc);
        try {
            DAOTrabajadores dao = new DAOTrabajadoresImpl();
            dao.registrar(t);
            JOptionPane.showMessageDialog(this, "El usuario fue registrado correctamente \n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            tFNombre.setText("");
            tFDni.setText("");
            tFClave.setText("");
            tFEmail.setText("");
            tFTelefono.setText("");
            tFDireccion.setText("");
            PanelAdmin.showJPanel(new PEmpleados());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al registrar \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardaModificacion() {
        String nombre = tFNombre.getText();
        String dni = tFDni.getText();
        String clave = tFClave.getText();
        String email = tFEmail.getText();
        String telefono = tFTelefono.getText();
        String direc = tFDireccion.getText();
        //validamos los campos
        if (nombre.isEmpty() || dni.isEmpty() || clave.isEmpty() || email.isEmpty() || telefono.isEmpty() || direc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe rellenar todos los campos. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
            tFNombre.requestFocus();
            return;
        }
        Trabajadores t = esEdicion ? trabajadorEdicion : new Trabajadores();
        t.setDni(dni);
        t.setClave(clave);
        t.setNombre(nombre);
        t.setEmail(email);
        t.setTelefono(telefono);
        t.setDirecion(direc);
        try {
            DAOTrabajadores dao = new DAOTrabajadoresImpl();
            dao.modificar(t);
            JOptionPane.showMessageDialog(this, "El usuario fue modificado correctamente \n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            tFNombre.setText("");
            tFDni.setText("");
            tFClave.setText("");
            tFEmail.setText("");
            tFTelefono.setText("");
            tFDireccion.setText("");
            PanelAdmin.showJPanel(new PEmpleados());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "debe seleccionar un empleado. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    private void botonBuscar() {
        try {

            DAOTrabajadores dao = new DAOTrabajadoresImpl();
            DefaultTableModel model = (DefaultTableModel) TableEmpleados.getModel();
            model.setRowCount(0);
            dao.listar(tFBuscar.getText()).forEach((t) -> model.addRow(new Object[]{t.getId(), t.getDni(), t.getNombre(), t.getEmail(), t.getTelefono(), t.getDirecion(), t.getNumero_ventas(), t.getVolumen_ventas(), t.getFecha_alta()}));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }

    private void botonModificar() {
        try {
            int traId = (int) TableEmpleados.getValueAt(TableEmpleados.getSelectedRow(), 0);
            DAOTrabajadores dao = new DAOTrabajadoresImpl();
            PanelAdmin.showJPanel(new PEmpleados(dao.getTrabajadorID(traId)));
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
        TableEmpleados = new javax.swing.JTable();
        nombreTxt = new javax.swing.JLabel();
        tFNombre = new javax.swing.JTextField();
        emailTxt = new javax.swing.JLabel();
        tFEmail = new javax.swing.JTextField();
        direcionTxt = new javax.swing.JLabel();
        tFTelefono = new javax.swing.JTextField();
        telefonoTxt = new javax.swing.JLabel();
        dniTxt = new javax.swing.JLabel();
        tFDni = new javax.swing.JTextField();
        guardarBtn = new javax.swing.JButton();
        registrarBtn = new javax.swing.JButton();
        modificarBtn = new javax.swing.JButton();
        buscarBtn = new javax.swing.JButton();
        claveTxt = new javax.swing.JLabel();
        tFClave = new javax.swing.JTextField();
        tFBuscar = new javax.swing.JTextField();
        borrarBtn = new javax.swing.JButton();
        tFDireccion = new javax.swing.JTextField();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        TableEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Dni", "Nombre", "Email", "Telefono", "Direccion", "Numero de ventas", "Volumen de ventas", "Fecha alta"
            }
        ));
        jScrollPane1.setViewportView(TableEmpleados);

        nombreTxt.setText("NOMBRE:");

        emailTxt.setText("EMAIL:");

        direcionTxt.setText("DIRECCIÓN:");

        telefonoTxt.setText("TELÉFONO:");

        dniTxt.setText("DNI:");

        guardarBtn.setText("GUARDAR");
        guardarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarBtnActionPerformed(evt);
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

        claveTxt.setText("CLAVE:");

        tFBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tFBuscarActionPerformed(evt);
            }
        });

        borrarBtn.setText("BORRAR");
        borrarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 916, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(telefonoTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                .addComponent(dniTxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                .addComponent(direcionTxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                .addComponent(emailTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                            .addComponent(claveTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nombreTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tFNombre)
                            .addComponent(tFEmail, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tFTelefono, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tFDni, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tFClave)
                            .addComponent(tFDireccion)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 9, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(buscarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(106, 106, 106))
                            .addComponent(tFBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(borrarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(guardarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(registrarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(55, 55, 55)
                                    .addComponent(modificarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(62, 62, 62))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 808, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nombreTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(tFNombre))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tFEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(emailTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tFTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(telefonoTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dniTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(tFDni, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(claveTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tFClave, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(direcionTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tFDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 181, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registrarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modificarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(borrarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addComponent(tFBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buscarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
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
        registrarEmpleado();
    }//GEN-LAST:event_registrarBtnActionPerformed

    private void borrarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarBtnActionPerformed
        if (TableEmpleados.getSelectedRow() > -1) {
            eliminarEmpleado();
        } else {
            JOptionPane.showMessageDialog(this, "debe seleccionar un empleado. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_borrarBtnActionPerformed

    private void modificarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarBtnActionPerformed
        if (TableEmpleados.getSelectedRow() > -1) {
            botonModificar();
        } else {
            JOptionPane.showMessageDialog(this, "debe seleccionar un empleado. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_modificarBtnActionPerformed

    private void guardarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarBtnActionPerformed
        guardaModificacion();

    }//GEN-LAST:event_guardarBtnActionPerformed

    private void tFBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tFBuscarActionPerformed

    }//GEN-LAST:event_tFBuscarActionPerformed

    private void buscarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarBtnActionPerformed
        botonBuscar();
    }//GEN-LAST:event_buscarBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableEmpleados;
    private javax.swing.JButton borrarBtn;
    private javax.swing.JButton buscarBtn;
    private javax.swing.JLabel claveTxt;
    private javax.swing.JLabel direcionTxt;
    private javax.swing.JLabel dniTxt;
    private javax.swing.JLabel emailTxt;
    private javax.swing.JButton guardarBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton modificarBtn;
    private javax.swing.JLabel nombreTxt;
    private javax.swing.JButton registrarBtn;
    private javax.swing.JTextField tFBuscar;
    private javax.swing.JTextField tFClave;
    private javax.swing.JTextField tFDireccion;
    private javax.swing.JTextField tFDni;
    private javax.swing.JTextField tFEmail;
    private javax.swing.JTextField tFNombre;
    private javax.swing.JTextField tFTelefono;
    private javax.swing.JLabel telefonoTxt;
    // End of variables declaration//GEN-END:variables
}
