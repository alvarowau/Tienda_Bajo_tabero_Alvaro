
package com.alvarobajo.views.panel;

import com.alvarobajo.Controllers.DAOLoguerImpl;
import java.awt.Color;

/**
 *
 * @author alvaroBajo
 */

/*
*en esta view podremos un mensaje de saludo y todo lo que podemos hacer con la app
*/
public class PPrincipal extends javax.swing.JPanel {

    /**
     * Creates new form Principal
     */
    public PPrincipal() {
        initComponents();
        InitStyles();
    }
    
    private void InitStyles(){
        tituloTxt.putClientProperty( "FlatLaf.styleClass", "h00" );
        tituloTxt.setText("Bienvenido "+ DAOLoguerImpl.us.getNombre());
        primeraTxt.putClientProperty( "FlatLaf.styleClass", "h0" );
        segundaTxt.putClientProperty( "FlatLaf.styleClass", "h0" );
        terceraTxt.putClientProperty( "FlatLaf.styleClass", "h1" );
        cuartaTxt.putClientProperty( "FlatLaf.styleClass", "h1" );
        quintaTxt.putClientProperty( "FlatLaf.styleClass", "h2" );
        sextaTxt.putClientProperty( "FlatLaf.styleClass", "h2" );
        septimaTxt.putClientProperty( "FlatLaf.styleClass", "h2" );
        occhoTxt.putClientProperty( "FlatLaf.styleClass", "h2" );
        nueveTxt.putClientProperty( "FlatLaf.styleClass", "h2" );
        diezTxt.putClientProperty( "FlatLaf.styleClass", "h2" );
        lemaTxt.putClientProperty( "FlatLaf.style", "font: 200% $light.font" );
        lemaTxt.setForeground(Color.red);
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
        tituloTxt = new javax.swing.JLabel();
        primeraTxt = new javax.swing.JLabel();
        segundaTxt = new javax.swing.JLabel();
        terceraTxt = new javax.swing.JLabel();
        cuartaTxt = new javax.swing.JLabel();
        quintaTxt = new javax.swing.JLabel();
        sextaTxt = new javax.swing.JLabel();
        septimaTxt = new javax.swing.JLabel();
        occhoTxt = new javax.swing.JLabel();
        nueveTxt = new javax.swing.JLabel();
        diezTxt = new javax.swing.JLabel();
        lemaTxt = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tituloTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloTxt.setText("Bienvenido {nombre}:");

        primeraTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        primeraTxt.setText("Sistema de gestión para bar/restaurante. Controle y administre");

        segundaTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        segundaTxt.setText("de forma óptima y fácil el flujo de trabajo.");

        terceraTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        terceraTxt.setText("Esta herramienta le permitirá llevar un control completo y detallado de su bar/restaurante");

        cuartaTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cuartaTxt.setText("tendrá acceso a herramientas especiales para tareas específicas, como por ejemplo:");

        quintaTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        quintaTxt.setText("— La tabla de sus empleados donde puede añadir o quitar, así como ver el número de ventas de cada uno.");

        sextaTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sextaTxt.setText("— Los productos de los que dispone, así como añadir más o quitar si ya no lo quisiera.");

        septimaTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        septimaTxt.setText("— Registro de clientes, de las compras realizadas, podrá añadir o quitar clientes.");

        occhoTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        occhoTxt.setText("— Listado de ventas realizadas, pudiendo filtrar por días, meses, clientes, etc…");

        nueveTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nueveTxt.setText("— Acceso a la TPV donde podrá realizar las ventas.");

        diezTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        diezTxt.setText("— También dispone de una pestaña de reportes donde podrá poner dudas o fallos encontrados, donde le atenderemos lo antes posible.");

        lemaTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lemaTxt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/alvarobajo/img/happy_smiley_icon-icons.com_49910.png"))); // NOI18N
        lemaTxt.setText("WORK HAPPY, HAVE FUN, MAKE HISTORY.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lemaTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(segundaTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tituloTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(primeraTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(terceraTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cuartaTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(quintaTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sextaTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(septimaTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(occhoTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nueveTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(diezTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 1370, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(tituloTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addGap(39, 39, 39)
                .addComponent(primeraTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(segundaTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(41, 41, 41)
                .addComponent(terceraTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cuartaTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(27, 27, 27)
                .addComponent(quintaTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sextaTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(septimaTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(occhoTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nueveTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(diezTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(65, 65, 65)
                .addComponent(lemaTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(281, 281, 281))
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cuartaTxt;
    private javax.swing.JLabel diezTxt;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lemaTxt;
    private javax.swing.JLabel nueveTxt;
    private javax.swing.JLabel occhoTxt;
    private javax.swing.JLabel primeraTxt;
    private javax.swing.JLabel quintaTxt;
    private javax.swing.JLabel segundaTxt;
    private javax.swing.JLabel septimaTxt;
    private javax.swing.JLabel sextaTxt;
    private javax.swing.JLabel terceraTxt;
    private javax.swing.JLabel tituloTxt;
    // End of variables declaration//GEN-END:variables
}
