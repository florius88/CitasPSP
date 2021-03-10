package servidor;

import envio.MsjServCargaVentana;
import envio.MsjServUsuario;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import mensaje.Firma;

/**
 *
 * @author Flor
 */
class Hilo extends Thread {

    //Accion usuario
    private final int ACCION_VAL_LOGIN = 1;
    private final int ACCION_REGISTRO_USUARIO = 2;
    private final int ACCION_GUARDAR_PREFERENCIAS = 3;
    private final int ACCION_DEVOLVER_USUARIO = 4;
    private final int ACCION_CREAR_USUARIO = 5;
    private final int ACCION_ACTUALIZAR_USUARIO = 6;

    //Accion mensaje
    private final int ACCION_ENVIAR_MSJ = 1;
    private final int ACCION_ELIMINAR_MSJ = 2;
    private final int ACCION_ACTUALIZAR_MSJ = 3;

    //Accion amigo
    private final int ACCION_LISTA_AMIGOS = 1;
    private final int ACCION_DEJAR_AMIGO = 2;
    private final int ACCION_BUSCAR_AMIGO = 3;
    private final int ACCION_ME_GUSTA = 4;

    //Accion conexion
    private final int ACCION_CREAR_CONEXION = 1;
    private final int ACCION_ELIMINAR_CONEXION = 2;

    //Accion administrador
    private final int ACCION_CARGAR_ADMIN = 1;
    private final int ACCION_BAJA_USUARIO = 2;
    private final int ACCION_ACTIVAR_USUARIO = 3;

    Socket cliente;

    Hilo(Socket cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        try {
            boolean valide = false;
            Firma f;

            Object resultado = null;
            Object recepcion = null;

            while (!valide) {
                ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
                recepcion = ois.readObject();

                if (recepcion instanceof MsjServUsuario) {
                    System.out.println("Entra Servidor: MsjServUsuario");
                    MsjServUsuario rec = (MsjServUsuario) recepcion;

                    //Segun la accion hace una cosa u orta
                    switch (rec.getAccion()) {
                        case ACCION_VAL_LOGIN:
                            //Validar entrar login
                            //Se rellena el mensaje con el codigo y el error
                            rec.setCodError(6);
                            rec.setMensaje("El usuario no está dado de alta en la aplicación.");

                            resultado = rec;
                            break;
                        case ACCION_REGISTRO_USUARIO:
                            //Registro del usuario
                            //resultado = gestionusuario.guardarRegistro(rec);
                            break;
                        case ACCION_GUARDAR_PREFERENCIAS:
                            //Guardar perfil
                            //resultado = gestionusuario.guardarPerfil(rec);
                            break;
                        case ACCION_DEVOLVER_USUARIO:
                            //Devolver usuario
                            //resultado = gestionusuario.obtenerUsuario(rec);
                            break;
                        case ACCION_CREAR_USUARIO:
                            //Crear usuario
                            //resultado = gestionusuario.guardarUsuario(rec);
                            break;
                        case ACCION_ACTUALIZAR_USUARIO:
                            //Actualizar usuario
                            //resultado = gestionusuario.actualizarUsuario(rec);
                            break;
                    }

                }

                //valide = UtilFirma.verificarFirma(f.getMensaje(), f.getFirma(), f.getClavePublica());

                //DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
                
                ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
                
                //dos.writeBoolean(valide);
                
                oos.writeObject(recepcion);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
