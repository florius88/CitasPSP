package ventanas.amigos;

import conexion.ConexionServidor;
import mensajes.entidades.Amigos;
import mensajes.entidades.Usuario;
import mensajes.MsjServAmigos;
import mensajes.MsjServUsuario;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.util.ArrayList;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import ventanas.principal.Principal;
import utilidades.Constantes;
import ventanas.espera.DialogoEspera;

/**
 *
 * @author Flor
 */
public class PanelAmigos extends javax.swing.JPanel {

    private int idUsuario;
    private ArrayList<Amigos> listaAmigos = null;
    private Principal principal = null;

    /**
     * Constructor
     *
     * @param principal
     */
    public PanelAmigos(Principal principal) {
        initComponents();

        lbl_no_amigos.setVisible(false);
        
        this.principal = principal;

        //Cambiamos preferencias de la tabla
        initTabla();
    }

    /**
     * Metodo que cambia las preferencias de la tabla
     */
    private void initTabla() {

        //Hacemos transparente el scroll de la tabla
        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);

        //Cambiamos el tipo de letra de la cabecera de la tabla
        java.awt.Font fuente = new java.awt.Font("Book Antiqua", 1, 20);
        jt_tabla_amigos.getTableHeader().setFont(fuente);

        //Cambiamos el renderizador de las siguientes celdas para incluir imagenes
        jt_tabla_amigos.getColumn("Concectado").setCellRenderer(new CellRenderer());

        //Captura el evento de doble click para ver el detalle
        jt_tabla_amigos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    //Ventana de dialogo de espera
                    DialogoEspera wait = new DialogoEspera();

                    SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>() {

                        @Override
                        protected Void doInBackground() throws Exception {
                            //Redirige al panel detalle
                    verAmigo();
                            wait.close();
                            return null;
                        }
                    };

                    mySwingWorker.execute();
                    wait.makeWaitMouseTable("Cargando", e);
                }
            }
        });

        //Captura el evento del intro en el teclado para ver el detalle
        jt_tabla_amigos.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    //Ventana de dialogo de espera
                    DialogoEspera wait = new DialogoEspera();

                    SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>() {

                        @Override
                        protected Void doInBackground() throws Exception {
                            //Redirige al panel detalle
                    verAmigo();
                            wait.close();
                            return null;
                        }
                    };

                    mySwingWorker.execute();
                    wait.makeWaitKeyTable("Cargando", e);
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
     * Metdo que recoge la informacion y redirige al panel del detalle
     */
    private void verAmigo() {
        
        MsjServUsuario mUsuarioEnvio = new MsjServUsuario();
        mUsuarioEnvio.setAccion(Constantes.ACCION_DEVOLVER_USUARIO);
        Usuario usuario = new Usuario();
        
        Amigos amigo = listaAmigos.get(jt_tabla_amigos.getSelectedRow());
        
        usuario.setIdUsuario(amigo.getIdUsuario());
        mUsuarioEnvio.setUsuario(usuario);
        
        //Envia la informacion al servidor
        MsjServUsuario mUsuarioRecibido = (MsjServUsuario) ConexionServidor.envioObjetoServidor(mUsuarioEnvio);

        if (null != mUsuarioRecibido) {
        //Segun el codigo devuelto por el servidor redirige o muestra un mensaje
        switch (mUsuarioRecibido.getCodError()) {
            case Constantes.OK:
                //Mostramos el panel del detalle del usuario
                principal.mostrarPanelVerAmigo(mUsuarioRecibido.getUsuario(),amigo.isConectado(),idUsuario);
                break;
            case Constantes.ERROR_BD:
                //Mostramos el mensaje devuelto por el servidor
                    JOptionPane.showMessageDialog(principal, mUsuarioRecibido.getMensaje(), "Amigos", JOptionPane.ERROR_MESSAGE);
                break;
        }
        } else {
            //Mostramos el mensaje
            JOptionPane.showMessageDialog(principal, "No hay conexión con el servidor, por favor, intentelo más tarde", "Amigos", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Metodo para renderizar la celda en la que queremos mostrar una imagen
     */
    private class CellRenderer implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            TableColumn tb1 = jt_tabla_amigos.getColumn("Concectado");
            tb1.setMaxWidth(120);
            tb1.setMinWidth(120);

            jt_tabla_amigos.setRowHeight(60);

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
     * Metodo para cargar la informacion en la tabla
     *
     * @param usuario
     */
    public void cargarDatos(Usuario usuario) {

        limpiarTabla();
        
        idUsuario = usuario.getIdUsuario();

        MsjServAmigos mAmigosEnvio = new MsjServAmigos();
        mAmigosEnvio.setIdUsuario(usuario.getIdUsuario());
        mAmigosEnvio.setAccion(Constantes.ACCION_LISTA_AMIGOS);

        //Envia la informacion al servidor
        MsjServAmigos mAmigosRecibido = (MsjServAmigos) ConexionServidor.envioObjetoServidor(mAmigosEnvio);

        if (null != mAmigosRecibido) {
        //Segun el codigo devuelto por el servidor carga informacion o muestra un mensaje
        switch (mAmigosRecibido.getCodError()) {
            case Constantes.OK:
                //Ocultamos la imagen de no amigos
                lbl_no_amigos.setVisible(false);
                
                //TODO REVISAR, el servidor al dar el OK tiene que tener informacion, si no seria el otro error!!!!!!!!!!!
                if (null != mAmigosRecibido.getListaAmigos() && !mAmigosRecibido.getListaAmigos().isEmpty()) {

                    listaAmigos = mAmigosRecibido.getListaAmigos();

                    DefaultTableModel model = (DefaultTableModel) jt_tabla_amigos.getModel();

                        int amigosConectados = 0;

                    for (Amigos amigo : listaAmigos) {

                        if (null != amigo) {

                            String nick = amigo.getNick();

                            JLabel lConectado;

                            if (amigo.isConectado()) {
                                ImageIcon iconLeido = new ImageIcon(getClass().getResource(Constantes.ICO_CONECTADO));
                                lConectado = new JLabel(iconLeido);
                                    amigosConectados++;
                            } else {
                                ImageIcon iconLeido = new ImageIcon(getClass().getResource(Constantes.ICO_NO_CONECTADO));
                                lConectado = new JLabel(iconLeido);
                            }

                            model.addRow(new Object[]{nick, lConectado});
                        }
                    }

                    jt_tabla_amigos.setModel(model);

                        //Se pasa la iformacion al panel principal para refrescar la informacion
                        principal.infAmigosConectados(amigosConectados, listaAmigos.size());
                }

                break;
            case Constantes.ERROR_NO_AMIGOS:
                //Mostramos el mensaje devuelto por el servidor
                    JOptionPane.showMessageDialog(principal, mAmigosRecibido.getMensaje(), "Amigos", JOptionPane.INFORMATION_MESSAGE);
                //Ocultamos el panel
                jp_contenedor.setVisible(false);
                //Mostramos la imagen de no amigos
                lbl_no_amigos.setVisible(true);
                    //Se pasa la iformacion al panel principal para refrescar la informacion
                    principal.infAmigosConectados(0, 0);
                break;
        }
        } else {
            //Mostramos el mensaje
            JOptionPane.showMessageDialog(principal, "No hay conexión con el servidor, por favor, intentelo más tarde", "Amigos", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Limpia la tabla de todos los registros
     */
    public void limpiarTabla() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) jt_tabla_amigos.getModel();
            int filas = jt_tabla_amigos.getRowCount();
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

        lbl_no_amigos = new javax.swing.JLabel();
        jp_contenedor = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_tabla_amigos = new javax.swing.JTable();

        setBackground(new java.awt.Color(155, 131, 131));
        setMinimumSize(new java.awt.Dimension(970, 510));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(970, 510));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_no_amigos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ico/ico_no_amigos-1.png.png"))); // NOI18N
        add(lbl_no_amigos, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, -1, -1));

        jp_contenedor.setOpaque(false);
        jp_contenedor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jt_tabla_amigos.setBackground(new java.awt.Color(221, 167, 181));
        jt_tabla_amigos.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        jt_tabla_amigos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Concectado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jt_tabla_amigos.setGridColor(new java.awt.Color(255, 255, 255));
        jt_tabla_amigos.setOpaque(false);
        jt_tabla_amigos.setSelectionBackground(new java.awt.Color(159, 106, 134));
        jt_tabla_amigos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jt_tabla_amigos.setShowGrid(true);
        jt_tabla_amigos.getTableHeader().setResizingAllowed(false);
        jt_tabla_amigos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jt_tabla_amigos);

        jp_contenedor.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(194, 63, 582, 384));

        add(jp_contenedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 510));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jp_contenedor;
    private javax.swing.JTable jt_tabla_amigos;
    private javax.swing.JLabel lbl_no_amigos;
    // End of variables declaration//GEN-END:variables
}
