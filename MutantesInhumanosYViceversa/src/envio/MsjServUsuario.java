package envio;

import entidades.Usuario;
import java.io.Serializable;

/**
 *  Objeto serializable de comunicacion con el servidor para pasar el usuario
 * 
 * @author Flor
 */
public class MsjServUsuario implements Serializable{
    
     //Variables
    private int accion = 0;
    private int codError = 0;
    private String mensaje = "";
    private Usuario usuario;

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
