package mensajes;

import java.io.Serializable;

/**
 *
 * @author Flor
 */
public class MsjServConexion implements Serializable {
    
    //Variables
    private int accion = 0;
    private int codError = 0;
    private String mensaje = "";
    private int idUsuario = 0;
    private int totalUsuarios = 0;
    private int totalAmigos = 0;
    private int totalAmigosConectados = 0;

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

    public int getTotalUsuarios() {
        return totalUsuarios;
    }

    public void setTotalUsuarios(int totalUsuarios) {
        this.totalUsuarios = totalUsuarios;
    }

    public int getTotalAmigos() {
        return totalAmigos;
    }

    public void setTotalAmigos(int totalAmigos) {
        this.totalAmigos = totalAmigos;
    }

    public int getTotalAmigosConectados() {
        return totalAmigosConectados;
    }

    public void setTotalAmigosConectados(int totalAmigosConectados) {
        this.totalAmigosConectados = totalAmigosConectados;
    }
}
