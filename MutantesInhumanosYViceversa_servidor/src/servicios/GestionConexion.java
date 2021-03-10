package servicios;

import mensajes.entidades.Amigos;
import mensajes.MsjServConexion;
import java.util.ArrayList;
import mensajes.entidades.Usuario;
import modelosBD.AmigosBD;
import modelosBD.ConexBD;
import modelosBD.UsuarioBD;
import utilidades.Constantes;

/**
 * Se encarga de la gestion con la conexion en la aplicacion
 *
 * @author Flor
 */
public class GestionConexion {

    private ConexBD servConexion = null;
    private AmigosBD servAmigos = null;
    private UsuarioBD servUsuario = null;

    /**
     * Constructor
     */
    public GestionConexion() {
        //Se inicializan las clases para invocar a la BD
        servConexion = new ConexBD();
        servAmigos = new AmigosBD();
        servUsuario = new UsuarioBD();
    }

    /**
     * Registra el mensaje en la BD
     *
     * @param mConexion
     * @return
     */
    public MsjServConexion crearConexion(MsjServConexion mConexion) {

        //Obtiene el total de usuarios de la aplicacion
        int cantidadUsuarios = servUsuario.cantidadUsuariosByRol(3);
        mConexion.setTotalUsuarios(cantidadUsuarios);

        int totalAmigos = 0;
        int totalAmigosConectados = 0;

        //Mira si el usuario existe en la BD
        Usuario usuarioBD = servUsuario.getUsuarioById(mConexion.getIdUsuario());

        if (null != usuarioBD) {
            ArrayList<Amigos> listaAmigosBD = servAmigos.obtenerListaAmigosPorIdUsuario(usuarioBD.getIdUsuario());

            if (null != listaAmigosBD && !listaAmigosBD.isEmpty()) {

                for (Amigos amigoBD : listaAmigosBD) {

                    //Buscamos que el amigo este registrado
                    Usuario userAmigoBD = servUsuario.getUsuarioById(amigoBD.getIdUsuario());

                    if (null != userAmigoBD) {

                        if (servConexion.getConexionByIdUsuario(userAmigoBD.getIdUsuario())) {
                            totalAmigosConectados++;
                        }
                        totalAmigos++;
                    }
                }
            }
        }

        if (servConexion.insertarConexionByIdUsuario(mConexion.getIdUsuario())) {
            mConexion.setTotalAmigos(totalAmigos);
            mConexion.setTotalAmigosConectados(totalAmigosConectados);

            //Se rellena el mensaje con el codigo y el error
            mConexion.setCodError(Constantes.OK);
            mConexion.setMensaje("Se ha registrado la conexion correctamente.");
        } else {
            //Se rellena el mensaje con el codigo y el error
            mConexion.setCodError(Constantes.ERROR_BD);
            mConexion.setMensaje("Se ha producido un error al registrar la conexion.");
        }

        return mConexion;
    }

    /**
     * Elimina el mensaje de la BD
     *
     * @param mConexion
     * @return
     */
    public MsjServConexion eliminarConexion(MsjServConexion mConexion) {

        if (servConexion.eliminarConexionByIdUsuario(mConexion.getIdUsuario())) {
            //Se rellena el mensaje con el codigo y el error
            mConexion.setCodError(Constantes.OK);
            mConexion.setMensaje("Se ha eliminado la conexion correctamente.");
        } else {
            //Se rellena el mensaje con el codigo y el error
            mConexion.setCodError(Constantes.ERROR_BD);
            mConexion.setMensaje("Se ha producido un al eliminar la conexion.");
        }

        return mConexion;
    }
}
