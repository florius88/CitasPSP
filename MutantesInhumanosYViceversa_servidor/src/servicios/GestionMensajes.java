package servicios;

import mensajes.entidades.Mensaje;
import mensajes.MsjServMensajes;
import mensajes.MsjServMsj;
import java.util.ArrayList;
import mensajes.entidades.Usuario;
import modelosBD.MensajeBD;
import modelosBD.UsuarioBD;
import utilidades.Constantes;

/**
 * Se encarga de la gestion con los mensajes
 *
 * @author Flor
 */
public class GestionMensajes {

    private MensajeBD servMensaje = null;
    private UsuarioBD servUsuario = null;

    public GestionMensajes() {
        //Se inicializan las clases para invocar a la BD
        servMensaje = new MensajeBD();
        servUsuario = new UsuarioBD();
    }

    /**
     * Registra el mensaje en la BD
     *
     * @param mMsj
     * @return
     */
    public MsjServMsj enviarMensaje(MsjServMsj mMsj) {

        Mensaje msj = mMsj.getMsj();

        if (null != msj.getMensajeEmisor() && !msj.getMensajeEmisor().isEmpty()) {

            if (servMensaje.insertarMensaje(msj)) {
                //Se rellena el mensaje con el codigo y el error
                mMsj.setCodError(Constantes.OK);
                mMsj.setMensaje("Se ha enviado el mensaje correctamente.");
            } else {
                //Se rellena el mensaje con el codigo y el error
                mMsj.setCodError(Constantes.ERROR_BD);
                mMsj.setMensaje("Se ha producido un error al enviar el mensaje.");
            }

        } else {
            //Se rellena el mensaje con el codigo y el error
            mMsj.setCodError(Constantes.ERROR_NO_MENSAJES);
            mMsj.setMensaje("Debe escribir algún mensaje para poder ser enviado.");
        }

        return mMsj;
    }

    /**
     * Elimina el mensaje de la BD
     *
     * @param mMsj
     * @return
     */
    public MsjServMsj eliminarMensaje(MsjServMsj mMsj) {

        Mensaje msj = mMsj.getMsj();

        if (servMensaje.eliminarAdjuntosMensaje(msj)) {
            if (servMensaje.eliminarMensaje(msj)) {
                //Se rellena el mensaje con el codigo y el error
                mMsj.setCodError(Constantes.OK);
                mMsj.setMensaje("Se ha eliminado el mensaje correctamente.");
            } else {
                //Se rellena el mensaje con el codigo y el error
                mMsj.setCodError(Constantes.ERROR_BD);
                mMsj.setMensaje("Se ha producido un error al eliminar el mensaje.");
            }
        } else {
            //Se rellena el mensaje con el codigo y el error
            mMsj.setCodError(Constantes.ERROR_BD);
            mMsj.setMensaje("Se ha producido un error al eliminar el mensaje.");
        }

        return mMsj;
    }

    /**
     * Actualiza el mensaje de la BD
     *
     * @param mMsj
     * @return
     */
    public MsjServMsj actualizarMensaje(MsjServMsj mMsj) {

        Mensaje msj = mMsj.getMsj();

        if (servMensaje.actualizarMensaje(msj)) {
            //Se rellena el mensaje con el codigo y el error
            mMsj.setCodError(Constantes.OK);
            mMsj.setMensaje("Se ha actualizado el mensaje correctamente.");
        } else {
            //Se rellena el mensaje con el codigo y el error
            mMsj.setCodError(Constantes.ERROR_BD);
            mMsj.setMensaje("Se ha producido un error al actualizar el mensaje.");
        }

        return mMsj;
    }

    /**
     * Obtienes una lista de mensajes por el Id del usuario
     *
     * @param mMensajes
     * @return
     */
    public MsjServMensajes obtenerListaMensajesPorIdUsuario(MsjServMensajes mMensajes) {

        //Mira si el usuario existe en la BD
        Usuario usuarioBD = servUsuario.getUsuarioById(mMensajes.getIdUsuario());

        if (null != usuarioBD) {
            ArrayList<Mensaje> listaMensajesBD = servMensaje.obtenerListaMensajesPorIdUsuario(mMensajes.getIdUsuario());

            if (null != listaMensajesBD && !listaMensajesBD.isEmpty()) {

                ArrayList<Mensaje> listaMensajes = new ArrayList();

                for (Mensaje msjBD : listaMensajesBD) {
                    
                    //Obtiene la lista de documentos adjuntos del mensaje
                    msjBD.setListaAdjuntosEmisor(servMensaje.obtenerListaAdjuntosPorIdMensaje(msjBD.getIdMensaje()));
                    
                    Usuario usuarioNickBD = servUsuario.getInformacionUsuarioById(msjBD.getIdUsuarioEmisor());
                    msjBD.setNickEmisor(usuarioNickBD.getNick());

                    listaMensajes.add(msjBD);
                }

                mMensajes.setListaMensajes(listaMensajes);

                //Se rellena el mensaje con el codigo y el error                
                mMensajes.setCodError(Constantes.OK);
                mMensajes.setMensaje("OK");

            } else {

                //Se rellena el mensaje con el codigo y el error                
                mMensajes.setCodError(Constantes.ERROR_NO_MENSAJES);
                mMensajes.setMensaje("No hay mensajes que mostrar.");
            }
        }

        return mMensajes;
    }
}
