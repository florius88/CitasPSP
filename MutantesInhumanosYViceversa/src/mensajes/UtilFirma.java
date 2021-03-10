/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajes;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;

/**
 *
 * @author Flor
 */
public class UtilFirma {

    public static Firma firmar(String mensaje) {
        Firma f = null;

        try {
            //La clase KeyPairGenerator nos permite gernerar el par de claves.
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
            SecureRandom numero = SecureRandom.getInstance("SHA1PRNG");
            keyGen.initialize(1024, numero);

            //Creamos el par de claves (privada y pública).
            KeyPair par = keyGen.generateKeyPair();
            PrivateKey clavepriv = par.getPrivate();
            PublicKey clavepubl = par.getPublic();

            //Firmamos con la clave privada el mensaje.
            //  - SHAwithDSA --> firma con DSA resumen con SHA.
            //  - MD5withRSA --> firma con RSA resumen con MD5.
            Signature dsa = Signature.getInstance("SHA1withDSA");
            dsa.initSign(clavepriv);

            dsa.update(mensaje.getBytes());

            byte[] firma = dsa.sign(); //Mensaje firmado.

            f = new Firma(mensaje, firma, clavepubl);

        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException ex) {
        }

        return f;
    }
}
