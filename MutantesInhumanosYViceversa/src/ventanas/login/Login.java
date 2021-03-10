package ventanas.login;

import conexion.ConexionServidor;
import ventanas.registro.Registro;
import ventanas.administrador.Administrador;
import mensajes.entidades.Usuario;
import mensajes.MsjServUsuario;
import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import ventanas.preferencias.Preferencias;
import ventanas.principal.Principal;
import utilidades.Constantes;
import ventanas.espera.DialogoEspera;

/**
 *
 * @author Flor
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form login
     */
    public Login() {
        initComponents();

        //TODO Datos para pruebas
//        txt_email.setText("f@f.es");
//        txt_pwd.setText("1234");
        //TODO Datos para pruebas

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Se pide una confirmación antes de finalizar el programa
                int option;
                option = JOptionPane.showConfirmDialog(
                        (Component) e.getSource(),
                        "¿Estás seguro de que quieres cerrar la aplicación?",
                        "Salir",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    //Cierra la pantalla y sale de la aplicacion
                    System.exit(0);
                }
            }
        });
    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource(Constantes.ICO_APP));

        return retValue;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_titulo = new javax.swing.JLabel();
        lbl_email = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        lbl_pwd = new javax.swing.JLabel();
        txt_pwd = new javax.swing.JPasswordField();
        btn_entrar = new javax.swing.JButton();
        lbl_registro = new javax.swing.JLabel();
        lbl_fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Mutantes e Inhumanos y viceversa");
        setIconImage(getIconImage());
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_titulo.setFont(new java.awt.Font("Book Antiqua", 1, 30)); // NOI18N
        lbl_titulo.setForeground(new java.awt.Color(255, 255, 255));
        lbl_titulo.setText("Mutantes e Inhumanos y viceversa");
        getContentPane().add(lbl_titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        lbl_email.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_email.setForeground(new java.awt.Color(255, 255, 255));
        lbl_email.setText("Email:");
        getContentPane().add(lbl_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 60, -1));
        lbl_email.getAccessibleContext().setAccessibleDescription("");

        txt_email.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        getContentPane().add(txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 250, -1));

        lbl_pwd.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_pwd.setForeground(new java.awt.Color(255, 255, 255));
        lbl_pwd.setText("Contraseña:");
        getContentPane().add(lbl_pwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

        txt_pwd.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        getContentPane().add(txt_pwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 250, -1));

        btn_entrar.setBackground(new java.awt.Color(249, 246, 246));
        btn_entrar.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        btn_entrar.setText("Entrar");
        btn_entrar.setToolTipText("Entrar");
        btn_entrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_entrarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_entrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 220, 260, -1));

        lbl_registro.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        lbl_registro.setForeground(new java.awt.Color(102, 255, 51));
        lbl_registro.setText("Registrarse");
        lbl_registro.setToolTipText("Registrarse");
        lbl_registro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_registro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_registroMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_registro, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, -1, -1));

        lbl_fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/fondo_login_nuevo1.jpg.png"))); // NOI18N
        getContentPane().add(lbl_fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, -20, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_registroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_registroMouseClicked
        //Ocultamos la ventana actual
        this.setVisible(false);

        //Inicializamos la ventana de registro y la mostramos
        Registro registro = new Registro();
        registro.setLocationRelativeTo(null);
        registro.setVisible(true);
    }//GEN-LAST:event_lbl_registroMouseClicked

    private void btn_entrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_entrarActionPerformed
        //Ventana de dialogo de espera
        DialogoEspera wait = new DialogoEspera();

        SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() throws Exception {
                cargarLogin();
                wait.close();
                return null;
            }
        };

        mySwingWorker.execute();
        wait.makeWait("Cargando", evt);
    }//GEN-LAST:event_btn_entrarActionPerformed

    private void cargarLogin() {
        MsjServUsuario mUsuarioEnvio = new MsjServUsuario();
        mUsuarioEnvio.setAccion(Constantes.ACCION_VAL_LOGIN);
        mUsuarioEnvio.setUsuario(informacionVentana());

        MsjServUsuario mUsuarioRecibido = (MsjServUsuario) ConexionServidor.envioObjetoServidor(mUsuarioEnvio);

        if (null != mUsuarioRecibido) {
            //Segun el codigo devuelto por el servidor redirige o muestra un mensaje
            switch (mUsuarioRecibido.getCodError()) {
                case Constantes.OK:
                    //Redirigimos a la ventana correspondiente
                    redirigirRol(mUsuarioRecibido.getUsuario());
                    break;
                case Constantes.OK_PRIMER_ACCESO:
                    
                    //Ocultamos la ventana actual
                    this.setVisible(false);

                    //Redirigimos a la ventana de preferencias para rellenar la informacion
                    Preferencias preferencias = new Preferencias(mUsuarioRecibido.getUsuario());
                    preferencias.setLocationRelativeTo(null);
                    preferencias.setVisible(true);
                    break;
                case Constantes.ERROR_FORMATO_EMAIL:
                case Constantes.ERROR_PWD:
                case Constantes.ERROR_USUARIO_NO_ACTIVO:
                case Constantes.ERROR_ENTRAR:
                case Constantes.ERROR_USUARIO_NO_REGISTRADO:
                    //Mostramos el mensaje devuelto por el servidor
                    JOptionPane.showMessageDialog(this, mUsuarioRecibido.getMensaje(), "Login", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        } else {
            //Mostramos el mensaje
            JOptionPane.showMessageDialog(this, "No hay conexión con el servidor, por favor, intentelo más tarde", "Login", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Metodo que redirige a un formulario u otro, segun el rol
     *
     * @param user
     */
    private void redirigirRol(Usuario user) {

        switch (user.getRol()) {
            case Constantes.ROL_SUPERADMIN:
                //Ocultamos la ventana actual
                this.setVisible(false);

                //Inicializamos la ventana de login y la mostramos
                Administrador superAdministrador = new Administrador(user);
                //Cargamos la informacion
                superAdministrador.cargarDatos();
                superAdministrador.setLocationRelativeTo(null);
                superAdministrador.setVisible(true);

                break;
            case Constantes.ROL_ADMIN:
                //Ocultamos la ventana actual
                this.setVisible(false);

                //Inicializamos la ventana de login y la mostramos
                Administrador administrador = new Administrador(user);
                //Cargamos la informacion
                administrador.cargarDatos();
                administrador.setLocationRelativeTo(null);
                administrador.setVisible(true);
                break;
            case Constantes.ROL_USER:
                //Redirigimos a la ventana principal para la aplicacion

                //Ocultamos la ventana actual
                this.setVisible(false);

                //Inicializamos la ventana de login y la mostramos
                Principal principal = new Principal(user);
                principal.setLocationRelativeTo(null);
                principal.setVisible(true);

                break;
        }
    }

    /**
     * Metodo que obtiene la informacion de la ventana y la pasa al usuario
     *
     * @return
     */
    private Usuario informacionVentana() {
        //Inicializa el usuario
        Usuario usuario = new Usuario();

        //Recupera la informacion de la pantalla
        String email = txt_email.getText();
        char[] pwd = txt_pwd.getPassword();

        //pasa la informacion de la pantalla al usuario
        usuario.setEmail(email);
        if (0 < pwd.length) {
            usuario.setPwd(String.valueOf(pwd));
        }

        return usuario;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
            * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */

 /*UIManager.put("control", new Color(128, 128, 128));
            UIManager.put("info", new Color(128, 128, 128));
            UIManager.put("nimbusBase", new Color(18, 30, 49));
            UIManager.put("nimbusAlertYellow", new Color(248, 187, 0));
            UIManager.put("nimbusDisabledText", new Color(128, 128, 128));
            UIManager.put("nimbusFocus", new Color(115, 164, 209));
            UIManager.put("nimbusGreen", new Color(176, 179, 50));
            UIManager.put("nimbusInfoBlue", new Color(66, 139, 221));
            UIManager.put("nimbusLightBackground", new Color(18, 30, 49));
            UIManager.put("nimbusOrange", new Color(191, 98, 4));
            UIManager.put("nimbusRed", new Color(169, 46, 34));
            UIManager.put("nimbusSelectedText", new Color(255, 255, 255));
            UIManager.put("nimbusSelectionBackground", new Color(104, 93, 156));
            UIManager.put("text", new Color(230, 230, 230));*/
 /*UIManager.put("Menu.acceleratorForeground", new Color(128, 128, 128));
            UIManager.put("Menu.acceleratorSelectionForeground", new Color(128, 128, 128));
            UIManager.put("Menu.background", new Color(128, 128, 128));
            UIManager.put("Menu.disabledBackground", new Color(128, 128, 128));
            UIManager.put("Menu.disabledForeground", new Color(128, 128, 128));
            UIManager.put("Menu.foreground", new Color(128, 128, 128));
            UIManager.put("Menu.selectionBackground", new Color(128, 128, 128));
            UIManager.put("Menu.selectionForeground", new Color(128, 128, 128));
            UIManager.put("MenuBar.background", new Color(128, 128, 128));
            UIManager.put("MenuBar.disabledBackground", new Color(128, 128, 128));
            UIManager.put("MenuBar.disabledForeground", new Color(128, 128, 128));
            UIManager.put("MenuBar.foreground", new Color(128, 128, 128));
            UIManager.put("MenuBar.highlight", new Color(128, 128, 128));
            UIManager.put("MenuBar.selectionBackground", new Color(128, 128, 128));
            UIManager.put("MenuBar.selectionForeground", new Color(128, 128, 128));
            UIManager.put("MenuBar.shadow", new Color(128, 128, 128));
            UIManager.put("MenuItem.acceleratorForeground", new Color(128, 128, 128));
            UIManager.put("MenuItem.acceleratorSelectionForeground", new Color(128, 128, 128));
            UIManager.put("MenuItem.background", new Color(128, 128, 128));
            UIManager.put("MenuItem.disabledBackground", new Color(128, 128, 128));
            UIManager.put("MenuItem.disabledForeground", new Color(128, 128, 128));
            UIManager.put("MenuItem.foreground", new Color(128, 128, 128));
            UIManager.put("MenuItem.selectionBackground", new Color(128, 128, 128));
            UIManager.put("MenuItem.selectionForeground", new Color(128, 128, 128));*/
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());

                    break;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (javax.swing.UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                try {

                Login login = new Login();
                login.setLocationRelativeTo(null);
                login.setVisible(true);

                    //TODO Conecta con el servidor
                    ConexionServidor.conectarServidor();

                } catch (IOException ex) {
                    //Mostramos el mensaje
                    JOptionPane.showMessageDialog(null, "No hay conexión con el servidor, por favor, intentelo más tarde", "Login", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        //</editor-fold>
        //</editor-fold>
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_entrar;
    private javax.swing.JLabel lbl_email;
    private javax.swing.JLabel lbl_fondo;
    private javax.swing.JLabel lbl_pwd;
    private javax.swing.JLabel lbl_registro;
    private javax.swing.JLabel lbl_titulo;
    private javax.swing.JTextField txt_email;
    private javax.swing.JPasswordField txt_pwd;
    // End of variables declaration//GEN-END:variables
}