package mensajes.entidades;

import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 *
 * @author Flor
 */
public class Adjuntos implements Serializable{
 
    private ImageIcon adjunto;

    public ImageIcon getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(ImageIcon adjunto) {
        this.adjunto = adjunto;
    }
}
