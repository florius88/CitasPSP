package mensajes;

import entidades.Adjuntos;
import entidades.Mensaje;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import principal.Principal;
import utilidades.Constantes;

/**
 *
 * @author Flor
 */
public class PanelVerMensaje extends javax.swing.JPanel {

    private Mensaje msj;
    private Principal principal = null;

    /**
     * Constructor
     *
     * @param msj
     * @param principal
     */
    public PanelVerMensaje(Mensaje msj, Principal principal) {
        initComponents();

        this.principal = principal;

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

        //Cambiamos el renderizador de las siguientes celdas para incluir imagenes
        jt_tabla_adjuntos.getColumnModel().getColumn(1).setCellRenderer(new CellRenderer());

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
     * Abre el dialogo con la imagen adjunta
     */
    private void verDocumentoAdjunto() {
        //Obtiene el nombre del documento para mostrar en el dialogo
        String txtAdj = (String) jt_tabla_adjuntos.getModel().getValueAt(jt_tabla_adjuntos.getSelectedRow(), 0);

        Adjuntos adj = msj.getListaAdjuntosEmisor().get(jt_tabla_adjuntos.getSelectedRow());

        ImageIcon icon = new ImageIcon(adj.getAdjunto());

        //Muestra el dialogo
        DialogAdjunto dialog = new DialogAdjunto(principal, true, icon);
        dialog.setTitle(txtAdj);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    /**
     * Metodo para renderizar la celda en la que queremos mostrar una imagen
     */
    private class CellRenderer implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            TableColumn tb1 = jt_tabla_adjuntos.getColumnModel().getColumn(1);
            tb1.setMaxWidth(70);
            tb1.setMinWidth(70);

            jt_tabla_adjuntos.setRowHeight(60);

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
     * Carga la informacion en el panel
     *
     * @param msj
     */
    private void cargarDatos(Mensaje msj) {

        this.msj = msj;

        String nick = msj.getNickEmisor();
        String fecha = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(msj.getFechaEnvioEmisor());
        String mensaje = msj.getMensajeEmisor();

        lbl_txt_nick.setText(nick);
        lbl_txt_fecha.setText(fecha);

        jta_mensaje.setText(mensaje);
        //Coloca el scroll arriba del todo
        jta_mensaje.setCaretPosition(0);

        if (null != msj.getListaAdjuntosEmisor() && !msj.getListaAdjuntosEmisor().isEmpty()) {

            DefaultTableModel model = (DefaultTableModel) jt_tabla_adjuntos.getModel();

            int contador = 1;
            for (Adjuntos adj : msj.getListaAdjuntosEmisor()) {

                if (null != adj) {

                    ImageIcon iconAdj = new ImageIcon(getClass().getResource(Constantes.ICO_ADJUNTOS));
                    JLabel lIcono = new JLabel(iconAdj);
                    //Muestra un ToolTip en cada imagen de la tabla
                    lIcono.setToolTipText("Doble click o Intro para ver el documento");

                    model.addRow(new Object[]{"Imagen adjunta nº: " + contador, lIcono});
                }
                contador++;
            }

            jt_tabla_adjuntos.setModel(model);
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

        btn_volver = new javax.swing.JButton();
        btn_responder = new javax.swing.JButton();
        lbl_nick = new javax.swing.JLabel();
        lbl_txt_nick = new javax.swing.JLabel();
        lbl_fecha = new javax.swing.JLabel();
        lbl_txt_fecha = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jta_mensaje = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jt_tabla_adjuntos = new javax.swing.JTable();

        setMinimumSize(new java.awt.Dimension(970, 510));
        setOpaque(false);

        btn_volver.setBackground(new java.awt.Color(249, 246, 246));
        btn_volver.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        btn_volver.setText("Volver");
        btn_volver.setToolTipText("Volver");
        btn_volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_volverActionPerformed(evt);
            }
        });

        btn_responder.setBackground(new java.awt.Color(249, 246, 246));
        btn_responder.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        btn_responder.setText("Responder");
        btn_responder.setToolTipText("Responder mensaje");
        btn_responder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_responderActionPerformed(evt);
            }
        });

        lbl_nick.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_nick.setForeground(new java.awt.Color(255, 255, 255));
        lbl_nick.setText("Nick:");

        lbl_txt_nick.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_txt_nick.setForeground(new java.awt.Color(255, 255, 255));
        lbl_txt_nick.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        lbl_fecha.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_fecha.setForeground(new java.awt.Color(255, 255, 255));
        lbl_fecha.setText("Fecha:");

        lbl_txt_fecha.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_txt_fecha.setForeground(new java.awt.Color(255, 255, 255));

        jta_mensaje.setEditable(false);
        jta_mensaje.setBackground(new java.awt.Color(232, 195, 158));
        jta_mensaje.setColumns(20);
        jta_mensaje.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        jta_mensaje.setLineWrap(true);
        jta_mensaje.setRows(5);
        jta_mensaje.setWrapStyleWord(true);
        jta_mensaje.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jta_mensaje.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(jta_mensaje);

        jt_tabla_adjuntos.setBackground(new java.awt.Color(232, 195, 158));
        jt_tabla_adjuntos.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        jt_tabla_adjuntos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Adjuntos", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jt_tabla_adjuntos.setGridColor(new java.awt.Color(255, 255, 255));
        jt_tabla_adjuntos.setRowHeight(30);
        jt_tabla_adjuntos.setSelectionBackground(new java.awt.Color(180, 137, 105));
        jt_tabla_adjuntos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jt_tabla_adjuntos.setShowGrid(true);
        jt_tabla_adjuntos.getTableHeader().setResizingAllowed(false);
        jt_tabla_adjuntos.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jt_tabla_adjuntos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_nick)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_txt_nick, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_fecha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_txt_fecha))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_responder)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_volver))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 45, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_volver)
                    .addComponent(btn_responder))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_nick, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_txt_nick)
                    .addComponent(lbl_fecha)
                    .addComponent(lbl_txt_fecha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_volverActionPerformed
        //Vuelve al panel de todos los mensajes
        principal.mostrarPanelMensajes();
    }//GEN-LAST:event_btn_volverActionPerformed

    private void btn_responderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_responderActionPerformed
        //Muestra el panel para responder el mensaje
        principal.mostrarPanelEnviarMensaje(msj, Constantes.TIPO_MENSAJE);
    }//GEN-LAST:event_btn_responderActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_responder;
    private javax.swing.JButton btn_volver;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jt_tabla_adjuntos;
    private javax.swing.JTextArea jta_mensaje;
    private javax.swing.JLabel lbl_fecha;
    private javax.swing.JLabel lbl_nick;
    private javax.swing.JLabel lbl_txt_fecha;
    private javax.swing.JLabel lbl_txt_nick;
    // End of variables declaration//GEN-END:variables
}
