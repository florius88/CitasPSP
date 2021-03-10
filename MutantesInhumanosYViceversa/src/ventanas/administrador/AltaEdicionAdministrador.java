package ventanas.administrador;

import conexion.ConexionServidor;
import mensajes.entidades.Hijos;
import mensajes.entidades.Interes;
import mensajes.entidades.Relacion;
import mensajes.entidades.Rol;
import mensajes.entidades.Sexo;
import mensajes.entidades.Usuario;
import mensajes.MsjServCargaVentana;
import mensajes.MsjServUsuario;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import utilidades.Constantes;

/**
 *
 * @author Flor
 */
public class AltaEdicionAdministrador extends javax.swing.JPanel {

    private final Administrador administrador;
    private Usuario usuario = null;
    private final boolean ventanaEdicion;

    /**
     * Constructor
     *
     * @param administrador
     * @param usuario
     * @param ventanaEdicion
     */
    public AltaEdicionAdministrador(Administrador administrador, Usuario usuario, boolean ventanaEdicion) {
        initComponents();

        this.administrador = administrador;
        this.usuario = usuario;
        this.ventanaEdicion = ventanaEdicion;

        //Rellenamos los combos
        rellenarCombos();

        //Carga los datos
        cargarDatos();
    }

    /**
     * Rellena la informacion de los combos
     */
    private void rellenarCombos() {

        DefaultComboBoxModel modelRol = new DefaultComboBoxModel();
        cb_rol.setModel(modelRol);

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

        //Segun el codigo devuelto por el servidor redirige o muestra un mensaje
        switch (mServCargaVentanaRecibido.getCodError()) {
            case Constantes.OK:

                for (Rol rol : mServCargaVentanaRecibido.getListaRol()) {
                    //El SuperAdmin, nunca se muestra
                    if (rol.getId() != 1) {
                        //Rol
                        modelRol.addElement(rol);
                    }
                }
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
                JOptionPane.showMessageDialog(this, mServCargaVentanaRecibido.getMensaje(), "Administrador", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    /**
     * Carga la informacion en la pantalla
     *
     * @param titulo
     */
    private void cargarDatos() {

        //Segun el tipo de ventana se muestra un titulo u otro
        if (ventanaEdicion) {
            lbl_titulo.setText("Edición de usuario");
        } else {
            lbl_titulo.setText("Alta de usuario");
        }

        if (null != usuario) {
            //En caso de edicion

            lbl_txt_id.setText(String.valueOf(usuario.getIdUsuario()));
            //Muestra la imagen segun su estado
            cargaIconoActivoDesactivo(usuario.getActivo());

            txt_email.setText(usuario.getEmail());
            if (3 == usuario.getRol()) {
                cb_rol.setSelectedIndex(1);
                txt_nick.setText(usuario.getNick());
                //Preferencias
                cb_relacion.setSelectedIndex(usuario.getRelacion() - 1);
                sl_deporte.setValue(usuario.getDeporte());
                sl_arte.setValue(usuario.getArte());
                sl_politica.setValue(usuario.getPolitica());
                cb_hijos.setSelectedIndex(usuario.getHijos() - 1);
                cb_sexo.setSelectedIndex(usuario.getSexo() - 1);
                cb_interes.setSelectedIndex(usuario.getInteres() - 1);
            } else {
                cb_rol.setSelectedIndex(0);
            }
        } else {
            //En caso del alta
            //Carga el icono desactivado
            cargaIconoActivoDesactivo(false);
            lbl_txt_id.setText("-");
        }
    }

    /**
     * Carga la imagen que corresponde
     *
     * @param activo
     * @return
     */
    private void cargaIconoActivoDesactivo(boolean activo) {

        if (activo) {
            ImageIcon iconLeido = new ImageIcon(getClass().getResource(Constantes.IMG_USUARIO_CONECTADO));
            lbl_activo.setIcon(iconLeido);
        } else {
            //Por defecto esta desactivado
            ImageIcon iconLeido = new ImageIcon(getClass().getResource(Constantes.IMG_USUARIO_NO_CONECTADO));
            lbl_activo.setIcon(iconLeido);
        }
    }

    /**
     * Habilita o no los campos de las preferencias del usuario
     *
     * @param enable
     */
    private void enablePreferencias(boolean enable) {

        txt_nick.setEnabled(enable);
        cb_relacion.setEnabled(enable);
        cb_hijos.setEnabled(enable);
        cb_sexo.setEnabled(enable);
        cb_interes.setEnabled(enable);
        sl_deporte.setEnabled(enable);
        sl_politica.setEnabled(enable);
        sl_arte.setEnabled(enable);

        //Limpiamos la informacion
        txt_nick.setText("");

        if (0 < cb_relacion.getModel().getSize()) {
            cb_relacion.setSelectedIndex(0);
        }
        if (0 < cb_hijos.getModel().getSize()) {
            cb_hijos.setSelectedIndex(0);
        }
        if (0 < cb_sexo.getModel().getSize()) {
            cb_sexo.setSelectedIndex(0);
        }
        if (0 < cb_interes.getModel().getSize()) {
            cb_interes.setSelectedIndex(0);
        }
        sl_deporte.setValue(50);
        sl_politica.setValue(50);
        sl_arte.setValue(50);
    }

    /**
     * Obtiene la informacion de la ventana y la pasa al usuario
     */
    private void informacionVentana() {

        usuario = new Usuario();

        if (ventanaEdicion) {
            usuario.setIdUsuario(Integer.valueOf(lbl_txt_id.getText()));
        }
        //TODO pwd
        String email = txt_email.getText();
        usuario.setEmail(email);

        Rol rol = (Rol) cb_rol.getSelectedItem();
        usuario.setRol(rol.getId());

        //Usuario
        if (3 == rol.getId()) {
            //Preferencias
            String nick = txt_nick.getText();
            usuario.setNick(nick);
            Relacion relacion = (Relacion) cb_relacion.getSelectedItem();
            usuario.setRelacion(relacion.getId());
            int deporte = sl_deporte.getValue();
            usuario.setDeporte(deporte);
            int arte = sl_arte.getValue();
            usuario.setArte(arte);
            int politica = sl_politica.getValue();
            usuario.setPolitica(politica);
            Hijos hijos = (Hijos) cb_hijos.getSelectedItem();
            usuario.setHijos(hijos.getId());
            Sexo sexo = (Sexo) cb_sexo.getSelectedItem();
            usuario.setSexo(sexo.getId());
            Interes interes = (Interes) cb_interes.getSelectedItem();
            usuario.setInteres(interes.getId());
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

        lbl_titulo = new javax.swing.JLabel();
        btn_guardar = new javax.swing.JButton();
        btn_volver = new javax.swing.JButton();
        lbl_id = new javax.swing.JLabel();
        lbl_txt_id = new javax.swing.JLabel();
        lbl_email = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        lbl_rol = new javax.swing.JLabel();
        cb_rol = new javax.swing.JComboBox<>();
        lbl_activo = new javax.swing.JLabel();
        jp_inf_usuario = new javax.swing.JPanel();
        lbl_titulo_inf = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lbl_nick = new javax.swing.JLabel();
        txt_nick = new javax.swing.JTextField();
        lbl_relacion = new javax.swing.JLabel();
        cb_relacion = new javax.swing.JComboBox<>();
        lbl_hijos = new javax.swing.JLabel();
        cb_hijos = new javax.swing.JComboBox<>();
        lbl_sexo = new javax.swing.JLabel();
        cb_sexo = new javax.swing.JComboBox<>();
        lbl_interes = new javax.swing.JLabel();
        cb_interes = new javax.swing.JComboBox<>();
        lbl_deporte = new javax.swing.JLabel();
        lbl_deporte_0 = new javax.swing.JLabel();
        sl_deporte = new javax.swing.JSlider();
        lbl_deporte_100 = new javax.swing.JLabel();
        lbl_politica = new javax.swing.JLabel();
        lbl_politica_0 = new javax.swing.JLabel();
        sl_politica = new javax.swing.JSlider();
        lbl_politica_100 = new javax.swing.JLabel();
        lbl_arte = new javax.swing.JLabel();
        lbl_arte_0 = new javax.swing.JLabel();
        sl_arte = new javax.swing.JSlider();
        lbl_arte_100 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(1010, 600));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1010, 600));

        lbl_titulo.setFont(new java.awt.Font("Book Antiqua", 1, 30)); // NOI18N
        lbl_titulo.setText("Titulo panel");

        btn_guardar.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        btn_guardar.setText("Guardar");
        btn_guardar.setToolTipText("Guardar usuario");
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });

        btn_volver.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        btn_volver.setText("Volver");
        btn_volver.setToolTipText("Volver");
        btn_volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_volverActionPerformed(evt);
            }
        });

        lbl_id.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_id.setText("Id:");

        lbl_txt_id.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_txt_id.setText("id");

        lbl_email.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_email.setText("Email:");

        txt_email.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N

        lbl_rol.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_rol.setText("Rol:");

        cb_rol.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        cb_rol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_rolActionPerformed(evt);
            }
        });

        lbl_activo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ico/user_activado.png"))); // NOI18N

        lbl_titulo_inf.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_titulo_inf.setText("Preferencias");

        lbl_nick.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_nick.setText("Nick:");

        txt_nick.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N

        lbl_relacion.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_relacion.setText("Relación:");

        cb_relacion.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N

        lbl_hijos.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_hijos.setText("Tiene/Quiere hijos:");

        cb_hijos.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N

        lbl_sexo.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_sexo.setText("Sexo:");

        cb_sexo.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N

        lbl_interes.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_interes.setText("Interés en:");

        cb_interes.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N

        lbl_deporte.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_deporte.setText("Deportivos:");

        lbl_deporte_0.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_deporte_0.setText("0");

        sl_deporte.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N

        lbl_deporte_100.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_deporte_100.setText("100");

        lbl_politica.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_politica.setText("Políticos:");

        lbl_politica_0.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_politica_0.setText("0");

        sl_politica.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N

        lbl_politica_100.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_politica_100.setText("100");

        lbl_arte.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_arte.setText("Artísticos:");

        lbl_arte_0.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_arte_0.setText("0");

        sl_arte.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N

        lbl_arte_100.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_arte_100.setText("100");

        javax.swing.GroupLayout jp_inf_usuarioLayout = new javax.swing.GroupLayout(jp_inf_usuario);
        jp_inf_usuario.setLayout(jp_inf_usuarioLayout);
        jp_inf_usuarioLayout.setHorizontalGroup(
            jp_inf_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_inf_usuarioLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbl_titulo_inf)
                .addGap(66, 66, 66))
            .addGroup(jp_inf_usuarioLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jp_inf_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jp_inf_usuarioLayout.createSequentialGroup()
                        .addGroup(jp_inf_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jp_inf_usuarioLayout.createSequentialGroup()
                                .addComponent(lbl_hijos)
                                .addGap(18, 18, 18)
                                .addComponent(cb_hijos, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jp_inf_usuarioLayout.createSequentialGroup()
                                .addComponent(lbl_sexo)
                                .addGap(18, 18, 18)
                                .addComponent(cb_sexo, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jp_inf_usuarioLayout.createSequentialGroup()
                                .addComponent(lbl_interes)
                                .addGap(18, 18, 18)
                                .addComponent(cb_interes, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jp_inf_usuarioLayout.createSequentialGroup()
                                .addGroup(jp_inf_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbl_nick)
                                    .addComponent(lbl_relacion))
                                .addGap(18, 18, 18)
                                .addGroup(jp_inf_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cb_relacion, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_nick, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jp_inf_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_deporte, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_arte, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_politica, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jp_inf_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_arte_0)
                            .addComponent(lbl_politica_0)
                            .addComponent(lbl_deporte_0, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jp_inf_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp_inf_usuarioLayout.createSequentialGroup()
                                .addComponent(sl_politica, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_politica_100))
                            .addGroup(jp_inf_usuarioLayout.createSequentialGroup()
                                .addComponent(sl_deporte, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_deporte_100))
                            .addGroup(jp_inf_usuarioLayout.createSequentialGroup()
                                .addComponent(sl_arte, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_arte_100)))
                        .addGap(41, 41, 41))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 887, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jp_inf_usuarioLayout.setVerticalGroup(
            jp_inf_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_inf_usuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_titulo_inf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jp_inf_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_nick)
                    .addComponent(txt_nick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jp_inf_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_inf_usuarioLayout.createSequentialGroup()
                        .addGroup(jp_inf_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_inf_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbl_relacion)
                                .addComponent(cb_relacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(sl_deporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_deporte_100))
                        .addGap(18, 18, 18)
                        .addGroup(jp_inf_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp_inf_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbl_hijos)
                                .addComponent(cb_hijos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(sl_politica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jp_inf_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbl_politica_0)
                                .addComponent(lbl_politica))
                            .addComponent(lbl_politica_100))
                        .addGap(18, 18, 18)
                        .addGroup(jp_inf_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp_inf_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cb_sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbl_arte))
                            .addComponent(lbl_sexo)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_inf_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbl_arte_100)
                                .addComponent(sl_arte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbl_arte_0, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jp_inf_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_interes)
                            .addComponent(cb_interes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jp_inf_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_deporte_0)
                        .addComponent(lbl_deporte)))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jp_inf_usuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(lbl_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_guardar)
                .addGap(18, 18, 18)
                .addComponent(btn_volver)
                .addGap(59, 59, 59))
            .addGroup(layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(lbl_activo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(lbl_id)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_txt_id))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_email)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(lbl_rol)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_rol, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(105, 105, 105))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_guardar)
                            .addComponent(btn_volver)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbl_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_id)
                            .addComponent(lbl_txt_id))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_email)
                            .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_rol)
                            .addComponent(cb_rol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lbl_activo, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jp_inf_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_volverActionPerformed
        //Ocultamos el panel
        this.setVisible(false);
        //Volvemos a mostrar el panel principal
        administrador.mostrarPanelPrincipal();
    }//GEN-LAST:event_btn_volverActionPerformed

    private void cb_rolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_rolActionPerformed
        //Recuperamos la opcion seleccionada
        Rol rol = (Rol) cb_rol.getSelectedItem();

        //Usuario
        if (3 == rol.getId()) {
            enablePreferencias(true);
        } else {
            enablePreferencias(false);
        }
    }//GEN-LAST:event_cb_rolActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        // Se pide una confirmación antes de guardar
        int option;
        option = JOptionPane.showConfirmDialog(
                this,
                "¿Estás seguro de que quieres guardar los cambios? ",
                "Adminstrador",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            //Actualiza la informacion del usuario
            informacionVentana();

            //Obtenemos la informacion de la ventana
            MsjServUsuario mUsuarioEnvio = new MsjServUsuario();

            //Editamos al usuario
            if (ventanaEdicion) {
                mUsuarioEnvio.setAccion(Constantes.ACCION_ACTUALIZAR_USUARIO);
            } else {
                mUsuarioEnvio.setAccion(Constantes.ACCION_CREAR_USUARIO);
            }
            mUsuarioEnvio.setUsuario(usuario);

            //Envia la informacion al servidor
            MsjServUsuario mUsuarioRecibido = (MsjServUsuario) ConexionServidor.envioObjetoServidor(mUsuarioEnvio);

            //Segun el codigo devuelto por el servidor redirige o muestra un mensaje
            switch (mUsuarioRecibido.getCodError()) {
                case Constantes.OK:
                    JOptionPane.showMessageDialog(this, mUsuarioRecibido.getMensaje(), "Adminstrador", JOptionPane.INFORMATION_MESSAGE);
                    //Ocultamos el panel
                    this.setVisible(false);
                    //Carga la informacion de la pantalla de nuevo
                    administrador.cargarDatos();
                    //Volvemos a mostrar el panel principal
                    administrador.mostrarPanelPrincipal();
                    break;
                case Constantes.ERROR_NO_NICK:
                case Constantes.ERROR_FORMATO_EMAIL:
                case Constantes.ERROR_BD:
                    //Mostramos el mensaje devuelto por el servidor
                    JOptionPane.showMessageDialog(this, mUsuarioRecibido.getMensaje(), "Adminstrador", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
    }//GEN-LAST:event_btn_guardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_volver;
    private javax.swing.JComboBox<String> cb_hijos;
    private javax.swing.JComboBox<String> cb_interes;
    private javax.swing.JComboBox<String> cb_relacion;
    private javax.swing.JComboBox<String> cb_rol;
    private javax.swing.JComboBox<String> cb_sexo;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel jp_inf_usuario;
    private javax.swing.JLabel lbl_activo;
    private javax.swing.JLabel lbl_arte;
    private javax.swing.JLabel lbl_arte_0;
    private javax.swing.JLabel lbl_arte_100;
    private javax.swing.JLabel lbl_deporte;
    private javax.swing.JLabel lbl_deporte_0;
    private javax.swing.JLabel lbl_deporte_100;
    private javax.swing.JLabel lbl_email;
    private javax.swing.JLabel lbl_hijos;
    private javax.swing.JLabel lbl_id;
    private javax.swing.JLabel lbl_interes;
    private javax.swing.JLabel lbl_nick;
    private javax.swing.JLabel lbl_politica;
    private javax.swing.JLabel lbl_politica_0;
    private javax.swing.JLabel lbl_politica_100;
    private javax.swing.JLabel lbl_relacion;
    private javax.swing.JLabel lbl_rol;
    private javax.swing.JLabel lbl_sexo;
    private javax.swing.JLabel lbl_titulo;
    private javax.swing.JLabel lbl_titulo_inf;
    private javax.swing.JLabel lbl_txt_id;
    private javax.swing.JSlider sl_arte;
    private javax.swing.JSlider sl_deporte;
    private javax.swing.JSlider sl_politica;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_nick;
    // End of variables declaration//GEN-END:variables
}
