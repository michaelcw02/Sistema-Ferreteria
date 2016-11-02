/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import control.Control;
import interfaces.Observer;
import java.awt.Color;
import java.util.LinkedList;
import javax.swing.ButtonGroup;
import javax.swing.JTabbedPane;
import javax.swing.RowFilter;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelo.productos.Producto;

/**
 *
 * @author Laura Alfaro
 */
public class VCatalogoProductos extends javax.swing.JFrame implements Observer{

    /**
     * Creates new form VentanaDespacho
     */
    public VCatalogoProductos(Control c) {
        initComponents();
        mainInit();
        ctrl = c;
    }
    @Override
    public void update() {
        updateTable(table1);
    }
    
    public void mainInit() {
        addActions();
        initInclusion();
        initModificacion();
    }
    
    private void addActions() {
        jTabbedPane2.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
            int index = tabbedPane.getSelectedIndex();
            switch(tabbedPane.getTitleAt(index)) {
                case MODIFICACION: updateTable(table1); break;
            }
        }
        });
        addDocumentListener(txtFieldCod2, table1);
        table1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                tableAction();
            }
        });
    }
    private void addDocumentListener(javax.swing.JTextField txtField, javax.swing.JTable table) {
        
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);
        
        txtField.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txtField.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtField.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
    }
    
    private void initModificacion() {
        initButtons(btnTrue2, btnFalse2);
        setBlankModificacion();
    }
    private void setBlankModificacion() {
        txtFieldCodi.setText("");
        txtFieldCodi.setEditable(false);
        txtFieldDesc2.setText("");
        txtFieldPrecio2.setText("");
        txtFieldUM2.setText("");
    }
    
    private void initInclusion() {
        initButtons(btnTrue, btnFalse);
        setBlankInclusion();
    }    
    
    private void setBlankInclusion() {
        lblExistencia.setText("");
        txtFieldCodigo.setText("");
        txtFieldDescripcion.setText("");
        txtFieldPrecioUnitario.setText("0.0");
        txtFieldUnidadMedida.setText("");
    }
    private void initButtons(javax.swing.JRadioButton btnTrue, javax.swing.JRadioButton btnFalse) {
        ButtonGroup group = new ButtonGroup();
        group.add(btnTrue);
        group.add(btnFalse);
        btnFalse.setSelected(true);
    }
    
    private void updateTable(javax.swing.JTable table) {
        LinkedList<Producto> list = ctrl.getAllProductos();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        Object o[];
        
        for(Producto p : list) {
            o = new Object[]{p.getCodigo(), p.getDescripcion(), p.getUnidadMedida(), p.getPrecio(), p.isActivo()};
            model.addRow(o);
        }
        
    }
    
    private void home() {
        this.dispose();
        ctrl.mostrarMenu();
    }
    
    private void tableAction() {
        int row = table1.getSelectedRow();
        int column = 0;
        
        String value = table1.getModel().getValueAt(row, column++).toString();
        txtFieldCodi.setText(value);
        value = table1.getModel().getValueAt(row, column++).toString();
        txtFieldDesc2.setText(value);

        value = table1.getModel().getValueAt(row, column++).toString();
        txtFieldUM2.setText(value);

        value = table1.getModel().getValueAt(row, column++).toString();
        txtFieldPrecio2.setText(value);

        value = table1.getModel().getValueAt(row, column++).toString();
        if(value == "true")
            btnTrue2.setSelected(true);

        else
            btnFalse2.setSelected(true);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JTabbedPane jTabbedPane1 = new javax.swing.JTabbedPane();
        jFrame1 = new javax.swing.JFrame();
        btnGroup2 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        javax.swing.ButtonGroup buttonGroup3 = new javax.swing.ButtonGroup();
        javax.swing.ButtonGroup buttonGroup4 = new javax.swing.ButtonGroup();
        javax.swing.ButtonGroup buttonGroup5 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtFieldCodigo = new javax.swing.JTextField();
        txtFieldDescripcion = new javax.swing.JTextField();
        txtFieldUnidadMedida = new javax.swing.JTextField();
        txtFieldPrecioUnitario = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        lblExistencia = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnTrue = new javax.swing.JRadioButton();
        btnFalse = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnModificar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        txtFieldCod2 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtFieldDesc2 = new javax.swing.JTextField();
        txtFieldUM2 = new javax.swing.JTextField();
        txtFieldPrecio2 = new javax.swing.JTextField();
        btnTrue2 = new javax.swing.JRadioButton();
        btnFalse2 = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        txtFieldCodi = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Mantenimiento del Catálogo de Productos");

        jLabel2.setText("Codigo de Producto: ");

        jLabel3.setText("Descripción: ");

        jLabel4.setText("Unidad de Medida:");

        jLabel5.setText("Precio Unitario: ");

        txtFieldCodigo.setText("<Codigo(String)>");
        txtFieldCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFieldCodigoFocusLost(evt);
            }
        });
        txtFieldCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldCodigoActionPerformed(evt);
            }
        });

        txtFieldDescripcion.setText("<Descripcion(String)>");

        txtFieldUnidadMedida.setText("<Unidad Medida (String)>");
        txtFieldUnidadMedida.setPreferredSize(new java.awt.Dimension(160, 24));

        txtFieldPrecioUnitario.setText("<Precio Unitario (Double)>");
        txtFieldPrecioUnitario.setScrollOffset(82);

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        lblExistencia.setText("<Existencia de producto>");

        jLabel6.setText("Activo:");

        btnTrue.setText("Si");

        btnFalse.setText("No");

        jButton2.setText("Home");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtFieldCodigo)
                                    .addComponent(txtFieldUnidadMedida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtFieldDescripcion)
                                    .addComponent(txtFieldPrecioUnitario))
                                .addGap(18, 18, 18)
                                .addComponent(lblExistencia))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnTrue)
                                .addGap(18, 18, 18)
                                .addComponent(btnFalse))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(36, 36, 36)
                        .addComponent(btnAgregar)))
                .addContainerGap(187, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblExistencia))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtFieldDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtFieldUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtFieldPrecioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(btnTrue)
                    .addComponent(btnFalse))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(jButton2))
                .addGap(27, 27, 27))
        );

        jTabbedPane2.addTab("Inclusion de Productos", jPanel2);

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod", "Des", "Medida", "Precio", "Activo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Boolean.class
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
        table1.getTableHeader().setReorderingAllowed(false);
        table1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                table1FocusGained(evt);
            }
        });
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table1);
        table1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jButton1.setText("Home");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtFieldCod2.setText("<Buscar Codigo>");
        txtFieldCod2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFieldCod2FocusGained(evt);
            }
        });
        txtFieldCod2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFieldCod2KeyTyped(evt);
            }
        });

        jLabel7.setText("Descripcion:");

        jLabel8.setText("Unidad de Medida: ");

        jLabel9.setText("Precio: ");

        jLabel10.setText("Activo: ");

        txtFieldDesc2.setText("<Descripcion>");

        txtFieldUM2.setText("<Unidad de medida>");

        txtFieldPrecio2.setText("<Precio>");

        btnTrue2.setText("Si");

        btnFalse2.setText("No");

        jLabel11.setText("Codigo: ");

        txtFieldCodi.setText("<Codigo>");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnModificar)
                                .addGap(135, 135, 135))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(jLabel10))
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel11))
                                .addGap(3, 3, 3)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(btnTrue2)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnFalse2)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(txtFieldUM2, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                                    .addComponent(txtFieldDesc2)
                                    .addComponent(txtFieldPrecio2)
                                    .addComponent(txtFieldCodi)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtFieldCod2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtFieldCod2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtFieldCodi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtFieldDesc2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtFieldUM2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtFieldPrecio2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnFalse2)
                            .addComponent(btnTrue2)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Modificacion de productos", jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 666, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 302, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Borrar Productos", jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 666, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 302, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Buscar Producto", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane2)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane2))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFieldCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldCodigoActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        
        String codigo = txtFieldCodigo.getText();
        String descripcion = txtFieldDescripcion.getText();
        String unidadMedida = txtFieldUnidadMedida.getText();
        boolean activo = (btnTrue.isSelected()) ? true : false;
        double precioUnitario;
        try {
            precioUnitario = Double.parseDouble(txtFieldPrecioUnitario.getText());
        } catch (Exception e) {
            precioUnitario = -1.0;
        }
     
        if (precioUnitario != -1.0) {
            if (codigo != "" && descripcion != "" && unidadMedida != "") {
                if (lblExistencia.getText() == "✔") {
                    ctrl.addProducto(codigo, descripcion, unidadMedida, precioUnitario, activo);
                    setBlankInclusion();
                }
            }
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void txtFieldCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFieldCodigoFocusLost
        if(!ctrl.verificarExistenciaProducto(txtFieldCodigo.getText())) {
            lblExistencia.setForeground(Color.GREEN);
            lblExistencia.setText("✔");
        } else {
            lblExistencia.setForeground(Color.RED);
            lblExistencia.setText("✘");
        }            
    }//GEN-LAST:event_txtFieldCodigoFocusLost

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        home();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtFieldCod2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFieldCod2KeyTyped
        
    }//GEN-LAST:event_txtFieldCod2KeyTyped

    private void txtFieldCod2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFieldCod2FocusGained
        txtFieldCod2.setText("");
    }//GEN-LAST:event_txtFieldCod2FocusGained

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        
    }//GEN-LAST:event_table1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        home();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        String cod = txtFieldCodi.getText();
        String des = txtFieldDesc2.getText();
        String uni = txtFieldUM2.getText();
        boolean ac = (btnTrue2.isSelected()) ? true : false;
        double pre;
        try {
            pre = Double.parseDouble(txtFieldPrecio2.getText());
        } catch (Exception e) {
            pre = -1;
        }
        
        if (pre != -1.0) {
            if (cod != "" && des != "" && uni != "") {
                ctrl.modificarProducto(cod, des, uni, pre, ac);
                setBlankModificacion();
            }
        }
        
    }//GEN-LAST:event_btnModificarActionPerformed

    private void table1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_table1FocusGained
        
    }//GEN-LAST:event_table1FocusGained

    private Control ctrl;
    static private final String MODIFICACION = "Modificacion de productos";
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JRadioButton btnFalse;
    private javax.swing.JRadioButton btnFalse2;
    private javax.swing.ButtonGroup btnGroup2;
    private javax.swing.JButton btnModificar;
    private javax.swing.JRadioButton btnTrue;
    private javax.swing.JRadioButton btnTrue2;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel lblExistencia;
    private javax.swing.JTable table1;
    private javax.swing.JTextField txtFieldCod2;
    private javax.swing.JTextField txtFieldCodi;
    private javax.swing.JTextField txtFieldCodigo;
    private javax.swing.JTextField txtFieldDesc2;
    private javax.swing.JTextField txtFieldDescripcion;
    private javax.swing.JTextField txtFieldPrecio2;
    private javax.swing.JTextField txtFieldPrecioUnitario;
    private javax.swing.JTextField txtFieldUM2;
    private javax.swing.JTextField txtFieldUnidadMedida;
    // End of variables declaration//GEN-END:variables
}
