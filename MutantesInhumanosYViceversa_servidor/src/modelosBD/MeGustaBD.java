package modelosBD;

import conexionBD.ConexionBD;
import java.sql.SQLException;

/**
 *
 * @author Flor
 */
public class MeGustaBD {

    /**
     * Metodo que obtiene si existe relacion entre ambos usuarios
     *
     * @param idUsuario
     * @param idUsuarioAmigo
     * @return
     */
    public synchronized boolean obtenerMeGustaByIdUsuarioIdUsuarioAmigo(int idUsuario, int idUsuarioAmigo) {

        boolean conectado = false;

        try {

            //Se inicializa la conexion
            ConexionBD conexionBD = new ConexionBD();
            //Se abre la conexion
            java.sql.Statement sentencia_SQL = conexionBD.abrirConexion();

            //Sentencia
            String sentencia = "SELECT * FROM ME_GUSTA WHERE ID_USUARIO = " + idUsuario + " AND ID_USUARIO_GUSTA = " + idUsuarioAmigo;

            java.sql.ResultSet conj_Registros = sentencia_SQL.executeQuery(sentencia);
            while (conj_Registros.next()) {

                int idUsuarioBD = conj_Registros.getInt("ID_USUARIO");
                int idUsuarioGustaBD = conj_Registros.getInt("ID_USUARIO_GUSTA");

                if (idUsuarioBD == idUsuario && idUsuarioGustaBD == idUsuarioAmigo) {
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
     * Metodo que crea una relacion entre 2 usuarios
     *
     * @param idUsuario
     * @param idUsuarioAmigo
     * @return
     */
    public synchronized boolean insertarMeGustaByIdUsuarioIdUsuarioAmigo(int idUsuario, int idUsuarioAmigo) {

        boolean insertado = true;

        try {

            //Se inicializa la conexion
            ConexionBD conexionBD = new ConexionBD();
            //Se abre la conexion
            conexionBD.abrirConexion();

            //Sentencia
            String sentencia = "INSERT INTO ME_GUSTA" + " VALUES (" + idUsuario + "," + idUsuarioAmigo + ")";

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
     * Metodo que elimina la relacion entre 2 usuarios
     *
     * @param idUsuario
     * @param idUsuarioAmigo
     * @return
     */
    public synchronized boolean eliminarMeGustaByIdUsuarioIdUsuarioAmigo(int idUsuario, int idUsuarioAmigo) {

        boolean eliminado = true;

        try {

            //Se inicializa la conexion
            ConexionBD conexionBD = new ConexionBD();
            //Se abre la conexion
            conexionBD.abrirConexion();

            //Sentencia
            String sentencia = "DELETE FROM ME_GUSTA WHERE ID_USUARIO = " + idUsuario + " AND ID_USUARIO_GUSTA = " + idUsuarioAmigo;

            java.sql.PreparedStatement statement = conexionBD.getConex().prepareStatement(sentencia);
            statement.executeUpdate();

            //Cierra la conexion
            conexionBD.cerrarConexion();

        } catch (SQLException ex) {
            eliminado = false;
        }

        return eliminado;
    }

    /**
     * Elimina los me gusta que tenia el usuario
     *
     * @param idUsuarioAmigo
     * @return
     */
    public synchronized boolean eliminarMeGustaByIdUsuarioAmigo(int idUsuarioAmigo) {

        boolean eliminado = true;

        try {

            //Se inicializa la conexion
            ConexionBD conexionBD = new ConexionBD();
            //Se abre la conexion
            conexionBD.abrirConexion();

            //Sentencia
            String sentencia = "DELETE FROM ME_GUSTA WHERE ID_USUARIO_GUSTA = " + idUsuarioAmigo;

            java.sql.PreparedStatement statement = conexionBD.getConex().prepareStatement(sentencia);
            statement.executeUpdate();

            //Cierra la conexion
            conexionBD.cerrarConexion();

        } catch (SQLException ex) {
            eliminado = false;
        }

        return eliminado;
    }
}
