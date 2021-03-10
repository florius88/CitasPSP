package mensajes;

import mensajes.entidades.Usuario;
import java.io.Serializable;
import mensajes.entidades.UsuarioFirmado;

/**
 * 
 * @author Flor
 */
public class MsjServUsuario implements Serializable{
    
     //Variables
    private int accion = 0;
    private int codError = 0;
    private String mensaje = "";
    private Usuario usuario;
    private UsuarioFirmado usuarioFirmado;

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

    public UsuarioFirmado getUsuarioFirmado() {
        return usuarioFirmado;
    }

    public void setUsuarioFirmado(UsuarioFirmado usuarioFirmado) {
        this.usuarioFirmado = usuarioFirmado;
    }
}
