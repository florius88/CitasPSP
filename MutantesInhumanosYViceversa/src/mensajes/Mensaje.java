package mensajes;

import java.io.Serializable;
import java.security.PrivateKey;
import seguridad.Seguridad;

/**
 *
 * @author Flor
 */
public class Mensaje implements Serializable{

    private String msg;
    private byte[] firma;

    public Mensaje(String msg) {
        this.msg = msg;
        this.firma = firma;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public byte[] getFirma() {
        return firma;
    }

    public void firmar(PrivateKey prk) throws Exception {
        this.firma = Seguridad.firmar(this.msg, prk);
    }

}
