
package com.alvarobajo.views.panel;


import com.alvarobajo.Controllers.DAOLoguerImpl;;
import com.alvarobajo.Controllers.DAORegistroVentasImpl;
import com.alvarobajo.utilidades.Utilidades;
import com.alvarobajo.Controllers.DAOTpvImpl;
import com.alvarobajo.Controllers.DAOVentaPDFImpl;
import com.alvarobajo.interfaces.DAORegistroVentas;
import com.alvarobajo.interfaces.DAOTpv;
import com.alvarobajo.interfaces.DAOVentaPDF;
import com.alvarobajo.models.Clientes;
import com.alvarobajo.models.DetalleVenta;
import com.alvarobajo.models.Productos;
import com.alvarobajo.models.Trabajadores;
import com.alvarobajo.models.Ventas;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alvaroBajo
 */

/*
*como esta view es la que mas errores podria causar es la que he intentado que
todo llegue de la manera mas conveniente hacia ella. mas abajo explico en gran 
mayoria todos los metodos que uso
*/
public class PTpv extends javax.swing.JPanel {

    /**
     * Creates new form Tpv
     */
    //lsita para los detalles de los productos para vender
    ArrayList<DetalleVenta> listaProduc = new ArrayList();
    private DetalleVenta produc;

    private int idProducto = 0;
    private String nombre = "";
    private int cantidadProductoBBDD = 0;
    private double precioUnitario = 0.0;
    // private final double PORCENTAJE_IVA = 1.21;
    private int IdCliente = 0;
    private final int idTrabajador = DAOLoguerImpl.us.getId();
    private int numeroCompras = 0;
    private double dineroGastado = 0;

    //creo variables para incrementar cosas en clientes.
    private int cantidad = 0; //cantidad de productos a comprar
    private double subTotal = 0.0;
    private double descuento = 0.0;
    private double totalPagar = 0.0;
    private final double PRIMER_DESCUENTO = 10;
    private final double SEGUNDO_DESCUENTO = 15;
    private Double subirDescuento = 0.0;
    //variable para borrar datos de arrays list
    //variables para calculos glovales
    private double ivaGeneral;
    private double subTotalGeneral;
    private double descuentoGeneral;
    private double TotalGeneral;
    //fin de variables de calculo glovales
    private int auxIdDetalle = 1;
    //conseguimos el id de la ultima venta
    private int idUltimaVenta = 0;
    Clientes cliente = new Clientes();

    public PTpv() {
        initComponents();
        //cargo los clientes en la vista.
        cargarClientes();
        loadTablaCompra();

        //los dejo no visibles hasta que se agregue el primer producto
        txtEfectivo.setVisible(false);
        btnCambio.setVisible(false);
        jLabel8.setVisible(false);
        jLabel9.setVisible(false);

    }


    /*
    Metodo para cargar clientes en el combobox
     */
    private void cargarClientes() {

        try {
            DAOTpv dao = new DAOTpvImpl();
            ArrayList<Clientes> lista = (ArrayList<Clientes>) dao.listar("");
            comBuscarCliente.removeAllItems();
            comBuscarCliente.addItem("selecione cliente");
            for (int i = 0; i < lista.size(); i++) {
                comBuscarCliente.addItem(lista.get(i).getNombre());
            }
        } catch (Exception ex) {
            Logger.getLogger(PTpv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
    *metodo para el boton buscar
     */
    private void botonBuscar() {
        try {
            String colum = txtBuscarCliente.getText();
            DAOTpv dao = new DAOTpvImpl();
            ArrayList<Clientes> lista = (ArrayList<Clientes>) dao.listar(colum);
            comBuscarCliente.removeAllItems();
            if (!lista.isEmpty()) {
                for (int i = 0; i < lista.size(); i++) {
                    comBuscarCliente.addItem(lista.get(i).getNombre());
                }
            } else {
                JOptionPane.showMessageDialog(this, "No existe cliente con ese numero", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                txtBuscarCliente.requestFocus();
                cargarClientes();
                comBuscarCliente.setSelectedItem("selecione cliente");
            }
            txtBuscarCliente.setText("");
        } catch (Exception ex) {
            System.out.println("!ERROR¡" + ex);
        }
    }

    /*
     *Metodo para categorizar los productos
     */
    private void categoriasProductos() {
        try {
            String nombre = cbxCategoria.getSelectedItem().toString();
            DAOTpv dao = new DAOTpvImpl();
            DefaultTableModel model = (DefaultTableModel) TablaProductos.getModel();
            model.setRowCount(0);
            dao.listarProductos(nombre).forEach((p) -> model.addRow(new Object[]{p.getProducto(), p.getEstado(), p.getDescripcion(), p.getCantidad(), p.getPrecio_venta()}));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /*
     *Metodo para agragar productos
     */
    private void ponerProducto() {
        String producto = (String) TablaProductos.getValueAt(TablaProductos.getSelectedRow(), 0);
        String cliente = comBuscarCliente.getSelectedItem().toString();
        cantidad = Integer.parseInt(txtCantidad.getText());
        Clientes c = new Clientes();
        Productos p = new Productos();
        DAOTpv dao = new DAOTpvImpl();

        try {
            p = dao.esUnProducto(producto);
            nombre = p.getProducto();
            idProducto = p.getId();
            cantidadProductoBBDD = Integer.parseInt(p.getCantidad());
            precioUnitario = Double.parseDouble(p.getPrecio_venta());
            if (cantidad <= cantidadProductoBBDD) {
                if (!cliente.equalsIgnoreCase("selecione cliente")) {
                    c = dao.esUnCliente(cliente);
                    descuento = Double.parseDouble(c.getDescuento());
                    IdCliente = c.getId();
                    numeroCompras = c.getNumero_compras();
                    dineroGastado = Double.parseDouble(c.getDinero_gastado());
                } else {
                    descuento = 0;
                }
                System.out.println(descuento);
                //this.calcularIva(precioUnitario);
                subTotal = precioUnitario * cantidad;
                totalPagar = subTotal * cantidad;
                //redondear decimales
                subTotal = (double) Math.round(subTotal * 100) / 100;
                //descuento = (double) Math.round(subTotal * 100) / 100;
                totalPagar = (double) Math.round(subTotal * 100) / 100;

                //Creo un nuevo producto
                produc = new DetalleVenta(1, auxIdDetalle, idProducto, nombre, cantidad, precioUnitario, subTotal, descuento, totalPagar);
                //añadimos a la lista
                listaProduc.add(produc);
                //icremento el contador
                auxIdDetalle++;
                //vacio el campo cantidad
                txtCantidad.setText("");

                //llamo metodos para calcular
                calcularTotal();

                //hago visibles los botones
                txtEfectivo.setVisible(true);
                btnCambio.setVisible(true);
                jLabel8.setVisible(true);
                jLabel9.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "No hay stock suficiente para esa cantiad. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            System.out.println("cae aqui PonerProducto()" + e.toString());
        }

    }

    /*
     *metodo para mostrar la tabla de los productos a comprar
     */
    private void loadTablaCompra() {
        DefaultTableModel model = (DefaultTableModel) tablaDetalle.getModel();
        listaProduc.forEach((p) -> model.addRow(new Object[]{p.getNombre(), p.getCantidad(), p.getPrecio_unitario(), p.getSub_total(), "eliminar"}));

    }

    /*
     *metodo para eliminar los campos de la tabla para que no se multipliquen en la misma
     */
    private void eliminarContenidoTablaDetalle() {
        DefaultTableModel tb = (DefaultTableModel) tablaDetalle.getModel();
        int a = tablaDetalle.getRowCount() - 1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount() - 1);
        }
    }

    /*
     * Metodo para que elimine el contenido de la tabla para un nuevo cliente
     */
    private void eliminarTablaProductos() {
        DefaultTableModel tb = (DefaultTableModel) TablaProductos.getModel();
        int a = TablaProductos.getRowCount() - 1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount() - 1);
        }
    }

    /*
     * metodo para calcular el total de los productos
     */
    private void calcularTotal() {
        subTotalGeneral = 0.0;
        descuentoGeneral = 0.0;
        TotalGeneral = 0.0;
        ivaGeneral = 0.0;

        for (DetalleVenta e : listaProduc) {
            subTotalGeneral += e.getSub_total();
            descuentoGeneral = e.getDescuento();
            TotalGeneral += e.getTotal_pagar();

        }

        //saco el descuento
        if (descuento > 0) {
            descuentoGeneral = (TotalGeneral * descuentoGeneral) / 100;
            TotalGeneral -= descuentoGeneral;
        }

        ivaGeneral = (TotalGeneral * 21) / 100;
        System.out.println(subTotalGeneral + "," + descuentoGeneral + " " + TotalGeneral);
        //redondeo los precios
        subTotalGeneral = (double) Math.round(subTotalGeneral * 100) / 100;
        TotalGeneral = (double) Math.round(TotalGeneral * 100) / 100;
        ivaGeneral = (double) Math.round(ivaGeneral * 100) / 100;
        //envio datos a al jFrame
        txtDescuento.setText(String.valueOf(descuentoGeneral));
        txtSubTotal.setText(String.valueOf(subTotalGeneral));
        txtTotal.setText(String.valueOf(TotalGeneral));
        txtIva.setText(String.valueOf(ivaGeneral));

    }

    private void registrarVenta() {

        DAORegistroVentas dao = new DAORegistroVentasImpl();
        Ventas venta = new Ventas();
        DetalleVenta detalle = new DetalleVenta();
        double precioTotalDou = Double.parseDouble(txtTotal.getText().trim());

        dineroGastado = Double.parseDouble(txtTotal.getText().trim());
        
        DAOTpv daoT = new DAOTpvImpl();
        DAORegistroVentas daoV = new DAORegistroVentasImpl();

        /*
         *En este metodo voy a validar si el  cliente esta registrado para poder
         * aplicarle el descuento que le corresponda
         */
        if (!comBuscarCliente.getSelectedItem().equals("selecione cliente")) {

            try {
                /*
                *Variables para añadir al cliente en caso de que hubiese
                 */
                String nombreCliente;
                double dineroGastadoCliente;
                String totalDinero;
                String subir;

                nombreCliente = comBuscarCliente.getSelectedItem().toString().trim();
                cliente = daoT.esUnCliente(nombreCliente);
                dineroGastadoCliente = Double.parseDouble(cliente.getDinero_gastado());
                dineroGastadoCliente = dineroGastadoCliente + dineroGastado;
                totalDinero = String.valueOf(dineroGastadoCliente);
                numeroCompras = cliente.getNumero_compras() + 1;
                /*
                 *ahora valido el numero de compras hechas para poder aplicarle el descuento en la compra 10 
                 *y subirselo un poco mas en la compra 15. 
                 */
                if (numeroCompras >= 15) {
                    subirDescuento = SEGUNDO_DESCUENTO;
                } else if (numeroCompras >= 10) {
                    subirDescuento = PRIMER_DESCUENTO;
                } else {
                    subirDescuento = 0.0;
                }

                subir = String.valueOf(subirDescuento);
                cliente.setDescuento(subir);
                cliente.setDinero_gastado(totalDinero);
                cliente.setNumero_compras(numeroCompras);
                IdCliente = cliente.getId();
                try {
                    daoT.añadirCompra(cliente);
                    System.out.println(cliente.toString());
                } catch (Exception ex) {
                    System.out.println("no se pudo añadir el cliente registrarVenta()" + ex.toString());
                }
            } catch (SQLException ex) {
                System.out.println("No se pudo aceder al clienteregistrarVenta()" + ex.toString());
            }
        }
        /*
             * ahora vamos con las varibles de la compra
         */
 /*
         si el programa no pasa por el if porque el cliente esta vacio va añadir 0 a id cliente lo que significara que no es cliente registrado
         */
        venta.setIdCliente(IdCliente);
        venta.setIdTrabajaor(idTrabajador);
        venta.setPrecioVenta(precioTotalDou);

        try {
            daoV.registrar(venta);
        } catch (Exception ex) {
            System.out.println("cae en RegistrarVEnta cuadno vamos a añadir la venta " + ex.toString());

        }
        idUltimaVenta = DAORegistroVentasImpl.idVentaRegistrada;
        int idRestarCantidad;
        int cantidadRestarProducoto;
        for (DetalleVenta elemento : listaProduc) {
            detalle.setId_venta(idUltimaVenta);
            detalle.setId_producto(elemento.getId_producto());

            detalle.setCantidad(elemento.getCantidad());
            detalle.setPrecio_unitario(elemento.getPrecio_unitario());
            detalle.setSub_total(elemento.getSub_total());
            detalle.setDescuento(elemento.getDescuento());
            detalle.setIva(elemento.getIva());
            detalle.setTotal_pagar(elemento.getTotal_pagar());
            try {
                daoV.registrarDetalle(detalle);
                System.out.println("el producto se registro correctamente");
                idRestarCantidad = detalle.getId_producto();
                cantidadRestarProducoto = detalle.getCantidad();
                restarCantidad(idRestarCantidad, cantidadRestarProducoto);

            } catch (Exception ex) {
                System.out.println("cae en el for de registrar venta" + ex.toString());
            }

        }
       

        //generamos las facturas 
        System.out.println("el id es:" +IdCliente);
        DAOVentaPDF factura = new DAOVentaPDFImpl();
        try {
                factura.datosClientes(IdCliente); 
        } catch (Exception ex) {
            System.out.println("falla en encontrar el id cliente en la factura");
        }
        subirVolumenTrabajador();
        factura.generarPDF();
        comprarealizada();


        /*
         * si no hubiese cliente regist
         */
    }

    /*
     * cuando termine la compra pongo todo a cero
     */
    private void comprarealizada() {
        listaProduc.clear();
        eliminarContenidoTablaDetalle();
        loadTablaCompra();
        categoriasProductos();
        cargarClientes();
        eliminarTablaProductos();
        IdCliente = 0;
        txtCambio.setText("");
        txtBuscarCliente.setText("");
        txtCantidad.setText("");
        txtDescuento.setText("");
        txtEfectivo.setText("");
        txtIva.setText("");
        txtSubTotal.setText("");
        txtTotal.setText("");
        txtEfectivo.setVisible(false);
        btnCambio.setVisible(false);
        jLabel8.setVisible(false);
        jLabel9.setVisible(false);
    }
    
    /*
    *par subir el volumen de las ventas del trabajador creo este metodo 
    */
    private void subirVolumenTrabajador(){
        
        String volumenVentas;
        double volumen;
        String volumen21;
        Trabajadores usuario = new Trabajadores();
        usuario = DAOLoguerImpl.us;
        System.out.println(usuario.toString());
        volumen21 = usuario.getVolumen_ventas().trim() ;
        System.out.println("el volumen de ventas es: "+volumen21);
        volumen = Double.parseDouble(volumen21);
        volumen += Double.parseDouble(txtSubTotal.getText());
        volumenVentas = String.valueOf(volumen);
        usuario.setVolumen_ventas(volumenVentas);
        usuario.setNumero_ventas(usuario.getNumero_ventas()+1);
        System.out.println("el volumen de ventas es: "+volumenVentas);
        DAOTpv dao = new DAOTpvImpl();
        System.out.println(usuario.toString()); 
       try {
            dao.modificarVolumenTrabajador(usuario);
        } catch (Exception ex) {
            System.out.println("ERROR " +ex);
        }
    }

    /*
     * con este metodo resto la cantidad de productos en la base de datos 
     * primeto obteniendo el producto desde su id que viene guardado 
     * en el elemento y que traigo aqui con IdrestarProducto y con la cantidad que vendemos 
     */
    private void restarCantidad(int idRestarProducoto, int cantidadRestarProducto) {
        int cantidadro;
        String cantidadCogida, cantidadPoner;
        Productos pro = new Productos();
        DAOTpv daoBorrar = new DAOTpvImpl();
        try {
            pro = daoBorrar.getProductoID(idRestarProducoto);
        } catch (Exception ex) {
            System.out.println("cae al conseguir producoto desde su ID en restarCantidad");
        }
        cantidadCogida = pro.getCantidad();
        cantidadro = Integer.parseInt(cantidadCogida);
        cantidadro -= cantidadRestarProducto;
        cantidadPoner = String.valueOf(cantidadro);
        pro.setCantidad(cantidadPoner);
        try {
            daoBorrar.modificarCantidad(pro);
        } catch (Exception ex) {
            System.out.println("cae en restarCantidad en el producto restar" + ex.toString());
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

        jPanelPrincipal = new javax.swing.JPanel();
        jPanelUsuario = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        comBuscarCliente = new javax.swing.JComboBox<>();
        txtBuscarCliente = new javax.swing.JTextField();
        btnBuscarCliente = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        btnBuscarCategoria = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaProductos = new javax.swing.JTable();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaDetalle = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JTextField();
        txtDescuento = new javax.swing.JTextField();
        txtIva = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        txtEfectivo = new javax.swing.JTextField();
        txtCambio = new javax.swing.JTextField();
        btnCambio = new javax.swing.JButton();
        btnVenta = new javax.swing.JButton();
        cbxCategoria = new javax.swing.JComboBox<>();

        jPanelPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("CLIENTE:");

        comBuscarCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "selecione cliente" }));

        btnBuscarCliente.setText("BUSCAR");
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });

        jLabel2.setText("CATEGORIA:");

        btnBuscarCategoria.setText("BUSCAR");
        btnBuscarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCategoriaActionPerformed(evt);
            }
        });

        TablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOMBRE", "ESTADO", "DESCRIPCION", "CANTIDAD STOCK", "PRECIO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TablaProductos);

        jLabel3.setText("CANTIDAD:");

        btnAceptar.setText("ACEPTAR");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        tablaDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PRODUCTO", "CANTIDAD", "PRECIO UNITARIO", "PRECIO TOTAL", "eliminar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaDetalle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaDetalleMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablaDetalle);

        jLabel4.setText("SUBTOTAL:");

        jLabel5.setText("DESCUENTO APLICADO:");

        jLabel6.setText("IVA:");

        jLabel7.setText("TOTAL A PAGAR:");

        jLabel8.setText("EFECTIVO:");

        jLabel9.setText("CAMBIO:");

        txtSubTotal.setEditable(false);
        txtSubTotal.setBorder(null);

        txtDescuento.setEditable(false);
        txtDescuento.setBorder(null);
        txtDescuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescuentoActionPerformed(evt);
            }
        });

        txtIva.setEditable(false);
        txtIva.setBorder(null);

        txtTotal.setEditable(false);
        txtTotal.setBorder(null);

        txtCambio.setEditable(false);
        txtCambio.setBorder(null);

        btnCambio.setText("CALCULAR CAMBIO");
        btnCambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambioActionPerformed(evt);
            }
        });

        btnVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/alvarobajo/img/impresora.png"))); // NOI18N
        btnVenta.setText("EJECUTAR VENTA");
        btnVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(txtIva, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(txtEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(txtCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addComponent(btnCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(270, 270, 270)
                                .addComponent(btnVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIva, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(btnCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(btnVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        cbxCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "bebida", "comida", "postre" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(btnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(btnBuscarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(326, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(jSeparator1)
                            .addComponent(jSeparator3)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 27, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanelUsuarioLayout = new javax.swing.GroupLayout(jPanelUsuario);
        jPanelUsuario.setLayout(jPanelUsuarioLayout);
        jPanelUsuarioLayout.setHorizontalGroup(
            jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelUsuarioLayout.setVerticalGroup(
            jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelPrincipalLayout = new javax.swing.GroupLayout(jPanelPrincipal);
        jPanelPrincipal.setLayout(jPanelPrincipalLayout);
        jPanelPrincipalLayout.setHorizontalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelPrincipalLayout.setVerticalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtDescuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescuentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescuentoActionPerformed

    private void btnBuscarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCategoriaActionPerformed
        categoriasProductos();
    }//GEN-LAST:event_btnBuscarCategoriaActionPerformed

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        botonBuscar();
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed

        String cantidadd = txtCantidad.getText();
        if (TablaProductos.getSelectedRow() > -1) {
            if (!txtCantidad.getText().isEmpty()) {
                if (Utilidades.isNumeric(cantidadd)) {
                    if (Integer.parseInt(cantidadd) > 0) {
                        // ponerProducto();
                        //this.cantidad = Integer.parseInt(cantidadd);
                        ponerProducto();
                    } else {
                        JOptionPane.showMessageDialog(this, "la cantidad no puede ser 0 o negativo. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "la cantidad debe de ser un numero entero. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);

                }
            } else {
                JOptionPane.showMessageDialog(this, "debe digitar una cantidad. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);

            }

        } else {
            JOptionPane.showMessageDialog(this, "debe seleccionar un producto. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);

        }
        eliminarContenidoTablaDetalle();//llamo al metodo para que limpie la tabla antes de cargar la nueva
        loadTablaCompra();// cargo la tabla

    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambioActionPerformed

        //valido que el campo efectivo no este vacio
        if (!txtEfectivo.getText().isEmpty()) {
            //valido que el usuario solo mete numeros y no caracteres especiales ni letras
            if (Utilidades.isNumericDouble(txtEfectivo.getText().trim())) {
                //validamos que el dinero en efectivo sea mayor al dinerto a pagar
                double efe = Double.parseDouble(txtEfectivo.getText().trim());
                double pag = Double.parseDouble(txtTotal.getText().trim());
                if (efe > pag) {
                    double cambios = (efe - pag);
                    double cambio = (double) Math.round(cambios * 100d) / 100;
                    String cambioFin = String.valueOf(cambio);
                    txtCambio.setText(cambioFin);
                } else {
                    JOptionPane.showMessageDialog(this, "El dinero entregado tiene que ser mayor al cambio\n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                    txtEfectivo.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Debe digitar numeros \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                txtEfectivo.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese un numero el el campo efectivo \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
            txtEfectivo.requestFocus();
        }
    }//GEN-LAST:event_btnCambioActionPerformed

    private void tablaDetalleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaDetalleMouseClicked

        //borramos un elemento desde la tabla
        int fila = tablaDetalle.getSelectedRow();
        if (fila > 0) {
            int opcion = JOptionPane.showConfirmDialog(this, "¿Esta seguro que desea borrar el producto?", "Eliminar", JOptionPane.YES_NO_OPTION);
            /*
         *le preguntamos si de verdad desea eliminarlo
         * esto funciona: opciones, si pulsa sobre SI nos devuelve un 1 
         *si presiono NO delvuelve 0
         *si presiono Cancel devuelve 2
         *si presiono close devuelve -1
             */
            if (opcion == 0) {
                listaProduc.remove(fila);
                eliminarContenidoTablaDetalle();
                loadTablaCompra();
                calcularTotal();
            }
        }
    }//GEN-LAST:event_tablaDetalleMouseClicked

    private void btnVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentaActionPerformed
        registrarVenta();
    }//GEN-LAST:event_btnVentaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaProductos;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnBuscarCategoria;
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnCambio;
    private javax.swing.JButton btnVenta;
    private javax.swing.JComboBox<String> cbxCategoria;
    private javax.swing.JComboBox<String> comBuscarCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JPanel jPanelUsuario;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    public static javax.swing.JTable tablaDetalle;
    private javax.swing.JTextField txtBuscarCliente;
    private javax.swing.JTextField txtCambio;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtDescuento;
    private javax.swing.JTextField txtEfectivo;
    private javax.swing.JTextField txtIva;
    private javax.swing.JTextField txtSubTotal;
    public static javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
