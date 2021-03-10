package mensajes.entidades;

import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 *
 * @author Flor
 */
public class Amigos implements Serializable{
    
    private int idUsuario;
    private String nick;
    private boolean conectado = false;
    private boolean meGusta = false;
    private ImageIcon foto;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public boolean isConectado() {
        return conectado;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

    public boolean isMeGusta() {
        return meGusta;
    }

    public void setMeGusta(boolean meGusta) {
        this.meGusta = meGusta;
    }

    public ImageIcon getFoto() {
        return foto;
    }

    public void setFoto(ImageIcon foto) {
        this.foto = foto;
    }
}
