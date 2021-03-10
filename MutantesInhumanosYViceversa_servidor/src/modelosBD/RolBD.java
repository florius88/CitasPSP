package modelosBD;

import conexionBD.ConexionBD;
import java.sql.SQLException;
import java.util.ArrayList;
import mensajes.entidades.Rol;

/**
 *
 * @author Flor
 */
public class RolBD {

    /**
     * Metodo que obtiene todos los roles
     *
     * @return
     */
    public synchronized ArrayList<Rol> getListaRol() {

        ArrayList<Rol> listaRol = new ArrayList();

        try {

            //Se inicializa la conexion
            ConexionBD conexionBD = new ConexionBD();
            //Se abre la conexion
            java.sql.Statement sentencia_SQL = conexionBD.abrirConexion();

            //Sentencia
            String Sentencia = "SELECT * FROM ROL";

            java.sql.ResultSet conj_Registros = sentencia_SQL.executeQuery(Sentencia);

            while (conj_Registros.next()) {
                //Nuevo rol
                Rol rolBD = new Rol(conj_Registros.getInt("CODE"), conj_Registros.getString("DESCRIPTION"));
                //Incluye la relacion
                listaRol.add(rolBD);
            }

            //Cierra la conexion
            conexionBD.cerrarConexion();

        } catch (SQLException ex) {

        }
        return listaRol;
    }

    /**
     * Metodo que devuelve la descripcion del rol
     *
     * @param codeRol
     * @return
     */
    public synchronized String getDescripcionRolByCodeRol(int codeRol) {

        String descripcionRol = null;

        try {

            //Se inicializa la conexion
            ConexionBD conexionBD = new ConexionBD();
            //Se abre la conexion
            java.sql.Statement sentencia_SQL = conexionBD.abrirConexion();

            //Sentencia
            String sentencia = "SELECT * FROM ROL WHERE CODE = " + codeRol;

            java.sql.ResultSet conj_Registros = sentencia_SQL.executeQuery(sentencia);
            while (conj_Registros.next()) {
                descripcionRol = conj_Registros.getString("DESCRIPTION");
            }
            //Cierra la conexion
            conexionBD.cerrarConexion();

        } catch (SQLException sq) {
            descripcionRol = null;
        }

        return descripcionRol;
    }
}
