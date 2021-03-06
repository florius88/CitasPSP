package ventanas.registro;

import conexion.ConexionServidor;
import mensajes.entidades.Usuario;
import mensajes.MsjServUsuario;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import mensajes.entidades.UsuarioFirmado;
import seguridad.Seguridad;
import ventanas.login.Login;
import utilidades.Constantes;

/**
 *
 * @author Flor
 */
public class Registro extends javax.swing.JFrame {

    /**
     * Constructor
     */
    public Registro() {
        initComponents();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //Inicializamos la ventana de login y la mostramos
                Login login = new Login();
                login.setLocationRelativeTo(null);
                login.setVisible(true);
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
        lbl_subtitulo = new javax.swing.JLabel();
        lbl_nombre = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        lbl_email = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        lbl_pwd = new javax.swing.JLabel();
        txt_pwd = new javax.swing.JPasswordField();
        lbl_confirm_pwd = new javax.swing.JLabel();
        txt_confirm_pwd = new javax.swing.JPasswordField();
        btn_guardar = new javax.swing.JButton();
        lbl_nota = new javax.swing.JLabel();
        lbl_fondo = new javax.swing.JLabel();

        setTitle("Mutantes e Inhumanos y viceversa");
        setIconImage(getIconImage());
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_titulo.setFont(new java.awt.Font("Book Antiqua", 1, 30)); // NOI18N
        lbl_titulo.setForeground(new java.awt.Color(255, 255, 255));
        lbl_titulo.setText("Registrate");
        getContentPane().add(lbl_titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, -1, -1));

        lbl_subtitulo.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_subtitulo.setForeground(new java.awt.Color(255, 255, 255));
        lbl_subtitulo.setText("Comienza a buscar pareja");
        getContentPane().add(lbl_subtitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, -1, -1));

        lbl_nombre.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_nombre.setForeground(new java.awt.Color(255, 255, 255));
        lbl_nombre.setText("Nick:");
        getContentPane().add(lbl_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, -1, -1));

        txt_nombre.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        getContentPane().add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 130, 250, -1));

        lbl_email.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_email.setForeground(new java.awt.Color(255, 255, 255));
        lbl_email.setText("Email:");
        getContentPane().add(lbl_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, -1, -1));

        txt_email.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        getContentPane().add(txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 170, 250, -1));

        lbl_pwd.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_pwd.setForeground(new java.awt.Color(255, 255, 255));
        lbl_pwd.setText("Contraseña:*");
        getContentPane().add(lbl_pwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, -1, -1));

        txt_pwd.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        getContentPane().add(txt_pwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 210, 250, -1));

        lbl_confirm_pwd.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_confirm_pwd.setForeground(new java.awt.Color(255, 255, 255));
        lbl_confirm_pwd.setText("Confirmar contraseña:*");
        getContentPane().add(lbl_confirm_pwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, -1, -1));

        txt_confirm_pwd.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        getContentPane().add(txt_confirm_pwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 250, 250, -1));

        btn_guardar.setBackground(new java.awt.Color(249, 246, 246));
        btn_guardar.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        btn_guardar.setText("Guardar");
        btn_guardar.setToolTipText("Guardar");
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 350, 340, -1));

        lbl_nota.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        lbl_nota.setForeground(new java.awt.Color(255, 255, 255));
        lbl_nota.setText("*Como mínimo la contraseña debe ser de 4 cifras.");
        getContentPane().add(lbl_nota, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, -1, -1));

        lbl_fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/fondo_registro_nuevo1.jpg.png"))); // NOI18N
        getContentPane().add(lbl_fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-90, 0, 720, 440));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        MsjServUsuario mUsuarioEnvio = new MsjServUsuario();
        mUsuarioEnvio.setUsuario(informacionVentana());
        mUsuarioEnvio.setAccion(Constantes.ACCION_REGISTRO_USUARIO);

        try {
            
            UsuarioFirmado uFirmado = new UsuarioFirmado();
            uFirmado.setEmailFirmado(Seguridad.firmar(String.valueOf(mUsuarioEnvio.getUsuario().getEmail()), ConexionServidor.privadaCliente));
            uFirmado.setPwdResumen(Seguridad.firmar(String.valueOf(mUsuarioEnvio.getUsuario().getPwd()), ConexionServidor.privadaCliente));

            mUsuarioEnvio.setUsuarioFirmado(uFirmado);
            
        } catch (Exception e) {

        }
        
        //Envia la informacion al servidor
        MsjServUsuario mUsuarioRecibido = (MsjServUsuario) ConexionServidor.envioObjetoServidor(mUsuarioEnvio);

        if (null != mUsuarioRecibido) {
            //Segun el codigo devuelto por el servidor redirige o muestra un mensaje
            switch (mUsuarioRecibido.getCodError()) {
                case Constantes.OK:
                    JOptionPane.showMessageDialog(this, mUsuarioRecibido.getMensaje(), "Registro", JOptionPane.INFORMATION_MESSAGE);

                    //Ocultamos la ventana actual
                    this.setVisible(false);

                    //Inicializamos la ventana de login y la mostramos
                    Login login = new Login();
                    login.setLocationRelativeTo(null);
                    login.setVisible(true);
                    break;
                case Constantes.ERROR_FORMATO_EMAIL:
                case Constantes.ERROR_PWD:
                case Constantes.ERROR_PWD_NO_IGUALES:
                case Constantes.ERROR_NO_NICK:
                case Constantes.ERROR_BD:
                    //Mostramos el mensaje devuelto por el servidor
                    JOptionPane.showMessageDialog(this, mUsuarioRecibido.getMensaje(), "Registro", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        } else {
            //Mostramos el mensaje
            JOptionPane.showMessageDialog(this, "No hay conexión con el servidor, por favor, intentelo más tarde", "Registro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_guardarActionPerformed

    /**
     * Metodo que obtiene la informacion de la ventana
     *
     * @return
     */
    private Usuario informacionVentana() {
        //Inicializa el usuario
        Usuario usuario = new Usuario();

        try {

            //Recupera la informacion de la pantalla
            String nick = txt_nombre.getText();
            String email = txt_email.getText();

            char[] pwd = txt_pwd.getPassword();
            char[] confirmPwd = txt_confirm_pwd.getPassword();

            //byte[] pwdResumen = Seguridad.resumir(String.valueOf(pwd));
            //byte[] pwdConfirmarResumen = Seguridad.resumir(String.valueOf(pwdResumen));

            //pasa la informacion de la pantalla al usuario
            usuario.setNick(nick);
            usuario.setEmail(email);
            if (0 < pwd.length) {
                usuario.setPwd(String.valueOf(pwd));
            }
            if (0 < confirmPwd.length) {
                usuario.setConfirmarPwd(String.valueOf(confirmPwd));
            }

            //Como se da de alta por la aplicacion, su rol es de usuario
            usuario.setRol(3);

        } catch (Exception e) {

        }

        return usuario;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_guardar;
    private javax.swing.JLabel lbl_confirm_pwd;
    private javax.swing.JLabel lbl_email;
    private javax.swing.JLabel lbl_fondo;
    private javax.swing.JLabel lbl_nombre;
    private javax.swing.JLabel lbl_nota;
    private javax.swing.JLabel lbl_pwd;
    private javax.swing.JLabel lbl_subtitulo;
    private javax.swing.JLabel lbl_titulo;
    private javax.swing.JPasswordField txt_confirm_pwd;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JPasswordField txt_pwd;
    // End of variables declaration//GEN-END:variables
}
