package mensajes.entidades;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Flor
 */
public class Mensaje implements Serializable{
    
    private int idMensaje;
    private int idUsuarioReceptor;
    private int idUsuarioEmisor;
    private String nickEmisor;
    private String mensajeEmisor;
    private ArrayList<Adjuntos> listaAdjuntosEmisor = new ArrayList<>();
    private boolean leidoReceptor = false;
    private String fechaEnvioEmisor;

    public int getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }

    public int getIdUsuarioReceptor() {
        return idUsuarioReceptor;
    }

    public void setIdUsuarioReceptor(int idUsuarioReceptor) {
        this.idUsuarioReceptor = idUsuarioReceptor;
    }

    public int getIdUsuarioEmisor() {
        return idUsuarioEmisor;
    }

    public void setIdUsuarioEmisor(int idUsuarioEmisor) {
        this.idUsuarioEmisor = idUsuarioEmisor;
    }

    public String getNickEmisor() {
        return nickEmisor;
    }

    public void setNickEmisor(String nickEmisor) {
        this.nickEmisor = nickEmisor;
    }

    public String getMensajeEmisor() {
        return mensajeEmisor;
    }

    public void setMensajeEmisor(String mensajeEmisor) {
        this.mensajeEmisor = mensajeEmisor;
    }

    public ArrayList<Adjuntos> getListaAdjuntosEmisor() {
        return listaAdjuntosEmisor;
    }

    public void setListaAdjuntosEmisor(ArrayList<Adjuntos> listaAdjuntosEmisor) {
        this.listaAdjuntosEmisor = listaAdjuntosEmisor;
    }

    public boolean isLeidoReceptor() {
        return leidoReceptor;
    }

    public void setLeidoReceptor(boolean leidoReceptor) {
        this.leidoReceptor = leidoReceptor;
    }

    public String getFechaEnvioEmisor() {
        return fechaEnvioEmisor;
    }

    public void setFechaEnvioEmisor(String fechaEnvioEmisor) {
        this.fechaEnvioEmisor = fechaEnvioEmisor;
    }
}
