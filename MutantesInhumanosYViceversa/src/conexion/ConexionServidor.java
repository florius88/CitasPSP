package conexion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import mensajes.MsjServAdmin;
import mensajes.MsjServAmigos;
import mensajes.MsjServCargaVentana;
import mensajes.MsjServConexion;
import mensajes.MsjServMensajes;
import mensajes.MsjServMsj;
import mensajes.MsjServUsuario;

/**
 *
 * @author Flor
 */
public class ConexionServidor {

    private static InetAddress dir;
    private static Socket servidorSocket;
    public static PublicKey publicaServer;
    public static PrivateKey privadaCliente;

    /**
     * Conecta con el servidor
     *
     * @throws UnknownHostException
     * @throws IOException
     */
    public static void conectarServidor() throws UnknownHostException, IOException {

        try {
            dir = InetAddress.getLocalHost();
            servidorSocket = new Socket(dir, 9000);

            //Abrimos el flujo de entrada de datos y recibimos la clave pública del servidor
            ObjectInputStream flujoE = new ObjectInputStream(servidorSocket.getInputStream());
            publicaServer = (PublicKey) flujoE.readObject();

            //System.out.println("conexion.ConexionServidor.conectarServidor() " + publicaServer.getAlgorithm());
            //Generamos el par de clave pública y privada
            KeyPairGenerator KeyGen = KeyPairGenerator.getInstance("RSA");
            KeyGen.initialize(1024);
            KeyPair par = KeyGen.generateKeyPair();
            privadaCliente = par.getPrivate();
            PublicKey clavepubl = par.getPublic();

            //Le enviamos la clave pública para que cifre
            ObjectOutputStream flujoS = new ObjectOutputStream(servidorSocket.getOutputStream());
            flujoS.writeObject(clavepubl);

        } catch (Exception e) {

        }
    }

    /**
     * Envia un objeto al servidor y devuelve la respuesta
     *
     * @param objEnvio
     * @return
     */
    public static Object envioObjetoServidor(Object objEnvio) {

        Object resultado = null;

        try {

            if (null == servidorSocket) {
                servidorSocket = null;
                //Se reintenta la conexion
                conectarServidor();
            } else {

                //Instanciamos el Cipher y lo inicializamos en el modo encriptación con la clave pública del servidor
                Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                c.init(Cipher.ENCRYPT_MODE, publicaServer);

                //Envio del objeto al servidor
                ObjectOutputStream oos = new ObjectOutputStream(servidorSocket.getOutputStream());
                oos.writeObject(objEnvio);

                /*if (objEnvio instanceof MsjServCargaVentana) {

                    SealedObject sealedObject = new SealedObject((MsjServCargaVentana) objEnvio, c);
                    ObjectOutputStream flujoS = new ObjectOutputStream(servidorSocket.getOutputStream());
                    flujoS.writeObject(sealedObject);

                } else if (objEnvio instanceof MsjServUsuario) {

                    SealedObject sealedObject = new SealedObject(((MsjServUsuario) objEnvio), c);
                    ObjectOutputStream flujoS = new ObjectOutputStream(servidorSocket.getOutputStream());
                    flujoS.writeObject(sealedObject);

                } else if (objEnvio instanceof MsjServMensajes) {

                    SealedObject sealedObject = new SealedObject((MsjServMensajes) objEnvio, c);
                    ObjectOutputStream flujoS = new ObjectOutputStream(servidorSocket.getOutputStream());
                    flujoS.writeObject(sealedObject);

                } else if (objEnvio instanceof MsjServMsj) {

                    SealedObject sealedObject = new SealedObject((MsjServMsj) objEnvio, c);
                    ObjectOutputStream flujoS = new ObjectOutputStream(servidorSocket.getOutputStream());
                    flujoS.writeObject(sealedObject);

                } else if (objEnvio instanceof MsjServAmigos) {

                    SealedObject sealedObject = new SealedObject((MsjServAmigos) objEnvio, c);
                    ObjectOutputStream flujoS = new ObjectOutputStream(servidorSocket.getOutputStream());
                    flujoS.writeObject(sealedObject);

                } else if (objEnvio instanceof MsjServConexion) {

                    SealedObject sealedObject = new SealedObject((MsjServConexion) objEnvio, c);
                    ObjectOutputStream flujoS = new ObjectOutputStream(servidorSocket.getOutputStream());
                    flujoS.writeObject(sealedObject);

                } else if (objEnvio instanceof MsjServAdmin) {

                    SealedObject sealedObject = new SealedObject((MsjServAdmin) objEnvio, c);
                    ObjectOutputStream flujoS = new ObjectOutputStream(servidorSocket.getOutputStream());
                    flujoS.writeObject(sealedObject);
                }*/

                //Recepcion del objeto desde el servidor
                ObjectInputStream ois = new ObjectInputStream(servidorSocket.getInputStream());
                resultado = ois.readObject();
            }

        } catch (ClassNotFoundException ex) {

        } catch (IOException ex) {
            try {
                //Se reintenta la conexion
                conectarServidor();
            } catch (IOException ex1) {
            }
        } catch (NoSuchAlgorithmException ex) {

        } catch (NoSuchPaddingException ex) {

        } catch (InvalidKeyException ex) {

        }

        return resultado;
    }
}
