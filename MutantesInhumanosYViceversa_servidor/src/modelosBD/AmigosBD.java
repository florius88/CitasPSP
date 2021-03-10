package modelosBD;

import conexionBD.ConexionBD;
import java.sql.SQLException;
import java.util.ArrayList;
import mensajes.entidades.Amigos;

/**
 *
 * @author Flor
 */
public class AmigosBD {

    /**
     * Elimina la amistad entre 2 usuarios
     *
     * @param idUsuario
     * @param idUsuarioAmigo
     * @return
     */
    public boolean eliminarAmgioByIdUsuarioIdUsuarioAmigo(int idUsuario, int idUsuarioAmigo) {

        boolean eliminado = true;

        try {

            //Se inicializa la conexion
            ConexionBD conexionBD = new ConexionBD();
            //Se abre la conexion
            conexionBD.abrirConexion();

            //Sentencia
            String sentencia = "DELETE FROM AMIGOS WHERE ID_USUARIO = " + idUsuario + " AND ID_USUARIO_AMIGO = " + idUsuarioAmigo;

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

    /**
     * Crea una amistad entre 2 usuarios
     *
     * @param idUsuario
     * @param idUsuarioAmigo
     * @return
     */
    public boolean insertarAmistadByIdUsuarioIdUsuarioAmigo(int idUsuario, int idUsuarioAmigo) {

        boolean insertado = true;

        try {

            //Se inicializa la conexion
            ConexionBD conexionBD = new ConexionBD();
            //Se abre la conexion
            conexionBD.abrirConexion();

            //Sentencia
            String sentencia = "INSERT INTO AMIGOS" + " VALUES (" + idUsuario + "," + idUsuarioAmigo + ")";

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
     * Devuelve una lista con los Id de los usuarios amigos
     *
     * @param idUsuario
     * @return
     */
    public ArrayList<Amigos> obtenerListaAmigosPorIdUsuario(int idUsuario) {

        ArrayList<Amigos> listaAmigos = new ArrayList<>();

        try {

            //Se inicializa la conexion
            ConexionBD conexionBD = new ConexionBD();
            //Se abre la conexion
            java.sql.Statement sentencia_SQL = conexionBD.abrirConexion();

            //Sentencia
            String sentencia = "SELECT * FROM AMIGOS WHERE ID_USUARIO = " + idUsuario;

            java.sql.ResultSet conj_Registros = sentencia_SQL.executeQuery(sentencia);

            while (conj_Registros.next()) {
                Amigos amigosBD = new Amigos();

                int idUsuarioAmigoBD = conj_Registros.getInt("ID_USUARIO_AMIGO");
                amigosBD.setIdUsuario(idUsuarioAmigoBD);

                //Incluye los usuarios
                listaAmigos.add(amigosBD);
            }

            //Cierra la conexion
            conexionBD.cerrarConexion();

        } catch (SQLException ex) {

        }

        return listaAmigos;
    }

    /**
     * Devuelve si hay amistad entre ambos usuarios
     *
     * @param idUsuario
     * @param idUsuarioAmigo
     * @return
     */
    public boolean obtenerAmistadByIdUsuarioIdUsuarioAmigo(int idUsuario, int idUsuarioAmigo) {

        boolean conectado = false;

        try {

            //Se inicializa la conexion
            ConexionBD conexionBD = new ConexionBD();
            //Se abre la conexion
            java.sql.Statement sentencia_SQL = conexionBD.abrirConexion();

            //Sentencia
            String sentencia = "SELECT * FROM AMIGOS WHERE ID_USUARIO = " + idUsuario + " AND ID_USUARIO_AMIGO = " + idUsuarioAmigo;

            java.sql.ResultSet conj_Registros = sentencia_SQL.executeQuery(sentencia);
            while (conj_Registros.next()) {
                conectado = true;
            }
            //Cierra la conexion
            conexionBD.cerrarConexion();

        } catch (SQLException sq) {
            conectado = false;
        }

        return conectado;
    }
}
