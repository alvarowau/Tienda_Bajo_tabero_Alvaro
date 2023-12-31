
package com.alvarobajo.views;



import com.alvarobajo.Controllers.DAOLoguerImpl;
import com.alvarobajo.views.panel.PClientes;
import com.alvarobajo.views.panel.PEmpleados;
import com.alvarobajo.views.panel.PPrincipal;
import com.alvarobajo.views.panel.PProductos;
import com.alvarobajo.views.panel.PReportes;
import com.alvarobajo.views.panel.PTpv;
import com.alvarobajo.views.panel.PVentas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author alvaroBajo
 */

/*
*este panel sera el que nos acompañe por toda la app ya que lo unico que cambiar 
* es el jpanel de dentro
*/
public class PanelAdmin extends javax.swing.JFrame {

    /**
     * Creates new form PanelAdmin
     */
    FrmLogin login = new FrmLogin();
        
        
    
    public PanelAdmin() {
        
        initComponents();
        InitStyles();
        SetDate();
        InitContent();
    }
        
    public static void showJPanel(JPanel p){
        p.setSize(1390,800);
        p.setLocation(0,0);
        content.removeAll();
        content.add(p,BorderLayout.CENTER);
        content.revalidate();
        content.repaint(); 
    }
    
    private void InitContent(){ 
        showJPanel(new PPrincipal());
  
    }
    
    private void SetDate() {
        LocalDate now = LocalDate.now();
        Locale SpanishLocale = new Locale("es", "ES");
        fechaTxt.setText(now.format(DateTimeFormatter.ofPattern("'Hoy es' EEEE dd 'de' MMMM 'de' YYYY", SpanishLocale)));
    }
    
    private void InitStyles() {
        mensaje.putClientProperty("FlatLaf.styleClass", "h1");
        mensaje.setText("Bienvenido de nuevo: "+ DAOLoguerImpl.us.getNombre()+ " ,su id es: "+ DAOLoguerImpl.us.getId());
        fechaTxt.putClientProperty("FlatLaf.styleClass", "h1");
        fechaTxt.setForeground(Color.BLACK);
        nawText.putClientProperty("FlatLaf.styleClass", "h3");
        nawText.setForeground(Color.WHITE);
        principalBtn.setForeground(Color.white);
        principalBtn.putClientProperty("FlatLaf.styleClass", "h3");
        empleadosBtn.setForeground(Color.white);
        empleadosBtn.putClientProperty("FlatLaf.styleClass", "h3");
        clientesBtn.setForeground(Color.white);
        clientesBtn.putClientProperty("FlatLaf.styleClass", "h3");
        tpvBtn.setForeground(Color.white);
        tpvBtn.putClientProperty("FlatLaf.styleClass", "h3");
        productosBtn.setForeground(Color.white);
        productosBtn.putClientProperty("FlatLaf.styleClass", "h3");
        ventasBtn.setForeground(Color.white);
        ventasBtn.putClientProperty("FlatLaf.styleClass", "h3");
        reportesBtn.setForeground(Color.white);
        reportesBtn.putClientProperty("FlatLaf.styleClass", "h3");
        salirBtn.setForeground(Color.white);
        salirBtn.putClientProperty("FlatLaf.styleClass", "h3");
    }
    
    private void salir(){
        int pregunta;
        pregunta = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea cerrar la sesión?", "pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (pregunta == 0){
            this.dispose();
            login.setVisible(true);
            //System.exit(0);       
        }else{
            
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

        bacground = new javax.swing.JPanel();
        menu = new javax.swing.JPanel();
        principalBtn = new javax.swing.JButton();
        empleadosBtn = new javax.swing.JButton();
        productosBtn = new javax.swing.JButton();
        clientesBtn = new javax.swing.JButton();
        ventasBtn = new javax.swing.JButton();
        tpvBtn = new javax.swing.JButton();
        salirBtn = new javax.swing.JButton();
        reportesBtn = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        mensaje = new javax.swing.JLabel();
        header = new javax.swing.JPanel();
        nawText = new javax.swing.JLabel();
        fechaTxt = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        content = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        bacground.setBackground(new java.awt.Color(255, 255, 255));
        bacground.setForeground(new java.awt.Color(255, 255, 255));

        menu.setBackground(new java.awt.Color(255, 153, 51));
        menu.setPreferredSize(new java.awt.Dimension(270, 640));

        principalBtn.setBackground(new java.awt.Color(255, 153, 51));
        principalBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/alvarobajo/img/icons-principal.png"))); // NOI18N
        principalBtn.setText("PRINCIPAL");
        principalBtn.setBorder(null);
        principalBtn.setBorderPainted(false);
        principalBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        principalBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        principalBtn.setIconTextGap(20);
        principalBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                principalBtnActionPerformed(evt);
            }
        });

        empleadosBtn.setBackground(new java.awt.Color(255, 153, 51));
        empleadosBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/alvarobajo/img/icons-usuario.png"))); // NOI18N
        empleadosBtn.setText("EMPLEADOS");
        empleadosBtn.setBorder(null);
        empleadosBtn.setBorderPainted(false);
        empleadosBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        empleadosBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        empleadosBtn.setIconTextGap(20);
        empleadosBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empleadosBtnActionPerformed(evt);
            }
        });

        productosBtn.setBackground(new java.awt.Color(255, 153, 51));
        productosBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/alvarobajo/img/icons-productos.png"))); // NOI18N
        productosBtn.setText("PRODUCTOS");
        productosBtn.setBorder(null);
        productosBtn.setBorderPainted(false);
        productosBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        productosBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        productosBtn.setIconTextGap(20);
        productosBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productosBtnActionPerformed(evt);
            }
        });

        clientesBtn.setBackground(new java.awt.Color(255, 153, 51));
        clientesBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/alvarobajo/img/icons-cliente.png"))); // NOI18N
        clientesBtn.setText("CLIENTES");
        clientesBtn.setBorder(null);
        clientesBtn.setBorderPainted(false);
        clientesBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clientesBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        clientesBtn.setIconTextGap(20);
        clientesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientesBtnActionPerformed(evt);
            }
        });

        ventasBtn.setBackground(new java.awt.Color(255, 153, 51));
        ventasBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/alvarobajo/img/icons-ventas.png"))); // NOI18N
        ventasBtn.setText("VENTAS");
        ventasBtn.setBorder(null);
        ventasBtn.setBorderPainted(false);
        ventasBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ventasBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ventasBtn.setIconTextGap(20);
        ventasBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ventasBtnActionPerformed(evt);
            }
        });

        tpvBtn.setBackground(new java.awt.Color(255, 153, 51));
        tpvBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/alvarobajo/img/icons-tpv.png"))); // NOI18N
        tpvBtn.setText("TPV");
        tpvBtn.setBorder(null);
        tpvBtn.setBorderPainted(false);
        tpvBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tpvBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tpvBtn.setIconTextGap(20);
        tpvBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tpvBtnActionPerformed(evt);
            }
        });

        salirBtn.setBackground(new java.awt.Color(255, 153, 51));
        salirBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/alvarobajo/img/icons-salir.png"))); // NOI18N
        salirBtn.setText("CERRAR SESIÓN");
        salirBtn.setBorder(null);
        salirBtn.setBorderPainted(false);
        salirBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        salirBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        salirBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirBtnActionPerformed(evt);
            }
        });

        reportesBtn.setBackground(new java.awt.Color(255, 153, 51));
        reportesBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/alvarobajo/img/icons-reporte.png"))); // NOI18N
        reportesBtn.setText("REPORTES");
        reportesBtn.setBorder(null);
        reportesBtn.setBorderPainted(false);
        reportesBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reportesBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        reportesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportesBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuLayout.createSequentialGroup()
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1)
                    .addComponent(clientesBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tpvBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(salirBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(principalBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, menuLayout.createSequentialGroup()
                        .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(empleadosBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(ventasBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(reportesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(productosBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addGap(263, 263, 263)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(principalBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(empleadosBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(productosBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clientesBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ventasBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tpvBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(reportesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(salirBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        mensaje.setText("Biemvenido de nuevo");

        header.setBackground(new java.awt.Color(255, 153, 102));

        nawText.setText("ADMINISTRACIÓN/CONTROL/PAGO/ TPV");

        fechaTxt.setText("Hoy es {dayname} {day} de {month} de {year}");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nawText, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addContainerGap(768, Short.MAX_VALUE)
                .addComponent(fechaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104))
            .addGroup(headerLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nawText, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(fechaTxt)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        content.setBackground(new java.awt.Color(51, 255, 51));

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout bacgroundLayout = new javax.swing.GroupLayout(bacground);
        bacground.setLayout(bacgroundLayout);
        bacgroundLayout.setHorizontalGroup(
            bacgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bacgroundLayout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(bacgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(bacgroundLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 938, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        bacgroundLayout.setVerticalGroup(
            bacgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE)
            .addGroup(bacgroundLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bacground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bacground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void clientesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientesBtnActionPerformed
        showJPanel(new PClientes());
    }//GEN-LAST:event_clientesBtnActionPerformed

    private void empleadosBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empleadosBtnActionPerformed
        showJPanel(new PEmpleados());
    }//GEN-LAST:event_empleadosBtnActionPerformed

    private void principalBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_principalBtnActionPerformed
        showJPanel(new PPrincipal());
    }//GEN-LAST:event_principalBtnActionPerformed

    private void productosBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productosBtnActionPerformed
        showJPanel(new PProductos());
    }//GEN-LAST:event_productosBtnActionPerformed

    private void ventasBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ventasBtnActionPerformed
        showJPanel(new PVentas());
    }//GEN-LAST:event_ventasBtnActionPerformed

    private void tpvBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tpvBtnActionPerformed
        showJPanel(new PTpv());
    }//GEN-LAST:event_tpvBtnActionPerformed

    private void reportesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportesBtnActionPerformed
        showJPanel(new PReportes());
    }//GEN-LAST:event_reportesBtnActionPerformed

    private void salirBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirBtnActionPerformed
        salir();
    }//GEN-LAST:event_salirBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PanelAdmin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PanelAdmin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PanelAdmin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PanelAdmin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PanelAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bacground;
    private javax.swing.JButton clientesBtn;
    private static javax.swing.JPanel content;
    private javax.swing.JButton empleadosBtn;
    private javax.swing.JLabel fechaTxt;
    private javax.swing.JPanel header;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel mensaje;
    private javax.swing.JPanel menu;
    private javax.swing.JLabel nawText;
    private javax.swing.JButton principalBtn;
    private javax.swing.JButton productosBtn;
    private javax.swing.JButton reportesBtn;
    private javax.swing.JButton salirBtn;
    private javax.swing.JButton tpvBtn;
    private javax.swing.JButton ventasBtn;
    // End of variables declaration//GEN-END:variables
}
