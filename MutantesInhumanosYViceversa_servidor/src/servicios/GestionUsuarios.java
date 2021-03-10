package servicios;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import mensajes.MsjServUsuario;
import mensajes.entidades.Amigos;
import mensajes.entidades.Mensaje;
import mensajes.entidades.Usuario;
import modelosBD.AmigosBD;
import modelosBD.ConexBD;
import modelosBD.MeGustaBD;
import modelosBD.MensajeBD;
import modelosBD.RolBD;
import modelosBD.UsuarioBD;
import utilidades.Constantes;
import utilidades.Utilidades;

/**
 * Se encarga de la gestion de los usuarios
 *
 * @author Flor
 */
public class GestionUsuarios {

    private UsuarioBD servUsuario = null;
    private RolBD servRol = null;
    private ConexBD servConexion = null;
    private MensajeBD servMensaje = null;
    private MeGustaBD servMeGusta = null;
    private AmigosBD servAmigos = null;

    /**
     * Constructor
     */
    public GestionUsuarios() {
        //Se inicializan las clase para invocar a la BD
        servUsuario = new UsuarioBD();
        servRol = new RolBD();
        servConexion = new ConexBD();
        servMensaje = new MensajeBD();
        servMeGusta = new MeGustaBD();
        servAmigos = new AmigosBD();
    }

    /**
     * ---------------------------------------------------------------------------------------------------------------
     * Login
     * ---------------------------------------------------------------------------------------------------------------
     */
    /**
     * Metodo que valida el acceso a la aplicacion
     *
     * @param mUsuario
     * @return
     */
    public synchronized MsjServUsuario entrarLogin(MsjServUsuario mUsuario) {

        Usuario usuarioMensaje = mUsuario.getUsuario();

        mUsuario = validarInformacionLogin(usuarioMensaje);

        if (Constantes.OK == mUsuario.getCodError()) {
            //TODO Validamos en la BD que el usuario exista para entrar
            Usuario usuarioBD = servUsuario.getUsuarioByEmailPwd(usuarioMensaje.getEmail(), usuarioMensaje.getPwd());

            if (null != usuarioBD) {

                //Si el usuario ya esta conectado, que no deje volver a entrar con el mismo
                if (servConexion.getConexionByIdUsuario(usuarioBD.getIdUsuario())) {
                    //Se rellena el mensaje con el codigo y el error
                    mUsuario.setCodError(Constantes.ERROR_ENTRAR);
                    mUsuario.setMensaje("El usuario ya está registrado en la aplicación.\nSi no puede entrar contacte con el administrador.");
                } else {
                    //Obtiene la descripcion del Rol
                    usuarioBD.setDescripcionRol(servRol.getDescripcionRolByCodeRol(usuarioBD.getRol()));

                    switch (usuarioBD.getRol()) {
                        case 1:
                            //Super Administrador

                            //Pasamos el usuario para enviarlo
                            mUsuario.setUsuario(usuarioBD);

                            //Se rellena el mensaje con el codigo y el error
                            mUsuario.setCodError(Constantes.OK);
                            mUsuario.setMensaje("OK");
                            break;
                        case 2:
                            //Administrador

                            if (null != usuarioBD.getActivo() && !usuarioBD.getActivo()) {
                                //Si no esta activo, se informa al usuario de que no puede acceder

                                //Se rellena el mensaje con el codigo y el error
                                mUsuario.setCodError(Constantes.ERROR_USUARIO_NO_ACTIVO);
                                mUsuario.setMensaje("El usuario no está activo, debe esperar a que el administrador valide su solicitud");

                            } else if (null != usuarioBD.getActivo() && usuarioBD.getActivo()) {
                                //Redirigimos a la ventana de preferencias para rellenar la informacion

                                //Pasamos el usuario para enviarlo
                                mUsuario.setUsuario(usuarioBD);

                                //Se rellena el mensaje con el codigo y el error
                                mUsuario.setCodError(Constantes.OK);
                                mUsuario.setMensaje("OK");
                            }
                            break;
                        case 3:
                            //Usuario

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

                                if ((null != usuarioBD.getActivo() && usuarioBD.getActivo()) && (null != usuarioBD.getFechaAcceso())) {
                                    //Si no es la primera vez que accede, se envia a la pantalla de la aplicacion

                                    //Pasamos el usuario para enviarlo
                                    mUsuario.setUsuario(usuarioBD);

                                    //Se rellena el mensaje con el codigo y el error
                                    mUsuario.setCodError(Constantes.OK);
                                    mUsuario.setMensaje("OK");

                                } else if (null != usuarioBD.getActivo() && !usuarioBD.getActivo()) {
                                    //Si no esta activo, se informa al usuario de que no puede acceder

                                    //Se rellena el mensaje con el codigo y el error
                                    mUsuario.setCodError(Constantes.ERROR_USUARIO_NO_ACTIVO);
                                    mUsuario.setMensaje("El usuario no está activo, debe esperar a que el administrador valide su solicitud");

                                } else if ((null != usuarioBD.getActivo() && usuarioBD.getActivo()) && null == usuarioBD.getFechaAcceso()) {
                                    //Redirigimos a la ventana de preferencias para rellenar la informacion

                                    //Pasamos el usuario para enviarlo
                                    mUsuario.setUsuario(usuarioBD);

                                    //Se rellena el mensaje con el codigo y el error
                                    mUsuario.setCodError(Constantes.OK_PRIMER_ACCESO);
                                    mUsuario.setMensaje("OK");

                                } else {
                                    //Se rellena el mensaje con el codigo y el error
                                    mUsuario.setCodError(Constantes.ERROR_ENTRAR);
                                    mUsuario.setMensaje("No puede acceder a la aplicación.");
                                }
                            }

                            break;
                    }
                }
            } else {
                //Se rellena el mensaje con el codigo y el error
                mUsuario.setCodError(Constantes.ERROR_USUARIO_NO_REGISTRADO);
                mUsuario.setMensaje("El usuario no está dado de alta en la aplicación.");
            }
        }

        return mUsuario;
    }

    /**
     * Metodo que valida la informacion antes de comprobarla en la BD
     *
     * @param usuario
     * @return
     */
    private synchronized MsjServUsuario validarInformacionLogin(Usuario usuario) {

        MsjServUsuario mUsuario = new MsjServUsuario();

        boolean correcto = true;

        String email = usuario.getEmail();
        String pwd = usuario.getPwd();

        if (null == email || email.isEmpty() || !Utilidades.validarEmail(email)) {
            //Se rellena el mensaje con el codigo y el error
            mUsuario.setCodError(Constantes.ERROR_FORMATO_EMAIL);
            mUsuario.setMensaje("El email no tiene el formato correcto.");
            correcto = false;
        }

        if (correcto && (null == pwd || 4 > pwd.length())) {
            //Se rellena el mensaje con el codigo y el error            
            mUsuario.setCodError(Constantes.ERROR_PWD);
            mUsuario.setMensaje("La contraseña no tiene la longitud correcta.");
            correcto = false;
        }

        if (correcto) {
            mUsuario.setCodError(Constantes.OK);
            mUsuario.setMensaje("OK");
        }

        return mUsuario;
    }

    /**
     * ---------------------------------------------------------------------------------------------------------------
     * Registro
     * ---------------------------------------------------------------------------------------------------------------
     */
    /**
     * Metodo que almacena la informacion del registro
     *
     * @param mUsuario
     * @return
     */
    public synchronized MsjServUsuario guardarRegistro(MsjServUsuario mUsuario) {

        Usuario usuario = mUsuario.getUsuario();

        mUsuario = validarInformacionRegistro(usuario);

        if (Constantes.OK == mUsuario.getCodError()) {
            //Comprobando que no exista el usuario
            Usuario usuarioBD = servUsuario.getUsuarioByEmailPwd(usuario.getEmail(), usuario.getPwd());

            if (null == usuarioBD) {
                //Insertamos en la BD el registro, 
                if (servUsuario.insertarUsuario(usuario)) {
                    mUsuario.setCodError(Constantes.OK);
                    mUsuario.setMensaje("Se ha registrado correctamente.");
                } else {
                    mUsuario.setCodError(Constantes.ERROR_BD);
                    mUsuario.setMensaje("Se ha producido un error al guardar la información.");
                }
            } else {
                mUsuario.setCodError(Constantes.ERROR_BD);
                mUsuario.setMensaje("El usuario ya existe en la aplicación.");
            }
        }

        return mUsuario;
    }

    /**
     * Metodo que valida el formato de la informacion antes de darla de alta en
     * la BD
     *
     * @return
     */
    private synchronized MsjServUsuario validarInformacionRegistro(Usuario usuario) {

        MsjServUsuario mUsuario = new MsjServUsuario();
        boolean correcto = true;

        String nick = usuario.getNick();
        String email = usuario.getEmail();
        String pwd = usuario.getPwd();
        String confirmPwd = usuario.getConfirmarPwd();

        if (null == nick || nick.isEmpty()) {
            //Se rellena el mensaje con el codigo y el error
            mUsuario.setCodError(Constantes.ERROR_NO_NICK);
            mUsuario.setMensaje("El nick no puede estar vacío.");
            correcto = false;
        }

        if (correcto && (null == email || email.isEmpty() || !Utilidades.validarEmail(email))) {
            //Se rellena el mensaje con el codigo y el error
            mUsuario.setCodError(Constantes.ERROR_FORMATO_EMAIL);
            mUsuario.setMensaje("El email no tiene el formato correcto.");
            correcto = false;
        }

        if (correcto && (null == pwd || null == confirmPwd || 4 > pwd.length() || 4 > confirmPwd.length())) {
            //Se rellena el mensaje con el codigo y el error
            mUsuario.setCodError(Constantes.ERROR_PWD);
            mUsuario.setMensaje("Las contraseñas no pueden tener menos de 4 caracteres.");
            correcto = false;
        }

        if (correcto && !Utilidades.validarPwd(pwd.toCharArray(), confirmPwd.toCharArray())) {
            //Se rellena el mensaje con el codigo y el error
            mUsuario.setCodError(Constantes.ERROR_PWD_NO_IGUALES);
            mUsuario.setMensaje("Las contraseñas no coinciden.");
            correcto = false;
        }

        if (correcto) {
            mUsuario.setCodError(Constantes.OK);
            mUsuario.setMensaje("OK");
        }

        return mUsuario;
    }

    /**
     * ---------------------------------------------------------------------------------------------------------------
     * PanelPerfil
     * ---------------------------------------------------------------------------------------------------------------
     */
    /**
     * Metodo que almacena la informacion de las preferencias
     *
     * @param mUsuario
     * @return
     */
    public synchronized MsjServUsuario guardarPreferencias(MsjServUsuario mUsuario) {

        Usuario usuario = mUsuario.getUsuario();

        mUsuario = validarInformacionPerfil(usuario);

        if (Constantes.OK == mUsuario.getCodError()) {

            //Validar si ya tiene informacion de usuario
            Usuario usuarioBD = servUsuario.getInformacionUsuarioById(usuario.getIdUsuario());

            //Si no tiene se inserta
            if (null == usuarioBD.getNick() || usuarioBD.getNick().isEmpty()) {
                if (servUsuario.insertarInformacionUsuario(usuario)) {
                    mUsuario.setCodError(Constantes.OK);
                    mUsuario.setMensaje("Se han guardado los cambios de las preferencias correctamente.");
                } else {
                    mUsuario.setCodError(Constantes.ERROR_BD);
                    mUsuario.setMensaje("Se ha producido un error al guardar la información.");
                }
            } else {
                //Guardamos las preferencias en la BD, por si tienen cambios
                if (servUsuario.actualizarInformacionUsuario(usuario)) {
                    mUsuario.setCodError(Constantes.OK);
                    mUsuario.setMensaje("Se han guardado los cambios de las preferencias correctamente.");
                } else {
                    mUsuario.setCodError(Constantes.ERROR_BD);
                    mUsuario.setMensaje("Se ha producido un error al guardar la información.");
                }
            }
        }

        return mUsuario;
    }

    /**
     * Metodo que almacena la informacion del perfil
     *
     * @param mUsuario
     * @return
     */
    public synchronized MsjServUsuario guardarPerfil(MsjServUsuario mUsuario) {

        Usuario usuario = mUsuario.getUsuario();

        mUsuario = validarInformacionPerfil(usuario);

        if (Constantes.OK == mUsuario.getCodError()) {

            if (servUsuario.actualizarUsuario(usuario)) {
                if (servUsuario.actualizarInformacionUsuario(usuario)) {
                    mUsuario.setCodError(Constantes.OK);
                    mUsuario.setMensaje("Se han guardado los cambios del perfil correctamente.");
                } else {
                    mUsuario.setCodError(Constantes.ERROR_BD);
                    mUsuario.setMensaje("Se ha producido un error al guardar la información.");
                }
            } else {
                mUsuario.setCodError(Constantes.ERROR_BD);
                mUsuario.setMensaje("Se ha producido un error al guardar la información.");
            }
        }

        return mUsuario;
    }

    /**
     * Metodo que valida el formato de la informacion antes de guardarla en la
     * BD
     *
     * @param usuario
     * @return
     */
    private synchronized MsjServUsuario validarInformacionPerfil(Usuario usuario) {

        MsjServUsuario mUsuario = new MsjServUsuario();
        boolean correcto = true;

        String nick = usuario.getNick();
        String email = usuario.getEmail();
        String pwd = usuario.getPwd();
        ImageIcon foto = usuario.getFoto();

        if (null == nick || nick.isEmpty()) {
            //Se rellena el mensaje con el codigo y el error
            mUsuario.setCodError(Constantes.ERROR_NO_NICK);
            mUsuario.setMensaje("El nick no puede estar vacío.");
            correcto = false;
        }

        if (correcto && (null == email || email.isEmpty() || !Utilidades.validarEmail(email))) {
            //Se rellena el mensaje con el codigo y el error
            mUsuario.setCodError(Constantes.ERROR_FORMATO_EMAIL);
            mUsuario.setMensaje("El email no tiene el formato correcto.");
            correcto = false;
        }

        if (correcto && (null == pwd || 4 > pwd.length())) {
            //Se rellena el mensaje con el codigo y el error            
            mUsuario.setCodError(Constantes.ERROR_PWD);
            mUsuario.setMensaje("La contraseña no tiene la longitud correcta.");
            correcto = false;
        }

        if (correcto && null == foto) {
            //Se rellena el mensaje con el codigo y el error
            mUsuario.setCodError(Constantes.ERROR_NO_FOTO);
            mUsuario.setMensaje("Debe seleccionar una imagén de perfil.");
            correcto = false;
        }

        if (correcto) {
            mUsuario.setCodError(Constantes.OK);
            mUsuario.setMensaje("OK");
        }

        return mUsuario;
    }

    /**
     * Metodo que devuelve la informacion del usuario
     *
     * @param mUsuario
     * @return
     */
    public synchronized MsjServUsuario obtenerUsuario(MsjServUsuario mUsuario) {

        Usuario usuario = mUsuario.getUsuario();

        if (Constantes.OK == mUsuario.getCodError()) {
            //TODO Insertamos en la BD el registro, comprobando que no exista el usuario
            Usuario usuarioBD = servUsuario.getUsuarioById(usuario.getIdUsuario());

            if (null == usuarioBD) {
                mUsuario.setCodError(Constantes.ERROR_BD);
                mUsuario.setMensaje("Se ha producido un error al guardar la información.");

            } else {

                //Obtiene la informacion del usuario
                Usuario infUsuarioBD = servUsuario.getInformacionUsuarioById(usuario.getIdUsuario());

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

                mUsuario.setUsuario(usuarioBD);
                mUsuario.setCodError(Constantes.OK);
                mUsuario.setMensaje("Se ha registrado correctamente.");
            }
        }

        return mUsuario;
    }

    /**
     * ---------------------------------------------------------------------------------------------------------------
     * Administracion
     * ---------------------------------------------------------------------------------------------------------------
     */
    /**
     * Metodo que crea un usuario
     *
     * @param mUsuario
     * @return
     */
    public synchronized MsjServUsuario guardarUsuario(MsjServUsuario mUsuario) {

        Usuario usuario = mUsuario.getUsuario();

        mUsuario = validarInformacionUsuario(usuario);

        if (Constantes.OK == mUsuario.getCodError()) {

            //Comprobando que no exista el usuario
            Usuario usuarioBD = servUsuario.getUsuarioByEmailPwd(usuario.getEmail(), usuario.getPwd());

            if (null == usuarioBD) {
                //Guardamos el usuario en la BD
                if (servUsuario.insertarUsuario(usuario)) {
                    mUsuario.setCodError(Constantes.OK);
                    mUsuario.setMensaje("Se ha creado el usuario correctamente.");
                } else {
                    mUsuario.setCodError(Constantes.ERROR_BD);
                    mUsuario.setMensaje("Se ha producido un error al crear el usuario.");
                }
            } else {
                mUsuario.setCodError(Constantes.ERROR_BD);
                mUsuario.setMensaje("Ya exite un usuario con el mismo email.");
            }
        }

        return mUsuario;
    }

    /**
     * Metodo que actualiza un usuario
     *
     * @param mUsuario
     * @return
     */
    public synchronized MsjServUsuario actualizarUsuario(MsjServUsuario mUsuario) {

        Usuario usuarioActualizar = mUsuario.getUsuario();

        mUsuario = validarInformacionUsuario(usuarioActualizar);

        if (Constantes.OK == mUsuario.getCodError()) {
            //TODO Validamos en la BD que el usuario exista para entrar
            Usuario usuarioBD = servUsuario.getUsuarioById(usuarioActualizar.getIdUsuario());

            if (null != usuarioBD) {

                switch (usuarioActualizar.getRol()) {
                    case 2:
                        //Administrador

                        //No cambia de Rol
                        if (usuarioActualizar.getRol() == usuarioBD.getRol()) {
                            //Actualiza la informacion
                            if (servUsuario.actualizarUsuario(usuarioActualizar)) {
                                mUsuario.setCodError(Constantes.OK);
                                mUsuario.setMensaje("Se ha actualizado el usuario correctamente.");
                            } else {
                                mUsuario.setCodError(Constantes.ERROR_BD);
                                mUsuario.setMensaje("Se ha producido un error al actualizar el usuario.");
                            }

                        } else {
                            //Cambia de Rol

                            //Actualiza la informacion
                            if (servUsuario.actualizarUsuario(usuarioActualizar)) {
                                if (servUsuario.eliminarInformacionUsuario(usuarioActualizar.getIdUsuario())) {

                                    //Elimina toda la informacion del usuario en la BD
                                    eliminarDatosUsuario(usuarioActualizar.getIdUsuario());

                                    mUsuario.setCodError(Constantes.OK);
                                    mUsuario.setMensaje("Se ha actualizado el usuario correctamente.");

                                } else {
                                    mUsuario.setCodError(Constantes.ERROR_BD);
                                    mUsuario.setMensaje("Se ha producido un error al actualizar el usuario.");
                                }
                            }
                        }

                        break;

                    case 3:
                        //Usuario

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

                        //No cambia de Rol - Se comprueba que tenga informacion del usuario
                        if (usuarioActualizar.getRol() == usuarioBD.getRol() && null != usuarioBD.getNick() && !usuarioBD.getNick().isEmpty()) {
                            //Actualiza la informacion
                            if (servUsuario.actualizarUsuario(usuarioActualizar)) {
                                if (servUsuario.actualizarInformacionUsuario(usuarioActualizar)) {
                                    mUsuario.setCodError(Constantes.OK);
                                    mUsuario.setMensaje("Se ha actualizado el usuario correctamente.");
                                } else {
                                    mUsuario.setCodError(Constantes.ERROR_BD);
                                    mUsuario.setMensaje("Se ha producido un error al actualizar el usuario.");
                                }
                            }
                        } else {
                            //Actualiza la informacion
                            if (servUsuario.actualizarUsuario(usuarioActualizar)) {

                                if (servUsuario.insertarInformacionUsuario(usuarioActualizar)) {
                                    mUsuario.setCodError(Constantes.OK);
                                    mUsuario.setMensaje("Se ha actualizado el usuario correctamente.");
                                } else {
                                    mUsuario.setCodError(Constantes.ERROR_BD);
                                    mUsuario.setMensaje("Se ha producido un error al actualizar el usuario.");
                                }
                            }
                        }
                        break;
                }
            }
        }

        return mUsuario;
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
     * Metodo que valida el formato de la informacion antes de crear/actualizar
     * un usuario en la BD
     *
     * @param usuario
     * @return
     */
    private synchronized MsjServUsuario validarInformacionUsuario(Usuario usuario) {

        MsjServUsuario mUsuario = new MsjServUsuario();
        boolean correcto = true;

        String email = usuario.getEmail();
        //TODO pwd
        //TODO --> REVISAR QUE PASA SI NO TIENE FOTO....... Image foto = usuario.getFoto();
        //DESACTIVAR AL CAMBIAR DE ROL Y REINICIAR PARA SOLICITAR LA INFO

        if (null == email || email.isEmpty() || !Utilidades.validarEmail(email)) {
            //Se rellena el mensaje con el codigo y el error
            mUsuario.setCodError(Constantes.ERROR_FORMATO_EMAIL);
            mUsuario.setMensaje("El email no tiene el formato correcto.");
            correcto = false;
        }

        //Segun el rol
        if (3 == usuario.getRol()) {
            String nick = usuario.getNick();

            if (correcto && (null == nick || nick.isEmpty())) {
                //Se rellena el mensaje con el codigo y el error
                mUsuario.setCodError(Constantes.ERROR_NO_NICK);
                mUsuario.setMensaje("El nick no puede estar vacío.");
                correcto = false;
            }

        }

        if (correcto) {
            mUsuario.setCodError(Constantes.OK);
            mUsuario.setMensaje("OK");
        }

        return mUsuario;
    }
}
