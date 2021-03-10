package conexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import utilidades.Constantes;

/**
 *
 * @author Flor
 */
public class ConexionBD {

    private java.sql.Connection Conex;

    /**
     * Abre la conexion con la BD
     *
     * @return
     * @throws SQLException
     */
    public java.sql.Statement abrirConexion() throws SQLException {

        java.sql.Statement sentencia_SQL;

        //Cargar el driver/controlador
        String connectionUrl
                = "jdbc:sqlserver://" + Constantes.SERVIDOR + ":" + Constantes.PUERTO + ";"
                + "database=" + Constantes.DATABASE + ";"
                + "user=" + Constantes.USER_BD + ";"
                + "password=" + Constantes.PWD_BD + ";";

        Conex = DriverManager.getConnection(connectionUrl);
        sentencia_SQL = Conex.createStatement();
        System.out.println("Conexion realizada con éxito");

        return sentencia_SQL;
    }

    /**
     * Cierra la conexion con la BD
     *
     * @throws SQLException
     */
    public void cerrarConexion() throws SQLException {
        //Ejecutamos un commit antes de cerrar la conexion
        this.Conex.commit();
        this.Conex.close();
        System.out.println("Desconectado de la Base de Datos");
    }

    public Connection getConex() {
        return Conex;
    }
}
