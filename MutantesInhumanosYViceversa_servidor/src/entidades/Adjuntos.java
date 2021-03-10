package entidades;

import java.awt.Image;
import java.io.Serializable;

/**
 *
 * @author Flor
 */
public class Adjuntos implements Serializable{
 
    private Image adjunto;

    public Image getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(Image adjunto) {
        this.adjunto = adjunto;
    }
}
