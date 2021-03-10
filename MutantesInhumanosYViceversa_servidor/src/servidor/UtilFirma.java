
package servidor;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

/**
 *
 * @author Flor
 */
public class UtilFirma {

    public static boolean verificarFirma(String mensaje, byte[] firma, PublicKey clavepubl) {
        boolean verificada = false;

        try {
            //Firmamos con la clave privada el mensaje.
            //  - SHAwithDSA --> firma con DSA resumen con SHA.
            //  - MD5withRSA --> firma con RSA resumen con MD5.
            Signature verifica = Signature.getInstance("SHA1withDSA");
            verifica.initVerify(clavepubl);

            verifica.update(mensaje.getBytes());
            boolean check = verifica.verify(firma);
            if (check) {
                verificada = true;
            } else {
                verificada = false;
            }

        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException ex) {
        }

        return verificada;
    }
}
