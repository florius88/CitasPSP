package seguridad;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;

/**
 *
 * @author Flor
 */
public class Seguridad {
    
    public static String Hexadecimal(byte []resumen){
        String hex="";
        for (int i=0;i<resumen.length;i++){
            String h = Integer.toHexString(resumen[i] & 0xFF);
            if (h.length() == 1) hex+=0;
            hex+=h;
        }
        return hex;
    }
    
    public static byte[] resumir(String txt) throws Exception{
        MessageDigest md=MessageDigest.getInstance("SHA1");
        md.update(txt.getBytes());
        return md.digest();
    }
    public static byte[] firmar(String msg, PrivateKey clavePrivada) throws Exception {        
        Signature dsa = Signature.getInstance("MD5withRSA");
        dsa.initSign(clavePrivada);
        dsa.update(msg.getBytes());
        return dsa.sign(); //Mensaje firmado.
    }

    public static boolean verifica(String msg, PublicKey clavePublica, byte[] firma) throws Exception {
        Signature verifica_dsa = Signature.getInstance("MD5withRSA");
        verifica_dsa.initVerify(clavePublica);

        //msg = "Otra cosa";
        verifica_dsa.update(msg.getBytes());
        return verifica_dsa.verify(firma);
    }

    public static byte[] cifrarSimetrico(String msg,SecretKey clave) throws Exception {
        Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, clave);

        byte[] TextoPlano = msg.getBytes();
        byte[] cifrado = c.doFinal(TextoPlano);
        return cifrado;
    }
    public static String desencriptarSimetrico(byte[] cifrado,SecretKey clave) throws Exception {
        Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, clave);
        byte[] desencriptado=c.doFinal(cifrado);
        return new String(desencriptado);
    }
    
    public static byte[] cifrarAsimetrico(String msg, PublicKey clave) throws Exception{
        Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        c.init(Cipher.ENCRYPT_MODE, clave);
        byte[] TextoPlano = msg.getBytes();
        byte[] cifrado = c.doFinal(TextoPlano);
        return cifrado;
    }
    
    public static String desencriptarAsimetrico(byte[] cifrado,PrivateKey clave) throws Exception {
        Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        c.init(Cipher.DECRYPT_MODE, clave);
        byte[] desencriptado=c.doFinal(cifrado);
        return new String(desencriptado);
    }

    public static SealedObject cifrarObjeto(Object obj, PublicKey clavePublica) throws Exception {
        Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        c.init(Cipher.ENCRYPT_MODE, clavePublica);        
        return new SealedObject((Serializable) obj, c);
    }
    
    public static Object desencriptarObjeto(SealedObject objeto, PrivateKey clavepriv) throws Exception {
        Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        c.init(Cipher.DECRYPT_MODE, clavepriv);        
        return objeto.getObject(c);
    }
    
    public static SealedObject cifrarObjetoSimetric(Object obj, SecretKey claveSimetrica) throws Exception {
        Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, claveSimetrica);        
        return new SealedObject((Serializable) obj, c);
    }
    
    public static Object desencriptarObjetoSimetric(SealedObject objeto, SecretKey claveSimetrica) throws Exception {
        Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, claveSimetrica);        
        return objeto.getObject(c);
    }
}
