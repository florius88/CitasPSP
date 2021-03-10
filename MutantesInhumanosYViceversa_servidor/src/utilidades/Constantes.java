package utilidades;

/**
 *
 * @author Flor
 */
public class Constantes {
    
    //Parametros para la conexion con la BD
    public static final String SERVIDOR = "localhost";
    public static final String PUERTO = "1433";
    public static final String DATABASE = "citas";
    public static final String USER_BD = "sa";
    public static final String PWD_BD = "Abc123456";
    
    //Accion usuario
    public static final int ACCION_VAL_LOGIN = 1;
    public static final int ACCION_REGISTRO_USUARIO = 2;
    public static final int ACCION_GUARDAR_PREFERENCIAS = 3;
    public static final int ACCION_DEVOLVER_USUARIO = 4;
    public static final int ACCION_CREAR_USUARIO = 5;
    public static final int ACCION_ACTUALIZAR_USUARIO = 6;
    public static final int ACCION_GUARDAR_PERFIL = 7;

    //Accion mensaje
    public static final int ACCION_ENVIAR_MSJ = 1;
    public static final int ACCION_ELIMINAR_MSJ = 2;
    public static final int ACCION_ACTUALIZAR_MSJ = 3;
    
     //Accion amigo
    public static final int ACCION_LISTA_AMIGOS = 1;
    public static final int ACCION_DEJAR_AMIGO = 2;
    public static final int ACCION_BUSCAR_AMIGO = 3;
    public static final int ACCION_ME_GUSTA = 4;

    //Accion conexion
    public static final int ACCION_CREAR_CONEXION = 1;
    public static final int ACCION_ELIMINAR_CONEXION = 2;
    
    //Accion administrador
    public static final int ACCION_CARGAR_ADMIN = 1;
    public static final int ACCION_BAJA_USUARIO = 2;
    public static final int ACCION_ACTIVAR_USUARIO = 3;
    
    //Roles
    public static final int ROL_SUPERADMIN = 1;
    public static final int ROL_ADMIN = 2;
    public static final int ROL_USER = 3;

    //Codigos de acceso
    public static final int OK = 0;
    public static final int OK_PRIMER_ACCESO = 4;

    //Codigos de error
    public static final int ERROR_FORMATO_EMAIL = 1;
    public static final int ERROR_PWD = 2;
    public static final int ERROR_PWD_NO_IGUALES = 7;
    public static final int ERROR_USUARIO_NO_ACTIVO = 3;
    public static final int ERROR_ENTRAR = 5;
    public static final int ERROR_USUARIO_NO_REGISTRADO = 6;
    public static final int ERROR_NO_NICK = 8;
    public static final int ERROR_NO_FOTO = 9;
    public static final int ERROR_NO_MENSAJES = 10;
    public static final int ERROR_NO_AMIGOS = 11;
    public static final int ERROR_NO_USUARIOS = 12;

    public static final int ERROR_BD = 99;
}
