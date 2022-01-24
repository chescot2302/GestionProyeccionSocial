package com.gps.view;

import com.gps.util.Imagenes;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.Document;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.button.StandardButtonShaper;
import org.jvnet.substance.skin.FieldOfWheatSkin;
import org.jvnet.substance.theme.SubstanceEbonyTheme;
import org.jvnet.substance.watermark.SubstanceMagneticFieldWatermark;
import org.netbeans.validation.api.Validator;
import org.netbeans.validation.api.builtin.Validators;
import org.netbeans.validation.api.ui.ValidationPanel;
import org.netbeans.validation.api.ui.ValidationStrategy;

public class ViewLogin extends JFrame {

    public static final Imagenes picture = new Imagenes();
    private JLabel lblusuario = new JLabel("Usuario", picture.getIconUser16(), JLabel.LEFT);
    private JLabel lblcontraseña = new JLabel("Contraseña", picture.getIconPassword16(), JLabel.LEFT);
    private JLabel izquierda = new JLabel("", picture.getIconArchive128(), JLabel.CENTER);
    private JLabel aux = new JLabel("");
    private JTextField txtusuario = new JTextField();
    private JPasswordField txtclave = new JPasswordField();
    private JButton btniniciar = new JButton("Iniciar Sesion");
    private JPanel panelFormulario = new JPanel();
    private JPanel contenido = new JPanel();
    private ValidationPanel pnl = new ValidationPanel();

    public ViewLogin() {
        construyendo();
    }

    private void construyendo() {
        this.setLayout(new BorderLayout());
        btniniciar.setIcon(null);
        btniniciar.setMnemonic('i');
        btniniciar.setActionCommand("logear");
        btniniciar.addActionListener(startListener);
        txtusuario.setToolTipText("Ingresar su usuario");
        txtclave.setToolTipText("Ingresar su clave");
        Font fuente = new Font("Monospaced", Font.BOLD, 18);
        txtusuario.setFont(fuente);
        txtusuario.setForeground(Color.BLACK);
        txtclave.setForeground(Color.BLACK);
        btniniciar.setFont(fuente);
        lblusuario.setFont(fuente);
        lblcontraseña.setFont(fuente);
        txtclave.setFont(fuente);
        panelFormulario.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelFormulario.add(lblusuario, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelFormulario.add(txtusuario, gbc);
        txtusuario.setColumns(25);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelFormulario.add(lblcontraseña, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelFormulario.add(txtclave, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelFormulario.add(aux, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelFormulario.add(btniniciar, gbc);
        this.setContentPane(pnl);
        pnl.setInnerComponent(contenido);
        contenido.setLayout(new BorderLayout());
        contenido.add("Center", panelFormulario);
        contenido.add("West", izquierda);
        this.setTitle("Proyeccion Social... Bienvenido");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setIconImage(picture.getIcono());        
        SubstanceLookAndFeel.setSkin(new FieldOfWheatSkin());
        //SubstanceLookAndFeel.setCurrentTheme(new SubstanceEbonyTheme());
        SubstanceLookAndFeel.setCurrentWatermark(new SubstanceMagneticFieldWatermark());
        SubstanceLookAndFeel.setCurrentButtonShaper(new StandardButtonShaper());
        Validator<Document> texto = Validators.forDocument(true, Validators.REQUIRE_NON_EMPTY_STRING);
        pnl.getValidationGroup().add(txtusuario, ValidationStrategy.DEFAULT, texto);
        pnl.getValidationGroup().add(txtclave, ValidationStrategy.DEFAULT, texto);
    }

    /*private void decision(String msg) throws SQLException {
        String mensaje = msg;
        if (mensaje.equals("vacio")) {
            JOptionPane.showMessageDialog(this, "Usuario y contraseña son obligatorios", "Alerta", JOptionPane.ERROR_MESSAGE);
        } else if (mensaje.equals("nouser")) {
            JOptionPane.showMessageDialog(this, "Usuario no encontrado", "Aviso", JOptionPane.ERROR_MESSAGE);
        } else if (mensaje.equals("noclave")) {
            JOptionPane.showMessageDialog(this, "Clave incorrecta", "Aviso", JOptionPane.ERROR_MESSAGE);
        }
    }*/

    private void iniciarSesion() throws SQLException {        
        String user=this.txtusuario.getText();
        String key=String.valueOf(this.txtclave.getPassword());        
        if (user.isEmpty() && key.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Usuario y contraseña son obligatorios", "Alerta", JOptionPane.ERROR_MESSAGE);
        } else if (user.isEmpty() || !user.equalsIgnoreCase("Admin")) {
            JOptionPane.showMessageDialog(this, "Usuario no encontrado", "Aviso", JOptionPane.ERROR_MESSAGE);
        } else if (key.isEmpty() || !key.equalsIgnoreCase("Admin")) {
            JOptionPane.showMessageDialog(this, "Clave incorrecta", "Aviso", JOptionPane.ERROR_MESSAGE);
        } else if (user.equalsIgnoreCase("Admin")&&key.equalsIgnoreCase("Admin")) {
            this.setVisible(false);            
            ViewApplication app = new ViewApplication();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle r = new Rectangle(screenSize.width, screenSize.height - 40);
            app.setBounds(r);
            app.setLocationRelativeTo(null);
            app.setVisible(true);
            app.setExtendedState(6);
        }
    }

    ActionListener startListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent event) {
            try {                
                iniciarSesion();
            } catch (Exception ex) {
            }
        }
    };
}
