/*
 * Lucia Calzado Piedrabuena
 * Trabajo de Fin de Grado - Grado en Ingenier�a Inform�tica
 * Universidad de Castilla-La Mancha
 */
package appinterface;

import appsmallinterfaces.EditarCompetenciasWindow;
import auxiliar.AuxiliarMethods;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;
import objects.Alumno;
import controladores.ControladorAlumno;
import controladores.ControladorCompetencia;
import controladores.ControladorPrueba;
import java.awt.Font;
import objects.Prueba;

/**
 *
 * @author lucia
 */
public class NuevaPruebaWindow extends javax.swing.JFrame {

    /**
     * Creates new form NuevaTareaWindow
     */
    ControladorAlumno contAlumnos;
    ControladorPrueba contPruebas;
    ControladorCompetencia contCompetencias;
    int asignatura;
    int tamañoLetra;
    boolean presionado;


    public NuevaPruebaWindow(String strAsignatura, int asignatura, ControladorAlumno contAlumnos, int tamañoLetra) {
        this.contAlumnos = contAlumnos;
        this.asignatura = asignatura;
        this.tamañoLetra = tamañoLetra;
        contPruebas = new ControladorPrueba();
        contCompetencias = new ControladorCompetencia();
        presionado = false;
        initComponents();
        
        try {
            contPruebas.cargarPruebasAsignatura(asignatura);
        } catch (SQLException e){
            AuxiliarMethods.showWarning(e.toString());
        }
        
        if (tamañoLetra != 0) {
            cambiarTamañoLetra();
        }
        
        getDate();

        //ocultamos la tabla
        panelTabla.setVisible(false);

        //Añadir los radio buttons a un grupo
        ButtonGroup cursosGrupo = new ButtonGroup();
        cursosGrupo.add(rdbtnSeleccionar);
        cursosGrupo.add(rdbtnTodos);

        if (contAlumnos.getAlumnosAsignatura().isEmpty()) {
            System.out.println("Constructor de NuevaTareaWindow dice: Por algún motivo, el controlador de alumnos está vacío.");
        }

        txtAsignatura.setText(strAsignatura); 
        lblImagen1.setIcon(new ImageIcon("assets/plus.png"));
        lblImagen2.setIcon(new ImageIcon("assets/sheet.png"));

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

        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        lblAsignatura = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblPeso = new javax.swing.JLabel();
        chbxTrabajoAdic = new javax.swing.JCheckBox();
        txtAsignatura = new javax.swing.JTextField();
        txtTitulo = new javax.swing.JTextField();
        rdbtnTodos = new javax.swing.JRadioButton();
        rdbtnSeleccionar = new javax.swing.JRadioButton();
        lblTituloGrande = new javax.swing.JLabel();
        txtPeso = new javax.swing.JTextField();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(25, 0), new java.awt.Dimension(25, 0), new java.awt.Dimension(25, 0));
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 25), new java.awt.Dimension(0, 25), new java.awt.Dimension(0, 25));
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10));
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0));
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 23), new java.awt.Dimension(0, 23), new java.awt.Dimension(0, 23));
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20));
        filler12 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        filler13 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        panelTabla = new javax.swing.JPanel();
        jScrollPane = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        lblTrimestre = new javax.swing.JLabel();
        comboTrimestre = new javax.swing.JComboBox<>();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        lblImagen2 = new javax.swing.JLabel();
        lblImagen1 = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        filler14 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        txtFecha = new javax.swing.JTextField();
        lblFecha = new javax.swing.JLabel();
        filler15 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        btnEditCompeten = new javax.swing.JButton();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        lblCompetencias = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaCompetencias = new javax.swing.JList<>();
        filler11 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        filler16 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 15), new java.awt.Dimension(0, 15), new java.awt.Dimension(32767, 15));
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nueva tarea");
        setBounds(new java.awt.Rectangle(600, 200, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 28;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(btnCancelar, gridBagConstraints);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 28;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(btnGuardar, gridBagConstraints);

        lblAsignatura.setText("Asignatura:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(lblAsignatura, gridBagConstraints);

        lblTitulo.setText("Título:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(lblTitulo, gridBagConstraints);

        lblPeso.setText("Peso (%):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(lblPeso, gridBagConstraints);

        chbxTrabajoAdic.setText("Trabajo adicional");
        chbxTrabajoAdic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbxTrabajoAdicActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(chbxTrabajoAdic, gridBagConstraints);

        txtAsignatura.setEnabled(false);
        txtAsignatura.setMaximumSize(new java.awt.Dimension(100, 22));
        txtAsignatura.setMinimumSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(txtAsignatura, gridBagConstraints);

        txtTitulo.setMinimumSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(txtTitulo, gridBagConstraints);

        rdbtnTodos.setSelected(true);
        rdbtnTodos.setText("Para todos los alumnos");
        rdbtnTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnTodosActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 20;
        getContentPane().add(rdbtnTodos, gridBagConstraints);

        rdbtnSeleccionar.setText("Seleccionar alumnos");
        rdbtnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnSeleccionarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 20;
        getContentPane().add(rdbtnSeleccionar, gridBagConstraints);

        lblTituloGrande.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTituloGrande.setText("Crear una nueva tarea o prueba");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(lblTituloGrande, gridBagConstraints);

        txtPeso.setMinimumSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(txtPeso, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 29;
        getContentPane().add(filler4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 10;
        getContentPane().add(filler5, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 21;
        getContentPane().add(filler6, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 28;
        getContentPane().add(filler1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 11;
        getContentPane().add(filler8, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 29;
        gridBagConstraints.gridwidth = 11;
        getContentPane().add(filler10, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 10;
        getContentPane().add(filler12, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 8;
        getContentPane().add(filler13, gridBagConstraints);

        panelTabla.setMinimumSize(new java.awt.Dimension(300, 200));
        panelTabla.setLayout(new javax.swing.BoxLayout(panelTabla, javax.swing.BoxLayout.LINE_AXIS));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Apellidos", "Nombre", "Seleccionar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
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
            tabla.getColumnModel().getColumn(1).setResizable(false);
            tabla.getColumnModel().getColumn(2).setResizable(false);
        }

        panelTabla.add(jScrollPane);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 25;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.gridheight = 2;
        getContentPane().add(panelTabla, gridBagConstraints);

        lblTrimestre.setText("Trimestre:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(lblTrimestre, gridBagConstraints);

        comboTrimestre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1º", "2º", "3º" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(comboTrimestre, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 8;
        getContentPane().add(filler3, gridBagConstraints);

        lblImagen2.setIconTextGap(0);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(lblImagen2, gridBagConstraints);

        lblImagen1.setToolTipText("");
        lblImagen1.setIconTextGap(0);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(lblImagen1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 26;
        getContentPane().add(filler2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        getContentPane().add(filler14, gridBagConstraints);

        txtFecha.setMinimumSize(new java.awt.Dimension(100, 22));
        txtFecha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFechaFocusLost(evt);
            }
        });
        txtFecha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtFechaMousePressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(txtFecha, gridBagConstraints);

        lblFecha.setText("Fecha:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(lblFecha, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 7;
        getContentPane().add(filler15, gridBagConstraints);

        btnEditCompeten.setText("Editar competencias");
        btnEditCompeten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditCompetenActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(btnEditCompeten, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridwidth = 8;
        getContentPane().add(filler7, gridBagConstraints);

        lblCompetencias.setText("Competencias:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(lblCompetencias, gridBagConstraints);

        jScrollPane1.setViewportView(listaCompetencias);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(jScrollPane1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 12;
        getContentPane().add(filler11, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 19;
        gridBagConstraints.gridwidth = 8;
        getContentPane().add(filler16, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridheight = 5;
        getContentPane().add(filler9, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void rdbtnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnSeleccionarActionPerformed
        this.setSize(675, 575);

        DefaultTableModel model = (DefaultTableModel) tabla.getModel(); //modelo para introducir filas en la tabla
        if (model.getRowCount() == 0) {
            model.setRowCount(0);

            //Rellenar tabla
            Object[] row = new Object[3];

            //TODO esto solo se rellena si se le da al botón cargar tabla
            for (Alumno alumno : contAlumnos.getAlumnosAsignatura().get(asignatura)) {
                row[0] = alumno.getNombre();
                row[1] = alumno.getApellidos();
                model.addRow(row);
            }
        }
        panelTabla.setVisible(true);

    }//GEN-LAST:event_rdbtnSeleccionarActionPerformed

    private void rdbtnTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnTodosActionPerformed
        panelTabla.setVisible(false);
        this.setSize(this.getMinimumSize());
    }//GEN-LAST:event_rdbtnTodosActionPerformed

    private void chbxTrabajoAdicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbxTrabajoAdicActionPerformed
        txtPeso.setEnabled(!chbxTrabajoAdic.isSelected());
    }//GEN-LAST:event_chbxTrabajoAdicActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        int peso = !chbxTrabajoAdic.isSelected() ? Integer.parseInt(String.valueOf(txtPeso.getText().charAt(0))) : 0;
        int trimestre = Integer.parseInt(String.valueOf(comboTrimestre.getSelectedItem().toString().charAt(0)));
        //validamos que la fecha tiene el formato correcto
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        
        try {
            dateFormat.parse(txtFecha.getText().trim());
            Prueba p = new Prueba(asignatura, txtTitulo.getText(), txtFecha.getText(), trimestre, peso);
        
            contPruebas.añadirPruebaAAsignatura(p, asignatura);
            p.commitNuevaPrueba();
        
            this.dispose();
        } catch (ParseException pe) {
            AuxiliarMethods.showWarning("Introduzca una fecha válida.\nFormato: dd/mm/aaaa");
        } 
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtFechaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFechaMousePressed
        // TODO add your handling code here:
        if(!presionado){
            txtFecha.setText("");
            presionado = true;
        }
    }//GEN-LAST:event_txtFechaMousePressed

    private void txtFechaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFechaFocusLost
        // TODO add your handling code here:
        if (txtFecha.getText().equals("")){
            getDate();
        }
    }//GEN-LAST:event_txtFechaFocusLost

    private void btnEditCompetenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditCompetenActionPerformed
        // TODO add your handling code here:
        EditarCompetenciasWindow ecw = new EditarCompetenciasWindow(contCompetencias, contPruebas, txtTitulo.getText(), asignatura);
        ecw.pack();
        ecw.setVisible(true);
    }//GEN-LAST:event_btnEditCompetenActionPerformed

    private void getDate(){
    //Rellenamos el campo de fecha por defecto con la fecha de hoy para que sepan el formato
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        txtFecha.setText(formatter.format(date));
    }
//    
//    private void cargarListaCompetencias(){
//        try {
//            contCompetencias.cargarCompetenciasAsignatura(asignatura);
//        } catch (SQLException e){
//            System.out.println(e.toString());
//        }
//        DefaultListModel<String> modelo = new DefaultListModel<>();
//        listaCompetencias.setModel(modelo);
//        
//        for (int i = 0; i < contCompetencias.getCompetenciasAsignatura().get(asignatura).size(); i++) {
//            modelo.addElement(contCompetencias.getCompetenciasAsignatura().get(asignatura).get(i).getNombre());
//        }
//        
//    }
    
    public void cambiarTamañoLetra(){
        lblAsignatura.setFont(new Font(lblAsignatura.getFont().getName(), Font.BOLD, tamañoLetra));
        lblCompetencias.setFont(new Font(lblCompetencias.getFont().getName(), Font.BOLD, tamañoLetra));
        lblFecha.setFont(new Font(lblFecha.getFont().getName(), Font.BOLD, tamañoLetra));
        lblPeso.setFont(new Font(lblPeso.getFont().getName(), Font.BOLD, tamañoLetra));
        lblTitulo.setFont(new Font(lblTitulo.getFont().getName(), Font.BOLD, tamañoLetra));
        lblTrimestre.setFont(new Font(lblTrimestre.getFont().getName(), Font.BOLD, tamañoLetra));
        lblTituloGrande.setFont(new Font(lblTrimestre.getFont().getName(), Font.BOLD, tamañoLetra+lblTituloGrande.getFont().getSize()));
        
        txtAsignatura.setFont(new Font(txtAsignatura.getFont().getName(), Font.BOLD, tamañoLetra));
        txtFecha.setFont(new Font(txtFecha.getFont().getName(), Font.BOLD, tamañoLetra));
        txtPeso.setFont(new Font(txtPeso.getFont().getName(), Font.BOLD, tamañoLetra));
        txtTitulo.setFont(new Font(txtTitulo.getFont().getName(), Font.BOLD, tamañoLetra));
        
        btnCancelar.setFont(new Font(btnCancelar.getFont().getName(), Font.BOLD, tamañoLetra));
        btnEditCompeten.setFont(new Font(btnEditCompeten.getFont().getName(), Font.BOLD, tamañoLetra));
        btnGuardar.setFont(new Font(btnGuardar.getFont().getName(), Font.BOLD, tamañoLetra));
        
        rdbtnSeleccionar.setFont(new Font(rdbtnSeleccionar.getFont().getName(), Font.BOLD, tamañoLetra));
        rdbtnTodos.setFont(new Font(rdbtnTodos.getFont().getName(), Font.BOLD, tamañoLetra));
        
        chbxTrabajoAdic.setFont(new Font(rdbtnSeleccionar.getFont().getName(), Font.BOLD, tamañoLetra));
        
        tabla.setFont(new Font(tabla.getFont().getName(), Font.PLAIN, tamañoLetra));
        
        comboTrimestre.setFont(new Font(comboTrimestre.getFont().getName(), Font.PLAIN, tamañoLetra));
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditCompeten;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JCheckBox chbxTrabajoAdic;
    private javax.swing.JComboBox<String> comboTrimestre;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler11;
    private javax.swing.Box.Filler filler12;
    private javax.swing.Box.Filler filler13;
    private javax.swing.Box.Filler filler14;
    private javax.swing.Box.Filler filler15;
    private javax.swing.Box.Filler filler16;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAsignatura;
    private javax.swing.JLabel lblCompetencias;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblImagen1;
    private javax.swing.JLabel lblImagen2;
    private javax.swing.JLabel lblPeso;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTituloGrande;
    private javax.swing.JLabel lblTrimestre;
    private javax.swing.JList<String> listaCompetencias;
    private javax.swing.JPanel panelTabla;
    private javax.swing.JRadioButton rdbtnSeleccionar;
    private javax.swing.JRadioButton rdbtnTodos;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtAsignatura;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtPeso;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
