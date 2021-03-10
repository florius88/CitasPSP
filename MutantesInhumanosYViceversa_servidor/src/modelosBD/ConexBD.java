package modelosBD;

import conexionBD.ConexionBD;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Flor
 */
public class ConexBD {

    /**
     * Metodo que busca si el usuario esta conectado por Id de usuario
     *
     * @param idUsuario
     * @return
     */
    public synchronized boolean getConexionByIdUsuario(int idUsuario) {

        boolean conectado = false;

        try {

            //Se inicializa la conexion
            ConexionBD conexionBD = new ConexionBD();
            //Se abre la conexion
            java.sql.Statement sentencia_SQL = conexionBD.abrirConexion();

            //Sentencia
            String sentencia = "SELECT * FROM CONEXION WHERE ID_USUARIO = " + idUsuario;

            java.sql.ResultSet conj_Registros = sentencia_SQL.executeQuery(sentencia);
            while (conj_Registros.next()) {

                int idUsuarioBD = conj_Registros.getInt("ID_USUARIO");

                if (idUsuarioBD == idUsuario) {
                    conectado = true;
                }
            }
            //Cierra la conexion
            conexionBD.cerrarConexion();

        } catch (SQLException sq) {
            conectado = false;
        }

        return conectado;
    }

    /**
     * Metodo que inserta la conexion en la BD por Id de usuario
     *
     * @param idUsuario
     * @return
     */
    public synchronized boolean insertarConexionByIdUsuario(int idUsuario) {

        boolean insertado = true;

        try {

            //Se inicializa la conexion
            ConexionBD conexionBD = new ConexionBD();
            //Se abre la conexion
            conexionBD.abrirConexion();

            //Se pasa la fecha formateada
            String fecha = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date());

            //Sentencia
            String sentencia = "INSERT INTO CONEXION" + " VALUES (" + idUsuario + "," + "'" + fecha + "')";

            java.sql.PreparedStatement statement = conexionBD.getConex().prepareStatement(sentencia);
            int filasInsertadas = statement.executeUpdate();
            if (filasInsertadas == 0) {
                //Error al crear la informacion del usuario.
                insertado = false;
            }

            //Cierra la conexion
            conexionBD.cerrarConexion();

        } catch (SQLException sq) {
            insertado = false;
        }

        return insertado;
    }

    /**
     * Metodo que elimina la conexion para el Id de usuario
     *
     * @param idUsuario
     * @return
     */
    public synchronized boolean eliminarConexionByIdUsuario(int idUsuario) {

        boolean eliminado = true;

        try {

            //Se inicializa la conexion
            ConexionBD conexionBD = new ConexionBD();
            //Se abre la conexion
            conexionBD.abrirConexion();

            //Sentencia
            String sentencia = "DELETE FROM CONEXION WHERE ID_USUARIO = " + idUsuario;

            java.sql.PreparedStatement statement = conexionBD.getConex().prepareStatement(sentencia);
            int filasEliminadas = statement.executeUpdate();

            if (filasEliminadas == 0) {
                //Error al actualizar el usuario.
                eliminado = false;
            }

            //Cierra la conexion
            conexionBD.cerrarConexion();

        } catch (SQLException ex) {
            eliminado = false;
        }

        return eliminado;
    }
}
