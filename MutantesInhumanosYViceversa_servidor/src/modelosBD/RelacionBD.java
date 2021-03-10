package modelosBD;

import conexionBD.ConexionBD;
import java.sql.SQLException;
import java.util.ArrayList;
import mensajes.entidades.Relacion;

/**
 *
 * @author Flor
 */
public class RelacionBD {

    /**
     * Obtiene todas las relaciones
     *
     * @return
     */
    public ArrayList<Relacion> getListaRelacion() {

        ArrayList<Relacion> listaRelacion = new ArrayList();

        try {

            //Se inicializa la conexion
            ConexionBD conexionBD = new ConexionBD();
            //Se abre la conexion
            java.sql.Statement sentencia_SQL = conexionBD.abrirConexion();

            //Sentencia
            String Sentencia = "SELECT * FROM RELACION";

            java.sql.ResultSet conj_Registros = sentencia_SQL.executeQuery(Sentencia);

            while (conj_Registros.next()) {
                //Nueva relacion
                Relacion relacionBD = new Relacion(conj_Registros.getInt("CODE"), conj_Registros.getString("DESCRIPTION"));
                //Incluye la relacion
                listaRelacion.add(relacionBD);
            }
            
            //Cierra la conexion
            conexionBD.cerrarConexion();
            
        } catch (SQLException ex) {

        }
        return listaRelacion;
    }
}
