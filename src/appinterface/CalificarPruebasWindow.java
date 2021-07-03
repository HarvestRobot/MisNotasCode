/*
 * Lucia Calzado Piedrabuena
 * Trabajo de Fin de Grado - Grado en Ingenier�a Inform�tica
 * Universidad de Castilla-La Mancha
 */
package appinterface;

import appsmallinterfaces.Ayuda;
import auxiliar.AuxiliarMethods;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import objects.Alumno;
import controladores.ControladorAlumno;
import controladores.ControladorPrueba;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import objects.Nota;
import objects.Opciones;

/**
 *
 * @author lucia
 */
public class CalificarPruebasWindow extends javax.swing.JFrame {

    /**
     * Creates new form CalificarTareasWindow
     */
    ControladorAlumno contAlumnos;
    ControladorPrueba contPruebas;
    int asignatura;
    int idPrueba;
    Opciones opciones;
    String desdeDonde; //para saber desde dónde se abrió la ventana para refrescar en caso de cambio
    HashMap<String, Integer> pruebaConID = new HashMap<>(); //para almacenar las pruebas con sus IDs
    DefaultTableModel model;
    NumberFormat formatter;

    public CalificarPruebasWindow(String strAsignatura, int asignatura, ControladorAlumno contAlumnos, ControladorPrueba contPruebas, Opciones opciones, String desdeDonde) {
        this.opciones = opciones;
        initComponents();
        this.asignatura = asignatura;
        this.contAlumnos = contAlumnos;
        this.desdeDonde = desdeDonde;
        if (contPruebas != null) {
            this.contPruebas = contPruebas;
        } else {
            this.contPruebas = new ControladorPrueba();
            try {
                this.contPruebas.cargarPruebasAsignatura(asignatura);
            } catch (SQLException e) {
                AuxiliarMethods.showWarning(e.toString());
            }
        }

        //opciones del number formatter
        this.formatter = NumberFormat.getInstance(Locale.US);
        this.formatter.setMaximumFractionDigits(2);
        this.formatter.setRoundingMode(RoundingMode.UP);

        setComportamientoBotonCerrar();
        ejecutarOpciones();

        tabla.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE); //para que cuando se clique un botón, deje de editarse la tabla
        tabla.setRowHeight(25);

        cargarPruebas(1);
        obtenerEstadísticas();
        txtAsignatura.setText(strAsignatura);
        model = (DefaultTableModel) tabla.getModel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblAsignatura = new javax.swing.JLabel();
        txtAsignatura = new javax.swing.JTextField();
        lblTarea = new javax.swing.JLabel();
        comboTarea = new javax.swing.JComboBox<>();
        jScrollPane = new javax.swing.JScrollPane();
        tabla = new JTable(model) {
            public Component prepareRenderer(TableCellRenderer renderer, int Index_row, int Index_col) {
                // get the current row
                model = (DefaultTableModel) tabla.getModel();
                Component comp = super.prepareRenderer(renderer, Index_row, Index_col);

                if(Index_col != 0 && Index_col != 1 && Index_col != 3 && model.getValueAt(Index_row, Index_col) != null && !model.getValueAt(Index_row, Index_col).equals("")){
                    if (Double.parseDouble(model.getValueAt(Index_row, Index_col).toString()) < 5.0 && !isCellSelected(Index_row, Index_col)) {
                        comp.setForeground(opciones.getColorSuspensos());
                    } else if (!isCellSelected(Index_row, Index_col)) {
                        comp.setForeground(opciones.getColorAprobados());
                    }
                }
                return comp;
            }

            //Implement table cell tool tips.           
            public String getToolTipText(MouseEvent e) {
                String tip = null;
                java.awt.Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);

                try {
                    if (columnAtPoint(p) == 3){
                        tip = getValueAt(rowIndex, 3).toString();
                    }   
                } catch (RuntimeException e1) {
                    //catch null pointer exception if mouse is over an empty line
                }

                return tip;
            }
        };
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        lblTitulo = new javax.swing.JLabel();
        filler12 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 40), new java.awt.Dimension(0, 40), new java.awt.Dimension(32767, 40));
        filler13 = new javax.swing.Box.Filler(new java.awt.Dimension(40, 0), new java.awt.Dimension(40, 0), new java.awt.Dimension(40, 32767));
        filler14 = new javax.swing.Box.Filler(new java.awt.Dimension(40, 0), new java.awt.Dimension(40, 0), new java.awt.Dimension(40, 32767));
        filler15 = new javax.swing.Box.Filler(new java.awt.Dimension(40, 0), new java.awt.Dimension(40, 0), new java.awt.Dimension(40, 32767));
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 45), new java.awt.Dimension(0, 45), new java.awt.Dimension(32767, 45));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 40), new java.awt.Dimension(0, 40), new java.awt.Dimension(32767, 40));
        panelEstadisticas = new javax.swing.JPanel();
        lblNumAp = new javax.swing.JLabel();
        lblAprob1 = new javax.swing.JLabel();
        filler16 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        filler17 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 50), new java.awt.Dimension(0, 50), new java.awt.Dimension(32767, 50));
        filler20 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        filler18 = new javax.swing.Box.Filler(new java.awt.Dimension(150, 0), new java.awt.Dimension(150, 0), new java.awt.Dimension(150, 32767));
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(40, 0), new java.awt.Dimension(40, 0), new java.awt.Dimension(40, 32767));
        lblTrimestre = new javax.swing.JLabel();
        comboTrimestre = new javax.swing.JComboBox<>();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(70, 0), new java.awt.Dimension(70, 0), new java.awt.Dimension(70, 32767));
        filler11 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 40), new java.awt.Dimension(0, 40), new java.awt.Dimension(32767, 40));
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(800, 0), new java.awt.Dimension(800, 0), new java.awt.Dimension(800, 32767));
        btnRefrescar = new javax.swing.JButton();
        filler19 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Calificar tareas");
        setBounds(new java.awt.Rectangle(300, 100, 0, 0));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        lblAsignatura.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblAsignatura.setText("Asignatura:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(lblAsignatura, gridBagConstraints);

        txtAsignatura.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtAsignatura.setEnabled(false);
        txtAsignatura.setMaximumSize(new java.awt.Dimension(100, 22));
        txtAsignatura.setMinimumSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(txtAsignatura, gridBagConstraints);

        lblTarea.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTarea.setText("Tarea o prueba:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(lblTarea, gridBagConstraints);

        comboTarea.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        comboTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTareaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(comboTarea, gridBagConstraints);

        tabla.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Apellidos", "Nombre", "Nota", "Comentario"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane.setViewportView(tabla);
        if (tabla.getColumnModel().getColumnCount() > 0) {
            tabla.getColumnModel().getColumn(0).setResizable(false);
            tabla.getColumnModel().getColumn(1).setResizable(false);
            tabla.getColumnModel().getColumn(2).setResizable(false);
        }

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.gridheight = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(jScrollPane, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 7;
        getContentPane().add(filler3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridheight = 6;
        getContentPane().add(filler4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 8;
        getContentPane().add(filler5, gridBagConstraints);

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon("C:\\Users\\lucia\\Desktop\\NoName\\assets\\Cancelar.png")); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 28;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(btnCancelar, gridBagConstraints);

        btnGuardar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon("C:\\Users\\lucia\\Desktop\\NoName\\assets\\Disquete.png")); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 17;
        gridBagConstraints.gridy = 28;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(btnGuardar, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 27;
        gridBagConstraints.gridwidth = 7;
        getContentPane().add(filler8, gridBagConstraints);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        lblTitulo.setText("Calificar tareas o pruebas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(lblTitulo, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 8;
        getContentPane().add(filler12, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 25;
        getContentPane().add(filler13, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 26;
        getContentPane().add(filler14, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 25;
        getContentPane().add(filler15, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 7;
        getContentPane().add(filler1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 29;
        gridBagConstraints.gridwidth = 11;
        getContentPane().add(filler2, gridBagConstraints);

        panelEstadisticas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estadísticas de la prueba", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N
        panelEstadisticas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelEstadisticas.setLayout(new java.awt.GridBagLayout());

        lblNumAp.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNumAp.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        panelEstadisticas.add(lblNumAp, gridBagConstraints);

        lblAprob1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblAprob1.setText("Aprobados:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        panelEstadisticas.add(lblAprob1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        panelEstadisticas.add(filler16, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelEstadisticas.add(filler17, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        panelEstadisticas.add(filler20, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 7;
        panelEstadisticas.add(filler18, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(panelEstadisticas, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 18;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 29;
        getContentPane().add(filler6, gridBagConstraints);

        lblTrimestre.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTrimestre.setText("Trimestre:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(lblTrimestre, gridBagConstraints);

        comboTrimestre.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        comboTrimestre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1º", "2º", "3º" }));
        comboTrimestre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTrimestreActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(comboTrimestre, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        getContentPane().add(filler7, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 13;
        gridBagConstraints.gridy = 28;
        gridBagConstraints.gridwidth = 4;
        getContentPane().add(filler10, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 27;
        getContentPane().add(filler11, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 26;
        gridBagConstraints.gridwidth = 7;
        getContentPane().add(filler9, gridBagConstraints);

        btnRefrescar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnRefrescar.setText("Refrescar estadísticas");
        btnRefrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefrescarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 24;
        gridBagConstraints.gridwidth = 6;
        getContentPane().add(btnRefrescar, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 23;
        getContentPane().add(filler19, gridBagConstraints);

        jMenuBar1.setName("menuEditar"); // NOI18N

        jMenu1.setText("Archivo");
        jMenu1.setName("menuArchivo"); // NOI18N

        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jMenuItem1.setText("Cerrar");
        jMenuItem1.setName("mnbtnSalir"); // NOI18N
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu4.setText("Ayuda");
        jMenu4.setName("menuAyuda"); // NOI18N

        jMenuItem2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jMenuItem2.setText("Manual de ayuda");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem2);

        jMenuItem11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jMenuItem11.setText("Acerca de...");
        jMenuItem11.setName("mnbtnAcercaDe"); // NOI18N
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem11);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        if (AuxiliarMethods.showCloseConfirmation("¿Seguro que quiere cancelar?\nSe perderán todos los cambios.") == 0){
            this.dispose();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void comboTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTareaActionPerformed
        boolean encontrado;
        if (comboTarea.getItemCount() != 0) {
            if (!comboTarea.getSelectedItem().toString().equals("Seleccionar...")) {
                //DefaultTableModel model = (DefaultTableModel) tabla.getModel(); //modelo para introducir filas en la tabla
                model.setRowCount(0);
                idPrueba = pruebaConID.get(comboTarea.getSelectedItem().toString());
                //Cargar tabla
                Object[] row = new Object[4];
                for (Alumno alumno : this.contAlumnos.getAlumnosAsignatura().get(asignatura)) {
                    encontrado = false;
                    row[0] = alumno.getApellidos();
                    row[1] = alumno.getNombre();
                    for (Nota n : alumno.getNotas()) {
                        if (n.getIdPrueba() == idPrueba && n.getNota() != -1) {
                            encontrado = true;
                            row[2] = n.getNota();
                            row[3] = n.getComentario();
                        }
                    }
                    if (!encontrado) {
                        row[2] = null;
                        row[3] = null;
                    }

                    model.addRow(row);
                }

                obtenerEstadísticas();
            } else {
                lblNumAp.setText("-");
                //model.setRowCount(0);
            }
        }
        AuxiliarMethods.ajustarColumnasTabla(tabla);
    }//GEN-LAST:event_comboTareaActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        int i;
        boolean update = false; //para saber si hay que hacer UPDATE o INSERT en la BD
        boolean yaCreada;
        model = (DefaultTableModel) tabla.getModel();

        for (i = 0; i < contAlumnos.getAlumnosAsignatura().get(asignatura).size(); i++) { //itera sobre los alumnos
            yaCreada = false;
            ArrayList<Nota> notas = contAlumnos.getAlumnosAsignatura().get(asignatura).get(i).getNotas();
            if (model.getValueAt(i, 2) != null) { //hay nota escrita
                for (Nota n : notas) {
                    if (n.getIdPrueba() == idPrueba) { //hay nota escrita: pero ya había una creada
                        yaCreada = true;
                        update = true;
                        n.setNota(Double.parseDouble(model.getValueAt(i, 2).toString()));
                        try {
                            n.setComentario(model.getValueAt(i, 3).toString());
                        } catch (NullPointerException e) {
                            n.setComentario("");
                        }
                    }
                }
                if (!yaCreada) {//hay nota escrita: pero hay que crearla
                    update = false;
                    try {
                        notas.add(new Nota(idPrueba, Double.parseDouble(model.getValueAt(i, 2).toString()), model.getValueAt(i, 3).toString()));
                    } catch (NullPointerException e) { //puede dar esto porque no hay comentario escrito
                        notas.add(new Nota(idPrueba, Double.parseDouble(model.getValueAt(i, 2).toString()), ""));
                    }
                }

            }

            contAlumnos.getAlumnosAsignatura().get(asignatura).get(i).setNotas(notas);

            try {
                contAlumnos.updateNotas(asignatura, idPrueba, contAlumnos.getAlumnosAsignatura().get(asignatura).get(i), update);
            } catch (SQLException e) {
                AuxiliarMethods.showWarning("Algo ha ido mal y no se han podido guardar algunas calificaciones.\nMás información: " + e.toString());
            }
        }
        this.dispose();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void comboTrimestreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTrimestreActionPerformed
        comboTarea.removeAllItems(); //se limpia el combo para meter las pruebas de otro trimestre
        switch (comboTrimestre.getSelectedIndex()) {
            case 0:
                cargarPruebas(1);
                break;

            case 1:
                cargarPruebas(2);
                break;

            case 2:
                cargarPruebas(3);
                break;

            default:
                AuxiliarMethods.showWarning("Estamos teniendo problemas para mostrar las tareas correspondientes a este trimestre.\nPor favor, contacta con un administrador.");

        }
    }//GEN-LAST:event_comboTrimestreActionPerformed

    private void btnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrescarActionPerformed
        obtenerEstadísticas();
    }//GEN-LAST:event_btnRefrescarActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Ayuda ayuda = new Ayuda(opciones, 3);
        ayuda.pack();
        ayuda.setVisible(true);
        ayuda.setMinimumSize(ayuda.getSize());
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        JOptionPane.showMessageDialog(new JFrame(), "Trabajo de Fin de Grado de Lucía Calzado Piedrabuena.\nGrado en Ingeniería Informática.\nUCLM.");
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void cargarPruebas(int trimestre) {
        int i;

        comboTarea.addItem("Seleccionar...");
        for (i = 0; i < contPruebas.getPruebasAsignatura().get(asignatura).get(trimestre).size(); i++) {
            comboTarea.addItem(contPruebas.getPruebasAsignatura().get(asignatura).get(trimestre).get(i).getTitulo());
            pruebaConID.put(contPruebas.getPruebasAsignatura().get(asignatura).get(trimestre).get(i).getTitulo(), contPruebas.getPruebasAsignatura().get(asignatura).get(trimestre).get(i).getIdPrueba());
        }

    }

    private void ejecutarOpciones() {
        List<Component> components = AuxiliarMethods.getAllComponents(this);

        for (Component c : components) {
            c.setFont(new Font(c.getFont().getName(), c.getFont().getStyle(), opciones.getTamañoLetra()));
            if (opciones.getOscuro() && c.getClass() != JButton.class && c.getClass() != JTextField.class && c.getClass() != JComboBox.class) {
                c.setForeground(Color.LIGHT_GRAY);
            }
        }

        lblTitulo.setFont(new Font(lblTitulo.getFont().getName(), Font.BOLD, opciones.getTamañoLetra() + 15));

        //cambiamos el color de fondo de todos los containers del frame
        Color colorBackground = opciones.getColorBackground();
        this.getContentPane().setBackground(colorBackground);
        panelEstadisticas.setBackground(colorBackground);
        tabla.setBackground(colorBackground);
        jScrollPane.setBackground(colorBackground);

        //terminamos cambiando a mano los TitledBorder de los paneles que los tienen
        if (opciones.getOscuro()) {
            TitledBorder titledBorder = (TitledBorder) panelEstadisticas.getBorder();
            titledBorder.setTitleColor(Color.LIGHT_GRAY);
        }
    }

    private void obtenerEstadísticas() {
        int numAlumnos = contAlumnos.getAlumnosAsignatura().get(asignatura).size();
        int aprobados = 0;

        for (int i = 0; i < tabla.getRowCount(); i++) {
            try {
                if (Double.parseDouble(tabla.getValueAt(i, 2).toString()) >= 5) {
                    aprobados++;
                } else if (tabla.getValueAt(i, 3).toString().equals("No tiene que hacer la prueba")){
                    numAlumnos--;
                }
            } catch (NullPointerException npe){
                
            }
        }
        lblNumAp.setText(String.valueOf(aprobados) + " (" + formatter.format(aprobados / (float) numAlumnos * 100) + "% del total de alumnos)");
    }
    
    public void setComportamientoBotonCerrar(){
        this.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent evt) {
            String titulo = "¿Seguro que quiere cerrar? Se perderán los cambios no guardados.";
            int resp = AuxiliarMethods.showCloseConfirmation(titulo);

            if (resp == JOptionPane.YES_OPTION) {
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            } else {
                setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        }
    });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JComboBox<String> comboTarea;
    private javax.swing.JComboBox<String> comboTrimestre;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler11;
    private javax.swing.Box.Filler filler12;
    private javax.swing.Box.Filler filler13;
    private javax.swing.Box.Filler filler14;
    private javax.swing.Box.Filler filler15;
    private javax.swing.Box.Filler filler16;
    private javax.swing.Box.Filler filler17;
    private javax.swing.Box.Filler filler18;
    private javax.swing.Box.Filler filler19;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler20;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JLabel lblAprob1;
    private javax.swing.JLabel lblAsignatura;
    private javax.swing.JLabel lblNumAp;
    private javax.swing.JLabel lblTarea;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTrimestre;
    private javax.swing.JPanel panelEstadisticas;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtAsignatura;
    // End of variables declaration//GEN-END:variables
}
