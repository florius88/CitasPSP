package principal;

import javax.swing.ImageIcon;

/**
 *
 * @author Flor
 */
public class PanelBuscar extends javax.swing.JPanel {

    private Principal principal = null;

    private boolean meGusta = false;
    private int posicionAfines = -1;

    /**
     * Creates new form PanelBuscar
     */
    public PanelBuscar(Principal principal) {
        initComponents();

        this.principal = principal;

        //Cargamos la informacion
        cargarDatos();
    }

    /**
     * Cargamos la informacion en la tabla
     *
     */
    private void cargarDatos() {

        // TODO -- CARGAR AFINES
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_foto = new javax.swing.JLabel();
        lbl_nick = new javax.swing.JLabel();
        lbl_txt_nick = new javax.swing.JLabel();
        lbl_me_gusta = new javax.swing.JLabel();
        lbl_flecha_izq = new javax.swing.JLabel();
        lbl_flecha_der = new javax.swing.JLabel();
        lbl_mensaje = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(155, 131, 131));
        setMinimumSize(new java.awt.Dimension(970, 510));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(970, 510));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_foto.setBackground(new java.awt.Color(180, 137, 105));
        lbl_foto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 137, 105), 2));
        lbl_foto.setOpaque(true);
        add(lbl_foto, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, 420, 360));

        lbl_nick.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_nick.setText("Nick:");
        add(lbl_nick, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, -1, -1));

        lbl_txt_nick.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_txt_nick.setText("Nombre");
        add(lbl_txt_nick, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, -1, -1));

        lbl_me_gusta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ico/ico_no_me_gusta.png"))); // NOI18N
        lbl_me_gusta.setToolTipText("Me gusta");
        lbl_me_gusta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_me_gustaMouseClicked(evt);
            }
        });
        add(lbl_me_gusta, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 430, -1, -1));

        lbl_flecha_izq.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ico/ico_flecha_izq.png"))); // NOI18N
        lbl_flecha_izq.setToolTipText("Siguiente");
        lbl_flecha_izq.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_flecha_izqMouseClicked(evt);
            }
        });
        add(lbl_flecha_izq, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));

        lbl_flecha_der.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ico/ico_flecha_der.png"))); // NOI18N
        lbl_flecha_der.setToolTipText("Anterior");
        lbl_flecha_der.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_flecha_derMouseClicked(evt);
            }
        });
        add(lbl_flecha_der, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 140, -1, -1));

        lbl_mensaje.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ico/ico_mensaje_sin_leer.png"))); // NOI18N
        lbl_mensaje.setToolTipText("Enviar mensaje");
        lbl_mensaje.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_mensajeMouseClicked(evt);
            }
        });
        add(lbl_mensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 440, -1, -1));

        jLabel1.setBackground(new java.awt.Color(232, 195, 158));
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 137, 105), 2));
        jLabel1.setOpaque(true);
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, 460, 460));
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_me_gustaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_me_gustaMouseClicked

        ImageIcon iconMegusta = null;

        if (meGusta) {
            iconMegusta = new ImageIcon(getClass().getResource("/recursos/ico/ico_no_me_gusta.png"));
            meGusta = false;
        } else {
            iconMegusta = new ImageIcon(getClass().getResource("/recursos/ico/ico_me_gusta.png"));
            meGusta = true;
        }
        lbl_me_gusta.setIcon(iconMegusta);

    }//GEN-LAST:event_lbl_me_gustaMouseClicked

    private void lbl_mensajeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_mensajeMouseClicked
        // TODO
    }//GEN-LAST:event_lbl_mensajeMouseClicked

    private void lbl_flecha_izqMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_flecha_izqMouseClicked
        // TODO
    }//GEN-LAST:event_lbl_flecha_izqMouseClicked

    private void lbl_flecha_derMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_flecha_derMouseClicked
        // TODO
    }//GEN-LAST:event_lbl_flecha_derMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbl_flecha_der;
    private javax.swing.JLabel lbl_flecha_izq;
    private javax.swing.JLabel lbl_foto;
    private javax.swing.JLabel lbl_me_gusta;
    private javax.swing.JLabel lbl_mensaje;
    private javax.swing.JLabel lbl_nick;
    private javax.swing.JLabel lbl_txt_nick;
    // End of variables declaration//GEN-END:variables
}
