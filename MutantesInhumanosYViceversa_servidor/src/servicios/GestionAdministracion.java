package servicios;

import mensajes.entidades.Usuario;
import mensajes.MsjServAdmin;
import java.util.ArrayList;
import modelosBD.UsuarioBD;
import utilidades.Constantes;

/**
 *
 * @author Flor
 */
public class GestionAdministracion {

    private UsuarioBD servUsuario = null;

    /**
     * Constructor
     */
    public GestionAdministracion() {
        //Se inicializan las clases para invocar a la BD
        servUsuario = new UsuarioBD();
    }

    /**
     * Obtienes una lista de los usuarios
     *
     * @param mAdmin
     * @return
     */
    public MsjServAdmin obtenerListaMensajesPorIdUsuario(MsjServAdmin mAdmin) {

        //Mira si el usuario existe en la BD
        Usuario usuarioAdminBD = servUsuario.getUsuarioById(mAdmin.getIdUsuario());

        //Recupera la informacion para los SUPERUSUARIO y los ADMIN
        if (null != usuarioAdminBD && (1 == usuarioAdminBD.getRol() || 2 == usuarioAdminBD.getRol())) {
            ArrayList<Usuario> listaUsuariosBD = servUsuario.listaUsuariosAplicacion();

            if (null != listaUsuariosBD && !listaUsuariosBD.isEmpty()) {

                ArrayList<Usuario> listaUsuarios = new ArrayList();

                for (Usuario usuarioBD : listaUsuariosBD) {
                    //Pasa todos los usuarios menos el del ADMIN y el del SUPERUSUARIO (no se puede mostrar)
                    if (usuarioBD.getIdUsuario() != usuarioAdminBD.getIdUsuario() && 1 != usuarioBD.getRol()) {

                        //Obtiene el resto de informacion del usuario
                        if (3 == usuarioBD.getRol()) {
                            //Obtiene la informacion del usuario
                            Usuario infUsuarioBD = servUsuario.getInformacionUsuarioById(usuarioBD.getIdUsuario());
                            
                            usuarioBD.setNick(infUsuarioBD.getNick());
                            usuarioBD.setDeporte(infUsuarioBD.getDeporte());
                            usuarioBD.setArte(infUsuarioBD.getArte());
                            usuarioBD.setPolitica(infUsuarioBD.getPolitica());
                            usuarioBD.setRelacion(infUsuarioBD.getRelacion());
                            usuarioBD.setHijos(infUsuarioBD.getHijos());
                            usuarioBD.setSexo(infUsuarioBD.getSexo());
                            usuarioBD.setInteres(infUsuarioBD.getInteres());
                            usuarioBD.setFoto(infUsuarioBD.getFoto());
                            usuarioBD.setFechaAcceso(infUsuarioBD.getFechaAcceso());
                        }

                        listaUsuarios.add(usuarioBD);
                    }
                }

                mAdmin.setListaUsuarios(listaUsuarios);

                //Se rellena el mensaje con el codigo y el error                
                mAdmin.setCodError(Constantes.OK);
                mAdmin.setMensaje("OK");

            } else {
                //Se rellena el mensaje con el codigo y el error                
                mAdmin.setCodError(Constantes.ERROR_NO_USUARIOS);
                mAdmin.setMensaje("No hay mensajes que mostrar.");
            }
        }

        return mAdmin;
    }

    /**
     * Elimina al usuario de la BD
     *
     * @param mAdmin
     * @return
     */
    public MsjServAdmin eliminarMensaje(MsjServAdmin mAdmin) {

        Usuario user = mAdmin.getListaUsuarios().get(0);

        //TODO INCLUIR EL RESTO DE TABLAS
        if (3 == user.getRol()) {
            if (servUsuario.eliminarInformacionUsuario(user.getIdUsuario())) {
                if (servUsuario.eliminarUsuario(user.getIdUsuario())) {
                    //Se rellena el mensaje con el codigo y el error
                    mAdmin.setCodError(Constantes.OK);
                    mAdmin.setMensaje("Se ha eliminado al usuario correctamente.");
                } else {
                    //Se rellena el mensaje con el codigo y el error
                    mAdmin.setCodError(Constantes.ERROR_BD);
                    mAdmin.setMensaje("Se ha producido un error al eliminar al usuario.");
                }
            } else {
                //Se rellena el mensaje con el codigo y el error
                mAdmin.setCodError(Constantes.ERROR_BD);
                mAdmin.setMensaje("Se ha producido un error al eliminar al usuario.");
            }
        } else {
            if (servUsuario.eliminarUsuario(user.getIdUsuario())) {
                //Se rellena el mensaje con el codigo y el error
                mAdmin.setCodError(Constantes.OK);
                mAdmin.setMensaje("Se ha eliminado al usuario correctamente.");
            } else {
                //Se rellena el mensaje con el codigo y el error
                mAdmin.setCodError(Constantes.ERROR_BD);
                mAdmin.setMensaje("Se ha producido un error al eliminar al usuario.");
            }
        }

        return mAdmin;
    }

    /**
     * Activa/Desactiva al usuario de la BD
     *
     * @param mAdmin
     * @return
     */
    public MsjServAdmin activarDesactivarUsuario(MsjServAdmin mAdmin) {
        String mensaje;
        Usuario user = mAdmin.getListaUsuarios().get(0);

        if (servUsuario.activaDesactivaUsuario(user.getIdUsuario(),user.getActivo())) {
            
            if (user.getActivo()){
                mensaje = "Se activo al usuario correctamente.";
            } else {
                mensaje = "Se desactivo al usuario correctamente.";
            }

            //Se rellena el mensaje con el codigo y el error
            mAdmin.setCodError(Constantes.OK);
            mAdmin.setMensaje(mensaje);
        } else {
            if (user.getActivo()){
                mensaje = "Se ha producido un error al activar al usuario.";
            } else {
                mensaje = "Se ha producido un error al desactivar al usuario.";
            }
            
            //Se rellena el mensaje con el codigo y el error
            mAdmin.setCodError(Constantes.ERROR_BD);
            mAdmin.setMensaje(mensaje);
        }
        return mAdmin;
    }
}
