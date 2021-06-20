/*
 * Lucia Calzado Piedrabuena
 * Trabajo de Fin de Grado - Grado en Ingenier�a Inform�tica
 * Universidad de Castilla-La Mancha
 */
package appinterface;

import auxiliar.AuxiliarMethods;
import controladores.ControladorAlumno;
import controladores.ControladorPrueba;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import objects.Alumno;
import objects.Competencia;
import objects.Nota;
import objects.Opciones;
import objects.Prueba;

/**
 *
 * @author lucia
 */
public class InformeTrimestreWindow extends javax.swing.JFrame {

    ControladorAlumno contAlumnos;
    ControladorPrueba contPruebas;
    Opciones opciones;
    int asignatura;
    DefaultTableModel modelNotas;
    DefaultTableModel modelPruebas;
    HashMap<String, Integer> pruebaConID = new HashMap<>(); //para almacenar las pruebas con sus IDs

    public InformeTrimestreWindow(ControladorAlumno contAlumnos, ControladorPrueba contPruebas, Opciones opciones, int asignatura, String nombreAsignatura, int trimestre) {
        
        this.contAlumnos = contAlumnos;
        this.opciones = opciones;
        this.asignatura = asignatura;

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
        
        initComponents();
        lblAsignatura.setText(nombreAsignatura);
        lblTitulo.setText("Vista general del " + trimestre + "º Trimestre");
        rellenarTablaNotas(trimestre);
        rellenarTablaPruebas(trimestre);
        ejecutarOpciones();

    }

    private void rellenarTablaNotas(int trimestre) {
        //rellenar tabla con el trimestre en el que estamos
        int i;
        int numPruebas = contPruebas.getPruebasAsignatura().get(asignatura).get(trimestre).size();
        
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Apellidos");
        tableModel.addColumn("Nombre");
        for (i = 0; i < numPruebas; i++) {
             tableModel.addColumn(contPruebas.getPruebasAsignatura().get(asignatura).get(trimestre).get(i).getEtiqueta());
         }

        tablaNotas.setModel(tableModel);
        modelNotas = (DefaultTableModel) tablaNotas.getModel();
        
        
        //añadir alumnos
        Object[] row = new Object[numPruebas+2];
        for (Alumno alumno : this.contAlumnos.getAlumnosAsignatura().get(asignatura)) {
            row[0] = alumno.getApellidos();
            row[1] = alumno.getNombre();
            for (i = 0; i < numPruebas; i++) {
                for (Nota n : alumno.getNotas()) {
                    for (Prueba p : contPruebas.getPruebasAsignatura().get(asignatura).get(trimestre)) {
                        if (n.getIdPrueba() == p.getIdPrueba()) {
                            
                            row[i + 2] = n.getNota();
                        }
                    }
                }
            }

            modelNotas.addRow(row);
        }
    }
    
    private void rellenarTablaPruebas (int trimestre) {
        modelPruebas = (DefaultTableModel) tablaPruebas.getModel();
        Object[] row = new Object[6];
        int i;
        ArrayList<Prueba> pruebas = contPruebas.getPruebasAsignatura().get(asignatura).get(trimestre);
        
        for (i=0; i<pruebas.size(); i++){
            row[0] = pruebas.get(i).getTitulo();
            row[1] = pruebas.get(i).getEtiqueta();
            row[2] = pruebas.get(i).getPeso();
            row[3] = pruebas.get(i).getFecha();
            pruebaConID.put(contPruebas.getPruebasAsignatura().get(asignatura).get(trimestre).get(i).getTitulo(), contPruebas.getPruebasAsignatura().get(asignatura).get(trimestre).get(i).getIdPrueba());
            modelPruebas.addRow(row);
        }
        
    }

    private void ejecutarOpciones() {
        List<Component> components = AuxiliarMethods.getAllComponents(this);

        for (Component c : components) {
            c.setFont(new Font(c.getFont().getName(), c.getFont().getStyle(), opciones.getTamañoLetra()));
            if (opciones.getOscuro() && c.getClass() != JButton.class && c.getClass() != JTextField.class) {
                c.setForeground(Color.LIGHT_GRAY);
            }
        }

        lblAsignatura.setFont(new Font(lblAsignatura.getFont().getName(), Font.PLAIN, opciones.getTamañoLetra() + 4));
        lblTitulo.setFont(new Font(lblTitulo.getFont().getName(), Font.BOLD, opciones.getTamañoLetra() + 15));

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

        lblTitulo = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(40, 0), new java.awt.Dimension(40, 0), new java.awt.Dimension(40, 32767));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(40, 0), new java.awt.Dimension(40, 0), new java.awt.Dimension(40, 32767));
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(80, 0), new java.awt.Dimension(80, 0), new java.awt.Dimension(80, 32767));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 80), new java.awt.Dimension(0, 80), new java.awt.Dimension(32767, 80));
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 40), new java.awt.Dimension(0, 40), new java.awt.Dimension(32767, 40));
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 40), new java.awt.Dimension(0, 40), new java.awt.Dimension(32767, 40));
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaNotas = new JTable() {
            public Component prepareRenderer(TableCellRenderer renderer, int Index_row, int Index_col) {
                // get the current row
                DefaultTableModel model = (DefaultTableModel) tablaNotas.getModel();
                Component comp = super.prepareRenderer(renderer, Index_row, Index_col);

                if(Index_col != 0 && Index_col != 1 && model.getValueAt(Index_row, Index_col) != null && !model.getValueAt(Index_row, Index_col).equals("")){
                    if (Double.parseDouble(model.getValueAt(Index_row, Index_col).toString()) < 5.0 && !isCellSelected(Index_row, Index_col)) {
                        comp.setForeground(opciones.getColorSuspensos());
                    } else if (!isCellSelected(Index_row, Index_col)) {
                        comp.setForeground(opciones.getColorAprobados());
                    }
                }
                return comp;
            }
        };
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaPruebas = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        listaCompetencias = new javax.swing.JList<>();
        btnBorrarPrueba = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblAsignatura = new javax.swing.JLabel();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 50), new java.awt.Dimension(0, 50), new java.awt.Dimension(32767, 50));
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 40), new java.awt.Dimension(0, 40), new java.awt.Dimension(32767, 40));
        btnCerrar = new javax.swing.JButton();
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 40), new java.awt.Dimension(0, 40), new java.awt.Dimension(32767, 40));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Vista general del trimestre");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblTitulo.setText("Vista general del trimestre");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(lblTitulo, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 7;
        getContentPane().add(filler1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 5;
        getContentPane().add(filler2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 8;
        getContentPane().add(filler4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 6;
        getContentPane().add(filler3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        getContentPane().add(filler5, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        getContentPane().add(filler6, gridBagConstraints);

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        tablaNotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null}
            },
            new String [] {
                "Apellidos", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaNotas);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1220, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Notas", jPanel2);

        tablaPruebas.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tablaPruebas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Título", "Etiqueta", "Peso (%)", "Fecha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tablaPruebas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPruebasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaPruebas);
        if (tablaPruebas.getColumnModel().getColumnCount() > 0) {
            tablaPruebas.getColumnModel().getColumn(0).setResizable(false);
            tablaPruebas.getColumnModel().getColumn(1).setResizable(false);
            tablaPruebas.getColumnModel().getColumn(3).setResizable(false);
        }

        listaCompetencias.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        listaCompetencias.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        listaCompetencias.setToolTipText("Selecciona varias competencias con Ctrl+Clic");
        listaCompetencias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaCompetenciasMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(listaCompetencias);

        btnBorrarPrueba.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnBorrarPrueba.setIcon(new javax.swing.ImageIcon("C:\\Users\\lucia\\Desktop\\NoName\\assets\\trash.png")); // NOI18N
        btnBorrarPrueba.setText("Borrar prueba");
        btnBorrarPrueba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarPruebaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setText("Competencias:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 935, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(15, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBorrarPrueba)
                        .addGap(58, 58, 58))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4)
                .addGap(30, 30, 30)
                .addComponent(btnBorrarPrueba)
                .addGap(75, 75, 75))
        );

        jTabbedPane1.addTab("Pruebas", jPanel1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jTabbedPane1, gridBagConstraints);

        lblAsignatura.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblAsignatura.setText("@Asignatura");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(lblAsignatura, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        getContentPane().add(filler7, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        getContentPane().add(filler8, gridBagConstraints);

        btnCerrar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(btnCerrar, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 9;
        getContentPane().add(filler9, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void listaCompetenciasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaCompetenciasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_listaCompetenciasMouseClicked

    private void btnBorrarPruebaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarPruebaActionPerformed
        String titulo = "¿Quieres borrar "+modelPruebas.getValueAt(tablaPruebas.getSelectedRow(), 0).toString()+"?";
        
        int borrar = JOptionPane.showConfirmDialog(null, titulo, "Confirmar borrado", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        // 0 --> sí         1 --> no
        if (borrar == 0){
            //TODO proceder a borrar la prueba de la tabla, de contPruebas, las notas de los alumnos respecto a esa prueba en contAlumnos y todo de la base de datos
        }
        
    }//GEN-LAST:event_btnBorrarPruebaActionPerformed

    private void tablaPruebasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPruebasMouseClicked
        DefaultListModel<String> modeloAsig = new DefaultListModel<>();
        String pruebaCogida = modelPruebas.getValueAt(tablaPruebas.getSelectedRow(), 0).toString();
        int idPruebaCogida = pruebaConID.get(pruebaCogida);
        ArrayList<Competencia> arrayCompetencias = contPruebas.getCompetenciasPrueba().get(idPruebaCogida); //competencias de la prueba seleccionada
        
        for (int i=0; i<arrayCompetencias.size(); i++){
            modeloAsig.addElement(arrayCompetencias.get(i).getNombre());
        }
    }//GEN-LAST:event_tablaPruebasMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrarPrueba;
    private javax.swing.JButton btnCerrar;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblAsignatura;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JList<String> listaCompetencias;
    private javax.swing.JTable tablaNotas;
    private javax.swing.JTable tablaPruebas;
    // End of variables declaration//GEN-END:variables
}
