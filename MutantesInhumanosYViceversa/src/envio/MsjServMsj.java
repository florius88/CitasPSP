package envio;

import entidades.Mensaje;
import java.io.Serializable;

/**
 *
 * @author Flor
 */
public class MsjServMsj implements Serializable {
    
     //Variables
    private int accion = 0;
    private int codError = 0;
    private String mensaje = "";
    private int idUsuario = 0;
    private Mensaje msj;

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

    public Mensaje getMsj() {
        return msj;
    }

    public void setMsj(Mensaje msj) {
        this.msj = msj;
    }

}
