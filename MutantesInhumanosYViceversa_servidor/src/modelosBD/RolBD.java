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
     * Obtiene todos los roles
     *
     * @return
     */
    public ArrayList<Rol> getListaRol() {

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
}
