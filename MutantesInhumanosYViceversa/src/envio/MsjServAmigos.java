package envio;

import entidades.Amigos;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Flor
 */
public class MsjServAmigos implements Serializable {

    //Variables
    private int accion = 0;
    private int codError = 0;
    private String mensaje = "";
    private int idUsuario = 0;
    private ArrayList<Amigos> listaAmigos = new ArrayList();

    public int getAccion() {
        return accion;
    }

    public void setAccion(int accion) {
        this.accion = accion;
    }

    public int getCodError() {
        return codError;
    }

    public void setCodError(int codError) {
        this.codError = codError;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public ArrayList<Amigos> getListaAmigos() {
        return listaAmigos;
    }

    public void setListaAmigos(ArrayList<Amigos> listaAmigos) {
        this.listaAmigos = listaAmigos;
    }
}
