package servidor;

import entidades.Sexo;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.crypto.KeyGenerator;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import mensaje.Mensaje;
import seguridad.Seguridad;

/**
 *
 * @author Flor
 */
public class Servidor {

    //********************* Atributos *************************
    private static java.sql.Connection Conex;
    //Atributo a través del cual hacemos la conexión física.
    private static java.sql.Statement Sentencia_SQL;
    //Atributo que nos permite ejecutar una sentencia SQL
    private static java.sql.ResultSet Conj_Registros;
    //(Cursor) En él están almacenados los datos.

    //private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {

        try {
            //Cargar el driver/controlador
            String connectionUrl
                    = "jdbc:sqlserver://localhost:1433;"
                    + "database=citas;"
                    + "user=sa;"
                    + "password=Abc123456;";

            Conex = DriverManager.getConnection(connectionUrl);
            Sentencia_SQL = Conex.createStatement();
            System.out.println("Conexion realizada con éxito");

            ArrayList<Sexo> lista = obtenerDatosTablaArrayList("sexo");
            for (Sexo p : lista) {
                System.out.println(p);
            }

            ServerSocket ss = new ServerSocket(9000);
            Socket cliente;
            while (true) {
                cliente = ss.accept();
                Hilo h = new Hilo(cliente);
                h.start();
            }

        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }

        /*
        //Establecemos la conexion
        ServerSocket servidor = new ServerSocket(9000);
        Socket cliente;
        System.out.println("Esperando conexion");
        cliente = servidor.accept();
        System.out.println("Cliente conectado");
        ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());

        //Generamos ambas claves
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair par = keyGen.generateKeyPair();
        PrivateKey clavepriv = par.getPrivate();
        PublicKey clavepubl = par.getPublic();
        
        //Recibimos la clave del otro extremo
        PublicKey clientKey = (PublicKey) ois.readObject();
        //Mandamos la clave publica al otro extremo
        oos.writeObject(clavepubl);
        
        //Generamos la clave simétrica.
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        kg.init(128);
        SecretKey claveSimetrica = kg.generateKey();
        //la enviamos cifrada al cliente
        oos.writeObject(Seguridad.cifrarObjeto(claveSimetrica, clientKey));
        
        try {
            String cad = "";
            while (!cad.equals("fin")) {
                Mensaje msg=(Mensaje) Seguridad.desencriptarObjetoSimetric((SealedObject)ois.readObject(),claveSimetrica);                
                cad=msg.getMsg();
                byte[]firma=msg.getFirma();                                
                System.out.println("Mensaje recibido: " + cad);                                
                System.out.print("Escribe:");
                cad = sc.nextLine();
                msg=new Mensaje(cad);
                msg.firmar(clavepriv);                
                oos.writeObject(Seguridad.cifrarObjetoSimetric(msg, claveSimetrica));                
            }
        } catch (SocketException se) {
            System.out.println("El cliente se ha desconectado");
        } finally {
            cliente.close();
        }*/
    }

    public static ArrayList obtenerDatosTablaArrayList(String nom_tabla) {
        ArrayList lp = new ArrayList();
        try {
            String Sentencia = "SELECT * FROM " + nom_tabla;
            Conj_Registros = Sentencia_SQL.executeQuery(Sentencia);
            while (Conj_Registros.next()) {
                lp.add(new Sexo(Conj_Registros.getInt("CODE"), Conj_Registros.getString("DESCRIPTION")));
            }
        } catch (SQLException ex) {
        }
        return lp;
    }
}
