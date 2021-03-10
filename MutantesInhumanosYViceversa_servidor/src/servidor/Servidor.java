package servidor;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Flor
 */
public class Servidor {

    //********************* Atributos *************************
    //private static java.sql.Connection Conex;
    //Atributo a través del cual hacemos la conexión física.
    //private static java.sql.Statement Sentencia_SQL;
    //Atributo que nos permite ejecutar una sentencia SQL
    //private static java.sql.ResultSet Conj_Registros;
    //(Cursor) En él están almacenados los datos.

    //private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {

        try {
            //Cargar el driver/controlador
            /*String connectionUrl
                    = "jdbc:sqlserver://" + Constantes.SERVIDOR + ":" + Constantes.PUERTO + ";"
                    + "database=" + Constantes.DATABASE + ";"
                    + "user=" + Constantes.USER_BD + ";"
                    + "password=" + Constantes.PWD_BD + ";";

            Conex = DriverManager.getConnection(connectionUrl);
            Sentencia_SQL = Conex.createStatement();
            System.out.println("Conexion realizada con éxito");

            //TODO PRUEBA DE CARGA CON LA BD
            ArrayList<Sexo> lista = obtenerDatosTablaArrayList("sexo");
            for (SexoBD p : lista) {
                System.out.println(p);
                System.out.println(p.getDescription());
            }*/

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

    /*public static ArrayList obtenerDatosTablaArrayList(String nom_tabla) {
        ArrayList lp = new ArrayList();
        try {
            String Sentencia = "SELECT * FROM " + nom_tabla;
            Conj_Registros = Sentencia_SQL.executeQuery(Sentencia);
            while (Conj_Registros.next()) {
                lp.add(new SexoBD(Conj_Registros.getInt("CODE"), Conj_Registros.getString("DESCRIPTION")));
            }
        } catch (SQLException ex) {
        }
        return lp;
    }*/
}
