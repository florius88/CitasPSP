package mensajes.entidades;

import java.io.Serializable;

/**
 *
 * @author Flor
 */
public class UsuarioFirmado implements Serializable{

    private byte[] emailFirmado;
    private byte[] pwdResumen;

    public byte[] getEmailFirmado() {
        return emailFirmado;
    }

    public void setEmailFirmado(byte[] emailFirmado) {
        this.emailFirmado = emailFirmado;
    }

    public byte[] getPwdResumen() {
        return pwdResumen;
    }

    public void setPwdResumen(byte[] pwdResumen) {
        this.pwdResumen = pwdResumen;
    }
}
