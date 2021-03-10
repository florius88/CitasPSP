package ventanas.administrador;

import conexion.ConexionServidor;
import mensajes.entidades.Usuario;
import mensajes.MsjServAdmin;
import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import ventanas.login.Login;
import utilidades.Constantes;

/**
 *
 * @author Flor
 */
public class Administrador extends javax.swing.JFrame {

    private final int COL_ACTIVO = 3;

    private ArrayList<Usuario> listaUsuarios = null;
    private Usuario usuario = null;

    /**
     * Constructor
     *
     * @param usuario
     */
    public Administrador(Usuario usuario) {
        initComponents();

        this.usuario = usuario;

        //Cambiamos preferencias de la tabla
        initTabla();

        //Cargamos la informacion
        cargarDatos();

        jp_contenedor.setVisible(false);

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
     * Cambiamos preferencias de la tabla
     */
    private void initTabla() {

        //Hacemos transparente el scroll de la tabla
        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);

        //Cambiamos el tipo de letra de la cabecera de la tabla
        java.awt.Font fuente = new java.awt.Font("Book Antiqua", 1, 20);
        jt_tabla.getTableHeader().setFont(fuente);

        //Cambiamos el renderizador de las siguientes celdas para incluir imagenes
        jt_tabla.getColumn("Activo").setCellRenderer(new CellRenderer());

        //Captura el evento de doble click para ver el mensaje
        jt_tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    //Muestra la edicion del usuario
                    edicionUsuario();
                }
            }
        });

        //Captura el evento del intro en el teclado para ver el mensaje
        jt_tabla.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    //Muestra la edicion del usuario
                    edicionUsuario();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    /**
     * Metodo para renderizar la celda en la que queremos mostrar una imagen
     */
    private class CellRenderer implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            TableColumn tb1 = jt_tabla.getColumn("Activo");
            tb1.setMaxWidth(120);
            tb1.setMinWidth(120);

            jt_tabla.setRowHeight(60);

            if (value instanceof JLabel) {
                JLabel label = (JLabel) value;

                //para hacer visible la etiqueta en primer plano y fondo
                label.setOpaque(true);
                fillColor(table, label, isSelected);
                return label;
            }

            return (Component) value;
        }

        public void fillColor(JTable t, JLabel l, boolean isSelected) {
            //Establecer el fondo y el primer plano cuando se selecciona JLabel
            if (isSelected) {
                l.setBackground(t.getSelectionBackground());
                l.setForeground(t.getSelectionForeground());
            } else {
                l.setBackground(t.getBackground());
                l.setForeground(t.getForeground());
            }
        }
    }

    /**
     * Cargamos la informacion en la tabla
     *
     */
    public void cargarDatos() {

        //Limpia la tabla
        limpiarTabla();

        lbl_txt_email.setText(usuario.getEmail());

        //TODO REVISAR
        lbl_txt_rol.setText(String.valueOf(usuario.getRol()));

        //Insertamos la conexion para mostrar que esta en linea
        MsjServAdmin mAdminEnvio = new MsjServAdmin();
        mAdminEnvio.setAccion(Constantes.ACCION_CARGAR_ADMIN);
        mAdminEnvio.setIdUsuario(usuario.getIdUsuario());

        //Envia la informacion al servidor
        MsjServAdmin mAdminRecibido = (MsjServAdmin) ConexionServidor.envioObjetoServidor(mAdminEnvio);

        //Segun el codigo devuelto por el servidor carga informacion o muestra un mensaje
        switch (mAdminRecibido.getCodError()) {
            case Constantes.OK:
                if (null != mAdminRecibido.getListaUsuarios() && !mAdminRecibido.getListaUsuarios().isEmpty()) {

                    listaUsuarios = mAdminRecibido.getListaUsuarios();

                    DefaultTableModel model = (DefaultTableModel) jt_tabla.getModel();

                    for (Usuario user : listaUsuarios) {

                        if (null != user) {

                            int idUsuario = user.getIdUsuario();
                            String email = user.getEmail();
                            String rol = String.valueOf(user.getRol());

                            //Obtiene el icono para mostrar en la tabla
                            JLabel lActivo = iconoActivoDesactivo(user.getActivo());

                            model.addRow(new Object[]{idUsuario, email, rol, lActivo});
                        }
                    }

                    jt_tabla.setModel(model);
                }
                break;
            case Constantes.ERROR_NO_USUARIOS:
                break;
        }
    }

    /**
     * Devuelve un label con el icono correspondiente
     *
     * @param activo
     * @return
     */
    private JLabel iconoActivoDesactivo(boolean activo) {

        JLabel lActivo;

        if (activo) {
            ImageIcon iconLeido = new ImageIcon(getClass().getResource(Constantes.ICO_USUARIO_CONECTADO));
            lActivo = new JLabel(iconLeido);
        } else {
            ImageIcon iconLeido = new ImageIcon(getClass().getResource(Constantes.ICO_USUARIO_NO_CONECTADO));
            lActivo = new JLabel(iconLeido);
        }

        return lActivo;
    }

    /**
     * Muestra de nuevo el panel principal
     */
    public void mostrarPanelPrincipal() {

        jp_contenedor.setVisible(false);
        jp_contenedor.removeAll();
        jp_panel_principal.setVisible(true);
        jp_panel_principal.validate();
    }

    /**
     * Redirige al panel de edicion del usuario
     */
    private void edicionUsuario() {
        int posUsuario = jt_tabla.getSelectedRow();

        //Se valida que tenga seleccionado un usuario
        if (posUsuario < 0 && null != listaUsuarios && !listaUsuarios.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un usuarios de la tabla.");
        } else if (null != listaUsuarios && !listaUsuarios.isEmpty()) {

            //Oculta el panel principal
            jp_panel_principal.setVisible(false);

            AltaEdicionAdministrador altaEdicion = new AltaEdicionAdministrador(this, listaUsuarios.get(posUsuario), true);
            altaEdicion.setVisible(true);
            jp_contenedor.add(altaEdicion);
            jp_contenedor.setVisible(true);
            jp_contenedor.validate();
        }
    }

    /**
     * Limpia la tabla de todos los registros
     */
    public void limpiarTabla() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) jt_tabla.getModel();
            int filas = jt_tabla.getRowCount();
            for (int i = 0; filas > i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
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

        jp_panel_principal = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_tabla = new javax.swing.JTable();
        lbl_email = new javax.swing.JLabel();
        lbl_txt_email = new javax.swing.JLabel();
        lbl_rol = new javax.swing.JLabel();
        lbl_txt_rol = new javax.swing.JLabel();
        btn_alta = new javax.swing.JButton();
        btn_baja = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        btn_activar = new javax.swing.JButton();
        jp_contenedor = new javax.swing.JPanel();
        mb_menu = new javax.swing.JMenuBar();
        m_cerrar_sesion = new javax.swing.JMenu();
        m_salir = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Administración");
        setIconImage(getIconImage());
        setMinimumSize(new java.awt.Dimension(1010, 670));
        setResizable(false);

        jp_panel_principal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jt_tabla.setBackground(new java.awt.Color(232, 195, 158));
        jt_tabla.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        jt_tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Usuario", "Email", "Rol", "Activo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jt_tabla.setGridColor(new java.awt.Color(255, 255, 255));
        jt_tabla.setOpaque(false);
        jt_tabla.setRowHeight(30);
        jt_tabla.setSelectionBackground(new java.awt.Color(180, 137, 105));
        jt_tabla.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jt_tabla.setShowGrid(true);
        jScrollPane1.setViewportView(jt_tabla);

        jp_panel_principal.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 100, 910, 460));

        lbl_email.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_email.setText("Email:");
        jp_panel_principal.add(lbl_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 57, -1, -1));

        lbl_txt_email.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_txt_email.setText("Email");
        jp_panel_principal.add(lbl_txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 57, -1, -1));

        lbl_rol.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_rol.setText("Rol:");
        jp_panel_principal.add(lbl_rol, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 26, -1, -1));

        lbl_txt_rol.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_txt_rol.setText("Rol");
        jp_panel_principal.add(lbl_txt_rol, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 26, -1, -1));

        btn_alta.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        btn_alta.setText("Alta");
        btn_alta.setToolTipText("Crear usuario");
        btn_alta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_altaActionPerformed(evt);
            }
        });
        jp_panel_principal.add(btn_alta, new org.netbeans.lib.awtextra.AbsoluteConstraints(484, 34, -1, -1));

        btn_baja.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        btn_baja.setText("Baja");
        btn_baja.setToolTipText("Eliminar usuario");
        btn_baja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bajaActionPerformed(evt);
            }
        });
        jp_panel_principal.add(btn_baja, new org.netbeans.lib.awtextra.AbsoluteConstraints(567, 34, -1, -1));

        btn_editar.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        btn_editar.setText("Editar");
        btn_editar.setToolTipText("Editar usuario");
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });
        jp_panel_principal.add(btn_editar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 34, -1, -1));

        btn_activar.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        btn_activar.setText("Activar/Desactivar");
        btn_activar.setToolTipText("Activar/Desactivar usuario");
        btn_activar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_activarActionPerformed(evt);
            }
        });
        jp_panel_principal.add(btn_activar, new org.netbeans.lib.awtextra.AbsoluteConstraints(749, 34, -1, -1));

        jp_contenedor.setMinimumSize(new java.awt.Dimension(1010, 600));
        jp_contenedor.setPreferredSize(new java.awt.Dimension(1010, 600));
        jp_contenedor.setRequestFocusEnabled(false);
        jp_contenedor.setLayout(new java.awt.BorderLayout());

        m_cerrar_sesion.setText("Cerrar sesión");
        m_cerrar_sesion.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        m_cerrar_sesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                m_cerrar_sesionMouseClicked(evt);
            }
        });
        mb_menu.add(m_cerrar_sesion);

        m_salir.setText("Salir");
        m_salir.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        m_salir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                m_salirMouseClicked(evt);
            }
        });
        mb_menu.add(m_salir);

        setJMenuBar(mb_menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jp_panel_principal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jp_contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jp_panel_principal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jp_contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void m_cerrar_sesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m_cerrar_sesionMouseClicked
        // Se pide una confirmación antes de cerrar la sesion
        int option;
        option = JOptionPane.showConfirmDialog(
                this,
                "¿Estás seguro de que quieres cerrar sesión?",
                "Cerrar sesión",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            //Ocultamos la ventana actual
            this.setVisible(false);

            //Inicializamos la ventana de login y la mostramos
            Login login = new Login();
            login.setLocationRelativeTo(null);
            login.setVisible(true);
        }
    }//GEN-LAST:event_m_cerrar_sesionMouseClicked

    private void m_salirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m_salirMouseClicked
        int option;
        option = JOptionPane.showConfirmDialog(
                this,
                "¿Estás seguro de que quieres cerrar la aplicación?",
                "Salir",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_m_salirMouseClicked

    private void btn_bajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bajaActionPerformed
        DefaultTableModel model = (DefaultTableModel) jt_tabla.getModel();

        int posUsuario = jt_tabla.getSelectedRow();

        //Se valida que tenga seleccionado un usuario
        if (posUsuario < 0 && null != listaUsuarios && !listaUsuarios.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un usuarios de la tabla.");
        } else if (null != listaUsuarios && !listaUsuarios.isEmpty()) {
            int option;
            option = JOptionPane.showConfirmDialog(
                    this,
                    "¿Estás seguro de querer dar de baja al usuario?",
                    "Administrador",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                //Obtenemos el usuario de la lista para eliminarlo
                Usuario user = listaUsuarios.get(posUsuario);

                MsjServAdmin mAdminEnvio = new MsjServAdmin();
                mAdminEnvio.setAccion(Constantes.ACCION_BAJA_USUARIO);
                mAdminEnvio.setIdUsuario(usuario.getIdUsuario());
                mAdminEnvio.getListaUsuarios().add(user);

                //Envia la informacion al servidor
                MsjServAdmin mAdminRecibido = (MsjServAdmin) ConexionServidor.envioObjetoServidor(mAdminEnvio);

                //Envia la informacion al servidor para eliminar
                //mAdmin = (MsjServAdmin) servidor.recepcion(mAdmin);
                //Segun el codigo devuelto por el servidor redirige o muestra un mensaje
                switch (mAdminRecibido.getCodError()) {
                    case Constantes.OK:
                        //Eliminamos de la lista al usuario
                        listaUsuarios.remove(posUsuario);
                        //Eliminamos de la tabla al usuario
                        model.removeRow(posUsuario);
                        JOptionPane.showMessageDialog(this, mAdminRecibido.getMensaje());
                        break;
                    case Constantes.ERROR_BD:
                        //Mostramos el mensaje devuelto por el servidor
                        JOptionPane.showMessageDialog(this, mAdminRecibido.getMensaje(), "Administrador", JOptionPane.ERROR_MESSAGE);
                        break;
                }
            }
        }
    }//GEN-LAST:event_btn_bajaActionPerformed

    private void btn_activarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_activarActionPerformed
        DefaultTableModel model = (DefaultTableModel) jt_tabla.getModel();

        int posUsuario = jt_tabla.getSelectedRow();

        //Se valida que tenga seleccionado un usuario
        if (posUsuario < 0 && null != listaUsuarios && !listaUsuarios.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un usuarios de la tabla.");
        } else if (null != listaUsuarios && !listaUsuarios.isEmpty()) {
            //Obtenemos el usuario de la lista para eliminarlo
            Usuario user = listaUsuarios.get(posUsuario);

            String mensajeDialog;

            //Segun su estado se muestra un mensaje u otro
            if (user.getActivo()) {
                mensajeDialog = "¿Estás seguro de querer desactivar al usuario?";
            } else {
                mensajeDialog = "¿Estás seguro de querer activar al usuario?";
            }

            int option;
            option = JOptionPane.showConfirmDialog(
                    this,
                    mensajeDialog,
                    "Administrador",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                MsjServAdmin mAdminEnvio = new MsjServAdmin();
                mAdminEnvio.setAccion(Constantes.ACCION_ACTIVAR_USUARIO);
                mAdminEnvio.setIdUsuario(usuario.getIdUsuario());

                //Se cambia el valor de su estado
                if (user.getActivo()) {
                    user.setActivo(false);
                } else {
                    user.setActivo(true);
                }

                mAdminEnvio.getListaUsuarios().add(user);

                //Envia la informacion al servidor
                MsjServAdmin mAdminRecibido = (MsjServAdmin) ConexionServidor.envioObjetoServidor(mAdminEnvio);

                //Segun el codigo devuelto por el servidor redirige o muestra un mensaje
                switch (mAdminRecibido.getCodError()) {
                    case Constantes.OK:
                        //Actualizamos la informacion del usuario en la lista
                        listaUsuarios.get(posUsuario).setActivo(user.getActivo());
                        //Mostramos en nuevo icono en la tabla
                        JLabel lActivo = iconoActivoDesactivo(user.getActivo());
                        model.setValueAt(lActivo, posUsuario, COL_ACTIVO);
                        //Mostramos el mensaje devuelto por el servidor
                        JOptionPane.showMessageDialog(this, mAdminRecibido.getMensaje());
                        break;
                    case Constantes.ERROR_BD:
                        //Mostramos el mensaje devuelto por el servidor
                        JOptionPane.showMessageDialog(this, mAdminRecibido.getMensaje(), "Administrador", JOptionPane.ERROR_MESSAGE);
                        break;
                }
            }
        }
    }//GEN-LAST:event_btn_activarActionPerformed

    private void btn_altaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_altaActionPerformed
        //Oculta el panel principal
        jp_panel_principal.setVisible(false);

        AltaEdicionAdministrador altaEdicion = new AltaEdicionAdministrador(this, null, false);
        altaEdicion.setVisible(true);
        jp_contenedor.add(altaEdicion);
        jp_contenedor.setVisible(true);
        jp_contenedor.validate();
    }//GEN-LAST:event_btn_altaActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        //Muestra la edicion del usuario
        edicionUsuario();
    }//GEN-LAST:event_btn_editarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_activar;
    private javax.swing.JButton btn_alta;
    private javax.swing.JButton btn_baja;
    private javax.swing.JButton btn_editar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jp_contenedor;
    private javax.swing.JPanel jp_panel_principal;
    private javax.swing.JTable jt_tabla;
    private javax.swing.JLabel lbl_email;
    private javax.swing.JLabel lbl_rol;
    private javax.swing.JLabel lbl_txt_email;
    private javax.swing.JLabel lbl_txt_rol;
    private javax.swing.JMenu m_cerrar_sesion;
    private javax.swing.JMenu m_salir;
    private javax.swing.JMenuBar mb_menu;
    // End of variables declaration//GEN-END:variables
}
