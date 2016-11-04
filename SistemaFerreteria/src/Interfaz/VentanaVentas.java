/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import control.Control;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;
import modelo.factura.Factura;
import modelo.factura.LineaDetalle;
import modelo.personas.clientes.Cliente;
import modelo.personas.empleados.Empleado;
import modelo.productos.Producto;

/**
 *
 * @author Laura Alfaro
 */
public class VentanaVentas extends javax.swing.JFrame {

    /**
     * Creates new form VentanaVentas
     */
    public VentanaVentas(Control c) {
        initComponents();
        ctrl = c;
        init();
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }
    public void setFecha(Date fecha) {
        factura.setFecha(fecha);
        txtFieldFecha.setText(dateToString(fecha));
    }
    public Control getCtrl() {
        return ctrl;
    }
    private void init() {
        setInitBlank();
        setComboBox();
        producto = null;
        cantidadLimite = 0;
        setActions(txtFieldCantidadPro);
        setActions(txtFieldDescuentoCli);
        txtFieldFecha.setEditable(false);
        txtFieldCodigoProducto.setEditable(false);
        txtFieldDescripcionPro.setEditable(false);
    }    

    public void setProducto(Producto producto) {
        this.producto = producto;
        setTextFieldProducto();
    }
    public void setCantidadLimite(int cantidad) {
        this.cantidadLimite = cantidad;
    }

    public void setDescuento(int descuento) {
        txtFieldDescuentoCli.setText(String.valueOf(descuento));
    }
    
    private void setTextFieldProducto() {
        txtFieldCodigoProducto.setText(producto.getCodigo());
        txtFieldDescripcionPro.setText(producto.getDescripcion());
    }
    
    private void setInitBlank() {
        txtFieldFecha.setText("");
        txtFieldCedulaCliente.setText("");
        txtFieldDescuentoCli.setText("0");
        lblSubtotal.setText("0");
        lblDescuento.setText("0");
        lblTotal.setText("0");
        clearTable();
        setBlank();
    }
    private void setBlank() {
        txtFieldCodigoProducto.setText("");
        txtFieldDescripcionPro.setText("");
        txtFieldCantidadPro.setText("");
        btnAgregarPro.requestFocus();
    }
    private void setComboBox() {
        LinkedList<Empleado> list = ctrl.getEmpleadosVendedor();
        List<String> ls = new ArrayList<>();
        for (Empleado e : list) {
            ls.add(e.getIdEmpleado() + " - "+  e.getNombre());
        }
        cmBoxVendedores.setModel(new DefaultComboBoxModel(ls.toArray()));
    }
    private void updateTable() {
        LinkedList<LineaDetalle> list = factura.getDetalles();
        DefaultTableModel model = (DefaultTableModel) tbleProductos.getModel();
        model.setRowCount(0);
        Object o[];
        
        for(LineaDetalle ld : list) {
            Producto p = ld.getProducto();
            o = new Object[]{p.getCodigo(), p.getDescripcion(), ld.getCantidad(),p.getUnidadMedida(), ld.getPrecio()};
            model.addRow(o);
        }
    }
    private void clearTable() {
        DefaultTableModel model = (DefaultTableModel) tbleProductos.getModel();
        model.setRowCount(0);
    }
    private String dateToString(Date date) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
    private void setClienteTxtField() {
        try {
            String cedula = txtFieldCedulaCliente.getText();
            if(!cedula.equalsIgnoreCase("")) {
                Cliente cli = ctrl.searchClienteByID(cedula);
                Cliente cliente = factura.getCliente();
                    if (cli == null) {
                        cliente = new Cliente();
                        cliente.setCedula(cedula);
                        cliente.setDescuento(Integer.parseInt(txtFieldDescuentoCli.getText()));
                        ctrl.addCliente(cliente);
                    } else {
                        cliente = cli;
                        cedula = cliente.getCedula();
                        String nombre = cliente.getNombre();
                        txtFieldCedulaCliente.setText(cedula + " - " + nombre);
                        setDescuento(cliente.getDescuento());
                    }
                factura.setCliente(cliente);
            }
        } catch (Exception e) {
        }        
    }
    private void updateDescuento() {
        int desc = Integer.parseInt(txtFieldDescuentoCli.getText());
        try {
            if (factura.getCliente().getDescuento() != desc) {
                factura.getCliente().setDescuento(desc);
                ctrl.updateCliente(factura.getCliente());
            }
        } catch (Exception e) {
        }
    }
    
    private void setActions(javax.swing.JTextField txtField) {
        txtField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent key) {
                char c = key.getKeyChar();
                if(Character.isLetter(c)) {
                    key.consume();
                }
            }
        });
    }
    private void agregaLinea() {
        
        int cantidad = Integer.valueOf(txtFieldCantidadPro.getText());
        if (cantidad < cantidadLimite) {
            double precio = cantidad * producto.getPrecio();
            LineaDetalle ld = new LineaDetalle(producto, cantidad, precio);
            factura.agregarDetalle(ld);
            setBlank();
            updateTable();
        } else {
            JOptionPane.showMessageDialog(this, String.format(MENSAJE_CANTIDAD_INSUFICIENTE, cantidadLimite), "Error", 1);
        }
        lblDescuento.setText(String.valueOf(factura.getDescuento()));
        lblSubtotal.setText(String.valueOf(factura.subTotal()));
        lblTotal.setText(String.valueOf(factura.calculateTotalPago()));
    }
    private void agregarEmpleado() {
        String emp = (String) cmBoxVendedores.getSelectedItem();
        String empleado[] = emp.split(" - ");
        Empleado e = ctrl.searchEmpleadoByID(empleado[0]);
        factura.setVendedor(e);
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnHome = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtFieldFecha = new javax.swing.JTextField();
        txtFieldCedulaCliente = new javax.swing.JTextField();
        txtFieldDescripcionPro = new javax.swing.JTextField();
        txtFieldCodigoProducto = new javax.swing.JTextField();
        btnAgregarPro = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbleProductos = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblSubtotal = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblDescuento = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        cmBoxVendedores = new javax.swing.JComboBox<>();
        txtFieldCantidadPro = new javax.swing.JTextField();
        txtFieldDescuentoCli = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnHome.setText("Home");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        jLabel7.setText("Fecha: ");

        jLabel8.setText("Cliente:");

        jLabel9.setText("Vendedor:");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Producto");

        jLabel1.setText("Codigo:");

        jLabel2.setText("Descripcion:");

        jLabel3.setText("Cantidad:");

        txtFieldFecha.setText("<FECHA>");

        txtFieldCedulaCliente.setText("<CEDULA CLIENTE>");
        txtFieldCedulaCliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFieldCedulaClienteFocusLost(evt);
            }
        });

        txtFieldDescripcionPro.setText("<DESCRIPCION>");

        txtFieldCodigoProducto.setText("<CODIGO PRODUCTO>");

        btnAgregarPro.setText("AGREGAR PRODUCTO");
        btnAgregarPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProActionPerformed(evt);
            }
        });

        tbleProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod", "Descripcion", "Cantidad", "Unidad de Medida", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbleProductos);

        jLabel4.setText("Descuento:");

        jLabel5.setText("%");

        jLabel6.setText("Subtotal: ");

        lblSubtotal.setText("<Subtotal>");

        jLabel12.setText("Descuento:");

        lblDescuento.setText("<Descuento>");

        jLabel16.setText("Total:");

        lblTotal.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblTotal.setText("<Total>");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        cmBoxVendedores.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtFieldCantidadPro.setText("<Cantidad>");

        txtFieldDescuentoCli.setText("<%>");
        txtFieldDescuentoCli.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFieldDescuentoCliFocusLost(evt);
            }
        });

        jButton1.setText("AGREGAR FACTURA");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7)
                                .addComponent(jLabel8)
                                .addComponent(jLabel9))
                            .addGap(21, 21, 21)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtFieldFecha)
                                .addComponent(txtFieldCedulaCliente)
                                .addComponent(cmBoxVendedores, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAgregarPro)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtFieldCodigoProducto)
                                        .addComponent(txtFieldDescripcionPro, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtFieldDescuentoCli, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel5)))
                                .addComponent(txtFieldCantidadPro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(9, 9, 9)))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDescuento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDescuento)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtFieldFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtFieldCedulaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(cmBoxVendedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFieldCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFieldDescripcionPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFieldCantidadPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAgregarPro)
                            .addComponent(btnBuscar))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(txtFieldDescuentoCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHome)
                            .addComponent(lblSubtotal)
                            .addComponent(jLabel6))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(lblTotal))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        this.dispose();
        ctrl.mostrarMenu();
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        ventanaBuscar = new VentanaVentasBuscar(this);
        ventanaBuscar.show();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtFieldCedulaClienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFieldCedulaClienteFocusLost
        setClienteTxtField();
    }//GEN-LAST:event_txtFieldCedulaClienteFocusLost

    private void btnAgregarProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProActionPerformed
        agregaLinea();
    }//GEN-LAST:event_btnAgregarProActionPerformed

    private void txtFieldDescuentoCliFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFieldDescuentoCliFocusLost
        updateDescuento();
    }//GEN-LAST:event_txtFieldDescuentoCliFocusLost

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        agregarEmpleado();
        ctrl.addFactura(factura);
        init();
        setFactura(new Factura());
        setFecha(new Date());
    }//GEN-LAST:event_jButton1ActionPerformed
    
    Producto producto;
    int cantidadLimite;
    Factura factura;
    private VentanaVentasBuscar ventanaBuscar;
    private Control ctrl;
    public static final String MENSAJE_CANTIDAD_INSUFICIENTE =  "La cantidad debe ser menor a la que hay disponible en el inventario, %d";
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarPro;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnHome;
    private javax.swing.JComboBox<String> cmBoxVendedores;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDescuento;
    private javax.swing.JLabel lblSubtotal;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tbleProductos;
    private javax.swing.JTextField txtFieldCantidadPro;
    private javax.swing.JTextField txtFieldCedulaCliente;
    private javax.swing.JTextField txtFieldCodigoProducto;
    private javax.swing.JTextField txtFieldDescripcionPro;
    private javax.swing.JTextField txtFieldDescuentoCli;
    private javax.swing.JTextField txtFieldFecha;
    // End of variables declaration//GEN-END:variables
}
