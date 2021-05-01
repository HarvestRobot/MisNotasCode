/*
 * Lucia Calzado Piedrabuena
 * Trabajo de Fin de Grado - Grado en Ingenier�a Inform�tica
 * Universidad de Castilla-La Mancha
 */
package appsmallinterfaces;

import appinterface.MainWindow;
import auxiliar.AuxiliarMethods;
import java.sql.SQLException;
import controladores.ControladorMaestro;
import java.awt.Font;

/**
 *
 * @author lucia
 */
public class LoginWindow extends javax.swing.JFrame {

    ControladorMaestro contMaestro;
    char mostrarContraseña;
    int tamañoLetra;

    public LoginWindow() {
        contMaestro = new ControladorMaestro();
        initComponents();
        panelOpciones.setVisible(false);
        mostrarContraseña = txtContraseña.getEchoChar();

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

        lblUsuario = new javax.swing.JLabel();
        lblContraseña = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtContraseña = new javax.swing.JPasswordField();
        lblIdentificacion = new javax.swing.JLabel();
        btnIniciar = new javax.swing.JButton();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(60, 0), new java.awt.Dimension(60, 0), new java.awt.Dimension(60, 32767));
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 25), new java.awt.Dimension(0, 25), new java.awt.Dimension(32767, 25));
        btnOpciones = new javax.swing.JButton();
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 35), new java.awt.Dimension(0, 35), new java.awt.Dimension(32767, 35));
        panelOpciones = new javax.swing.JPanel();
        lbltamañoLetra = new javax.swing.JLabel();
        comboTamaño = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        filler11 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        chbxMostrar = new javax.swing.JCheckBox();
        filler12 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        filler13 = new javax.swing.Box.Filler(new java.awt.Dimension(60, 0), new java.awt.Dimension(60, 0), new java.awt.Dimension(60, 32767));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(60, 0), new java.awt.Dimension(60, 0), new java.awt.Dimension(60, 32767));
        filler15 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 60), new java.awt.Dimension(0, 60), new java.awt.Dimension(32767, 60));
        filler16 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 40), new java.awt.Dimension(0, 40), new java.awt.Dimension(32767, 40));
        filler17 = new javax.swing.Box.Filler(new java.awt.Dimension(40, 0), new java.awt.Dimension(40, 0), new java.awt.Dimension(40, 32767));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Identificación");
        setBounds(new java.awt.Rectangle(600, 200, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        lblUsuario.setText("Usuario:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(lblUsuario, gridBagConstraints);

        lblContraseña.setText("Contraseña:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(lblContraseña, gridBagConstraints);

        txtUsuario.setText("Usuario");
        txtUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUsuarioFocusLost(evt);
            }
        });
        txtUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtUsuarioMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(txtUsuario, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(txtContraseña, gridBagConstraints);

        lblIdentificacion.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        lblIdentificacion.setText("Identificación");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        getContentPane().add(lblIdentificacion, gridBagConstraints);

        btnIniciar.setText("Iniciar sesión");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 4;
        getContentPane().add(btnIniciar, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        getContentPane().add(filler5, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        getContentPane().add(filler6, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        getContentPane().add(filler9, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 4;
        getContentPane().add(filler7, gridBagConstraints);

        btnOpciones.setText("Mostrar opciones");
        btnOpciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpcionesActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        getContentPane().add(btnOpciones, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        getContentPane().add(filler10, gridBagConstraints);

        panelOpciones.setLayout(new java.awt.GridBagLayout());

        lbltamañoLetra.setText("Tamaño de letra:");
        panelOpciones.add(lbltamañoLetra, new java.awt.GridBagConstraints());

        comboTamaño.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "12", "14", "18", "20", "24", "30", "36" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        panelOpciones.add(comboTamaño, gridBagConstraints);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        panelOpciones.add(btnGuardar, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        panelOpciones.add(filler8, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        panelOpciones.add(filler11, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 6;
        getContentPane().add(panelOpciones, gridBagConstraints);

        chbxMostrar.setText("Mostrar contraseña");
        chbxMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbxMostrarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        getContentPane().add(chbxMostrar, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 7;
        getContentPane().add(filler12, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 3;
        getContentPane().add(filler13, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 9;
        getContentPane().add(filler3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.gridheight = 2;
        getContentPane().add(filler15, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 9;
        getContentPane().add(filler16, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 12;
        getContentPane().add(filler17, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        try {
            contMaestro.comprobarCredenciales(Integer.parseInt(txtUsuario.getText()), new String(txtContraseña.getPassword()));
        } catch (SQLException e) {
            AuxiliarMethods.showWarning(e.toString());
        } catch (NumberFormatException e) {
            AuxiliarMethods.showWarning("El ID de usuario debe ser un número.");
        }
        
        MainWindow mw = new MainWindow(1, tamañoLetra);
        mw.pack();
        mw.setVisible(true);
        mw.setMinimumSize(mw.getSize());

    }//GEN-LAST:event_btnIniciarActionPerformed

    private void btnOpcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpcionesActionPerformed
        if (panelOpciones.isVisible()) {
            panelOpciones.setVisible(false);
            this.setSize(this.getWidth() - panelOpciones.getWidth(), this.getHeight());
            btnOpciones.setText("Mostrar opciones");
        } else {
            panelOpciones.setVisible(true);
            this.setSize(this.getWidth() + panelOpciones.getWidth(), this.getHeight());
            btnOpciones.setText("Ocultar opciones");
        }
    }//GEN-LAST:event_btnOpcionesActionPerformed

    private void txtUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUsuarioMouseClicked
        if(txtUsuario.getText().equals("Usuario")){
            txtUsuario.setText("");
        }
    }//GEN-LAST:event_txtUsuarioMouseClicked

    private void txtUsuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsuarioFocusLost
        if(txtUsuario.getText().equals("")){
            txtUsuario.setText("Usuario");
        }
    }//GEN-LAST:event_txtUsuarioFocusLost

    private void chbxMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbxMostrarActionPerformed
        if (!chbxMostrar.isSelected()){
            txtContraseña.setEchoChar(mostrarContraseña); 
        } else {
            txtContraseña.setEchoChar((char) 0);
        }
    }//GEN-LAST:event_chbxMostrarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        tamañoLetra = Integer.valueOf(comboTamaño.getSelectedItem().toString());
        
        lblContraseña.setFont(new Font(lblContraseña.getFont().getName(), Font.PLAIN, tamañoLetra));
        lblUsuario.setFont(new Font(lblUsuario.getFont().getName(), Font.PLAIN, tamañoLetra));
        lbltamañoLetra.setFont(new Font(lbltamañoLetra.getFont().getName(), Font.PLAIN, tamañoLetra));
        lblIdentificacion.setFont(new Font(lbltamañoLetra.getFont().getName(), Font.PLAIN, tamañoLetra+lblIdentificacion.getFont().getSize()));
        
        comboTamaño.setFont(new Font(comboTamaño.getFont().getName(), Font.PLAIN, tamañoLetra));
        
        txtContraseña.setFont(new Font(txtContraseña.getFont().getName(), Font.PLAIN, tamañoLetra));
        txtUsuario.setFont(new Font(txtUsuario.getFont().getName(), Font.PLAIN, tamañoLetra));
        
        btnGuardar.setFont(new Font(btnGuardar.getFont().getName(), Font.PLAIN, tamañoLetra));
        btnIniciar.setFont(new Font(btnIniciar.getFont().getName(), Font.PLAIN, tamañoLetra));
        btnOpciones.setFont(new Font(btnOpciones.getFont().getName(), Font.PLAIN, tamañoLetra));
        
        chbxMostrar.setFont(new Font(chbxMostrar.getFont().getName(), Font.PLAIN, tamañoLetra));
        
    }//GEN-LAST:event_btnGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnOpciones;
    private javax.swing.JCheckBox chbxMostrar;
    private javax.swing.JComboBox<String> comboTamaño;
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler11;
    private javax.swing.Box.Filler filler12;
    private javax.swing.Box.Filler filler13;
    private javax.swing.Box.Filler filler15;
    private javax.swing.Box.Filler filler16;
    private javax.swing.Box.Filler filler17;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JLabel lblContraseña;
    private javax.swing.JLabel lblIdentificacion;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lbltamañoLetra;
    private javax.swing.JPanel panelOpciones;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
