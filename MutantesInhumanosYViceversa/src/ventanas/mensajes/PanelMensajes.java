package ventanas.mensajes;

import mensajes.entidades.Mensaje;
import mensajes.entidades.Usuario;
import mensajes.MsjServMensajes;
import mensajes.MsjServMsj;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import ventanas.principal.Principal;
import utilidades.Constantes;

/**
 *
 * @author Flor
 */
public class PanelMensajes extends javax.swing.JPanel {

    private ArrayList<Mensaje> listaMensajes = null;
    private Principal principal = null;

    private final String COL_LEIDO = "Leido";
    private final String MSJ_NO_LEIDO = "NO";

    private final int ICONO_VACIO = 1;
    private final int ICONO_MSJ_LEIDO = 2;
    private final int ICONO_MSJ_NO_LEIDO = 3;
    private final int ICONO_ADJ = 4;

    /**
     * Constructor
     *
     * @param principal
     */
    public PanelMensajes(Principal principal) {
        initComponents();

        this.principal = principal;

        //Cambiamos preferencias de la tabla
        initTabla();
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
        jt_tabla.getColumn(COL_LEIDO).setCellRenderer(new CellRenderer());
        jt_tabla.getColumnModel().getColumn(3).setCellRenderer(new CellRenderer());

        //Se asigna un nuevo renderizador para poder cambiar de color las filas
        ColorearFilas colorear = new ColorearFilas();
        jt_tabla.setDefaultRenderer(Object.class, colorear);

        //Captura el evento de doble click para ver el mensaje
        jt_tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    verDetalleMensaje();
                }
            }
        });

        //Captura el evento del intro en el teclado para ver el mensaje
        jt_tabla.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    verDetalleMensaje();
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
     * Recoge la informacion y redirige al panel del detalle
     */
    private void verDetalleMensaje() {
        //Obtenemos el mensaje de la lista para mostrarlo
        Mensaje msj = listaMensajes.get(jt_tabla.getSelectedRow());

        //Si el mensaje no esta leido
        if (!msj.isLeidoReceptor()) {
            //se marca como leido 
            msj.setLeidoReceptor(true);

            MsjServMsj mMsj = new MsjServMsj();
            mMsj.setAccion(Constantes.ACCION_ACTUALIZAR_MSJ);
            mMsj.setMsj(msj);

            //TODO No mostramos al usuario mensaje en caso de error!!!!!!!!!!!!!
        }

        //Redirige al panel para ver el mensaje
        principal.mostrarPanelVerMensaje(msj);
    }

    /**
     * Se encarga de cambiar de color del resto de celdas en el caso de no estar
     * leido
     */
    private class ColorearFilas extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean Selected, boolean hasFocus, int row, int col) {

            super.getTableCellRendererComponent(table, value, Selected, hasFocus, row, col);

            if (table.getValueAt(row, 0) instanceof JLabel) {
                JLabel label = (JLabel) table.getValueAt(row, 0);

                if (Selected) {
                    setBackground(table.getSelectionBackground());
                    setForeground(table.getSelectionForeground());
                } else {
                    //En caso de no estar leido, le cambia el color
                    if (null != label.getName() && label.getName().equals(MSJ_NO_LEIDO)) {
                        setBackground(new Color(186, 124, 69));
                        setForeground(table.getForeground());
                    } else {
                        setBackground(table.getBackground());
                        setForeground(table.getForeground());
                    }
                }
            }

            return this;
        }
    }

    /**
     * Metodo para renderizar la celda en la que queremos mostrar una imagen
     */
    private class CellRenderer implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            TableColumn tb1 = jt_tabla.getColumn(COL_LEIDO);
            tb1.setMaxWidth(70);
            tb1.setMinWidth(70);

            TableColumn tb2 = jt_tabla.getColumnModel().getColumn(3);
            tb2.setMaxWidth(70);
            tb2.setMinWidth(70);

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
                //En caso de no estar leido, le cambia el color
                if (null != l.getName() && l.getName().equals(MSJ_NO_LEIDO)) {
                    l.setBackground(new Color(186, 124, 69));
                } else {
                    l.setBackground(t.getBackground());
                    l.setForeground(t.getForeground());
                }
            }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jt_tabla = new javax.swing.JTable();
        btn_eliminar = new javax.swing.JButton();

        setBackground(new java.awt.Color(155, 131, 131));
        setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        setMinimumSize(new java.awt.Dimension(970, 510));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(970, 510));

        jScrollPane1.setBackground(new java.awt.Color(155, 131, 131));
        jScrollPane1.setOpaque(false);

        jt_tabla.setBackground(new java.awt.Color(232, 195, 158));
        jt_tabla.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        jt_tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Leido", "Usuario", "Fecha", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
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
        jt_tabla.getTableHeader().setResizingAllowed(false);
        jt_tabla.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jt_tabla);
        if (jt_tabla.getColumnModel().getColumnCount() > 0) {
            jt_tabla.getColumnModel().getColumn(2).setResizable(false);
            jt_tabla.getColumnModel().getColumn(2).setPreferredWidth(155);
            jt_tabla.getColumnModel().getColumn(3).setResizable(false);
        }

        btn_eliminar.setBackground(new java.awt.Color(249, 246, 246));
        btn_eliminar.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.setToolTipText("Eliminar mensaje");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(110, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(110, 110, 110))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed

        DefaultTableModel model = (DefaultTableModel) jt_tabla.getModel();

        int posMensaje = jt_tabla.getSelectedRow();

        //Se valida que tenga seleccionado un mensaje
        if (posMensaje < 0 && null != listaMensajes && !listaMensajes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un mensaje de la tabla.");
        } else if (null != listaMensajes && !listaMensajes.isEmpty()) {

            int option;
            option = JOptionPane.showConfirmDialog(
                    this,
                    "¿Estás seguro de que quieres eliminar el mensaje?",
                    "Mensaje",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                //Obtenemos el mensaje de la lista para eliminarlo
                Mensaje msj = listaMensajes.get(posMensaje);

                MsjServMsj mMsj = new MsjServMsj();
                mMsj.setAccion(Constantes.ACCION_ELIMINAR_MSJ);
                mMsj.setMsj(msj);

                //Segun el codigo devuelto por el servidor redirige o muestra un mensaje
                switch (mMsj.getCodError()) {
                    case Constantes.OK:
                        //Eliminamos de la lista el mensaje
                        listaMensajes.remove(posMensaje);
                        //Eliminamos de la tabla el mensaje
                        model.removeRow(posMensaje);
                        JOptionPane.showMessageDialog(this, mMsj.getMensaje());
                        break;
                    case Constantes.ERROR_BD:
                        //Mostramos el mensaje devuelto por el servidor
                        JOptionPane.showMessageDialog(this, mMsj.getMensaje(), "Mensaje", JOptionPane.ERROR_MESSAGE);
                        break;
                }
            }
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    /**
     * Cargamos la informacion en la tabla
     *
     * @param usuario
     */
    public void cargarDatos(Usuario usuario) {

        limpiarTabla();

        MsjServMensajes mMensajes = new MsjServMensajes();
        mMensajes.setIdUsuario(usuario.getIdUsuario());

        //Segun el codigo devuelto por el servidor carga informacion o muestra un mensaje
        switch (mMensajes.getCodError()) {
            case Constantes.OK:

                if (null != mMensajes.getListaMensajes() && !mMensajes.getListaMensajes().isEmpty()) {

                    listaMensajes = mMensajes.getListaMensajes();

                    DefaultTableModel model = (DefaultTableModel) jt_tabla.getModel();

                    for (Mensaje msj : listaMensajes) {

                        if (null != msj) {

                            String nick = msj.getNickEmisor();
                            String fecha = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(msj.getFechaEnvioEmisor());

                            JLabel lLeido;
                            boolean noLeido = false;

                            if (msj.isLeidoReceptor()) {
                                lLeido = iconoTabla(ICONO_MSJ_LEIDO, noLeido);
                            } else {
                                noLeido = true;
                                lLeido = iconoTabla(ICONO_MSJ_NO_LEIDO, noLeido);
                            }

                            JLabel lAdj;

                            if (null != msj.getListaAdjuntosEmisor() && !msj.getListaAdjuntosEmisor().isEmpty()) {
                                lAdj = iconoTabla(ICONO_ADJ, noLeido);
                            } else {
                                lAdj = iconoTabla(ICONO_VACIO, noLeido);
                            }

                            model.addRow(new Object[]{lLeido, nick, fecha, lAdj});
                        }
                    }

                    jt_tabla.setModel(model);
                }

                break;
            case Constantes.ERROR_NO_MENSAJES:
                //Mostramos el mensaje devuelto por el servidor
                JOptionPane.showMessageDialog(this, mMensajes.getMensaje(), "Mensajes", JOptionPane.ERROR_MESSAGE);
                break;
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
     * Devuelve un label con el icono que se pide
     *
     * @param tipo
     * @return
     */
    private JLabel iconoTabla(int tipo, boolean noLeido) {

        JLabel lIcono = null;

        switch (tipo) {
            case ICONO_VACIO:
                lIcono = new JLabel();
                break;
            case ICONO_MSJ_LEIDO:
                ImageIcon iconLeido = new ImageIcon(getClass().getResource(Constantes.ICO_MENSAJE));
                lIcono = new JLabel(iconLeido);
                break;
            case ICONO_MSJ_NO_LEIDO:
                ImageIcon iconNoLeido = new ImageIcon(getClass().getResource(Constantes.ICO_MENSAJE_NO_LEIDO));
                lIcono = new JLabel(iconNoLeido);
                break;
            case ICONO_ADJ:
                ImageIcon iconAdj = new ImageIcon(getClass().getResource(Constantes.ICO_ADJUNTOS));
                lIcono = new JLabel(iconAdj);
                
                break;
        }

        if (null != lIcono && noLeido) {
            lIcono.setName(MSJ_NO_LEIDO);
        }
        
        //Muestra un ToolTip en cada imagen de la tabla
        lIcono.setToolTipText("Doble click o Intro para ver el mensaje");

        return lIcono;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jt_tabla;
    // End of variables declaration//GEN-END:variables

}
