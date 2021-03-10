package modelosBD;

import conexionBD.ConexionBD;
import java.sql.SQLException;
import java.util.ArrayList;
import mensajes.entidades.Hijos;

/**
 *
 * @author Flor
 */
public class HijosBD {

     /**
     * Obtiene todos los hijos
     *
     * @return
     */
    public ArrayList<Hijos> getListaHijos() {

        ArrayList<Hijos> listaHijos = new ArrayList();

        try {

            //Se inicializa la conexion
            ConexionBD conexionBD = new ConexionBD();
            //Se abre la conexion
            java.sql.Statement sentencia_SQL = conexionBD.abrirConexion();

            //Sentencia
            String Sentencia = "SELECT * FROM HIJOS";

            java.sql.ResultSet conj_Registros = sentencia_SQL.executeQuery(Sentencia);

            while (conj_Registros.next()) {
                //Nuevo hijo
                Hijos hijoBD = new Hijos(conj_Registros.getInt("CODE"), conj_Registros.getString("DESCRIPTION"));
                //Incluye la relacion
                listaHijos.add(hijoBD);
            }

            //Cierra la conexion
            conexionBD.cerrarConexion();

        } catch (SQLException ex) {

        }
        return listaHijos;
    }
}
