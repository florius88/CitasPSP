package ventanas.preferencias;

import conexion.ConexionServidor;
import mensajes.entidades.Hijos;
import mensajes.entidades.Interes;
import mensajes.entidades.Relacion;
import mensajes.entidades.Sexo;
import mensajes.entidades.Usuario;
import mensajes.MsjServCargaVentana;
import mensajes.MsjServUsuario;
import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import ventanas.principal.Principal;
import ventanas.login.Login;
import utilidades.Constantes;
import ventanas.espera.DialogoEspera;

/**
 *
 * @author Flor
 */
public class Preferencias extends javax.swing.JFrame {

    private Usuario usuario = null;

    /**
     * Constructor
     *
     * @param usuario
     */
    public Preferencias(Usuario usuario) {
        initComponents();

        //Rellenamos los combos
        rellenarCombos();

        //Cargamos la informacion
        cargarInformacion(usuario);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Se pide una confirmación antes de finalizar el programa
                int option;
                option = JOptionPane.showConfirmDialog(
                        (Component) e.getSource(),
                        "¿Estás seguro de que quieres volver al login?",
                        "Cerrar preferencias",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    //Ocultamos la ventana actual
                    JFrame preferencias = (JFrame) e.getSource();
                    preferencias.setVisible(false);

                    //Inicializamos la ventana de login y la mostramos
                    Login login = new Login();
                    login.setLocationRelativeTo(null);
                    login.setVisible(true);
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
        lbl_subtitulo = new javax.swing.JLabel();
        lbl_nick = new javax.swing.JLabel();
        txt_nick = new javax.swing.JTextField();
        lbl_foto = new javax.swing.JLabel();
        btn_selec_foto = new javax.swing.JButton();
        lbl_relacion = new javax.swing.JLabel();
        cb_relacion = new javax.swing.JComboBox<>();
        lbl_deporte = new javax.swing.JLabel();
        lbl_deporte_menos = new javax.swing.JLabel();
        sl_deportivo = new javax.swing.JSlider();
        lbl_deporte_mas = new javax.swing.JLabel();
        lbl_arte = new javax.swing.JLabel();
        lbl_arte_menos = new javax.swing.JLabel();
        sl_arte = new javax.swing.JSlider();
        lbl_arte_mas = new javax.swing.JLabel();
        lbl_politico = new javax.swing.JLabel();
        lbl_politica_menos = new javax.swing.JLabel();
        sl_politico = new javax.swing.JSlider();
        lbl_politica_mas = new javax.swing.JLabel();
        lbl_hijos = new javax.swing.JLabel();
        cb_hijos = new javax.swing.JComboBox<>();
        lbl_sexo = new javax.swing.JLabel();
        cb_sexo = new javax.swing.JComboBox<>();
        lbl_interes = new javax.swing.JLabel();
        cb_interes = new javax.swing.JComboBox<>();
        btn_guardar = new javax.swing.JButton();
        lbl_fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Mutantes e Inhumanos y viceversa");
        setIconImage(getIconImage());
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_titulo.setFont(new java.awt.Font("Book Antiqua", 1, 30)); // NOI18N
        lbl_titulo.setForeground(new java.awt.Color(255, 255, 255));
        lbl_titulo.setText("Mutantes e Inhumanos y viceversa");
        getContentPane().add(lbl_titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        lbl_subtitulo.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_subtitulo.setForeground(new java.awt.Color(255, 255, 255));
        lbl_subtitulo.setText("El amor está por todas partes");
        getContentPane().add(lbl_subtitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, -1, -1));

        lbl_nick.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_nick.setForeground(new java.awt.Color(255, 255, 255));
        lbl_nick.setText("Nick:");
        getContentPane().add(lbl_nick, new org.netbeans.lib.awtextra.AbsoluteConstraints(286, 150, -1, -1));

        txt_nick.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        getContentPane().add(txt_nick, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 148, 250, -1));

        lbl_foto.setBackground(new java.awt.Color(221, 167, 181));
        lbl_foto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ico/ico_foto_perfil.png"))); // NOI18N
        lbl_foto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(159, 106, 134), 2));
        lbl_foto.setOpaque(true);
        getContentPane().add(lbl_foto, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 130, 130));

        btn_selec_foto.setBackground(new java.awt.Color(249, 246, 246));
        btn_selec_foto.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        btn_selec_foto.setText("Sel. Foto");
        btn_selec_foto.setToolTipText("Seleccionar foto");
        btn_selec_foto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_selec_fotoActionPerformed(evt);
            }
        });
        getContentPane().add(btn_selec_foto, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 130, -1));

        lbl_relacion.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_relacion.setForeground(new java.awt.Color(255, 255, 255));
        lbl_relacion.setText("Relación:");
        getContentPane().add(lbl_relacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, -1, -1));

        cb_relacion.setBackground(new java.awt.Color(249, 246, 246));
        cb_relacion.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        getContentPane().add(cb_relacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 190, 250, -1));

        lbl_deporte.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_deporte.setForeground(new java.awt.Color(255, 255, 255));
        lbl_deporte.setText("Deportivos:");
        getContentPane().add(lbl_deporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(228, 230, -1, -1));

        lbl_deporte_menos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ico/ico_menos-nuevo-1.png"))); // NOI18N
        getContentPane().add(lbl_deporte_menos, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 230, -1, -1));

        sl_deportivo.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        sl_deportivo.setToolTipText("a@a");
        getContentPane().add(sl_deportivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 230, 150, 35));

        lbl_deporte_mas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ico/ico_mas-nuevo-1.png"))); // NOI18N
        getContentPane().add(lbl_deporte_mas, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 230, -1, -1));

        lbl_arte.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_arte.setForeground(new java.awt.Color(255, 255, 255));
        lbl_arte.setText("Artísticos:");
        getContentPane().add(lbl_arte, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 270, -1, -1));

        lbl_arte_menos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ico/ico_menos-nuevo-1.png"))); // NOI18N
        getContentPane().add(lbl_arte_menos, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 270, -1, -1));

        sl_arte.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        getContentPane().add(sl_arte, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 270, 150, 35));

        lbl_arte_mas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ico/ico_mas-nuevo-1.png"))); // NOI18N
        getContentPane().add(lbl_arte_mas, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 270, -1, -1));

        lbl_politico.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_politico.setForeground(new java.awt.Color(255, 255, 255));
        lbl_politico.setText("Políticos:");
        getContentPane().add(lbl_politico, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 310, -1, -1));

        lbl_politica_menos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ico/ico_menos-nuevo-1.png"))); // NOI18N
        getContentPane().add(lbl_politica_menos, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 310, -1, -1));

        sl_politico.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        getContentPane().add(sl_politico, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 310, 150, 35));

        lbl_politica_mas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ico/ico_mas-nuevo-1.png"))); // NOI18N
        getContentPane().add(lbl_politica_mas, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 310, -1, -1));

        lbl_hijos.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_hijos.setForeground(new java.awt.Color(255, 255, 255));
        lbl_hijos.setText("Tiene/Quiere hijos:");
        getContentPane().add(lbl_hijos, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 350, -1, -1));

        cb_hijos.setBackground(new java.awt.Color(249, 246, 246));
        cb_hijos.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        getContentPane().add(cb_hijos, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 350, 250, -1));

        lbl_sexo.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_sexo.setForeground(new java.awt.Color(255, 255, 255));
        lbl_sexo.setText("Sexo:");
        getContentPane().add(lbl_sexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 392, -1, -1));

        cb_sexo.setBackground(new java.awt.Color(249, 246, 246));
        cb_sexo.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        getContentPane().add(cb_sexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 390, 250, -1));

        lbl_interes.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_interes.setForeground(new java.awt.Color(255, 255, 255));
        lbl_interes.setText("Interés en:");
        getContentPane().add(lbl_interes, new org.netbeans.lib.awtextra.AbsoluteConstraints(242, 430, -1, -1));

        cb_interes.setBackground(new java.awt.Color(249, 246, 246));
        cb_interes.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        getContentPane().add(cb_interes, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 430, 250, -1));

        btn_guardar.setBackground(new java.awt.Color(249, 246, 246));
        btn_guardar.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        btn_guardar.setText("Guardar");
        btn_guardar.setToolTipText("Guardar");
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 520, 370, -1));

        lbl_fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/fondo_preferencias_nuevo1.jpg.png"))); // NOI18N
        getContentPane().add(lbl_fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -40, 640, 630));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        //Ventana de dialogo de espera
        DialogoEspera wait = new DialogoEspera();

        SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() throws Exception {
                guardarInformacion();
                wait.close();
                return null;
            }
        };

        mySwingWorker.execute();
        wait.makeWait("Cargando", evt);


    }//GEN-LAST:event_btn_guardarActionPerformed

    private void guardarInformacion() {
        MsjServUsuario mUsuarioEnvio = new MsjServUsuario();
        mUsuarioEnvio.setAccion(Constantes.ACCION_GUARDAR_PREFERENCIAS);
        //Obtenemos la informacion de la ventana
        informacionVentana();
        mUsuarioEnvio.setUsuario(usuario);

        //Envia la informacion al servidor
        MsjServUsuario mUsuarioRecibido = (MsjServUsuario) ConexionServidor.envioObjetoServidor(mUsuarioEnvio);

        if (null != mUsuarioRecibido) {
        //Segun el codigo devuelto por el servidor redirige o muestra un mensaje
        switch (mUsuarioRecibido.getCodError()) {
            case Constantes.OK:
                JOptionPane.showMessageDialog(this, mUsuarioRecibido.getMensaje(), "Preferencias", JOptionPane.INFORMATION_MESSAGE);

                //Ocultamos la ventana actual
                this.setVisible(false);

                //Inicializamos la ventana de login y la mostramos
                Principal principal = new Principal(usuario);
                principal.setLocationRelativeTo(null);
                principal.setVisible(true);
                break;
            case Constantes.ERROR_NO_NICK:
            case Constantes.ERROR_NO_FOTO:
            case Constantes.ERROR_BD:
                //Mostramos el mensaje devuelto por el servidor
                JOptionPane.showMessageDialog(this, mUsuarioRecibido.getMensaje(), "Preferencias", JOptionPane.ERROR_MESSAGE);
                break;
        }
        } else {
            //Mostramos el mensaje
            JOptionPane.showMessageDialog(this, "No hay conexión con el servidor, por favor, intentelo más tarde", "Preferencias", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btn_selec_fotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_selec_fotoActionPerformed
        //Muestra el cuadro de dialogo para seleccionar imagenes
        JFileChooser selectorArchivos = new JFileChooser();
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JPG, PNG", "jpg", "png");
        selectorArchivos.setFileFilter(filtroImagen);
        selectorArchivos.showOpenDialog(this);
        File archivo = selectorArchivos.getSelectedFile();

        //En caso de seleccionar un documento lo guarda en el objeto a enviar
        if (null != archivo) {
            String origen = archivo.getPath();
            ImageIcon icon = new ImageIcon(origen);
            //Redimensionamos la imagen al espacio del label
            Image image = icon.getImage();
            //Se escala de manera suave
            Image newimg = image.getScaledInstance(420, 360, java.awt.Image.SCALE_SMOOTH);

            //Almacenamos la imagen en el usuario para almacenarla
            usuario.setFoto(new ImageIcon(newimg));
            
            //Se escala de manera suave para mostrarla
            Image imgLbl = newimg.getScaledInstance(130, 130, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(imgLbl);
            //Pasamos la imgane al label
            this.lbl_foto.setIcon(icon);

        } else {
            //Informa al usuario de que seleccione una imagen
            JOptionPane.showMessageDialog(this, "Por favor seleccione un archivo.", "Seleccionar foto", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btn_selec_fotoActionPerformed

    /**
     * Metodo que carga la informacion de los combos
     */
    private void rellenarCombos() {

        DefaultComboBoxModel modelRelacion = new DefaultComboBoxModel();
        cb_relacion.setModel(modelRelacion);

        DefaultComboBoxModel modelHijos = new DefaultComboBoxModel();
        cb_hijos.setModel(modelHijos);

        DefaultComboBoxModel modelSexo = new DefaultComboBoxModel();
        cb_sexo.setModel(modelSexo);

        DefaultComboBoxModel modelInteres = new DefaultComboBoxModel();
        cb_interes.setModel(modelInteres);

        //cb_relacion.setModel(mdljComboBox);
        MsjServCargaVentana mServCargaVentanaEnvio = new MsjServCargaVentana();
        
        //Envia la informacion al servidor
        MsjServCargaVentana mServCargaVentanaRecibido = (MsjServCargaVentana) ConexionServidor.envioObjetoServidor(mServCargaVentanaEnvio);
        
        if (null != mServCargaVentanaRecibido) {
        //Segun el codigo devuelto por el servidor redirige o muestra un mensaje
        switch (mServCargaVentanaRecibido.getCodError()) {
            case Constantes.OK:

                for (Relacion relacion : mServCargaVentanaRecibido.getListaRelacion()) {
                    //Relacion
                    modelRelacion.addElement(relacion);
                }
                for (Hijos hijos : mServCargaVentanaRecibido.getListaHijos()) {
                    //Hijos
                    modelHijos.addElement(hijos);
                }
                for (Sexo sexo : mServCargaVentanaRecibido.getListaSexo()) {
                    //Sexo
                    modelSexo.addElement(sexo);
                }
                for (Interes interes : mServCargaVentanaRecibido.getListaInteres()) {
                    //Interes   
                    modelInteres.addElement(interes);
                }

                break;
            case Constantes.ERROR_BD:
                //Mostramos el mensaje devuelto por el servidor
                JOptionPane.showMessageDialog(this, mServCargaVentanaRecibido.getMensaje(), "Preferencias", JOptionPane.ERROR_MESSAGE);
                break;
        }
        } else {
            //Mostramos el mensaje
            JOptionPane.showMessageDialog(this, "No hay conexión con el servidor, por favor, intentelo más tarde", "Preferencias", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Metodo que carga la informacion
     *
     * @param usuario
     */
    private void cargarInformacion(Usuario usuario) {
        this.usuario = usuario;
        txt_nick.setText(usuario.getNick());
                
        //Preferencias
        if (0 != usuario.getRelacion()){
            cb_relacion.setSelectedIndex(usuario.getRelacion()-1);
        }
        if (0 != usuario.getHijos()){
            cb_hijos.setSelectedIndex(usuario.getHijos()-1);
        }
        if (0 != usuario.getSexo()){
            cb_sexo.setSelectedIndex(usuario.getSexo()-1);
        }
        if (0 != usuario.getInteres()){
            cb_interes.setSelectedIndex(usuario.getInteres()-1);
        }
        sl_deportivo.setValue(usuario.getDeporte());
        sl_arte.setValue(usuario.getArte());
        sl_politico.setValue(usuario.getPolitica());
    }

    /**
     * Metodo que obtiene la informacion
     */
    private void informacionVentana() {

        String nick = txt_nick.getText();
        usuario.setNick(nick);
        Relacion relacion = (Relacion) cb_relacion.getSelectedItem();
        usuario.setRelacion(relacion.getId());
        int deporte = sl_deportivo.getValue();
        usuario.setDeporte(deporte);
        int arte = sl_arte.getValue();
        usuario.setArte(arte);
        int politica = sl_politico.getValue();
        usuario.setPolitica(politica);
        Hijos hijos = (Hijos) cb_hijos.getSelectedItem();
        usuario.setHijos(hijos.getId());
        Sexo sexo = (Sexo) cb_sexo.getSelectedItem();
        usuario.setSexo(sexo.getId());
        Interes interes = (Interes) cb_interes.getSelectedItem();
        usuario.setInteres(interes.getId());
        //Se pasa la fecha formateada
        String fecha = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date());
        usuario.setFechaAcceso(fecha);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_selec_foto;
    private javax.swing.JComboBox<String> cb_hijos;
    private javax.swing.JComboBox<String> cb_interes;
    private javax.swing.JComboBox<Relacion> cb_relacion;
    private javax.swing.JComboBox<String> cb_sexo;
    private javax.swing.JLabel lbl_arte;
    private javax.swing.JLabel lbl_arte_mas;
    private javax.swing.JLabel lbl_arte_menos;
    private javax.swing.JLabel lbl_deporte;
    private javax.swing.JLabel lbl_deporte_mas;
    private javax.swing.JLabel lbl_deporte_menos;
    private javax.swing.JLabel lbl_fondo;
    private javax.swing.JLabel lbl_foto;
    private javax.swing.JLabel lbl_hijos;
    private javax.swing.JLabel lbl_interes;
    private javax.swing.JLabel lbl_nick;
    private javax.swing.JLabel lbl_politica_mas;
    private javax.swing.JLabel lbl_politica_menos;
    private javax.swing.JLabel lbl_politico;
    private javax.swing.JLabel lbl_relacion;
    private javax.swing.JLabel lbl_sexo;
    private javax.swing.JLabel lbl_subtitulo;
    private javax.swing.JLabel lbl_titulo;
    private javax.swing.JSlider sl_arte;
    private javax.swing.JSlider sl_deportivo;
    private javax.swing.JSlider sl_politico;
    private javax.swing.JTextField txt_nick;
    // End of variables declaration//GEN-END:variables
}