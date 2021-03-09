package mensajes;

import entidades.Adjuntos;
import entidades.Mensaje;
import entidades.Usuario;
import envio.MsjServMsj;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import principal.Principal;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import utilidades.Constantes;

/**
 *
 * @author Flor
 */
public class PanelEnviarMensaje extends javax.swing.JPanel {

    private final Mensaje msjEnviar = new Mensaje();
    private Mensaje msjRecibido;
    private Principal principal = null;
    private int ventana = 0;
    private Usuario usuario;
    private boolean conectado;
    private int idUsuario;

    /**
     * Constructor
     * 
     * @param principal
     * @param ventana
     * @param usuario
     * @param conectado
     * @param idUsuario 
     */
    public PanelEnviarMensaje(Principal principal, int ventana, Usuario usuario, boolean conectado, int idUsuario) {
        initComponents();

        this.principal = principal;
        this.ventana = ventana;
        this.usuario = usuario;
        this.conectado = conectado;
        this.idUsuario = idUsuario;
        
        //Cambiamos preferencias de la tabla
        initTabla();

        //Cargamos la informacion
        cargarDatos(null);
    }
    
    /**
     * Constructor
     *
     * @param msj
     * @param principal
     * @param ventana
     */
    public PanelEnviarMensaje(Mensaje msj, Principal principal, int ventana) {
        initComponents();

        this.principal = principal;
        this.ventana = ventana;
        
        //Cambiamos preferencias de la tabla
        initTabla();

        //Cargamos la informacion
        cargarDatos(msj);
    }

    /**
     * Cambiamos preferencias de la tabla
     */
    private void initTabla() {

        //Hacemos transparente el scroll de la tabla
        jScrollPane2.setOpaque(false);
        jScrollPane2.getViewport().setOpaque(false);

        //Cambiamos el tipo de letra de la cabecera de la tabla
        java.awt.Font fuente = new java.awt.Font("Book Antiqua", 1, 20);
        jt_tabla_adjuntos.getTableHeader().setFont(fuente);

        //Captura el evento de doble click para ver el mensaje
        jt_tabla_adjuntos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    verDocumentoAdjunto();
                }
            }
        });

        //Captura el evento del intro en el teclado para ver el mensaje
        jt_tabla_adjuntos.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    verDocumentoAdjunto();
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
     * Muestra el dialogo con el documento adjuntado
     */
    private void verDocumentoAdjunto() {
        //Obtiene el nombre del documento para mostrar en el dialogo
        String txtAdj = (String) jt_tabla_adjuntos.getModel().getValueAt(jt_tabla_adjuntos.getSelectedRow(), 0);

        Adjuntos adj = msjEnviar.getListaAdjuntosEmisor().get(jt_tabla_adjuntos.getSelectedRow());

        ImageIcon icon = new ImageIcon(adj.getAdjunto());

        //Muestra el dialogo
        DialogAdjunto dialog = new DialogAdjunto(principal, true, icon);
        dialog.setTitle(txtAdj);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    /**
     * Carga informacion en el panel
     *
     * @param msj
     */
    private void cargarDatos(Mensaje msj) {
        if (null != msj) {
            this.msjRecibido = msj;
            String nick = msjRecibido.getNickEmisor();
            lbl_txt_nick.setText(nick);

            //El emisor del mensaje es el usuario que lo recibio
            msjEnviar.setIdUsuarioEmisor(msjRecibido.getIdUsuarioReceptor());
            //El receptor del mensaje es el usuario que lo envio
            msjEnviar.setIdUsuarioReceptor(msjRecibido.getIdUsuarioEmisor());
            msjEnviar.setNickEmisor(nick);
            
        } else if (null != usuario){
            
            String nick = usuario.getNick();
            lbl_txt_nick.setText(nick);

            //El emisor del mensaje es el usuario que lo recibio
            msjEnviar.setIdUsuarioEmisor(idUsuario);
            //El receptor del mensaje es el usuario que lo envio
            msjEnviar.setIdUsuarioReceptor(usuario.getIdUsuario());
            msjEnviar.setNickEmisor(nick);
        }
    }

    /**
     * Metodo encargado de redirigir al panel que corresponda
     */
    private void volver() {
        switch (ventana) {
            case Constantes.TIPO_MENSAJE:
                principal.mostrarPanelVerMensaje(msjRecibido);
                break;
            case Constantes.TIPO_BUSQUEDA:
                principal.mostrarPanelBusqueda();
                break;
            case Constantes.TIPO_AMIGOS:
                principal.mostrarPanelVerAmigo(usuario,conectado,idUsuario);
                break;
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

        lbl_nick = new javax.swing.JLabel();
        lbl_txt_nick = new javax.swing.JLabel();
        btn_enviar = new javax.swing.JButton();
        btn_volver = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jta_mensaje = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jt_tabla_adjuntos = new javax.swing.JTable();
        btn_adjuntar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(970, 510));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(970, 510));

        lbl_nick.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_nick.setForeground(new java.awt.Color(255, 255, 255));
        lbl_nick.setText("Nick:");

        lbl_txt_nick.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_txt_nick.setForeground(new java.awt.Color(255, 255, 255));
        lbl_txt_nick.setText("jLabel2");

        btn_enviar.setBackground(new java.awt.Color(249, 246, 246));
        btn_enviar.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        btn_enviar.setText("Enviar");
        btn_enviar.setToolTipText("Enviar el mensaje");
        btn_enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_enviarActionPerformed(evt);
            }
        });

        btn_volver.setBackground(new java.awt.Color(249, 246, 246));
        btn_volver.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        btn_volver.setText("Volver");
        btn_volver.setToolTipText("Volver");
        btn_volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_volverActionPerformed(evt);
            }
        });

        jta_mensaje.setBackground(new java.awt.Color(232, 195, 158));
        jta_mensaje.setColumns(20);
        jta_mensaje.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        jta_mensaje.setLineWrap(true);
        jta_mensaje.setRows(5);
        jta_mensaje.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jta_mensaje);

        jt_tabla_adjuntos.setBackground(new java.awt.Color(232, 195, 158));
        jt_tabla_adjuntos.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        jt_tabla_adjuntos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Adjuntos"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jt_tabla_adjuntos.setOpaque(false);
        jt_tabla_adjuntos.setRowHeight(30);
        jt_tabla_adjuntos.setSelectionBackground(new java.awt.Color(180, 137, 105));
        jt_tabla_adjuntos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jt_tabla_adjuntos.getTableHeader().setResizingAllowed(false);
        jt_tabla_adjuntos.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jt_tabla_adjuntos);

        btn_adjuntar.setBackground(new java.awt.Color(249, 246, 246));
        btn_adjuntar.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        btn_adjuntar.setText("Adjuntar");
        btn_adjuntar.setToolTipText("Adjuntar imagen");
        btn_adjuntar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_adjuntarActionPerformed(evt);
            }
        });

        btn_eliminar.setBackground(new java.awt.Color(249, 246, 246));
        btn_eliminar.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.setToolTipText("Eliminar imagen");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_nick)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_txt_nick)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_enviar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_volver))
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 869, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_adjuntar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_eliminar)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_nick)
                            .addComponent(lbl_txt_nick)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_volver)
                        .addComponent(btn_enviar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_eliminar)
                    .addComponent(btn_adjuntar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_volverActionPerformed
        //Redirigir al panel que corresponda
        volver();
    }//GEN-LAST:event_btn_volverActionPerformed

    private void btn_enviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_enviarActionPerformed

        //Obtiene la informacion del panel
        informacionVentana();

        MsjServMsj mMsj = new MsjServMsj();
        mMsj.setAccion(Constantes.ACCION_ENVIAR_MSJ);
        mMsj.setMsj(msjEnviar);

        //Segun el codigo devuelto por el servidor redirige o muestra un mensaje
        switch (mMsj.getCodError()) {
            case Constantes.OK:
                //Mostramos el mensaje devuelto por el servidor
                JOptionPane.showMessageDialog(this, mMsj.getMensaje());
                //Redirigir al panel que corresponda
                volver();
                break;
            case Constantes.ERROR_NO_MENSAJES:
            case Constantes.ERROR_BD:
                //Mostramos el mensaje devuelto por el servidor
                JOptionPane.showMessageDialog(this, mMsj.getMensaje(), "Mensaje", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }//GEN-LAST:event_btn_enviarActionPerformed

    private void btn_adjuntarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_adjuntarActionPerformed
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
            //Se ecala de manera suave
            Image newimg = image.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);

            Adjuntos adj = new Adjuntos();
            adj.setAdjunto(newimg);

            msjEnviar.getListaAdjuntosEmisor().add(adj);

            DefaultTableModel model = (DefaultTableModel) jt_tabla_adjuntos.getModel();

            model.addRow(new Object[]{origen});

            jt_tabla_adjuntos.setModel(model);
        }
    }//GEN-LAST:event_btn_adjuntarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        //Elimina de la tabla el documento adjunto seleccionado
        DefaultTableModel model = (DefaultTableModel) jt_tabla_adjuntos.getModel();

        int posAdj = jt_tabla_adjuntos.getSelectedRow();

        //Comprueba de que este seleccionado un documento, para quitarlo del objeto
        if (posAdj < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una imagen de la tabla.");
        } else {

            int option;
            option = JOptionPane.showConfirmDialog(
                    this,
                    "¿Estás seguro de que quieres quitar la imagen?",
                    "Mensaje",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {

                msjEnviar.getListaAdjuntosEmisor().remove(posAdj);
                model.removeRow(posAdj);
            }
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    /**
     * Obtiene la informacion de la ventana y la pasa al usuario
     */
    private void informacionVentana() {

        //Se obtiene el contenido del mensaje
        String texto = jta_mensaje.getText();
        msjEnviar.setMensajeEmisor(texto);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_adjuntar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_enviar;
    private javax.swing.JButton btn_volver;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jt_tabla_adjuntos;
    private javax.swing.JTextArea jta_mensaje;
    private javax.swing.JLabel lbl_nick;
    private javax.swing.JLabel lbl_txt_nick;
    // End of variables declaration//GEN-END:variables
}
