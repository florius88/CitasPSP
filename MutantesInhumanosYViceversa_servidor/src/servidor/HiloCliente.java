package servidor;

import mensajes.MsjServCargaVentana;
import mensajes.MsjServUsuario;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import mensajes.Firma;
import mensajes.MsjServAdmin;
import mensajes.MsjServAmigos;
import mensajes.MsjServConexion;
import mensajes.MsjServMensajes;
import mensajes.MsjServMsj;
import servicios.GestionAdministracion;
import servicios.GestionAmigos;
import servicios.GestionConexion;
import servicios.GestionMensajes;
import servicios.GestionUsuarios;
import servicios.GestionVentana;
import utilidades.Constantes;

/**
 *
 * @author Flor
 */
class HiloCliente extends Thread {

    Socket cliente;

    HiloCliente(Socket cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        try {
            boolean valide = false;
            Firma f;

            Object resultado = null;

            while (!valide) {
                ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
                Object recepcion = ois.readObject();

                if (recepcion instanceof MsjServCargaVentana) {
                    System.out.println("Entra Servidor: MsjServCargaVentana");
                    //Se encarga de recuperar la informacion de los combos para las ventanas
                    GestionVentana gestionVentana = new GestionVentana();

                    MsjServCargaVentana rec = (MsjServCargaVentana) recepcion;

                    resultado = gestionVentana.obtenerInfoCargaVentana(rec);

                } else if (recepcion instanceof MsjServUsuario) {
                    System.out.println("Entra Servidor: MsjServUsuario");
                    //Se encarga de la gestion de los usuarios
                    GestionUsuarios gestionusuario = new GestionUsuarios();

                    MsjServUsuario rec = (MsjServUsuario) recepcion;

                    //Segun la accion hace una cosa u orta
                    switch (rec.getAccion()) {
                        case Constantes.ACCION_VAL_LOGIN:
                            //Validar entrar login
                            resultado = gestionusuario.entrarLogin(rec);
                            break;
                        case Constantes.ACCION_REGISTRO_USUARIO:
                            //Registro del usuario
                            resultado = gestionusuario.guardarRegistro(rec);
                            break;
                        case Constantes.ACCION_GUARDAR_PREFERENCIAS:
                            //Guardar perfil
                            resultado = gestionusuario.guardarPreferencias(rec);
                            break;
                        case Constantes.ACCION_DEVOLVER_USUARIO:
                            //Devolver usuario
                            resultado = gestionusuario.obtenerUsuario(rec);
                            break;
                        case Constantes.ACCION_CREAR_USUARIO:
                            //Crear usuario
                            resultado = gestionusuario.guardarUsuario(rec);
                            break;
                        case Constantes.ACCION_ACTUALIZAR_USUARIO:
                            //Actualizar usuario
                            resultado = gestionusuario.actualizarUsuario(rec);
                            break;
                        case Constantes.ACCION_GUARDAR_PERFIL:
                            //Guardar perfil
                            resultado = gestionusuario.guardarPerfil(rec);
                            break;
                    }
                } else if (recepcion instanceof MsjServMensajes) {
                    //Se encarga de recuperar la informacion de todos los mensajes del usuario
                    GestionMensajes gestionMensajes = new GestionMensajes();

                    MsjServMensajes rec = (MsjServMensajes) recepcion;

                    resultado = gestionMensajes.obtenerListaMensajesPorIdUsuario(rec);

                } else if (recepcion instanceof MsjServMsj) {
                    //Se encarga de la gestion con los mensajes
                    GestionMensajes gestionMensajes = new GestionMensajes();

                    MsjServMsj rec = (MsjServMsj) recepcion;

                    //Segun la accion hace una cosa u orta
                    switch (rec.getAccion()) {
                        case Constantes.ACCION_ENVIAR_MSJ:
                            //Enviar mensaje
                            resultado = gestionMensajes.enviarMensaje(rec);
                            break;
                        case Constantes.ACCION_ELIMINAR_MSJ:
                            //Eliminar mensaje
                            resultado = gestionMensajes.eliminarMensaje(rec);
                            break;
                        case Constantes.ACCION_ACTUALIZAR_MSJ:
                            //Actualizar mensaje
                            resultado = gestionMensajes.actualizarMensaje(rec);
                            break;
                    }
                } else if (recepcion instanceof MsjServAmigos) {
                    //Se encarga de toda la gestion con los amigos
                    GestionAmigos gestionAmigos = new GestionAmigos();

                    MsjServAmigos rec = (MsjServAmigos) recepcion;

                    //Segun la accion hace una cosa u orta
                    switch (rec.getAccion()) {
                        case Constantes.ACCION_LISTA_AMIGOS:
                            //Lista amigos
                            resultado = gestionAmigos.obtenerListaAmigosPorIdUsuario(rec);
                            break;
                        case Constantes.ACCION_DEJAR_AMIGO:
                            //Eliminar amigo
                            //resultado = gestionAmigos.eliminarAmigo(rec);
                            break;
                        case Constantes.ACCION_BUSCAR_AMIGO:
                            //Buscar amigos
                            resultado = gestionAmigos.obtenerListaBuscarAmigos(rec);
                            break;
                        case Constantes.ACCION_ME_GUSTA:
                            //Me gusta
                            resultado = gestionAmigos.modificarMeGusta(rec);
                            break;
                    }
                } else if (recepcion instanceof MsjServConexion) {
                    //Se encarga de la gestion con la conexion en la aplicacion
                    GestionConexion gestionConexion = new GestionConexion();

                    MsjServConexion rec = (MsjServConexion) recepcion;

                    //Segun la accion hace una cosa u orta
                    switch (rec.getAccion()) {
                        case Constantes.ACCION_CREAR_CONEXION:
                            //Lista amigos
                            resultado = gestionConexion.crearConexion(rec);
                            break;
                        case Constantes.ACCION_ELIMINAR_CONEXION:
                            //Eliminar amigo
                            resultado = gestionConexion.eliminarConexion(rec);
                            break;
                    }
                } else if (recepcion instanceof MsjServAdmin) {
                    //Se encarga de la gestion de la administracion
                    GestionAdministracion gestionAdmin = new GestionAdministracion();

                    MsjServAdmin rec = (MsjServAdmin) recepcion;

                    //Segun la accion hace una cosa u orta
                    switch (rec.getAccion()) {
                        case Constantes.ACCION_CARGAR_ADMIN:
                            //Lista usuarios
                            resultado = gestionAdmin.obtenerListaUsuarios(rec);
                            break;
                        case Constantes.ACCION_BAJA_USUARIO:
                            //Baja del usuario
                            resultado = gestionAdmin.eliminarUsuario(rec);
                            break;
                        case Constantes.ACCION_ACTIVAR_USUARIO:
                            //Activar/Desactivar al usuario
                            resultado = gestionAdmin.activarDesactivarUsuario(rec);
                            break;
                    }
                }

                //valide = UtilFirma.verificarFirma(f.getMensaje(), f.getFirma(), f.getClavePublica());
                //DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
                ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());

                //dos.writeBoolean(valide);
                oos.writeObject(resultado);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
