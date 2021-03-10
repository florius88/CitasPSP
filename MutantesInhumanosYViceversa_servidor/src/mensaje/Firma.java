package mensaje;

import java.io.Serializable;
import java.security.PublicKey;

/**
 *
 * @author Flor
 */
public class Firma implements Serializable {

    private String mensaje;
    private byte[] firma;
    private PublicKey clavePublica;

    public Firma(String mensaje, byte[] firma, PublicKey clavePublica) {
        this.mensaje = mensaje;
        this.firma = firma;
        this.clavePublica = clavePublica;
    }

    public String getMensaje() {
        return mensaje;
    }

    public byte[] getFirma() {
        return firma;
    }

    public PublicKey getClavePublica() {
        return clavePublica;
    }

}
