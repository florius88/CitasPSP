package servicios;

import mensajes.entidades.Usuario;
import mensajes.MsjServAdmin;
import java.util.ArrayList;
import mensajes.entidades.Amigos;
import mensajes.entidades.Mensaje;
import modelosBD.AmigosBD;
import modelosBD.ConexBD;
import modelosBD.MeGustaBD;
import modelosBD.MensajeBD;
import modelosBD.RolBD;
import modelosBD.UsuarioBD;
import utilidades.Constantes;

/**
 *
 * @author Flor
 */
public class GestionAdministracion {

    private UsuarioBD servUsuario = null;
    private RolBD servRol = null;
    private ConexBD servConexion = null;
    private MensajeBD servMensaje = null;
    private MeGustaBD servMeGusta = null;
    private AmigosBD servAmigos = null;

    /**
     * Constructor
     */
    public GestionAdministracion() {
        //Se inicializan las clases para invocar a la BD
        servUsuario = new UsuarioBD();
        servRol = new RolBD();
        servConexion = new ConexBD();
        servMensaje = new MensajeBD();
        servMeGusta = new MeGustaBD();
        servAmigos = new AmigosBD();
    }

    /**
     * Metodo que obtienes una lista de los usuarios
     *
     * @param mAdmin
     * @return
     */
    public synchronized MsjServAdmin obtenerListaUsuarios(MsjServAdmin mAdmin) {

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

                        //Obtiene la descripcion del Rol
                        usuarioBD.setDescripcionRol(servRol.getDescripcionRolByCodeRol(usuarioBD.getRol()));

                        //Obtiene el resto de informacion del usuario
                        if (3 == usuarioBD.getRol()) {
                            //Obtiene la informacion del usuario
                            Usuario infUsuarioBD = servUsuario.getInformacionUsuarioById(usuarioBD.getIdUsuario());
                            if (null != infUsuarioBD) {
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
     * Metodo que elimina al usuario de la BD
     *
     * @param mAdmin
     * @return
     */
    public synchronized MsjServAdmin eliminarUsuario(MsjServAdmin mAdmin) {

        Usuario user = mAdmin.getListaUsuarios().get(0);

        if (3 == user.getRol()) {

            //Elimina toda la informacion del usuario en la BD
            eliminarDatosUsuario(user.getIdUsuario());

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
     * Elimina toda la informacion del usuario en la BD
     *
     * @param idUsuario
     */
    private synchronized void eliminarDatosUsuario(int idUsuario) {
        //Eliminar conexion
        servConexion.eliminarConexionByIdUsuario(idUsuario);

        //Se obtiene los mensajes que tenga el usuario
        ArrayList<Mensaje> listaMensajes = servMensaje.obtenerListaMensajesPorIdUsuario(idUsuario);
        if (null != listaMensajes && !listaMensajes.isEmpty()) {
            for (Mensaje msj : listaMensajes) {
                //Eliminar documentos
                servMensaje.eliminarAdjuntosMensaje(msj);
                //Eliminar mensajes
                servMensaje.eliminarMensaje(msj);
            }
        }

        //Se obtiene los amigos que tenga el usuario
        ArrayList<Amigos> listaAmigosBD = servAmigos.obtenerListaAmigosPorIdUsuario(idUsuario);
        if (null != listaAmigosBD && !listaAmigosBD.isEmpty()) {
            for (Amigos amigo : listaAmigosBD) {
                //Eliminar me gusta
                servMeGusta.eliminarMeGustaByIdUsuarioIdUsuarioAmigo(idUsuario, amigo.getIdUsuario());
                servMeGusta.eliminarMeGustaByIdUsuarioAmigo(idUsuario);
                //Si tenian amistad, se elimina
                if (servAmigos.obtenerAmistadByIdUsuarioIdUsuarioAmigo(idUsuario, amigo.getIdUsuario())
                        || servAmigos.obtenerAmistadByIdUsuarioIdUsuarioAmigo(amigo.getIdUsuario(), idUsuario)) {
                    //Eliminar amigos
                    servAmigos.eliminarAmgioByIdUsuarioIdUsuarioAmigo(idUsuario, amigo.getIdUsuario());
                    servAmigos.eliminarAmgioByIdUsuarioIdUsuarioAmigo(amigo.getIdUsuario(), idUsuario);
                }
            }
        }
    }

    /**
     * Metodo que Activa/Desactiva al usuario de la BD
     *
     * @param mAdmin
     * @return
     */
    public synchronized MsjServAdmin activarDesactivarUsuario(MsjServAdmin mAdmin) {
        String mensaje;
        Usuario user = mAdmin.getListaUsuarios().get(0);

        if (servUsuario.activaDesactivaUsuario(user.getIdUsuario(), user.getActivo())) {

            if (user.getActivo()) {
                mensaje = "Se activo al usuario correctamente.";
            } else {
                mensaje = "Se desactivo al usuario correctamente.";
            }

            //Se rellena el mensaje con el codigo y el error
            mAdmin.setCodError(Constantes.OK);
            mAdmin.setMensaje(mensaje);
        } else {
            if (user.getActivo()) {
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
