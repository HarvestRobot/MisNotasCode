/*
 * Lucia Calzado Piedrabuena
 * Trabajo de Fin de Grado - Grado en Ingenier�a Inform�tica
 * Universidad de Castilla-La Mancha
 */
package appinterface;

import auxiliar.AuxiliarMethods;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import objects.Alumno;
import controladores.ControladorAlumno;
import controladores.ControladorPrueba;
import java.awt.Font;
import java.util.HashMap;
import javax.swing.JFrame;

/**
 *
 * @author lucia
 */
public class InformeAlumnoWindow extends javax.swing.JFrame {

    int asignatura;
    ControladorAlumno contAlumnos;
    ControladorPrueba contPruebas;
    HashMap<String, Integer> pruebaConID = new HashMap<>(); //para almacenar las pruebas con sus IDs
    Alumno alumno;
    int index = 0; //indice para navegar entre los alumnos
    int tamañoLetra;

    /**
     * Creates new form InformeAlumnoWindow
     *
     * @param alumno
     * @param asignatura
     * @param contAlumnos
     * @param tamañoLetra
     */
    public InformeAlumnoWindow(Alumno alumno, int asignatura, ControladorAlumno contAlumnos, int tamañoLetra, JFrame mainWindow) {
        this.asignatura = asignatura;
        this.contAlumnos = contAlumnos;
        this.contPruebas = new ControladorPrueba();
        this.alumno = alumno;
        this.tamañoLetra = tamañoLetra;
        initComponents();

        try {
            this.contPruebas.cargarPruebasAsignatura(asignatura);
        } catch (SQLException e) {
            AuxiliarMethods.showWarning(e.toString());
        }
        
        tabla1.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        tabla2.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        tabla3.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

        if (tamañoLetra != 0) {
            cambiarTamañoLetra();
        }

        cargarCampos(alumno);

    }

    private void cargarCampos(Alumno alumno) {
        lblApellidos.setText(alumno.getApellidos());
        lblNombre.setText(alumno.getNombre());
        lblDNI.setText(alumno.getDni());
        lblImagen.setIcon(new ImageIcon("assets/person.png"));
        //Cargar tablas
        cargarTabla(tabla1, alumno, 1);
        cargarTabla(tabla2, alumno, 2);
        cargarTabla(tabla3, alumno, 3);

        //Ajustar columnas
        AuxiliarMethods.ajustarColumnasTabla(tabla2);
        AuxiliarMethods.ajustarColumnasTabla(tabla3);
        AuxiliarMethods.ajustarColumnasTabla(tabla1);

        if (!alumno.getNotasFinales().isEmpty()) { //si tiene puestas las notas finales
            final1.setText(alumno.getNotasFinales().get(asignatura).get(1).toString());
            final2.setText(alumno.getNotasFinales().get(asignatura).get(2).toString());
            final3.setText(alumno.getNotasFinales().get(asignatura).get(3).toString());
            double mediaAsig = Double.parseDouble(final1.getText())
                    + Double.parseDouble(final2.getText())
                    + Double.parseDouble(final3.getText()) / 3.0;
            NumberFormat formatter = new DecimalFormat("#0.00");
            lblMediaAsignaturaN.setText(formatter.format(mediaAsig));
            //Cargar medias
            lblMedia1N.setText(calcularMedia(tabla1));
            lblMedia2N.setText(calcularMedia(tabla2));
            lblMedia3N.setText(calcularMedia(tabla3));
        } else { //si no tiene notas finales todavía
            final1.setText("");
            final2.setText("");
            final3.setText("");
            lblMediaAsignaturaN.setText("N/A");
        }
    }

    public void cargarTabla(JTable tabla, Alumno alumno, int trimestre) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.setRowCount(0);
        int i;
        for (i = 0; i < contPruebas.getPruebasAsignatura().get(asignatura).size(); i++) {
            Object[] row = new Object[2];
            if (contPruebas.getPruebasAsignatura().get(asignatura).get(i).getTrimestre() == trimestre) {
                row[0] = contPruebas.getPruebasAsignatura().get(asignatura).get(i).getNombrePrueba();
                row[1] = alumno.getNotas().get(contPruebas.getPruebasAsignatura().get(asignatura).get(i).getIdPrueba());
                model.addRow(row);
                pruebaConID.put(contPruebas.getPruebasAsignatura().get(asignatura).get(i).getNombrePrueba(), contPruebas.getPruebasAsignatura().get(asignatura).get(i).getIdPrueba());
            }
        }
    }

    public String calcularMedia(JTable tabla) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        int i;
        int contadorMedia = 0; //las filas que cuentan para la media
        NumberFormat formatter = new DecimalFormat("#0.00");
        double media = 0.0;

        for (i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 1) != null) {
                media += Double.parseDouble(model.getValueAt(i, 1).toString());
                contadorMedia++;
            }
        }
        return formatter.format(media / contadorMedia);
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
        lblApellidos = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblImagen = new javax.swing.JLabel();
        lblDNI = new javax.swing.JLabel();
        panel1 = new javax.swing.JPanel();
        lblMedia1 = new javax.swing.JLabel();
        lblFinal1 = new javax.swing.JLabel();
        tabla1 = new javax.swing.JTable();
        final1 = new javax.swing.JTextField();
        lblMedia1N = new javax.swing.JLabel();
        filler29 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        filler30 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        filler31 = new javax.swing.Box.Filler(new java.awt.Dimension(350, 0), new java.awt.Dimension(350, 0), new java.awt.Dimension(350, 32767));
        filler32 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        filler36 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));
        filler40 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        filler11 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        filler16 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        lblPruebas1 = new javax.swing.JLabel();
        filler61 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 30), new java.awt.Dimension(0, 30), new java.awt.Dimension(32767, 30));
        panel2 = new javax.swing.JPanel();
        lblMedia2 = new javax.swing.JLabel();
        lblFinal2 = new javax.swing.JLabel();
        tabla2 = new javax.swing.JTable();
        final2 = new javax.swing.JTextField();
        lblMedia2N = new javax.swing.JLabel();
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        filler12 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        filler25 = new javax.swing.Box.Filler(new java.awt.Dimension(350, 0), new java.awt.Dimension(350, 0), new java.awt.Dimension(350, 32767));
        filler15 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        filler34 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));
        filler38 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        filler24 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 15), new java.awt.Dimension(0, 15), new java.awt.Dimension(32767, 15));
        filler43 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        filler44 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        lblPruebas2 = new javax.swing.JLabel();
        panel3 = new javax.swing.JPanel();
        lblMedia3 = new javax.swing.JLabel();
        lblFinal3 = new javax.swing.JLabel();
        tabla3 = new javax.swing.JTable();
        final3 = new javax.swing.JTextField();
        lblMedia3N = new javax.swing.JLabel();
        filler26 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        filler27 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        filler28 = new javax.swing.Box.Filler(new java.awt.Dimension(350, 0), new java.awt.Dimension(350, 0), new java.awt.Dimension(350, 32767));
        filler33 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        filler35 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));
        filler41 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        filler42 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 15), new java.awt.Dimension(0, 15), new java.awt.Dimension(32767, 15));
        filler45 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        filler46 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        lblPruebas3 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(15, 15), new java.awt.Dimension(15, 15), new java.awt.Dimension(15, 15));
        filler13 = new javax.swing.Box.Filler(new java.awt.Dimension(25, 25), new java.awt.Dimension(25, 25), new java.awt.Dimension(25, 25));
        filler17 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        btnSiguiente = new javax.swing.JButton();
        btnAnterior = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblFinalAsig = new javax.swing.JLabel();
        finalAsig = new javax.swing.JTextField();
        lblMediaAsignaturaN = new javax.swing.JLabel();
        lblMediaAsignatura = new javax.swing.JLabel();
        filler47 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        filler51 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        filler52 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        filler53 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        filler54 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        filler55 = new javax.swing.Box.Filler(new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 32767));
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(25, 0), new java.awt.Dimension(25, 0), new java.awt.Dimension(25, 32767));
        filler18 = new javax.swing.Box.Filler(new java.awt.Dimension(25, 0), new java.awt.Dimension(25, 0), new java.awt.Dimension(25, 32767));
        filler19 = new javax.swing.Box.Filler(new java.awt.Dimension(300, 0), new java.awt.Dimension(300, 0), new java.awt.Dimension(300, 32767));
        filler20 = new javax.swing.Box.Filler(new java.awt.Dimension(300, 0), new java.awt.Dimension(300, 0), new java.awt.Dimension(300, 32767));
        filler21 = new javax.swing.Box.Filler(new java.awt.Dimension(300, 0), new java.awt.Dimension(300, 0), new java.awt.Dimension(300, 32767));
        filler23 = new javax.swing.Box.Filler(new java.awt.Dimension(40, 0), new java.awt.Dimension(40, 0), new java.awt.Dimension(40, 32767));
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 50), new java.awt.Dimension(0, 50), new java.awt.Dimension(32767, 50));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 40), new java.awt.Dimension(0, 40), new java.awt.Dimension(32767, 40));
        filler37 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        filler39 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 70), new java.awt.Dimension(0, 70), new java.awt.Dimension(32767, 70));
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(80, 0), new java.awt.Dimension(80, 0), new java.awt.Dimension(80, 32767));
        filler14 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 30), new java.awt.Dimension(0, 30), new java.awt.Dimension(32767, 30));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(40, 0), new java.awt.Dimension(40, 0), new java.awt.Dimension(40, 32767));
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(40, 0), new java.awt.Dimension(40, 0), new java.awt.Dimension(40, 32767));
        txtComentarios = new javax.swing.JTextArea();
        filler48 = new javax.swing.Box.Filler(new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 32767));
        filler50 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 100), new java.awt.Dimension(0, 100), new java.awt.Dimension(32767, 100));
        filler57 = new javax.swing.Box.Filler(new java.awt.Dimension(70, 0), new java.awt.Dimension(70, 0), new java.awt.Dimension(70, 32767));
        filler58 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 40), new java.awt.Dimension(0, 40), new java.awt.Dimension(32767, 40));
        lblComentarios = new javax.swing.JLabel();
        filler22 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        filler49 = new javax.swing.Box.Filler(new java.awt.Dimension(40, 0), new java.awt.Dimension(40, 0), new java.awt.Dimension(40, 32767));
        filler59 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 200), new java.awt.Dimension(0, 200), new java.awt.Dimension(32767, 200));
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 70), new java.awt.Dimension(0, 70), new java.awt.Dimension(32767, 70));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Informe del alumno");
        setBounds(new java.awt.Rectangle(200, 200, 0, 0));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        lblTitulo.setText("Informe del alumno");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(lblTitulo, gridBagConstraints);

        lblApellidos.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblApellidos.setText("apellidos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(lblApellidos, gridBagConstraints);

        lblNombre.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblNombre.setText("nombre");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(lblNombre, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        getContentPane().add(lblImagen, gridBagConstraints);

        lblDNI.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblDNI.setText("DNI");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(lblDNI, gridBagConstraints);

        panel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "1º Trimestre", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N
        panel1.setLayout(new java.awt.GridBagLayout());

        lblMedia1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMedia1.setText("Nota media:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        panel1.add(lblMedia1, gridBagConstraints);

        lblFinal1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblFinal1.setText("Nota final:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        panel1.add(lblFinal1, gridBagConstraints);

        tabla1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Prueba", "Nota"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 12;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.ipadx = 120;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        panel1.add(tabla1, gridBagConstraints);
        if (tabla1.getColumnModel().getColumnCount() > 0) {
            tabla1.getColumnModel().getColumn(1).setResizable(false);
        }

        final1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        final1.setText("F1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panel1.add(final1, gridBagConstraints);

        lblMedia1N.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMedia1N.setText("numMedia1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        panel1.add(lblMedia1N, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridheight = 3;
        panel1.add(filler29, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 4;
        panel1.add(filler30, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 12;
        panel1.add(filler31, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridheight = 3;
        panel1.add(filler32, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 12;
        panel1.add(filler36, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 10;
        panel1.add(filler40, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 11;
        panel1.add(filler11, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 13;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 13;
        panel1.add(filler16, gridBagConstraints);

        lblPruebas1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblPruebas1.setText("Pruebas y exámenes");
        lblPruebas1.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        panel1.add(lblPruebas1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 11;
        panel1.add(filler61, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.gridheight = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(panel1, gridBagConstraints);

        panel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "2º Trimestre", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N
        panel2.setLayout(new java.awt.GridBagLayout());

        lblMedia2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMedia2.setText("Nota media:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        panel2.add(lblMedia2, gridBagConstraints);

        lblFinal2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblFinal2.setText("Nota final:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        panel2.add(lblFinal2, gridBagConstraints);

        tabla2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabla2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Prueba", "Nota"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.ipadx = 120;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        panel2.add(tabla2, gridBagConstraints);
        if (tabla2.getColumnModel().getColumnCount() > 0) {
            tabla2.getColumnModel().getColumn(1).setResizable(false);
        }

        final2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        final2.setText("F2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panel2.add(final2, gridBagConstraints);

        lblMedia2N.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMedia2N.setText("numMedia2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        panel2.add(lblMedia2N, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridheight = 3;
        panel2.add(filler9, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 9;
        panel2.add(filler12, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 10;
        panel2.add(filler25, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridheight = 3;
        panel2.add(filler15, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 10;
        panel2.add(filler34, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 10;
        panel2.add(filler38, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 10;
        panel2.add(filler24, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 12;
        panel2.add(filler43, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 12;
        panel2.add(filler44, gridBagConstraints);

        lblPruebas2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblPruebas2.setText("Pruebas y exámenes");
        lblPruebas2.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        panel2.add(lblPruebas2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 16;
        gridBagConstraints.gridheight = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(panel2, gridBagConstraints);

        panel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "3º Trimestre", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N
        panel3.setToolTipText("");
        panel3.setLayout(new java.awt.GridBagLayout());

        lblMedia3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMedia3.setText("Nota media:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        panel3.add(lblMedia3, gridBagConstraints);

        lblFinal3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblFinal3.setText("Nota final:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        panel3.add(lblFinal3, gridBagConstraints);

        tabla3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabla3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Prueba", "Nota"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.ipadx = 120;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        panel3.add(tabla3, gridBagConstraints);
        if (tabla3.getColumnModel().getColumnCount() > 0) {
            tabla3.getColumnModel().getColumn(1).setResizable(false);
        }

        final3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        final3.setText("F3");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panel3.add(final3, gridBagConstraints);

        lblMedia3N.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMedia3N.setText("numMedia3");
        lblMedia3N.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        panel3.add(lblMedia3N, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridheight = 3;
        panel3.add(filler26, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 4;
        panel3.add(filler27, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 10;
        panel3.add(filler28, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        panel3.add(filler33, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 10;
        panel3.add(filler35, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 10;
        panel3.add(filler41, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 10;
        panel3.add(filler42, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 12;
        panel3.add(filler45, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 10;
        panel3.add(filler46, gridBagConstraints);

        lblPruebas3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblPruebas3.setText("Pruebas y exámenes");
        lblPruebas3.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        panel3.add(lblPruebas3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 29;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(panel3, gridBagConstraints);

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 31;
        gridBagConstraints.gridy = 31;
        getContentPane().add(btnCancelar, gridBagConstraints);

        btnGuardar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 32;
        gridBagConstraints.gridy = 31;
        gridBagConstraints.gridwidth = 3;
        getContentPane().add(btnGuardar, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        getContentPane().add(filler6, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 19;
        getContentPane().add(filler13, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 23;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.gridheight = 5;
        getContentPane().add(filler17, gridBagConstraints);

        btnSiguiente.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnSiguiente.setText(">");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 33;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.gridheight = 3;
        getContentPane().add(btnSiguiente, gridBagConstraints);

        btnAnterior.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnAnterior.setText("<");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.gridheight = 3;
        getContentPane().add(btnAnterior, gridBagConstraints);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Notas finales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        jPanel1.setLayout(new java.awt.GridBagLayout());

        lblFinalAsig.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblFinalAsig.setText("Nota final de la asignatura:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel1.add(lblFinalAsig, gridBagConstraints);

        finalAsig.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        finalAsig.setText("FAsig");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(finalAsig, gridBagConstraints);

        lblMediaAsignaturaN.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMediaAsignaturaN.setText("mediaAs");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(lblMediaAsignaturaN, gridBagConstraints);

        lblMediaAsignatura.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMediaAsignatura.setText("Nota media de la asignatura:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel1.add(lblMediaAsignatura, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 6;
        jPanel1.add(filler47, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 3;
        jPanel1.add(filler51, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        jPanel1.add(filler52, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        jPanel1.add(filler53, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        jPanel1.add(filler54, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        jPanel1.add(filler55, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 4;
        jPanel1.add(filler5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(jPanel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 19;
        getContentPane().add(filler10, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 32;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 24;
        getContentPane().add(filler18, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 19;
        gridBagConstraints.gridwidth = 5;
        getContentPane().add(filler19, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 19;
        gridBagConstraints.gridwidth = 13;
        getContentPane().add(filler20, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 29;
        gridBagConstraints.gridy = 19;
        gridBagConstraints.gridwidth = 3;
        getContentPane().add(filler21, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 26;
        getContentPane().add(filler23, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 26;
        gridBagConstraints.gridheight = 2;
        getContentPane().add(filler1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 32;
        gridBagConstraints.gridwidth = 35;
        getContentPane().add(filler3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 3;
        getContentPane().add(filler37, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.gridwidth = 24;
        getContentPane().add(filler39, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 35;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 32;
        getContentPane().add(filler4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 35;
        getContentPane().add(filler14, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridheight = 15;
        getContentPane().add(filler2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 28;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridheight = 15;
        getContentPane().add(filler7, gridBagConstraints);

        txtComentarios.setColumns(20);
        txtComentarios.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtComentarios.setRows(5);
        txtComentarios.setBorder(null);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.gridheight = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(txtComentarios, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.gridheight = 6;
        getContentPane().add(filler48, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 29;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.gridheight = 7;
        getContentPane().add(filler50, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 33;
        getContentPane().add(filler57, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 8;
        getContentPane().add(filler58, gridBagConstraints);

        lblComentarios.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblComentarios.setText("Comentarios sobre el alumno:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        getContentPane().add(lblComentarios, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 21;
        getContentPane().add(filler22, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 34;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 31;
        getContentPane().add(filler49, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.gridheight = 7;
        getContentPane().add(filler59, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 29;
        gridBagConstraints.gridwidth = 21;
        getContentPane().add(filler8, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        // TODO add your handling code here:
        if (alumno.getPosicion() != contAlumnos.getAlumnosAsignatura().get(asignatura).size() - 1) {
            //guardar campos
            cargarCampos(contAlumnos.getAlumnosAsignatura().get(asignatura).get(alumno.getPosicion() + 1));
            this.alumno = contAlumnos.getAlumnosAsignatura().get(asignatura).get(alumno.getPosicion() + 1);
        }
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        // TODO add your handling code here:
        if (alumno.getPosicion() != 0) {
            //guardar campos
            cargarCampos(contAlumnos.getAlumnosAsignatura().get(asignatura).get(alumno.getPosicion() - 1));
            this.alumno = contAlumnos.getAlumnosAsignatura().get(asignatura).get(alumno.getPosicion() - 1);
        }
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        
        //Guardar notas
        boolean update;
        DefaultTableModel model1 = (DefaultTableModel) tabla1.getModel();
        DefaultTableModel model2 = (DefaultTableModel) tabla2.getModel();
        DefaultTableModel model3 = (DefaultTableModel) tabla3.getModel();
        HashMap<Integer, Double> notas = contAlumnos.getAlumnosAsignatura().get(asignatura).get(alumno.getIdAlumno()).getNotas();
        updateNotas(model1, notas);
        updateNotas(model2, notas);
        updateNotas(model3, notas);
        
        //Guardar notas finales
        ArrayList<Double> notasFinales = contAlumnos.getAlumnosAsignatura().get(asignatura).get(alumno.getIdAlumno()).getNotasFinales().get(asignatura);
        if (notasFinales.isEmpty()){ //si no tiene todavía notas finales
            if(final1.getText().equals("")){
                notasFinales.add(0.0);
            } else {
                notasFinales.add(Double.parseDouble(final1.getText()));
            }
            
            if(final2.getText().equals("")){
                notasFinales.add(0.0);
            } else {
                notasFinales.add(Double.parseDouble(final2.getText()));
            }
            
            if(final3.getText().equals("")){
                notasFinales.add(0.0);
            } else {
                notasFinales.add(Double.parseDouble(final3.getText()));
            }
            
            if(finalAsig.getText().equals("")){
                notasFinales.add(0.0);
            } else {
                notasFinales.add(Double.parseDouble(finalAsig.getText()));
            }
  
        } else {
            if(final1.getText().equals("")){
                notasFinales.add(0.0);
            } else {
                notasFinales.add(Double.parseDouble(final1.getText()));
            }
            
            if(final2.getText().equals("")){
                notasFinales.add(0.0);
            } else {
                notasFinales.add(Double.parseDouble(final2.getText()));
            }
            
            if(final3.getText().equals("")){
                notasFinales.add(0.0);
            } else {
                notasFinales.add(Double.parseDouble(final3.getText()));
            }
            
            if(finalAsig.getText().equals("")){
                notasFinales.add(0.0);
            } else {
                notasFinales.add(Double.parseDouble(finalAsig.getText()));
            }
        }
        
        try {
            contAlumnos.updateNotasFinales(alumno, asignatura);
        } catch (SQLException e){
            AuxiliarMethods.showWarning("Ha ocurrido un error al guardar las notas de los alumnos.\nMás información: "+e.toString());
        }
        
        this.dispose();
        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void updateNotas(DefaultTableModel modelo, HashMap notas) {
        int i;
        boolean update = false; //para saber si hay que hacer UPDATE o INSERT en la BD
        for (i = 0; i < modelo.getRowCount(); i++) {
            int idPrueba = pruebaConID.get((modelo.getValueAt(i, 0)).toString());
            if (modelo.getValueAt(i, 1) == null) { //si no hay nada en la tabla, se pone nada en la nota, pero se crea
                notas.put(idPrueba, null);
            } else if (notas.get(idPrueba) == null) { //si por el contrario, la prueba ya existe pero no tiene nada, se le pone esta nota nueva
                notas.put(idPrueba, Double.parseDouble(modelo.getValueAt(i, 1).toString()));
                update = false;
            } else { //en este caso, ya había tanto prueba como nota, así que se reemplaza la anterior
                notas.replace(idPrueba, Double.parseDouble(modelo.getValueAt(i, 1).toString()));
                update = true;
            }
            try {
                contAlumnos.updateNotas(asignatura, idPrueba, alumno, update);
            } catch (SQLException e) {
                //AuxiliarMethods.showWarning(e.toString());
                System.out.println(e.toString());
            }
        }

    }

    private void cambiarTamañoLetra() {
        lblApellidos.setFont(new Font(lblApellidos.getFont().getName(), Font.PLAIN, tamañoLetra));
        lblDNI.setFont(new Font(lblDNI.getFont().getName(), Font.PLAIN, tamañoLetra));
        lblFinal2.setFont(new Font(lblFinal2.getFont().getName(), Font.PLAIN, tamañoLetra));
        lblFinal2.setFont(new Font(lblFinal2.getFont().getName(), Font.PLAIN, tamañoLetra));
        lblFinal3.setFont(new Font(lblFinal3.getFont().getName(), Font.PLAIN, tamañoLetra));
        lblMedia2.setFont(new Font(lblMedia2.getFont().getName(), Font.PLAIN, tamañoLetra));
        lblMedia2N.setFont(new Font(lblMedia2N.getFont().getName(), Font.PLAIN, tamañoLetra));
        lblMedia2.setFont(new Font(lblMedia2.getFont().getName(), Font.PLAIN, tamañoLetra));
        lblMedia2N.setFont(new Font(lblMedia2N.getFont().getName(), Font.PLAIN, tamañoLetra));
        lblMedia3.setFont(new Font(lblMedia3.getFont().getName(), Font.PLAIN, tamañoLetra));
        lblMedia3N.setFont(new Font(lblMedia3N.getFont().getName(), Font.PLAIN, tamañoLetra));
        lblMediaAsignatura.setFont(new Font(lblMediaAsignatura.getFont().getName(), Font.PLAIN, tamañoLetra));
        lblMediaAsignaturaN.setFont(new Font(lblMediaAsignaturaN.getFont().getName(), Font.PLAIN, tamañoLetra));
        lblNombre.setFont(new Font(lblNombre.getFont().getName(), Font.PLAIN, tamañoLetra));
        lblFinalAsig.setFont(new Font(lblFinalAsig.getFont().getName(), Font.PLAIN, tamañoLetra));
        lblTitulo.setFont(new Font(lblApellidos.getFont().getName(), Font.PLAIN, tamañoLetra + lblTitulo.getFont().getSize()));

        btnAnterior.setFont(new Font(btnAnterior.getFont().getName(), Font.PLAIN, tamañoLetra));
        btnCancelar.setFont(new Font(btnCancelar.getFont().getName(), Font.PLAIN, tamañoLetra));
        btnGuardar.setFont(new Font(btnGuardar.getFont().getName(), Font.PLAIN, tamañoLetra));
        btnSiguiente.setFont(new Font(btnSiguiente.getFont().getName(), Font.PLAIN, tamañoLetra));

        tabla2.setFont(new Font(tabla2.getFont().getName(), Font.PLAIN, tamañoLetra));
        tabla3.setFont(new Font(tabla3.getFont().getName(), Font.PLAIN, tamañoLetra));
        tabla1.setFont(new Font(tabla1.getFont().getName(), Font.PLAIN, tamañoLetra));

        final2.setFont(new Font(final2.getFont().getName(), Font.PLAIN, tamañoLetra));
        final2.setFont(new Font(final2.getFont().getName(), Font.PLAIN, tamañoLetra));
        final3.setFont(new Font(final3.getFont().getName(), Font.PLAIN, tamañoLetra));
        finalAsig.setFont(new Font(final3.getFont().getName(), Font.PLAIN, tamañoLetra));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSiguiente;
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
    private javax.swing.Box.Filler filler21;
    private javax.swing.Box.Filler filler22;
    private javax.swing.Box.Filler filler23;
    private javax.swing.Box.Filler filler24;
    private javax.swing.Box.Filler filler25;
    private javax.swing.Box.Filler filler26;
    private javax.swing.Box.Filler filler27;
    private javax.swing.Box.Filler filler28;
    private javax.swing.Box.Filler filler29;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler30;
    private javax.swing.Box.Filler filler31;
    private javax.swing.Box.Filler filler32;
    private javax.swing.Box.Filler filler33;
    private javax.swing.Box.Filler filler34;
    private javax.swing.Box.Filler filler35;
    private javax.swing.Box.Filler filler36;
    private javax.swing.Box.Filler filler37;
    private javax.swing.Box.Filler filler38;
    private javax.swing.Box.Filler filler39;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler40;
    private javax.swing.Box.Filler filler41;
    private javax.swing.Box.Filler filler42;
    private javax.swing.Box.Filler filler43;
    private javax.swing.Box.Filler filler44;
    private javax.swing.Box.Filler filler45;
    private javax.swing.Box.Filler filler46;
    private javax.swing.Box.Filler filler47;
    private javax.swing.Box.Filler filler48;
    private javax.swing.Box.Filler filler49;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler50;
    private javax.swing.Box.Filler filler51;
    private javax.swing.Box.Filler filler52;
    private javax.swing.Box.Filler filler53;
    private javax.swing.Box.Filler filler54;
    private javax.swing.Box.Filler filler55;
    private javax.swing.Box.Filler filler57;
    private javax.swing.Box.Filler filler58;
    private javax.swing.Box.Filler filler59;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler61;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JTextField final1;
    private javax.swing.JTextField final2;
    private javax.swing.JTextField final3;
    private javax.swing.JTextField finalAsig;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblApellidos;
    private javax.swing.JLabel lblComentarios;
    private javax.swing.JLabel lblDNI;
    private javax.swing.JLabel lblFinal1;
    private javax.swing.JLabel lblFinal2;
    private javax.swing.JLabel lblFinal3;
    private javax.swing.JLabel lblFinalAsig;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblMedia1;
    private javax.swing.JLabel lblMedia1N;
    private javax.swing.JLabel lblMedia2;
    private javax.swing.JLabel lblMedia2N;
    private javax.swing.JLabel lblMedia3;
    private javax.swing.JLabel lblMedia3N;
    private javax.swing.JLabel lblMediaAsignatura;
    private javax.swing.JLabel lblMediaAsignaturaN;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPruebas1;
    private javax.swing.JLabel lblPruebas2;
    private javax.swing.JLabel lblPruebas3;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    private javax.swing.JPanel panel3;
    private javax.swing.JTable tabla1;
    private javax.swing.JTable tabla2;
    private javax.swing.JTable tabla3;
    private javax.swing.JTextArea txtComentarios;
    // End of variables declaration//GEN-END:variables
}
