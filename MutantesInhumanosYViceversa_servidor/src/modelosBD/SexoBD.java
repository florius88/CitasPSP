package modelosBD;

import conexionBD.ConexionBD;
import java.sql.SQLException;
import java.util.ArrayList;
import mensajes.entidades.Sexo;

/**
 *
 * @author Flor
 */
public class SexoBD {

     /**
     * Obtiene todas los sexos
     *
     * @return
     */
    public ArrayList<Sexo> getListaSexo() {

        ArrayList<Sexo> listaSexo = new ArrayList();

        try {

            //Se inicializa la conexion
            ConexionBD conexionBD = new ConexionBD();
            //Se abre la conexion
            java.sql.Statement sentencia_SQL = conexionBD.abrirConexion();

            //Sentencia
            String Sentencia = "SELECT * FROM SEXO";

            java.sql.ResultSet conj_Registros = sentencia_SQL.executeQuery(Sentencia);

            while (conj_Registros.next()) {
                //Nuevo sexo
                Sexo sexoBD = new Sexo(conj_Registros.getInt("CODE"), conj_Registros.getString("DESCRIPTION"));
                //Incluye la relacion
                listaSexo.add(sexoBD);
            }
            
            //Cierra la conexion
            conexionBD.cerrarConexion();
            
        } catch (SQLException ex) {

        }
        return listaSexo;
    }
}
