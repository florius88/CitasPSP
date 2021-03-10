package conexion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ventanas.login.Login;

/**
 *
 * @author Flor
 */
public class ConexionServidor {

    private static InetAddress dir;
    private static Socket servidorSocket;

    /**
     * Conecta con el servidor
     */
    public static void conectarServidor() {
        try {

            dir = InetAddress.getLocalHost();
            servidorSocket = new Socket(dir, 9000);

        } catch (UnknownHostException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException io) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, io);
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

            //Envio
            ObjectOutputStream oos = new ObjectOutputStream(servidorSocket.getOutputStream());
            oos.writeObject(objEnvio);

            //Recepcion
            ObjectInputStream ois = new ObjectInputStream(servidorSocket.getInputStream());
            resultado = ois.readObject();

        } catch (ClassNotFoundException ex) {

        } catch (IOException ex) {

        }

        return resultado;
    }
}
