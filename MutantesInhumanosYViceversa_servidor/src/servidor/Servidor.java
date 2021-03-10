package servidor;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Flor
 */
public class Servidor {

    public static void main(String[] args) throws Exception {

        try {

            ServerSocket ss = new ServerSocket(9000);
            Socket cliente;
            while (true) {
                cliente = ss.accept();
                HiloCliente hCliente = new HiloCliente(cliente);
                hCliente.start();
            }

        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
