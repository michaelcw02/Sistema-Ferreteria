/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import control.Control;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.ButtonGroup;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import modelo.cobros.Pago;
import modelo.factura.Factura;
import modelo.inventarios.Inventario;

/**
 *
 * @author Laura Alfaro
 */
public class VentanaCobros extends javax.swing.JFrame {

    /**
     * Creates new form VentanaCobros
     
     */
    public VentanaCobros(Control c) {
        initComponents();
        ctrl = c;
        init();
    }
    public void init(){
        setBlank();
        initButtons();
        updateTable();
        addActions();
    }
    private void setBlank() {
        pago.setText("");
        totalAPagar.setEditable(false);
        codigoFactura.setText("");
    }
    private void initButtons() {
        ButtonGroup group = new ButtonGroup();
        group.add(cheque);
        group.add(efectivo);
        group.add(tarjeta);
        efectivo.setSelected(true);
    }
    public void updateTable() {
        LinkedList<Factura> list= ctrl.getAllFacturas();
        DefaultTableModel model = (DefaultTableModel) tableFacturas.getModel();
        model.setRowCount(0);
        Object o[];
         for (Factura fac : list) {
           if (!fac.isPagado()) {
                o = new Object[]{fac.getCodigo(), dateToString(fac.getFecha()), fac.getVendedor().getNombre(), fac.getCliente().getNombre(), fac.isPagado()};
                model.addRow(o);
           }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void addActions() {
       tableFacturas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if(!event.getValueIsAdjusting()) {
                tableAction();
                }
            }
        });
    }

    private void tableAction() {
        int row = tableFacturas.getSelectedRow();
        String value = "";
        value = tableFacturas.getModel().getValueAt(row, 0).toString();
        codigoFactura.setText(value);
        Factura fac = ctrl.getFacturaByCod(Integer.parseInt(value));
        String total = String.valueOf(fac.calculateTotalPago());
        totalAPagar.setText(total);
    }

    public void calculateVuelto() {
        double p1 = Double.parseDouble(totalAPagar.getText());
        double p = Double.parseDouble(pago.getText());
        double v = p - p1;
        vuelto.setText(String.valueOf(v));
    }

    private String dateToString(Date date) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableFacturas = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        codigoFactura = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        totalAPagar = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        efectivo = new javax.swing.JRadioButton();
        tarjeta = new javax.swing.JRadioButton();
        cheque = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        pago = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        vuelto = new javax.swing.JTextField();
        pagar = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("FACTURAS POR COBRAR");

        tableFacturas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código", "Fecha", "Vendedor", "Cliente", "Pagada"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableFacturas);

        jLabel3.setText("Codigo Factura:");

        codigoFactura.setEditable(false);

        jLabel4.setText("Total a pagar:");

        totalAPagar.setEditable(false);

        jLabel5.setText("Método de pago:");

        efectivo.setText("Efectivo");

        tarjeta.setText("Tarjeta de crédito");

        cheque.setText("Cheque");

        jLabel6.setText("Paga con:");

        jLabel7.setText("Vuelto:");

        vuelto.setEditable(false);

        pagar.setText("Pagar");
        pagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pagarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(efectivo)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tarjeta))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(codigoFactura))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(18, 18, 18)
                                    .addComponent(totalAPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(pagar)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel7))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(pago, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                                        .addComponent(vuelto)))))
                        .addGap(0, 33, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(cheque)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(181, 181, 181)
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(codigoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(totalAPagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(efectivo)
                            .addComponent(tarjeta))
                        .addGap(11, 11, 11)
                        .addComponent(cheque)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(pago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(vuelto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pagar)
                        .addGap(22, 22, 22))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pagarActionPerformed
        calculateVuelto();
        double totalPago = Double.parseDouble(totalAPagar.getText());
        Factura fac = ctrl.getFacturaByCod(Integer.parseInt(codigoFactura.getText()));
        fac.setPagado(true);
        ctrl.updateFactura(fac);
        int tipoPago = 1;
        int count=0;
        int count1=0;
        if (cheque.isSelected()) {
            tipoPago = 3;
            count1=1;
        }
        if (efectivo.isSelected()) {
            tipoPago = 1;
        }
        if (tarjeta.isSelected()) {
            tipoPago = 2;
            count=1;
        }
        Pago  pag = new Pago(fac, tipoPago, totalPago, count, count1);
        ctrl.addPago(pag);
    }//GEN-LAST:event_pagarActionPerformed
    private Control ctrl;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton cheque;
    private javax.swing.JTextField codigoFactura;
    private javax.swing.JRadioButton efectivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton pagar;
    private javax.swing.JTextField pago;
    private javax.swing.JTable tableFacturas;
    private javax.swing.JRadioButton tarjeta;
    private javax.swing.JTextField totalAPagar;
    private javax.swing.JTextField vuelto;
    // End of variables declaration//GEN-END:variables
}
