package servicios;

import javax.swing.ImageIcon;
import mensajes.MsjServUsuario;
import mensajes.entidades.Usuario;
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

    /**
     * Constructor
     */
    public GestionUsuarios() {
        //Se inicializan las clase para invocar a la BD
        servUsuario = new UsuarioBD();
    }

    /**
     * ---------------------------------------------------------------------------------------------------------------
     * Login
     * ---------------------------------------------------------------------------------------------------------------
     */
    /**
     * Validamos el acceso a la aplicacion
     *
     * @param mUsuario
     * @return
     */
    public MsjServUsuario entrarLogin(MsjServUsuario mUsuario) {

        Usuario usuarioMensaje = mUsuario.getUsuario();

        mUsuario = validarInformacionLogin(usuarioMensaje);

        if (Constantes.OK == mUsuario.getCodError()) {
            //TODO Validamos en la BD que el usuario exista para entrar
            Usuario usuarioBD = servUsuario.getUsuarioByEmailPwd(usuarioMensaje.getEmail(), usuarioMensaje.getPwd());

            if (null != usuarioBD) {

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

                        if (null != infUsuarioBD){
                        
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
            } else {
                //Se rellena el mensaje con el codigo y el error
                mUsuario.setCodError(Constantes.ERROR_USUARIO_NO_REGISTRADO);
                mUsuario.setMensaje("El usuario no está dado de alta en la aplicación.");
            }
        }

        return mUsuario;
    }

    /**
     * Valida la informacion antes de comprobarla en la BD
     *
     * @param usuario
     * @return
     */
    private MsjServUsuario validarInformacionLogin(Usuario usuario) {

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
     * Almacena la informacion del registro
     *
     * @param mUsuario
     * @return
     */
    /*public MsjServUsuario guardarRegistro(MsjServUsuario mUsuario) {

        Usuario usuario = mUsuario.getUsuario();

        mUsuario = validarInformacionRegistro(usuario);

        if (Constantes.OK == mUsuario.getCodError()) {
            //Cambiamos el tipo de objeto
            UsuarioBD usuarioBD = UtilidadesServidor.parsearUsuarioToUsuarioBD(usuario);

            //TODO Insertamos en la BD el registro, comprobando que no exista el usuario
            usuarioBD = servUsuario.insertarUsuario(usuarioBD);

            if (null == usuarioBD) {
                mUsuario.setCodError(Constantes.ERROR_BD);
                mUsuario.setMensaje("Se ha producido un error al guardar la información.");

            } else {
                //Pasamos el usuario para enviarlo
                usuario = UtilidadesServidor.parsearUsuarioBDToUsuario(usuarioBD);
                mUsuario.setUsuario(usuario);
                mUsuario.setCodError(Constantes.OK);
                mUsuario.setMensaje("Se ha registrado correctamente.");
            }
        }

        return mUsuario;
    }*/
    /**
     * Valida el formato de la informacion antes de darla de alta en la BD
     *
     * @return
     */
    /*private MsjServUsuario validarInformacionRegistro(Usuario usuario) {

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
    }*/
    /**
     * ---------------------------------------------------------------------------------------------------------------
     * PanelPerfil
     * ---------------------------------------------------------------------------------------------------------------
     */
    /**
     * Almacena la informacion de las preferencias
     *
     * @param mUsuario
     * @return
     */
    public MsjServUsuario guardarPerfil(MsjServUsuario mUsuario) {

        Usuario usuario = mUsuario.getUsuario();

        mUsuario = validarInformacionPerfil(usuario);

        if (Constantes.OK == mUsuario.getCodError()) {

            //Validar si ya tiene informacion de usuario
            Usuario usuarioBD = servUsuario.getInformacionUsuarioById(usuario.getIdUsuario());

            //Si no tiene se inserta
            if (null == usuarioBD.getNick() || usuarioBD.getNick().isEmpty()) {
                if (servUsuario.insertarInformacionUsuario(usuario)) {
                    mUsuario.setCodError(Constantes.OK);
                    mUsuario.setMensaje("Se han guardado los cambios del perfil correctamente.");
                } else {
                    mUsuario.setCodError(Constantes.ERROR_BD);
                    mUsuario.setMensaje("Se ha producido un error al guardar la información.");
                }
            } else {
                //Guardamos las preferencias en la BD, por si tienen cambios
                if (servUsuario.actualizarInformacionUsuario(usuario)) {
                    mUsuario.setCodError(Constantes.OK);
                    mUsuario.setMensaje("Se han guardado los cambios del perfil correctamente.");
                } else {
                    mUsuario.setCodError(Constantes.ERROR_BD);
                    mUsuario.setMensaje("Se ha producido un error al guardar la información.");
                }
            }
        }

        return mUsuario;
    }

    /**
     * Valida el formato de la informacion antes de guardarla en la BD
     *
     * @param usuario
     * @return
     */
    private MsjServUsuario validarInformacionPerfil(Usuario usuario) {

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
     * Devuelve la informacion del usuario
     *
     * @param mUsuario
     * @return
     */
    /*public MsjServUsuario obtenerUsuario(MsjServUsuario mUsuario) {

        Usuario usuario = mUsuario.getUsuario();

        if (Constantes.OK == mUsuario.getCodError()) {
            //TODO Insertamos en la BD el registro, comprobando que no exista el usuario
            UsuarioBD usuarioBD = servUsuario.getUsuarioById(usuario.getIdUsuario());

            if (null == usuarioBD) {
                mUsuario.setCodError(Constantes.ERROR_BD);
                mUsuario.setMensaje("Se ha producido un error al guardar la información.");

            } else {
                //Pasamos el usuario para enviarlo
                usuario = UtilidadesServidor.parsearUsuarioBDToUsuario(usuarioBD);
                mUsuario.setUsuario(usuario);
                mUsuario.setCodError(Constantes.OK);
                mUsuario.setMensaje("Se ha registrado correctamente.");
            }
        }

        return mUsuario;
    }*/
    /**
     * ---------------------------------------------------------------------------------------------------------------
     * Administracion
     * ---------------------------------------------------------------------------------------------------------------
     */
    /**
     * Crea un usuario
     *
     * @param mUsuario
     * @return
     */
    public MsjServUsuario guardarUsuario(MsjServUsuario mUsuario) {

        Usuario usuario = mUsuario.getUsuario();

        mUsuario = validarInformacionUsuario(usuario);

        if (Constantes.OK == mUsuario.getCodError()) {

            //Guardamos el usuario en la BD
            if (servUsuario.insertarUsuario(usuario)) {
                mUsuario.setCodError(Constantes.OK);
                mUsuario.setMensaje("Se ha creado el usuario correctamente.");
            } else {
                mUsuario.setCodError(Constantes.ERROR_BD);
                mUsuario.setMensaje("Se ha producido un error al crear el usuario.");
            }
        }

        return mUsuario;
    }

    /**
     * Actualiza un usuario
     *
     * @param mUsuario
     * @return
     */
    public MsjServUsuario actualizarUsuario(MsjServUsuario mUsuario) {

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

                            //TODO BORRAMOS LOS DATOS DEL RESTO DE TABLAS SIN USO
                            //Eliminar conexion
                            //Eliminar mensajes
                            //Eliminar documentos
                            //Eliminar me gusta
                            //Eliminar amigos                            
                            //Actualiza la informacion
                            if (servUsuario.actualizarUsuario(usuarioActualizar)) {
                                if (servUsuario.eliminarInformacionUsuario(usuarioActualizar.getIdUsuario())) {
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
     * Valida el formato de la informacion antes de crear/actualizar un usuario
     * en la BD
     *
     * @param usuario
     * @return
     */
    private MsjServUsuario validarInformacionUsuario(Usuario usuario) {

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
