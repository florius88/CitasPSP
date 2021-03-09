package preferencias;

import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import principal.Principal;
import registro.Login;
import utilidades.Constantes;

/**
 *
 * @author Flor
 */
public class Preferencias extends javax.swing.JFrame {

    /**
     * Creates new form Preferencias
     *
     */
    public Preferencias() {
        initComponents();

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

        lbl_foto.setBackground(new java.awt.Color(232, 195, 158));
        lbl_foto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ico/ico_foto_perfil.png"))); // NOI18N
        lbl_foto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 137, 105), 2));
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

        lbl_deporte_menos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ico/ico_menos.png"))); // NOI18N
        getContentPane().add(lbl_deporte_menos, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 230, -1, -1));

        sl_deportivo.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        sl_deportivo.setToolTipText("a@a");
        getContentPane().add(sl_deportivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 230, 150, 35));

        lbl_deporte_mas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ico/ico_mas.png"))); // NOI18N
        getContentPane().add(lbl_deporte_mas, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 230, -1, -1));

        lbl_arte.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_arte.setForeground(new java.awt.Color(255, 255, 255));
        lbl_arte.setText("Artísticos:");
        getContentPane().add(lbl_arte, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 270, -1, -1));

        lbl_arte_menos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ico/ico_menos.png"))); // NOI18N
        getContentPane().add(lbl_arte_menos, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 270, -1, -1));

        sl_arte.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        getContentPane().add(sl_arte, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 270, 150, 35));

        lbl_arte_mas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ico/ico_mas.png"))); // NOI18N
        getContentPane().add(lbl_arte_mas, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 270, -1, -1));

        lbl_politico.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_politico.setForeground(new java.awt.Color(255, 255, 255));
        lbl_politico.setText("Políticos:");
        getContentPane().add(lbl_politico, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 310, -1, -1));

        lbl_politica_menos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ico/ico_menos.png"))); // NOI18N
        getContentPane().add(lbl_politica_menos, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 310, -1, -1));

        sl_politico.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        getContentPane().add(sl_politico, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 310, 150, 35));

        lbl_politica_mas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ico/ico_mas.png"))); // NOI18N
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

        lbl_fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/fondo_preferencias_1.jpg"))); // NOI18N
        getContentPane().add(lbl_fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -40, 640, 630));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed

        JOptionPane.showMessageDialog(this, "Guardadas las preferencias", "Preferencias", JOptionPane.INFORMATION_MESSAGE);

        //Ocultamos la ventana actual
        this.setVisible(false);

        //Inicializamos la ventana de login y la mostramos
        Principal principal = new Principal();
        principal.setLocationRelativeTo(null);
        principal.setVisible(true);

    }//GEN-LAST:event_btn_guardarActionPerformed

    private void btn_selec_fotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_selec_fotoActionPerformed
        //TODO
    }//GEN-LAST:event_btn_selec_fotoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_selec_foto;
    private javax.swing.JComboBox<String> cb_hijos;
    private javax.swing.JComboBox<String> cb_interes;
    private javax.swing.JComboBox<String> cb_relacion;
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
