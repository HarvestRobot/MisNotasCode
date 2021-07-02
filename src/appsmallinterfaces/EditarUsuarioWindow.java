/*
 * Lucia Calzado Piedrabuena
 * Trabajo de Fin de Grado - Grado en Ingenier�a Inform�tica
 * Universidad de Castilla-La Mancha
 */
package appsmallinterfaces;

import appinterface.MainWindow;
import appinterface.MainWindow;
import auxiliar.AuxiliarMethods;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextField;
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import objects.Maestro;
import objects.Opciones;

/**
 *
 * @author lucia
 */
public class EditarUsuarioWindow extends javax.swing.JFrame {

    Opciones opciones;
    char mostrarContraseña1;
    char mostrarContraseña2;
    Maestro maestro;
    MainWindow mw;

    /**
     * Crear un nuevo form EditarUsuarioWindow
     *
     * @param maestro
     * @param opciones
     */
    public EditarUsuarioWindow(Maestro maestro, Opciones opciones, MainWindow mw) {
        this.opciones = opciones;
        this.maestro = maestro;
        this.mw = mw;
        initComponents();
        mostrarContraseña1 = txtContraseña1.getEchoChar();
        mostrarContraseña2 = txtContraseña2.getEchoChar();

        //Cambiamos el tamaño de la letra si se ha pedido
        ejecutarOpciones();

        txtIdMaestro.setText(String.valueOf(maestro.getIdMaestro()));
        txtNombre.setText(maestro.getNombre());
        imagen.setIcon(maestro.getImagen());

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
        txtContraseña1 = new javax.swing.JPasswordField();
        txtContraseña2 = new javax.swing.JPasswordField();
        txtIdMaestro = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        lblIdentificador = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblNuevaContraseña1 = new javax.swing.JLabel();
        lblNuevaContraseña2 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 40), new java.awt.Dimension(0, 40), new java.awt.Dimension(32767, 40));
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 40), new java.awt.Dimension(0, 40), new java.awt.Dimension(32767, 40));
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 30), new java.awt.Dimension(0, 30), new java.awt.Dimension(32767, 30));
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        filler11 = new javax.swing.Box.Filler(new java.awt.Dimension(150, 0), new java.awt.Dimension(150, 0), new java.awt.Dimension(150, 32767));
        filler12 = new javax.swing.Box.Filler(new java.awt.Dimension(130, 0), new java.awt.Dimension(130, 0), new java.awt.Dimension(130, 32767));
        lblCambiarContraseña = new javax.swing.JLabel();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 40), new java.awt.Dimension(0, 40), new java.awt.Dimension(32767, 40));
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 40), new java.awt.Dimension(0, 40), new java.awt.Dimension(32767, 40));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 25), new java.awt.Dimension(0, 25), new java.awt.Dimension(32767, 25));
        filler16 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        filler13 = new javax.swing.Box.Filler(new java.awt.Dimension(40, 0), new java.awt.Dimension(40, 0), new java.awt.Dimension(40, 32767));
        filler15 = new javax.swing.Box.Filler(new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 32767));
        filler18 = new javax.swing.Box.Filler(new java.awt.Dimension(200, 0), new java.awt.Dimension(200, 0), new java.awt.Dimension(200, 32767));
        lblCambiarNombre = new javax.swing.JLabel();
        filler17 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 25), new java.awt.Dimension(0, 25), new java.awt.Dimension(32767, 25));
        filler20 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 50), new java.awt.Dimension(0, 50), new java.awt.Dimension(32767, 50));
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(40, 0), new java.awt.Dimension(40, 0), new java.awt.Dimension(40, 32767));
        chbxMostrar = new javax.swing.JCheckBox();
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));
        imagen = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        filler14 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 15), new java.awt.Dimension(0, 15), new java.awt.Dimension(32767, 15));
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Datos personales");
        setBounds(new java.awt.Rectangle(300, 50, 0, 0));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        lblTitulo.setText("Datos personales del/de la maestr@");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 18;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(lblTitulo, gridBagConstraints);

        txtContraseña1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 23;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(txtContraseña1, gridBagConstraints);

        txtContraseña2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 21;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(txtContraseña2, gridBagConstraints);

        txtIdMaestro.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtIdMaestro.setToolTipText("El identificador es único y no se puede cambiar");
        txtIdMaestro.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(txtIdMaestro, gridBagConstraints);

        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(txtNombre, gridBagConstraints);

        lblIdentificador.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblIdentificador.setText("Identificador de usuario:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(lblIdentificador, gridBagConstraints);

        lblNombre.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNombre.setText("Nuevo nombre:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(lblNombre, gridBagConstraints);

        lblNuevaContraseña1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNuevaContraseña1.setText("Nueva contraseña:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 21;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(lblNuevaContraseña1, gridBagConstraints);

        lblNuevaContraseña2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNuevaContraseña2.setText("Repetir nueva contraseña:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 23;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(lblNuevaContraseña2, gridBagConstraints);

        btnGuardar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnGuardar.setText("Guardar cambios");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 27;
        gridBagConstraints.gridwidth = 9;
        getContentPane().add(btnGuardar, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 18;
        getContentPane().add(filler4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 29;
        gridBagConstraints.gridwidth = 11;
        getContentPane().add(filler5, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 8;
        getContentPane().add(filler7, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 13;
        gridBagConstraints.gridy = 22;
        getContentPane().add(filler8, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 21;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 15;
        gridBagConstraints.weightx = 0.4;
        getContentPane().add(filler11, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 18;
        getContentPane().add(filler12, gridBagConstraints);

        lblCambiarContraseña.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblCambiarContraseña.setText("Cambiar contraseña:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(lblCambiarContraseña, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 4;
        getContentPane().add(filler3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        getContentPane().add(filler6, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 26;
        gridBagConstraints.gridwidth = 5;
        getContentPane().add(filler10, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 12;
        getContentPane().add(filler2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 5;
        getContentPane().add(filler16, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        getContentPane().add(filler13, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        getContentPane().add(filler15, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 5;
        getContentPane().add(filler18, gridBagConstraints);

        lblCambiarNombre.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblCambiarNombre.setText("Cambiar nombre:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(lblCambiarNombre, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.gridwidth = 11;
        getContentPane().add(filler17, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridheight = 4;
        getContentPane().add(filler20, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 2;
        getContentPane().add(filler1, gridBagConstraints);

        chbxMostrar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        chbxMostrar.setText("Mostrar contraseña");
        chbxMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbxMostrarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 25;
        gridBagConstraints.gridwidth = 4;
        getContentPane().add(chbxMostrar, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 24;
        getContentPane().add(filler9, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 14;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridheight = 3;
        getContentPane().add(imagen, gridBagConstraints);

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton1.setText("Cambiar imagen");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 14;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        getContentPane().add(jButton1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 14;
        gridBagConstraints.gridy = 9;
        getContentPane().add(filler14, gridBagConstraints);

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

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (new String(txtContraseña1.getPassword()).equals(new String(txtContraseña2.getPassword()))) {
            if (!new String(txtContraseña1.getPassword()).equals("")) {
                try {
                    maestro.updateMaestro(txtNombre.getText(), new String(txtContraseña1.getPassword()));
                } catch (SQLException e) {
                    AuxiliarMethods.showWarning("Ha ocurrido un error al modificar los datos del maestro.\nMás información: " + e.toString());
                }
                this.dispose();
            } else {
                try {
                    maestro.updateMaestro(txtNombre.getText(), "");
                } catch (SQLException e) {
                    AuxiliarMethods.showWarning("Ha ocurrido un error al modificar los datos del maestro.\nMás información: " + e.toString());
                }
                this.dispose();
            }
        } else {
            AuxiliarMethods.showWarning("Las contraseñas facilitadas no son iguales.");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void chbxMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbxMostrarActionPerformed
        if (!chbxMostrar.isSelected()) {
            txtContraseña1.setEchoChar(mostrarContraseña1);
            txtContraseña2.setEchoChar(mostrarContraseña2);
        } else {
            txtContraseña1.setEchoChar((char) 0);
            txtContraseña2.setEchoChar((char) 0);
        }
    }//GEN-LAST:event_chbxMostrarActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        mw.lblBienvenida.setText("Bienvenido/a, "+maestro.getNombre());
        mw.lblFotoMaestro.setIcon(maestro.getImagen());
    }//GEN-LAST:event_formWindowClosed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser file = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        //filter the files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png");
        file.addChoosableFileFilter(filter);
        int result = file.showOpenDialog(null);
        //if the user click on save in Jfilechooser
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            ImageIcon MyImage = new ImageIcon(path);
            Image img = MyImage.getImage();
            Image newImg = img.getScaledInstance(imagen.getWidth(), imagen.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon image = new ImageIcon(newImg);
            imagen.setIcon(image);
            maestro.setImagen(image);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Ayuda ayuda = new Ayuda(opciones, 8);
        ayuda.pack();
        ayuda.setVisible(true);
        ayuda.setMinimumSize(ayuda.getSize());
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        JOptionPane.showMessageDialog(new JFrame(), "Trabajo de Fin de Grado de Lucía Calzado Piedrabuena.\nGrado en Ingeniería Informática.\nUCLM.");
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void ejecutarOpciones() {
        List<Component> components = AuxiliarMethods.getAllComponents(this);
        
        for (Component c : components){
            c.setFont(new Font(c.getFont().getName(), c.getFont().getStyle(), opciones.getTamañoLetra()));
            if(opciones.getOscuro() && c.getClass() != JButton.class && c.getClass() != JTextField.class){
                c.setForeground(Color.LIGHT_GRAY);
            }
        }
        
        lblCambiarNombre.setFont(new Font(lblCambiarNombre.getFont().getName(), Font.BOLD, opciones.getTamañoLetra() + 6));
        lblCambiarContraseña.setFont(new Font(lblCambiarContraseña.getFont().getName(), Font.BOLD, opciones.getTamañoLetra() + 6));
        lblTitulo.setFont(new Font(lblTitulo.getFont().getName(), Font.BOLD, opciones.getTamañoLetra() + 15));
        
        //cambiar el fondo de los containers
        Color colorBackground = opciones.getColorBackground();
        this.getContentPane().setBackground(colorBackground);
        chbxMostrar.setBackground(colorBackground);
        

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JCheckBox chbxMostrar;
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
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler20;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JLabel imagen;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JLabel lblCambiarContraseña;
    private javax.swing.JLabel lblCambiarNombre;
    private javax.swing.JLabel lblIdentificador;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNuevaContraseña1;
    private javax.swing.JLabel lblNuevaContraseña2;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPasswordField txtContraseña1;
    private javax.swing.JPasswordField txtContraseña2;
    private javax.swing.JTextField txtIdMaestro;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

}
