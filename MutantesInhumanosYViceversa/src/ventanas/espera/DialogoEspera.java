/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas.espera;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.AbstractButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

/**
 *
 * @author Flor
 */
public class DialogoEspera {

    private JDialog dialog;

    public void makeWait(String msg, ActionEvent evt) {

        java.awt.Window win = SwingUtilities.getWindowAncestor((AbstractButton) evt.getSource());
        dialog = new JDialog(win, msg, java.awt.Dialog.ModalityType.APPLICATION_MODAL);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(progressBar, BorderLayout.CENTER);

        panel.setBackground(new Color(221, 167, 181));

        java.awt.Font fuente = new java.awt.Font("Book Antiqua", 1, 20);
        JLabel label = new JLabel("  Cargando.......  ");
        label.setFont(fuente);

        panel.add(label, BorderLayout.PAGE_START);
        dialog.add(panel);
        //Elimina el boton de la X
        //dialog.setUndecorated(true);
        dialog.pack();
        dialog.setLocationRelativeTo(win);
        dialog.setVisible(true);
    }

    public void makeWaitMouseLabel(String msg, MouseEvent evt) {

        java.awt.Window win = SwingUtilities.getWindowAncestor((JLabel) evt.getSource());
        dialog = new JDialog(win, msg, java.awt.Dialog.ModalityType.APPLICATION_MODAL);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(progressBar, BorderLayout.CENTER);

        panel.setBackground(new Color(221, 167, 181));

        java.awt.Font fuente = new java.awt.Font("Book Antiqua", 1, 20);
        JLabel label = new JLabel("  Cargando.......  ");
        label.setFont(fuente);

        panel.add(label, BorderLayout.PAGE_START);
        dialog.add(panel);
        //Elimina el boton de la X
        //dialog.setUndecorated(true);
        dialog.pack();
        dialog.setLocationRelativeTo(win);
        dialog.setVisible(true);
    }

    public void makeWaitMouseTable(String msg, MouseEvent evt) {

        java.awt.Window win = SwingUtilities.getWindowAncestor((JTable) evt.getSource());
        dialog = new JDialog(win, msg, java.awt.Dialog.ModalityType.APPLICATION_MODAL);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(progressBar, BorderLayout.CENTER);

        panel.setBackground(new Color(221, 167, 181));

        java.awt.Font fuente = new java.awt.Font("Book Antiqua", 1, 20);
        JLabel label = new JLabel("  Cargando.......  ");
        label.setFont(fuente);

        panel.add(label, BorderLayout.PAGE_START);
        dialog.add(panel);
        //Elimina el boton de la X
        //dialog.setUndecorated(true);
        dialog.pack();
        dialog.setLocationRelativeTo(win);
        dialog.setVisible(true);
    }

    public void makeWaitKeyTable(String msg, KeyEvent evt) {

        java.awt.Window win = SwingUtilities.getWindowAncestor((JTable) evt.getSource());
        dialog = new JDialog(win, msg, java.awt.Dialog.ModalityType.APPLICATION_MODAL);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(progressBar, BorderLayout.CENTER);

        panel.setBackground(new Color(221, 167, 181));

        java.awt.Font fuente = new java.awt.Font("Book Antiqua", 1, 20);
        JLabel label = new JLabel("  Cargando.......  ");
        label.setFont(fuente);

        panel.add(label, BorderLayout.PAGE_START);
        dialog.add(panel);
        //Elimina el boton de la X
        //dialog.setUndecorated(true);
        dialog.pack();
        dialog.setLocationRelativeTo(win);
        dialog.setVisible(true);
    }

    public void close() {
        dialog.dispose();
    }
}
