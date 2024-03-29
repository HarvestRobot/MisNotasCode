/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appinterface;

import appsmallinterfaces.Ayuda;
import appsmallinterfaces.CargarAlumno;
import objects.Chart;
import appsmallinterfaces.EditarUsuarioWindow;
import auxiliar.AuxiliarMethods;
import auxiliar.ToolTipHeader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import objects.Alumno;
import controladores.ControladorAlumno;
import controladores.ControladorCompetencia;
import controladores.ControladorCurso;
import controladores.ControladorPrueba;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import mainpackage.DBConnection;
import objects.Maestro;
import objects.Opciones;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 *
 * @author lucia
 */
public class MainWindow extends javax.swing.JFrame {

    ControladorAlumno contAlumnos;
    ControladorCurso contCurso;
    ControladorCompetencia contCompetencias;
    ControladorPrueba contPruebas;
    Maestro maestro;
    ButtonGroup cursosGrupo;
    ButtonGroup asignaturasGrupo;
    DefaultTableModel model;
    NumberFormat formatter;
    Opciones opciones;
    
//    //para las estadísticas
//    int aprobados1;
//    int aprobados2;
//    int aprobados3;

    /**
     *
     * @param maestro
     * @param opciones
     */
    public MainWindow(Maestro maestro, Opciones opciones) {
        this.opciones = opciones;
        initComponents();
        getDateTime(); //cargar hora y fecha
        this.maestro = maestro;
        cargarLogoColegio();
        
        //Asociamos los assets a los botones y labels
        btnAddAlumno.setIcon(new ImageIcon ("assets/crearAlumno.png"));
        btnCalificar.setIcon(new ImageIcon ("assets/calificarPruebaPeq.png"));
        btnCerrarSesion.setIcon(new ImageIcon("assets/cerrarSesion.png"));
        btnNuevaTarea.setIcon(new ImageIcon ("assets/nuevaPruebaPeq.png"));
        lblFotoMaestro.setIcon(maestro.getImagen());
        
        //labels con el nombre del maestro
        lblBienvenida.setText("Bienvenido/a, " + maestro.getNombre());

        nombreAsignatura.setVisible(false);
        model = (DefaultTableModel) tabla.getModel();

        cargarToolTipsTabla();

        //Cargar el maestro
        try {
            maestro.cargarMaestro();
        } catch (SQLException e) {
            AuxiliarMethods.showWarning("No se han podido cargar los datos del maestro.\nMás información: " + e.toString());
        }

        //Formato de los decimales
        this.formatter = NumberFormat.getInstance(Locale.US);
        this.formatter.setMaximumFractionDigits(2);
        this.formatter.setRoundingMode(RoundingMode.UP);

        //cositas para la tabla
        tabla.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (tabla.columnAtPoint(e.getPoint())) { //recogemos qué columnheader estamos apuntando en el momento del clic
                    case 2:
                        InformeTrimestreWindow itw1 = new InformeTrimestreWindow(contAlumnos, contPruebas, opciones, getAsignatura(), nombreAsignatura.getText(), 1);
                        itw1.pack();
                        itw1.setVisible(true);
                        itw1.setMinimumSize(itw1.getSize());
                        break;
                    case 3:
                        InformeTrimestreWindow itw2 = new InformeTrimestreWindow(contAlumnos, contPruebas, opciones, getAsignatura(), nombreAsignatura.getText(), 2);
                        itw2.pack();
                        itw2.setVisible(true);
                        itw2.setMinimumSize(itw2.getSize());
                        break;
                    case 4:
                        InformeTrimestreWindow itw3 = new InformeTrimestreWindow(contAlumnos, contPruebas, opciones, getAsignatura(), nombreAsignatura.getText(), 3);
                        itw3.pack();
                        itw3.setVisible(true);
                        itw3.setMinimumSize(itw3.getSize());
                        break;
                }
            }
        });
        tabla.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE); //para que cuando se clique un botón, deje de editarse la tabla
        tabla.setRowHeight(25);

        //Cambiamos el tamaño de la letra si se ha pedido
        //setComportamientoBotonCerrar();
        ejecutarOpciones();

        //exclusión de los rdbtn cursos
        cursosGrupo = new ButtonGroup();
        cursosGrupo.add(rdbtnc1);
        cursosGrupo.add(rdbtnc2);
        cursosGrupo.add(rdbtnc3);

        //exclusión de los rdbtn asignaturas
        asignaturasGrupo = new ButtonGroup();
        asignaturasGrupo.add(rdbtnmat3A);
        asignaturasGrupo.add(rdbtnlen3A);
        asignaturasGrupo.add(rdbtnrel4A);
        asignaturasGrupo.add(rdbtncon1A);

        rdbtnmat3A.setEnabled(false);
        rdbtnlen3A.setEnabled(false);

        rdbtncon1A.setVisible(false);
        rdbtnrel4A.setVisible(false);

        //por último cargamos el controlador de alumnos
        try {
            this.contAlumnos = new ControladorAlumno();
        } catch (SQLException e) {
            AuxiliarMethods.showWarning(e.toString());
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
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        lblBienvenida = new javax.swing.JLabel();
        fecha = new javax.swing.JLabel();
        hora = new javax.swing.JLabel();
        nombreAsignatura = new javax.swing.JLabel();
        ventormentaPicture = new javax.swing.JLabel();
        lblCursos = new javax.swing.JLabel();
        lblAsignaturas = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new JTable(model) {
            public Component prepareRenderer(TableCellRenderer renderer, int Index_row, int Index_col) {
                // get the current row
                model = (DefaultTableModel) tabla.getModel();
                Component comp = super.prepareRenderer(renderer, Index_row, Index_col);
                // even index, not selected
                if(Index_col != 0 && Index_col != 1 && model.getValueAt(Index_row, Index_col) != null){
                    if (Double.parseDouble(model.getValueAt(Index_row, Index_col).toString()) < 5.0 && !isCellSelected(Index_row, Index_col)) {
                        comp.setForeground(opciones.getColorSuspensos());
                    } else if (!isCellSelected(Index_row, Index_col)) {
                        comp.setForeground(opciones.getColorAprobados());
                    }
                }
                return comp;
            }
        };
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(25, 0), new java.awt.Dimension(25, 0), new java.awt.Dimension(25, 32767));
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 25), new java.awt.Dimension(0, 25), new java.awt.Dimension(0, 25));
        btnCalificar = new javax.swing.JButton();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 25), new java.awt.Dimension(0, 25), new java.awt.Dimension(0, 25));
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        btnNuevaTarea = new javax.swing.JButton();
        rdbtnc2 = new javax.swing.JRadioButton();
        rdbtnc3 = new javax.swing.JRadioButton();
        rdbtnc1 = new javax.swing.JRadioButton();
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));
        rdbtnmat3A = new javax.swing.JRadioButton();
        rdbtnlen3A = new javax.swing.JRadioButton();
        rdbtnrel4A = new javax.swing.JRadioButton();
        btnEditarUsuario = new javax.swing.JButton();
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        filler11 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 15), new java.awt.Dimension(0, 15), new java.awt.Dimension(32767, 15));
        filler12 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 15), new java.awt.Dimension(0, 15), new java.awt.Dimension(32767, 15));
        lblCentro = new javax.swing.JLabel();
        filler13 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 15), new java.awt.Dimension(0, 15), new java.awt.Dimension(32767, 15));
        rdbtncon1A = new javax.swing.JRadioButton();
        filler15 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 40), new java.awt.Dimension(0, 40), new java.awt.Dimension(32767, 40));
        filler16 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        panelEstadisticas = new javax.swing.JPanel();
        lblAprob3 = new javax.swing.JLabel();
        lblNumAp2 = new javax.swing.JLabel();
        lblNumAp3 = new javax.swing.JLabel();
        lblNumAp1 = new javax.swing.JLabel();
        lblAprobTotal = new javax.swing.JLabel();
        lblAprob2 = new javax.swing.JLabel();
        lblAprob1 = new javax.swing.JLabel();
        lblNumApTotal = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        filler19 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        panelGraficos = new javax.swing.JPanel();
        filler18 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 130), new java.awt.Dimension(0, 130), new java.awt.Dimension(32767, 130));
        filler14 = new javax.swing.Box.Filler(new java.awt.Dimension(70, 0), new java.awt.Dimension(70, 0), new java.awt.Dimension(70, 32767));
        filler22 = new javax.swing.Box.Filler(new java.awt.Dimension(70, 0), new java.awt.Dimension(70, 0), new java.awt.Dimension(70, 32767));
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        filler23 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        filler24 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 500), new java.awt.Dimension(0, 500), new java.awt.Dimension(32767, 500));
        lblFotoMaestro = new javax.swing.JLabel();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 32767));
        btnAddAlumno = new javax.swing.JButton();
        btnAddAlumno.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnAddAlumno.setHorizontalTextPosition(SwingConstants.CENTER);
        btnCerrarSesion = new javax.swing.JButton();
        filler17 = new javax.swing.Box.Filler(new java.awt.Dimension(900, 0), new java.awt.Dimension(900, 0), new java.awt.Dimension(900, 32767));
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ventana principal");
        setBounds(new java.awt.Rectangle(250, 10, 0, 0));
        setIconImage(new ImageIcon("assets/logo.png").getImage());
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel2.setName("panelPrincipal"); // NOI18N
        java.awt.GridBagLayout jPanel2Layout = new java.awt.GridBagLayout();
        jPanel2Layout.columnWidths = new int[] {0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0};
        jPanel2Layout.rowHeights = new int[] {0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0};
        jPanel2.setLayout(jPanel2Layout);

        lblBienvenida.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblBienvenida.setText("Bienvenido/a, @User");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 46;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        jPanel2.add(lblBienvenida, gridBagConstraints);

        fecha.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        fecha.setText("@Fecha");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        jPanel2.add(fecha, gridBagConstraints);

        hora.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        hora.setText("@Hora");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        jPanel2.add(hora, gridBagConstraints);

        nombreAsignatura.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        nombreAsignatura.setText("@Asignatura");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 40;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel2.add(nombreAsignatura, gridBagConstraints);

        ventormentaPicture.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        jPanel2.add(ventormentaPicture, gridBagConstraints);

        lblCursos.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblCursos.setText("Cursos:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel2.add(lblCursos, gridBagConstraints);

        lblAsignaturas.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblAsignaturas.setText("Asignaturas:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel2.add(lblAsignaturas, gridBagConstraints);

        jScrollPane1.setBackground(new java.awt.Color(252, 237, 237));
        jScrollPane1.setForeground(new java.awt.Color(252, 244, 237));

        tabla.setBackground(getBackground());
        tabla.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Apellidos", "Nombre", "1º Trimestre", "2º Trimestre", "3º Trimestre", "Nota media", "Nota final"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabla.setColumnSelectionAllowed(true);
        tabla.setMinimumSize(new java.awt.Dimension(500, 80));
        tabla.setName("tabla"); // NOI18N
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);
        tabla.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.gridwidth = 37;
        gridBagConstraints.gridheight = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(jScrollPane1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 24;
        gridBagConstraints.gridy = 8;
        jPanel2.add(filler1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 35;
        jPanel2.add(filler4, gridBagConstraints);

        btnCalificar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnCalificar.setText("Calificar tareas o pruebas");
        btnCalificar.setName("btnCalificar"); // NOI18N
        btnCalificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalificarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 46;
        gridBagConstraints.gridy = 36;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel2.add(btnCalificar, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 42;
        gridBagConstraints.gridwidth = 63;
        jPanel2.add(filler3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 11;
        jPanel2.add(filler8, gridBagConstraints);

        btnNuevaTarea.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnNuevaTarea.setText("Nueva tarea o prueba");
        btnNuevaTarea.setName("btnNuevaTarea"); // NOI18N
        btnNuevaTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaTareaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 42;
        gridBagConstraints.gridy = 36;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel2.add(btnNuevaTarea, gridBagConstraints);

        rdbtnc2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        rdbtnc2.setText("4ºA");
        rdbtnc2.setName("rdbtnc2"); // NOI18N
        rdbtnc2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnc2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 16;
        jPanel2.add(rdbtnc2, gridBagConstraints);

        rdbtnc3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        rdbtnc3.setText("3ºA");
        rdbtnc3.setName("rdbtnc3"); // NOI18N
        rdbtnc3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnc3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 14;
        gridBagConstraints.gridy = 16;
        jPanel2.add(rdbtnc3, gridBagConstraints);

        rdbtnc1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        rdbtnc1.setText("1ºA");
        rdbtnc1.setName("rdbtnc1"); // NOI18N
        rdbtnc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnc1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridwidth = 3;
        jPanel2.add(rdbtnc1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 36;
        gridBagConstraints.gridy = 30;
        jPanel2.add(filler9, gridBagConstraints);

        rdbtnmat3A.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        rdbtnmat3A.setText("Matemáticas 3ºA");
        rdbtnmat3A.setName("rdbtnam3"); // NOI18N
        rdbtnmat3A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnmat3AActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridwidth = 21;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel2.add(rdbtnmat3A, gridBagConstraints);

        rdbtnlen3A.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        rdbtnlen3A.setText("Lengua Castellana 3ºA");
        rdbtnlen3A.setName("rdbtnmat3C"); // NOI18N
        rdbtnlen3A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnlen3AActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 20;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel2.add(rdbtnlen3A, gridBagConstraints);

        rdbtnrel4A.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        rdbtnrel4A.setText("Religiones 4ºA (opt.)");
        rdbtnrel4A.setName("rdbtnmat3C"); // NOI18N
        rdbtnrel4A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnrel4AActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel2.add(rdbtnrel4A, gridBagConstraints);

        btnEditarUsuario.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnEditarUsuario.setText("Editar usuario");
        btnEditarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarUsuarioActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 46;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel2.add(btnEditarUsuario, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 44;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridheight = 23;
        jPanel2.add(filler10, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 35;
        jPanel2.add(filler11, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 39;
        jPanel2.add(filler12, gridBagConstraints);

        lblCentro.setFont(new java.awt.Font("Segoe UI", 2, 45)); // NOI18N
        lblCentro.setText("C.P. Ventormenta");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 14;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 21;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel2.add(lblCentro, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 63;
        jPanel2.add(filler13, gridBagConstraints);

        rdbtncon1A.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        rdbtncon1A.setText("Conocimiento del Medio 1ºA");
        rdbtncon1A.setName("rdbtnmat3C"); // NOI18N
        rdbtncon1A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtncon1AActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel2.add(rdbtncon1A, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 35;
        jPanel2.add(filler15, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.gridwidth = 37;
        jPanel2.add(filler16, gridBagConstraints);

        panelEstadisticas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estadísticas del curso", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
        panelEstadisticas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblAprob3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblAprob3.setText("Aprobados 3º Trimestre:");

        lblNumAp2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNumAp2.setText("-");

        lblNumAp3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNumAp3.setText("-");

        lblNumAp1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNumAp1.setText("-");

        lblAprobTotal.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblAprobTotal.setText("Aprobados en el curso:");

        lblAprob2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblAprob2.setText("Aprobados 2º Trimestre:");

        lblAprob1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblAprob1.setText("Aprobados 1º Trimestre:");

        lblNumApTotal.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNumApTotal.setText("-");

        panelGraficos.setLayout(new javax.swing.BoxLayout(panelGraficos, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout panelEstadisticasLayout = new javax.swing.GroupLayout(panelEstadisticas);
        panelEstadisticas.setLayout(panelEstadisticasLayout);
        panelEstadisticasLayout.setHorizontalGroup(
            panelEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEstadisticasLayout.createSequentialGroup()
                .addGroup(panelEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filler19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelEstadisticasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelEstadisticasLayout.createSequentialGroup()
                                .addGroup(panelEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblAprobTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblAprob3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblAprob2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(5, 5, 5)
                                .addGroup(panelEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNumApTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
                                    .addComponent(lblNumAp3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblNumAp2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(6, 6, 6))
                            .addGroup(panelEstadisticasLayout.createSequentialGroup()
                                .addComponent(lblAprob1)
                                .addGap(5, 5, 5)
                                .addComponent(lblNumAp1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filler18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelGraficos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelEstadisticasLayout.setVerticalGroup(
            panelEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEstadisticasLayout.createSequentialGroup()
                .addGroup(panelEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEstadisticasLayout.createSequentialGroup()
                        .addGroup(panelEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(filler19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(panelEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelEstadisticasLayout.createSequentialGroup()
                                .addGroup(panelEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAprob1)
                                    .addComponent(lblNumAp1))
                                .addGap(5, 5, 5)
                                .addGroup(panelEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAprob2)
                                    .addComponent(lblNumAp2))
                                .addGap(5, 5, 5)
                                .addGroup(panelEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAprob3)
                                    .addComponent(lblNumAp3))
                                .addGap(5, 5, 5)
                                .addGroup(panelEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAprobTotal)
                                    .addComponent(lblNumApTotal)))
                            .addComponent(panelGraficos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEstadisticasLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(filler18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 36;
        gridBagConstraints.gridwidth = 35;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(panelEstadisticas, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 39;
        jPanel2.add(filler14, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 60;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 39;
        jPanel2.add(filler22, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 42;
        gridBagConstraints.gridy = 40;
        jPanel2.add(filler7, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 30;
        gridBagConstraints.gridy = 40;
        jPanel2.add(filler23, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.gridheight = 11;
        jPanel2.add(filler24, gridBagConstraints);

        lblFotoMaestro.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 46;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.gridheight = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        jPanel2.add(lblFotoMaestro, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        jPanel2.add(filler5, gridBagConstraints);

        btnAddAlumno.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnAddAlumno.setIcon(new javax.swing.ImageIcon("C:\\Users\\lucia\\Desktop\\MisNotas\\assets\\CrearAlumno.png")); // NOI18N
        btnAddAlumno.setText("Añadir alumno/a");
        btnAddAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddAlumnoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 48;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.gridheight = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel2.add(btnAddAlumno, gridBagConstraints);

        btnCerrarSesion.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnCerrarSesion.setIcon(new javax.swing.ImageIcon("C:\\Users\\lucia\\Desktop\\MisNotas\\assets\\cerrarSesion.png")); // NOI18N
        btnCerrarSesion.setToolTipText("Cerrar sesión");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 54;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
        jPanel2.add(btnCerrarSesion, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 34;
        gridBagConstraints.gridwidth = 33;
        jPanel2.add(filler17, gridBagConstraints);

        getContentPane().add(jPanel2);

        jMenuBar1.setName("menuEditar"); // NOI18N

        jMenu1.setText("Archivo");
        jMenu1.setName("menuArchivo"); // NOI18N

        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jMenuItem1.setText("Cerrar sesión");
        jMenuItem1.setName("mnbtnSalir"); // NOI18N
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Editar");

        jMenu7.setText("Alumnado");

        jMenuItem3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jMenuItem3.setText("Cargar un/a alumno/a");
        jMenuItem3.setToolTipText("Para cargar un/a solo/a alumno/a");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem3);

        jMenuItem6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jMenuItem6.setText("Cargar alumnos/as mediante Excel");
        jMenuItem6.setToolTipText("Carga varios/as alumnos/as mediante un archivo Excel.\nPara más información, vea el Manual de ayuda.");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem6);

        jMenu2.add(jMenu7);

        jMenuBar1.add(jMenu2);

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

    /**
     * Botón de cerrar sesión en el jmenu
     * @param evt 
     */
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        String confirmation = "¿Seguro que quiere cerrar la sesión?";
        if (AuxiliarMethods.showCloseConfirmation(confirmation) == 0){
            MainWindow.this.dispose();
            System.exit(0);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * Botón de cargar varios alumnos mediante un excel en el jmenu
     * @param evt 
     */
    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        int idAsignatura = getAsignatura();
        int idCurso = getCurso();
        if (idCurso != 0 && idAsignatura != 0) {
            try {
                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

                int returnValue = jfc.showOpenDialog(null);

                File file = null;
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    file = jfc.getSelectedFile();
                }

                FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file  
                //creating Workbook instance that refers to .xlsx file  
                XSSFWorkbook wb = new XSSFWorkbook(fis);
                XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object  
                Iterator<Row> itr = sheet.iterator();    //iterating over excel file  
                while (itr.hasNext()) {
                    Object[] alumno = new Object[4];
                    int i = 0;
                    Row row = itr.next();
                    Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column  
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        alumno[i] = cell.toString();
                        i++;
                    }
                    Alumno a = new Alumno();
                    a.setApellidos(alumno[0].toString());
                    a.setNombre(alumno[1].toString());
                    a.setDni(alumno[2].toString());
                    a.setFechaNacimiento(alumno[3].toString());
                    a.setIdAlumno(a.commitNuevoAlumno());
//                    a.cargarNotas();
//                    a.cargarNotasFinales();

                    if (contAlumnos.esOptativa(idAsignatura)) {
                        contAlumnos.añadirAlumnoAAsignatura(a, idAsignatura);
                        contAlumnos.commitAlumnoAsignatura(a, idAsignatura);
                    } else {
                        //se tiene que añadir el alumno a todas las asignaturas del curso actual
                        contAlumnos.añadirAlumnoACurso(a, idAsignatura);
                        contAlumnos.commitAlumnoCurso(a, idAsignatura); //commit el alumno a todas las asignaturas de un curso
                    }
                    
                }
            } catch (SQLIntegrityConstraintViolationException e1) {
                AuxiliarMethods.showWarning("Error.\nDNI del alumno duplicado. Por favor, revise el Excel.");
            } catch (FileNotFoundException e2) {
                AuxiliarMethods.showWarning("Error.\nNo se encuentra el archivo especificado.");
            } catch (NullPointerException e3){
                
            } catch (Exception e4) {
                AuxiliarMethods.showWarning("Error desconocido.\nPor favor, contacte con un administrador.\nMás información: "+e4.toString());
            }
            cargarTabla(idCurso, idAsignatura);
        }
        
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    /**
     * Botón de acerca de
     * @param evt 
     */
    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        JOptionPane.showMessageDialog(new JFrame(), "Trabajo de Fin de Grado de Lucía Calzado Piedrabuena.\nGrado en Ingeniería Informática.\nUCLM.");
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    /**
     * Botón de calificar pruebas. Abre una nueva ventana CalificarPruebasWindow
     * @param evt 
     */
    private void btnCalificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalificarActionPerformed
        int pasarAsignatura = 0;
        if (!((pasarAsignatura = getAsignatura()) == 0)) {
            CalificarPruebasWindow ctw = new CalificarPruebasWindow(nombreAsignatura.getText(), pasarAsignatura, contAlumnos, contPruebas, opciones, "MainWindow", null);
            ctw.pack();
            ctw.setVisible(true);
            ctw.setMinimumSize(ctw.getSize());
        }
    }//GEN-LAST:event_btnCalificarActionPerformed

    /**
     * Botón de nueva prueba. Abre una nueva ventana NuevaPruebaWindow
     * @param evt 
     */
    private void btnNuevaTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaTareaActionPerformed
        int pasarAsignatura = 0;
        if (!((pasarAsignatura = getAsignatura()) == 0)) {
            NuevaPruebaWindow ntw = new NuevaPruebaWindow(nombreAsignatura.getText(), pasarAsignatura, contAlumnos, contPruebas, contCompetencias, opciones);
            ntw.pack();
            ntw.setVisible(true);
            ntw.setMinimumSize(ntw.getSize());
        }

    }//GEN-LAST:event_btnNuevaTareaActionPerformed

    /**
     * Comportamiento del radio button
     * @param evt 
     */
    private void rdbtnc2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnc2ActionPerformed
        //Curso 4ºA
        limpiarSelecciones();
        rdbtnmat3A.setVisible(false);
        rdbtnlen3A.setVisible(false);
        rdbtncon1A.setVisible(false);
        rdbtnrel4A.setVisible(true);
    }//GEN-LAST:event_rdbtnc2ActionPerformed

    /**
     * Comportamiento del radio button
     * @param evt 
     */
    private void rdbtnc3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnc3ActionPerformed
        //Curso 3ºA
        limpiarSelecciones();
        rdbtnmat3A.setVisible(true);
        rdbtnlen3A.setVisible(true);
        rdbtnlen3A.setEnabled(true);
        rdbtnmat3A.setEnabled(true);
        rdbtncon1A.setVisible(false);
        rdbtnrel4A.setVisible(false);

    }//GEN-LAST:event_rdbtnc3ActionPerformed

    /**
     * Comportamiento del radio button
     * @param evt 
     */
    private void rdbtnc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnc1ActionPerformed
        //Curso 1ºA 
        limpiarSelecciones();
        rdbtnrel4A.setVisible(false);
        rdbtnmat3A.setVisible(false);
        rdbtnlen3A.setVisible(false);
        rdbtncon1A.setVisible(true);
    }//GEN-LAST:event_rdbtnc1ActionPerformed

    /**
     * Acción de clicar en la tabla. Esto abre una ventana InformeAlumnoWindow
     * @param evt 
     */
    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        int pasarAsignatura = 0;

        if (!((pasarAsignatura = getAsignatura()) == 0)) {
            if (tabla.getSelectedColumn() == 0 || tabla.getSelectedColumn() == 1) {
                for (Alumno alumno : this.contAlumnos.getAlumnosAsignatura().get(getAsignatura())) {
                    if (alumno.getApellidos().equals(model.getValueAt(tabla.getSelectedRow(), 0)) && alumno.getNombre().equals(model.getValueAt(tabla.getSelectedRow(), 1))) {
                        InformeAlumnoWindow iaw = new InformeAlumnoWindow(alumno, pasarAsignatura, nombreAsignatura.getText(), contAlumnos, opciones, this);
                        iaw.pack();
                        iaw.setVisible(true);
                        iaw.setMinimumSize(iaw.getSize());
                        iaw.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    }
                }
            }
        }
    }//GEN-LAST:event_tablaMouseClicked

    /**
     * Seleccionar botón Matemáticas 3ºA
     *
     * @param evt
     */
    private void rdbtnmat3AActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnmat3AActionPerformed
        nombreAsignatura.setText("Matemáticas 3ºA");
        cargarTabla(getCurso(), getAsignatura());
        obtenerEstadísticas();
    }//GEN-LAST:event_rdbtnmat3AActionPerformed

    /**
     * Seleccionar botón Conocimiento del Medio 1ºA
     * @param evt 
     */
    private void rdbtncon1AActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtncon1AActionPerformed
        nombreAsignatura.setText("Conocimiento del Medio 1ºA");
        cargarTabla(getCurso(), getAsignatura());
        obtenerEstadísticas();
    }//GEN-LAST:event_rdbtncon1AActionPerformed

    /**
     * Botón de editar usuario. Esto abre una nueva ventana EditarUsuarioWindow
     * @param evt 
     */
    private void btnEditarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarUsuarioActionPerformed
        EditarUsuarioWindow euw = new EditarUsuarioWindow(maestro, opciones, this);
        euw.pack();
        euw.setVisible(true);
        AuxiliarMethods.centrarVentana(euw);
        euw.setMinimumSize(euw.getSize());
    }//GEN-LAST:event_btnEditarUsuarioActionPerformed

    /**
     * Seleccionar botón Lengua 3ºA
     *
     * @param evt
     */
    private void rdbtnlen3AActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnlen3AActionPerformed
        nombreAsignatura.setText("Lengua Castellana 3ºA");
        cargarTabla(getCurso(), getAsignatura());
        obtenerEstadísticas();
    }//GEN-LAST:event_rdbtnlen3AActionPerformed

    /**
     * Seleccionar botón Religión 4ºA
     *
     * @param evt
     */
    private void rdbtnrel4AActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnrel4AActionPerformed
        nombreAsignatura.setText("Religiones 4ºA (opt.)");
        cargarTabla(getCurso(), getAsignatura());
        obtenerEstadísticas();
    }//GEN-LAST:event_rdbtnrel4AActionPerformed

    /**
     * Botón del manual de ayuda. Abre un nuevo manual de ayuda.
     * @param evt 
     */
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Ayuda ayuda = new Ayuda(opciones, 1);
        ayuda.pack();
        ayuda.setVisible(true);
        ayuda.setMinimumSize(ayuda.getSize());
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    /**
     * Botón para cargar un solo alumno. Abre una nueva ventana CargarAlumno.
     * @param evt 
     */
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        int asignatura = getAsignatura();
        if (asignatura != 0) {
            CargarAlumno ca = new CargarAlumno(opciones, nombreAsignatura.getText(), getAsignatura(), getCurso(), contAlumnos, this);
            ca.pack();
            AuxiliarMethods.centrarVentana(ca);
            ca.setVisible(true);
            ca.setMinimumSize(ca.getSize());
        }

    }//GEN-LAST:event_jMenuItem3ActionPerformed

    /**
     * Add un alumno nuevo a la asignatura seleccionada
     * @param evt 
     */
    private void btnAddAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddAlumnoActionPerformed
       int asignatura = getAsignatura();
        if (asignatura != 0) {
            CargarAlumno ca = new CargarAlumno(opciones, nombreAsignatura.getText(), getAsignatura(), getCurso(), contAlumnos, this);
            ca.pack();
            AuxiliarMethods.centrarVentana(ca);
            ca.setVisible(true);
            ca.setMinimumSize(ca.getSize());
        }
    }//GEN-LAST:event_btnAddAlumnoActionPerformed

    /**
     * Botón de cerrar sesión: cierra el programa
     * @param evt 
     */
    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        String confirmation = "¿Seguro que quiere cerrar la sesión?";
        if (AuxiliarMethods.showCloseConfirmation(confirmation) == 0){
            MainWindow.this.dispose();
            try {
                DBConnection.closeConnection();
            } catch (SQLException sql){
                AuxiliarMethods.showWarning("No se ha podido cerrar la conexión con la base de datos.\nPor favor, contacte con un administrador.\nMás información: "+sql.toString());
            }
            System.exit(0);
        }
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    /**
     * Devuelve un curso, pero lo uso principalmente para comprobar si se ha
     * seleccionado curso
     *
     * @return el curso seleccionado, o 0 si no hay ninguno
     */
    public int getCurso() {
        if (rdbtnc1.isSelected()) {
            return 1;
        } else if (rdbtnc2.isSelected()) {
            return 4;
        } else if (rdbtnc3.isSelected()) {
            return 3;
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "Selecciona un curso primero.");
        }
        return 0;
    }

    /**
     * Devuelve la asignatura seleccionada
     *
     * @return el código de la asignatura o 0 si no hay ninguna seleccionada
     */
    public int getAsignatura() {
        if (rdbtnmat3A.isSelected()) {
            return 131; //Matemáticas de 3ºA
        } else if (rdbtnlen3A.isSelected()) {
            return 331; //Lengua de 3ºA
        } else if (rdbtnrel4A.isSelected()) {
            return 441; //Religiones de 4ºA
        } else if (rdbtncon1A.isSelected()) {
            return 211; //Conocimiento del Medio 1ºA
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "Selecciona una asignatura primero.");
        }
        return 0;
    }

    /**
     * Recoge la fecha y la hora actuales. La hora va cambiando dinámicamente
     */
    private void getDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        fecha.setText(formatter.format(date));

        //hora que se actualiza sola
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                String t = new SimpleDateFormat("HH:mm").format(new Date());
                hora.setText(t);
            }
        }, 0, 1000);
    }

    /**
     * Carga el logo del colegio
     */
    private void cargarLogoColegio() {
        ImageIcon imageIcon = new ImageIcon("assets/alliance_logo.png"); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(110, 120, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        imageIcon = new ImageIcon(newimg);  // transform it back
        ventormentaPicture.setIcon(imageIcon);
    }

    /**
     * Carga la tabla grande
     * @param curso
     * @param asignatura 
     */
    public void cargarTabla(int curso, int asignatura) {
        //modelo para introducir filas en la tabla
        model.setRowCount(0);

        if (curso != 0 && asignatura != 0) {

            Object[] row = new Object[7]; //matriz para el addRow del modelo de la tabla

            try {
                this.contAlumnos.cargarAlumnosAsignatura(asignatura);
            } catch (SQLException e) {
                //AuxiliarMethods.showWarning(e.toString());
                System.out.println(e.toString());
            }

            for (Alumno alumno : this.contAlumnos.getAlumnosAsignatura().get(asignatura)) {
                row[0] = alumno.getApellidos();
                row[1] = alumno.getNombre();
                ArrayList<Double> notasFinales = alumno.getNotasFinales().get(asignatura);

                if (notasFinales == null || notasFinales.size() < 4) {
                    row[2] = null;
                    row[3] = null;
                    row[4] = null;
                    row[5] = null;
                    row[6] = null;
                } else {
                    if (notasFinales.get(0) == 0.0) {
                        row[2] = null;
                    } else {
                        row[2] = notasFinales.get(0);
                    }
                    row[2] = ((notasFinales.get(0) != 0.0) ? notasFinales.get(0) : null); //condicion ? cosa_true : cosa_false
                    row[3] = ((notasFinales.get(1) != 0.0) ? notasFinales.get(1) : null); //si la primera nota final es 0.0 metes en row[2] la nota final, si no, null
                    row[4] = ((notasFinales.get(2) != 0.0) ? notasFinales.get(2) : null);
                    row[6] = ((notasFinales.get(3) != 0.0) ? notasFinales.get(3) : null);
                }

                model.addRow(row);
            }
        }
        calcularNotasMedias();
        añadirFilaMedias();
        //AuxiliarMethods.ajustarColumnasTabla(tabla); //recalculamos el tamaño de las columnas a su contenido
    }

    
    /**
     * Calcula las notas medias ed los trimestres y las añade en la columna de notas medias
     */
    public void calcularNotasMedias() {
        int i, j;
        double media = 0;

        for (i = 0; i < tabla.getRowCount(); i++) {
            for (j = 2; j < 5; j++) {
                media += ((tabla.getValueAt(i, j) != null) ? Double.parseDouble(tabla.getValueAt(i, j).toString()) : 0.0);
                if (media / 3 != 0) {
                    tabla.setValueAt(Double.parseDouble(formatter.format(media / 3)), i, 5);
                }
            }
            media = 0;
        }
    }

    /**
     * Obtiene la estadísticas del controlador de alumnos y hace los diagramas de quesitos
     */
    public void obtenerEstadísticas() {
        panelGraficos.removeAll();
        int aprobados1 = 0; //aprobados 1º Trimestre
        int aprobados2 = 0; //aprobados 2º Trimestre
        int aprobados3 = 0; //aprobados 3º Trimestre
        int aprobadosCurso = 0; //aprobados en la asignatura
        int numAlumnos = contAlumnos.getAlumnosAsignatura().get(getAsignatura()).size();

        for (Alumno a : contAlumnos.getAlumnosAsignatura().get(getAsignatura())) {
            if (a.getNotasFinales().get(getAsignatura()) != null && !a.getNotasFinales().get(getAsignatura()).isEmpty() && a.getNotasFinales().get(getAsignatura()).get(0) >= 5) {
                aprobados1++;
            }
            if (a.getNotasFinales().get(getAsignatura()) != null && !a.getNotasFinales().get(getAsignatura()).isEmpty() && a.getNotasFinales().get(getAsignatura()).get(1) >= 5) {
                aprobados2++;
            }
            if (a.getNotasFinales().get(getAsignatura()) != null && !a.getNotasFinales().get(getAsignatura()).isEmpty() && a.getNotasFinales().get(getAsignatura()).get(2) >= 5) {
                aprobados3++;
            }
            if (a.getNotasFinales().get(getAsignatura()) != null && !a.getNotasFinales().get(getAsignatura()).isEmpty() && a.getNotasFinales().get(getAsignatura()).get(3) >= 5) {
                aprobadosCurso++;
            }
        }

        lblNumAp1.setText(String.valueOf(aprobados1) + " (" + formatter.format(aprobados1 / (float) numAlumnos * 100) + "% del total de alumnos)");
        lblNumAp2.setText(String.valueOf(aprobados2) + " (" + formatter.format(aprobados2 / (float) numAlumnos * 100) + "% del total de alumnos)");
        lblNumAp3.setText(String.valueOf(aprobados3) + " (" + formatter.format(aprobados3 / (float) numAlumnos * 100) + "% del total de alumnos)");
        lblNumApTotal.setText(String.valueOf(aprobadosCurso) + " (" + formatter.format(aprobadosCurso / (float) numAlumnos * 100) + "% del total de alumnos)");
        
        HashMap<String, Float> datos = new HashMap<>();
        datos.put("1º Trimestre", aprobados1 / (float) numAlumnos * 100);
        datos.put("2º Trimestre", aprobados2 / (float) numAlumnos * 100);
        datos.put("3º Trimestre", aprobados3 / (float) numAlumnos * 100);
        
        HashMap<String, Float> datos2 = new HashMap<>();
        datos2.put("Aprobados", aprobadosCurso / (float) numAlumnos * 100);
        datos2.put("Suspensos", 100 - aprobadosCurso / (float) numAlumnos * 100);
        
        Chart chart = new Chart("% de aprobados por trimestre", datos, panelGraficos);
        chart.paint(panelGraficos);
        Chart chart2 = new Chart("% de aprobados en el curso", datos2, panelGraficos);
        chart2.paint(panelGraficos);

              
    }

    /**
     * Añade la fila de las medias de todos los alumnos al final de la tabla
     */
    public void añadirFilaMedias() {
        Object[] row = new Object[7];

        double media1 = 0.0;
        double media2 = 0.0;
        double media3 = 0.0;
        double media4 = 0.0;
        double media5 = 0.0;
        int contador1 = 0;
        int contador2 = 0;
        int contador3 = 0;
        int contador4 = 0;
        int contador5 = 0;

        row[1] = "MEDIAS TOTALES:";
        for (int i = 0; i < contAlumnos.getAlumnosAsignatura().get(getAsignatura()).size(); i++) {
            Alumno a = contAlumnos.getAlumnosAsignatura().get(getAsignatura()).get(i);
            if (a.getNotasFinales().get(getAsignatura()) != null && !a.getNotasFinales().get(getAsignatura()).isEmpty()) {
                //condicion ? cosa_true : cosa_false
                if (a.getNotasFinales().get(getAsignatura()).get(0) == null || a.getNotasFinales().get(getAsignatura()).get(0) == 0) {
                    media1 += 0;
                } else {
                    media1 += a.getNotasFinales().get(getAsignatura()).get(0);
                    contador1++;
                }

                if (a.getNotasFinales().get(getAsignatura()).get(1) == null || a.getNotasFinales().get(getAsignatura()).get(1) == 0) {
                    media2 += 0;
                } else {
                    media2 += a.getNotasFinales().get(getAsignatura()).get(1);
                    contador2++;
                }

                if (a.getNotasFinales().get(getAsignatura()).get(2) == null || a.getNotasFinales().get(getAsignatura()).get(2) == 0) {
                    media3 += 0;
                } else {
                    media3 += a.getNotasFinales().get(getAsignatura()).get(2);
                    contador3++;
                }

                if (model.getValueAt(i, 5) == null) {
                    media4 += 0;
                } else {
                    media4 += (double) model.getValueAt(i, 5);
                    contador4++;
                }

                if (a.getNotasFinales().get(getAsignatura()).get(3) == null || a.getNotasFinales().get(getAsignatura()).get(3) == 0) {
                    media5 += 0;
                } else {
                    media5 += a.getNotasFinales().get(getAsignatura()).get(3);
                    contador5++;
                }
            }
        }

        try {
            row[2] = Double.parseDouble(formatter.format(media1 / contador1));
            row[3] = Double.parseDouble(formatter.format(media2 / contador2));
            row[4] = Double.parseDouble(formatter.format(media3 / contador3));
            row[5] = Double.parseDouble(formatter.format(media4 / contador4));
            row[6] = Double.parseDouble(formatter.format(media5 / contador5));
        } catch (NumberFormatException e) { //esto ocurre si nadie tiene notas finales
            row[2] = 0;
            row[3] = 0;
            row[4] = 0;
            row[5] = 0;
            row[6] = 0;
        }

        model.addRow(new Object[7]);
        model.addRow(row); //añadimos fila extra en blanco para separar de la nueva
    }

    /**
     * Carga los tooltips de las columnas de la tabla creando un objeto ToolTipHeader
     */
    private void cargarToolTipsTabla() {
        JTableHeader header = tabla.getTableHeader();

        ToolTipHeader tips = new ToolTipHeader();

        TableColumn col2 = tabla.getColumnModel().getColumn(2); //columna trimestre 1
        TableColumn col3 = tabla.getColumnModel().getColumn(3); //columna trimestre 2
        TableColumn col4 = tabla.getColumnModel().getColumn(4); //columna trimestre 3
        tips.setToolTip(col2, "Fecha evaluación: 7/12/2021");
        tips.setToolTip(col3, "Fecha evaluación: 18/03/2022");
        tips.setToolTip(col4, "Fecha evaluación: 27/06/2022");
        header.addMouseMotionListener(tips);
    }

    /**
     * Limpia las selecciones y las estadísticas
     */
    private void limpiarSelecciones() {
        model.setRowCount(0);
        lblNumAp1.setText("-");
        lblNumAp2.setText("-");
        lblNumAp3.setText("-");
        lblNumApTotal.setText("-");
        asignaturasGrupo.clearSelection();
    }
    
    /**
     * El comportamiento del botón cerrar
     */
    public void setComportamientoBotonCerrar(){
        this.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent evt) {
            String titulo = "¿Quiere cerrar sesión?";
            int resp = AuxiliarMethods.showCloseConfirmation(titulo);

            if (resp == JOptionPane.YES_OPTION) {
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            } else {
                setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        }
    });
    }

    /**
     * Ejecución del look and feel deseado por el usuario en el login
     */
    private void ejecutarOpciones() {
        List<Component> components = AuxiliarMethods.getAllComponents(this); //recoge todos los componentes del frame

        for (Component c : components) {
            c.setFont(new Font(c.getFont().getName(), c.getFont().getStyle(), opciones.getTamañoLetra()));
            if (opciones.getOscuro() && c.getClass() != JButton.class && c.getClass() != JTextField.class) {
                c.setForeground(Color.LIGHT_GRAY);
            }
        }

        lblCentro.setFont(new Font(lblCentro.getFont().getName(), Font.ITALIC, opciones.getTamañoLetra() + 25));
        ventormentaPicture.setSize(ventormentaPicture.getWidth() + opciones.getTamañoLetra(), ventormentaPicture.getHeight() + opciones.getTamañoLetra());

        //cambiar el fondo de los containers
        Color colorBackground = opciones.getColorBackground();
        jScrollPane1.setBackground(colorBackground);
        jScrollPane1.setForeground(colorBackground);
        jPanel2.setBackground(colorBackground);
        rdbtnc1.setBackground(colorBackground);
        rdbtnc2.setBackground(colorBackground);
        rdbtnc3.setBackground(colorBackground);
        rdbtncon1A.setBackground(colorBackground);
        rdbtnlen3A.setBackground(colorBackground);
        rdbtnmat3A.setBackground(colorBackground);
        rdbtnrel4A.setBackground(colorBackground);
        panelEstadisticas.setBackground(colorBackground);
        jScrollPane1.setBackground(colorBackground);
        tabla.setBackground(colorBackground);

        //terminamos cambiando a mano los TitledBorder de los paneles que los tienen
        if (opciones.getOscuro()) {
            TitledBorder titledBorder = (TitledBorder) panelEstadisticas.getBorder();
            titledBorder.setTitleColor(Color.LIGHT_GRAY);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddAlumno;
    private javax.swing.JButton btnCalificar;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnEditarUsuario;
    private javax.swing.JButton btnNuevaTarea;
    private javax.swing.JLabel fecha;
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
    private javax.swing.Box.Filler filler22;
    private javax.swing.Box.Filler filler23;
    private javax.swing.Box.Filler filler24;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JLabel hora;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAprob1;
    private javax.swing.JLabel lblAprob2;
    private javax.swing.JLabel lblAprob3;
    private javax.swing.JLabel lblAprobTotal;
    private javax.swing.JLabel lblAsignaturas;
    public javax.swing.JLabel lblBienvenida;
    private javax.swing.JLabel lblCentro;
    private javax.swing.JLabel lblCursos;
    public javax.swing.JLabel lblFotoMaestro;
    private javax.swing.JLabel lblNumAp1;
    private javax.swing.JLabel lblNumAp2;
    private javax.swing.JLabel lblNumAp3;
    private javax.swing.JLabel lblNumApTotal;
    private javax.swing.JLabel nombreAsignatura;
    private javax.swing.JPanel panelEstadisticas;
    private javax.swing.JPanel panelGraficos;
    private javax.swing.JRadioButton rdbtnc1;
    private javax.swing.JRadioButton rdbtnc2;
    private javax.swing.JRadioButton rdbtnc3;
    private javax.swing.JRadioButton rdbtncon1A;
    private javax.swing.JRadioButton rdbtnlen3A;
    private javax.swing.JRadioButton rdbtnmat3A;
    private javax.swing.JRadioButton rdbtnrel4A;
    public javax.swing.JTable tabla;
    private javax.swing.JLabel ventormentaPicture;
    // End of variables declaration//GEN-END:variables

}
