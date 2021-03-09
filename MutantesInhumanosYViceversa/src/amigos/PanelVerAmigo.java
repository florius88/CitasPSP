package amigos;

import principal.Principal;

/**
 *
 * @author Flor
 */
public class PanelVerAmigo extends javax.swing.JPanel {

    private Principal principal = null;

    /**
     * Constructor
     *
     * @param principal
     */
    public PanelVerAmigo(Principal principal) {
        initComponents();

        this.principal = principal;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_conectado = new javax.swing.JLabel();
        lbl_foto = new javax.swing.JLabel();
        lbl_nick = new javax.swing.JLabel();
        lbl_txt_nick = new javax.swing.JLabel();
        lbl_relacion = new javax.swing.JLabel();
        lbl_txt_relacion = new javax.swing.JLabel();
        lbl_deporte = new javax.swing.JLabel();
        lbl_deporte_menos = new javax.swing.JLabel();
        js_deporte = new javax.swing.JSlider();
        lbl_deporte_mas = new javax.swing.JLabel();
        lbl_arte = new javax.swing.JLabel();
        lbl_arte_menos = new javax.swing.JLabel();
        js_arte = new javax.swing.JSlider();
        lbl_arte_mas = new javax.swing.JLabel();
        lbl_politica = new javax.swing.JLabel();
        lbl_politica_menos = new javax.swing.JLabel();
        js_politica = new javax.swing.JSlider();
        lbl_politica_mas = new javax.swing.JLabel();
        lbl_hijos = new javax.swing.JLabel();
        lbl_txt_hijos = new javax.swing.JLabel();
        lbl_sexo = new javax.swing.JLabel();
        lbl_txt_sexo = new javax.swing.JLabel();
        lbl_interes = new javax.swing.JLabel();
        lbl_txt_interes = new javax.swing.JLabel();
        lbl_me_gusta = new javax.swing.JLabel();
        btn_mensaje = new javax.swing.JButton();
        btn_volver = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(970, 510));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(970, 510));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_conectado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ico/ico_luz_verde.png"))); // NOI18N
        add(lbl_conectado, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, -1));

        lbl_foto.setBackground(new java.awt.Color(180, 137, 105));
        lbl_foto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 195, 158), 2));
        lbl_foto.setOpaque(true);
        add(lbl_foto, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 420, 360));

        lbl_nick.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_nick.setForeground(new java.awt.Color(255, 255, 255));
        lbl_nick.setText("Nick:");
        add(lbl_nick, new org.netbeans.lib.awtextra.AbsoluteConstraints(594, 100, -1, -1));

        lbl_txt_nick.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_txt_nick.setForeground(new java.awt.Color(255, 255, 255));
        lbl_txt_nick.setText("Nombre");
        add(lbl_txt_nick, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 100, -1, -1));

        lbl_relacion.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_relacion.setForeground(new java.awt.Color(255, 255, 255));
        lbl_relacion.setText("Relación:");
        add(lbl_relacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 140, -1, -1));

        lbl_txt_relacion.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_txt_relacion.setForeground(new java.awt.Color(255, 255, 255));
        lbl_txt_relacion.setText("Relacion");
        add(lbl_txt_relacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 140, -1, -1));

        lbl_deporte.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_deporte.setForeground(new java.awt.Color(255, 255, 255));
        lbl_deporte.setText("Deportivo:");
        add(lbl_deporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(546, 180, -1, -1));

        lbl_deporte_menos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ico/ico_menos.png"))); // NOI18N
        add(lbl_deporte_menos, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 170, -1, -1));

        js_deporte.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        js_deporte.setEnabled(false);
        add(js_deporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 180, 150, -1));

        lbl_deporte_mas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ico/ico_mas.png"))); // NOI18N
        add(lbl_deporte_mas, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 170, -1, -1));

        lbl_arte.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_arte.setForeground(new java.awt.Color(255, 255, 255));
        lbl_arte.setText("Artísticos:");
        add(lbl_arte, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 220, -1, -1));

        lbl_arte_menos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ico/ico_menos.png"))); // NOI18N
        add(lbl_arte_menos, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 210, -1, -1));

        js_arte.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        js_arte.setEnabled(false);
        add(js_arte, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 220, 150, -1));

        lbl_arte_mas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ico/ico_mas.png"))); // NOI18N
        add(lbl_arte_mas, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 210, -1, -1));

        lbl_politica.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_politica.setForeground(new java.awt.Color(255, 255, 255));
        lbl_politica.setText("Políticos:");
        add(lbl_politica, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 260, -1, -1));

        lbl_politica_menos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ico/ico_menos.png"))); // NOI18N
        add(lbl_politica_menos, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 250, -1, -1));

        js_politica.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        js_politica.setEnabled(false);
        add(js_politica, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 260, 150, -1));

        lbl_politica_mas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ico/ico_mas.png"))); // NOI18N
        add(lbl_politica_mas, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 250, -1, -1));

        lbl_hijos.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_hijos.setForeground(new java.awt.Color(255, 255, 255));
        lbl_hijos.setText("Tiene/Quiere hijos:");
        add(lbl_hijos, new org.netbeans.lib.awtextra.AbsoluteConstraints(468, 300, -1, -1));

        lbl_txt_hijos.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_txt_hijos.setForeground(new java.awt.Color(255, 255, 255));
        lbl_txt_hijos.setText("hijos");
        add(lbl_txt_hijos, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 300, -1, -1));

        lbl_sexo.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_sexo.setForeground(new java.awt.Color(255, 255, 255));
        lbl_sexo.setText("Sexo:");
        add(lbl_sexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(598, 340, -1, -1));

        lbl_txt_sexo.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_txt_sexo.setForeground(new java.awt.Color(255, 255, 255));
        lbl_txt_sexo.setText("sexo");
        add(lbl_txt_sexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 340, -1, -1));

        lbl_interes.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_interes.setForeground(new java.awt.Color(255, 255, 255));
        lbl_interes.setText("Interés en:");
        add(lbl_interes, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 378, -1, -1));

        lbl_txt_interes.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_txt_interes.setForeground(new java.awt.Color(255, 255, 255));
        lbl_txt_interes.setText("interes");
        add(lbl_txt_interes, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 378, -1, -1));

        lbl_me_gusta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ico/ico_me_gusta.png"))); // NOI18N
        lbl_me_gusta.setToolTipText("Me gusta");
        lbl_me_gusta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_me_gustaMouseClicked(evt);
            }
        });
        add(lbl_me_gusta, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 360, -1, -1));

        btn_mensaje.setBackground(new java.awt.Color(249, 246, 246));
        btn_mensaje.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        btn_mensaje.setText("Enviar mensaje");
        btn_mensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_mensajeActionPerformed(evt);
            }
        });
        add(btn_mensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(651, 19, -1, -1));

        btn_volver.setBackground(new java.awt.Color(249, 246, 246));
        btn_volver.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        btn_volver.setText("Volver");
        btn_volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_volverActionPerformed(evt);
            }
        });
        add(btn_volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(833, 19, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btn_mensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mensajeActionPerformed
        // TODO
    }//GEN-LAST:event_btn_mensajeActionPerformed

    private void btn_volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_volverActionPerformed
        // TODO
    }//GEN-LAST:event_btn_volverActionPerformed

    private void lbl_me_gustaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_me_gustaMouseClicked
        // TODO
    }//GEN-LAST:event_lbl_me_gustaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_mensaje;
    private javax.swing.JButton btn_volver;
    private javax.swing.JSlider js_arte;
    private javax.swing.JSlider js_deporte;
    private javax.swing.JSlider js_politica;
    private javax.swing.JLabel lbl_arte;
    private javax.swing.JLabel lbl_arte_mas;
    private javax.swing.JLabel lbl_arte_menos;
    private javax.swing.JLabel lbl_conectado;
    private javax.swing.JLabel lbl_deporte;
    private javax.swing.JLabel lbl_deporte_mas;
    private javax.swing.JLabel lbl_deporte_menos;
    private javax.swing.JLabel lbl_foto;
    private javax.swing.JLabel lbl_hijos;
    private javax.swing.JLabel lbl_interes;
    private javax.swing.JLabel lbl_me_gusta;
    private javax.swing.JLabel lbl_nick;
    private javax.swing.JLabel lbl_politica;
    private javax.swing.JLabel lbl_politica_mas;
    private javax.swing.JLabel lbl_politica_menos;
    private javax.swing.JLabel lbl_relacion;
    private javax.swing.JLabel lbl_sexo;
    private javax.swing.JLabel lbl_txt_hijos;
    private javax.swing.JLabel lbl_txt_interes;
    private javax.swing.JLabel lbl_txt_nick;
    private javax.swing.JLabel lbl_txt_relacion;
    private javax.swing.JLabel lbl_txt_sexo;
    // End of variables declaration//GEN-END:variables
}
