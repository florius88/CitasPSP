package servicios;

import modelosBD.MeGustaBD;
import mensajes.entidades.Amigos;
import mensajes.MsjServAmigos;
import java.util.ArrayList;
import mensajes.entidades.Usuario;
import modelosBD.ConexBD;
import modelosBD.UsuarioBD;
import modelosBD.AmigosBD;
import utilidades.Constantes;

/**
 * Se encarga de toda la gestion con los amigos
 *
 * @author Flor
 */
public class GestionAmigos {

    private final int RANGO_MIN = 0;
    private final int RANGO_MAX = 100;
    //Rango que buscara de afinidad
    private final int RANGO_COMUN = 10;

    //Filtro hijos
    private final int TENGO_HIJOS = 1;
    private final int NO_TENGO_HIJOS = 2;
    private final int ME_GUSTARIA_TENER_HIJOS = 3;
    private final int NO_QUIERO_TENER_HIJOS = 4;
    //Filtro Interes
    private final int INTERES_HOMBRES = 1;
    private final int INTERES_MUJERES = 2;
    //Filtro Sexo
    private final int HOMBRE = 1;
    private final int MUJER = 2;

    private AmigosBD servAmigos = null;
    private UsuarioBD servUsuario = null;
    private ConexBD servConexion = null;
    private MeGustaBD servMeGusta = null;

    /**
     * Constructor
     */
    public GestionAmigos() {
        //Se inicializan las clases para invocar a la BD
        servAmigos = new AmigosBD();
        servUsuario = new UsuarioBD();
        servConexion = new ConexBD();
        servMeGusta = new MeGustaBD();
    }

    /**
     * Elimina amigo de la BD
     *
     * @param mAmigos
     * @return
     */
    /*public MsjServAmigos eliminarAmigo(MsjServAmigos mAmigos) {

        if (servAmigos.eliminarAmgioByIdUsuarioIdUsuarioAmigo(mAmigos.getIdUsuario())) {
            //Se rellena el mensaje con el codigo y el error
            mAmigos.setCodError(Constantes.OK);
            mAmigos.setMensaje("Se ha eliminado el amigo correctamente.");
        } else {
            //Se rellena el mensaje con el codigo y el error
            mAmigos.setCodError(Constantes.ERROR_BD);
            mAmigos.setMensaje("Se ha producido un error al eliminar el amigo.");
        }

        return mAmigos;
    }*/
    /**
     * Obtiene una lista de amigos por Id de usuario
     *
     * @param mAmigos
     * @return
     */
    public MsjServAmigos obtenerListaAmigosPorIdUsuario(MsjServAmigos mAmigos) {

        //Mira si el usuario existe en la BD
        Usuario usuarioBD = servUsuario.getUsuarioById(mAmigos.getIdUsuario());

        ArrayList<Amigos> listaAmigosBDResult = null;
        ArrayList<Amigos> listaAmigosBD = null;

        if (null != usuarioBD) {
            listaAmigosBD = servAmigos.obtenerListaAmigosPorIdUsuario(mAmigos.getIdUsuario());

            if (null != listaAmigosBD && !listaAmigosBD.isEmpty()) {

                listaAmigosBDResult = new ArrayList();

                for (Amigos amigoBD : listaAmigosBD) {

                    Usuario userAmigoBD = servUsuario.getUsuarioById(amigoBD.getIdUsuario());

                    if (null != userAmigoBD) {

                        //Obtiene la informacion del usuario
                        Usuario infUsuarioBD = servUsuario.getInformacionUsuarioById(amigoBD.getIdUsuario());

                        if (null != infUsuarioBD) {

                            Amigos amigo = new Amigos();

                            amigo.setIdUsuario(userAmigoBD.getIdUsuario());
                            amigo.setNick(infUsuarioBD.getNick());
                            //Devuelve si el usuario esta o no conectado
                            amigo.setConectado(servConexion.getConexionByIdUsuario(userAmigoBD.getIdUsuario()));
                            listaAmigosBDResult.add(amigo);
                        }
                    }
                }

                mAmigos.setListaAmigos(listaAmigosBDResult);

                //Se rellena el mensaje con el codigo y el error                
                mAmigos.setCodError(Constantes.OK);
                mAmigos.setMensaje("OK");

            } else {
                //Se rellena el mensaje con el codigo y el error                
                mAmigos.setCodError(Constantes.ERROR_NO_AMIGOS);
                mAmigos.setMensaje("Todavia no has hecho ningún amigo.");
            }
        }

        return mAmigos;
    }

    /**
     * Obtiene una lista de todos los usuarios afines
     *
     * @param mAmigos
     * @return
     */
    public MsjServAmigos obtenerListaBuscarAmigos(MsjServAmigos mAmigos) {

        //Mira si el usuario existe en la BD
        Usuario usuarioBD = servUsuario.getUsuarioById(mAmigos.getIdUsuario());

        if (null != usuarioBD) {

            //Obtiene la informacion del usuario
            Usuario infUsuarioBD = servUsuario.getInformacionUsuarioById(usuarioBD.getIdUsuario());

            //Almacena la relacion que quiere el usuario
            int filtroRelacion = infUsuarioBD.getRelacion();

            //Si el sexo es 0 no se usa ese filtro, pero se filta por los intereses 'ambos'
            int filtroSexo = buscarFiltroInteres(infUsuarioBD.getInteres());

            //Filtrar por los hijos
            int filtroHijos = buscarFiltroHijos(infUsuarioBD.getHijos());

            ArrayList<Amigos> listaUsuariosAfines = new ArrayList();
            ArrayList<Usuario> listaUsuariosAfinesBD = servUsuario.listaUsuariosAfines(filtroRelacion, infUsuarioBD.getSexo(), filtroSexo, filtroHijos, infUsuarioBD.getHijos());

            for (Usuario usuarioAfinBD : listaUsuariosAfinesBD) {

                //Obtiene la informacion del usuario afin
                Usuario AfinBD = servUsuario.getUsuarioById(usuarioAfinBD.getIdUsuario());

                //Si esta activo se continua con las comprobaciones
                if (AfinBD.getActivo()) {

                    //Sacar un porcentaje minimo y maximo de estos 10%
                    boolean comun = false;

                    if (preferenciasComunes(infUsuarioBD.getDeporte(), usuarioAfinBD.getDeporte())) {
                        comun = true;
                    }
                    if (preferenciasComunes(infUsuarioBD.getArte(), usuarioAfinBD.getArte())) {
                        comun = true;
                    }
                    if (preferenciasComunes(infUsuarioBD.getPolitica(), usuarioAfinBD.getPolitica())) {
                        comun = true;
                    }

                    //Con al menos 1 preferencia en comun, es apto y tiene que tener foto de perfil
                    if (comun && null != usuarioAfinBD.getFoto()) {
                        Amigos amigo = new Amigos();

                        amigo.setIdUsuario(usuarioAfinBD.getIdUsuario());
                        amigo.setNick(usuarioAfinBD.getNick());
                        amigo.setFoto(usuarioAfinBD.getFoto());

                        //Buscar si tiene me gusta
                        amigo.setMeGusta(servMeGusta.obtenerMeGustaByIdUsuarioIdUsuarioAmigo(usuarioBD.getIdUsuario(), usuarioAfinBD.getIdUsuario()));

                        listaUsuariosAfines.add(amigo);
                    }
                }
            }

            if (listaUsuariosAfines.isEmpty()) {
                //Se rellena el mensaje con el codigo y el error                
                mAmigos.setCodError(Constantes.ERROR_NO_AMIGOS);
                mAmigos.setMensaje("No hay personas afines que mostrar. No te preocupes, seguiremos buscando...");
            } else {
                //Almacena la lista de las personas que han resultado afines
                mAmigos.setListaAmigos(listaUsuariosAfines);

                //Se rellena el mensaje con el codigo y el error                
                mAmigos.setCodError(Constantes.OK);
                mAmigos.setMensaje("OK");
            }
        }

        return mAmigos;
    }

    /**
     * Segun el valor del me gusta, gestionamos la tabla de me gustas
     *
     * @param mAmigos
     * @return
     */
    public MsjServAmigos modificarMeGusta(MsjServAmigos mAmigos) {

        int idUsuario = mAmigos.getIdUsuario();
        Amigos amigo = mAmigos.getListaAmigos().get(0);

        if (amigo.isMeGusta()) {
            //Inserta el me gusta
            if (servMeGusta.insertarMeGustaByIdUsuarioIdUsuarioAmigo(idUsuario, amigo.getIdUsuario())) {
                //Si el otro usuario tambien tiene registro en la tabla, se crea una amistad
                if (servMeGusta.obtenerMeGustaByIdUsuarioIdUsuarioAmigo(amigo.getIdUsuario(), idUsuario)) {
                    servAmigos.insertarAmistadByIdUsuarioIdUsuarioAmigo(idUsuario, amigo.getIdUsuario());
                    servAmigos.insertarAmistadByIdUsuarioIdUsuarioAmigo(amigo.getIdUsuario(), idUsuario);
                }
                //Se rellena el mensaje con el codigo y el error
                mAmigos.setCodError(Constantes.OK);
                mAmigos.setMensaje("Se ha registrado el me gusta correctamente.");
            } else {
                //Se rellena el mensaje con el codigo y el error
                mAmigos.setCodError(Constantes.ERROR_BD);
                mAmigos.setMensaje("Se ha producido un error al registrar el me gusta.");
            }
        } else {
            //Borra el me gusta
            if (servMeGusta.eliminarMeGustaByIdUsuarioIdUsuarioAmigo(idUsuario, amigo.getIdUsuario())) {
                //Si tenian amistad, se elimina
                if (servAmigos.obtenerAmistadByIdUsuarioIdUsuarioAmigo(idUsuario, amigo.getIdUsuario())
                        || servAmigos.obtenerAmistadByIdUsuarioIdUsuarioAmigo(amigo.getIdUsuario(), idUsuario)) {
                    servAmigos.eliminarAmgioByIdUsuarioIdUsuarioAmigo(idUsuario, amigo.getIdUsuario());
                    servAmigos.eliminarAmgioByIdUsuarioIdUsuarioAmigo(amigo.getIdUsuario(), idUsuario);
                }

                /*if (servAmigos.obtenerAmistadByIdUsuarioIdUsuarioAmigo(idUsuario, amigo.getIdUsuario())) {
                    servAmigos.eliminarAmgioByIdUsuarioIdUsuarioAmigo(idUsuario, amigo.getIdUsuario());
                } else if (servAmigos.obtenerAmistadByIdUsuarioIdUsuarioAmigo(amigo.getIdUsuario(), idUsuario)) {
                    servAmigos.eliminarAmgioByIdUsuarioIdUsuarioAmigo(idUsuario, amigo.getIdUsuario());
                }*/

                //Se rellena el mensaje con el codigo y el error
                mAmigos.setCodError(Constantes.OK);
                mAmigos.setMensaje("Se ha eliminado el me gusta correctamente.");
            } else {
                //Se rellena el mensaje con el codigo y el error
                mAmigos.setCodError(Constantes.ERROR_BD);
                mAmigos.setMensaje("Se ha producido un error al registrar el me gusta.");
            }
        }

        return mAmigos;
    }

    /**
     * Devuelve el filtro para el interes segun las opciones
     *
     * @param interes
     * @return
     */
    private int buscarFiltroInteres(int interes) {

        int filtroSexo = 0;

        switch (interes) {
            case INTERES_HOMBRES:
                //Hombres
                filtroSexo = HOMBRE;
                break;
            case INTERES_MUJERES:
                //Mujeres
                filtroSexo = MUJER;
                break;
        }

        return filtroSexo;
    }

    /**
     * Devuelve el filtro para los hijos segun las opciones
     *
     * @param hijos
     * @return
     */
    private int buscarFiltroHijos(int hijos) {

        int filtroHijos = 0;

        switch (hijos) {
            case TENGO_HIJOS:
                //buscar por este filtro y los que les gustaria tener hijos,
                //se considera que tendran mas afinidad
                filtroHijos = ME_GUSTARIA_TENER_HIJOS;
                break;
            case NO_TENGO_HIJOS:
                //Si buscara sin filtro, ya que les puede gustar cualquiera, 
                //queda asi por si hay que cambiar el algoritmos
                break;
            case ME_GUSTARIA_TENER_HIJOS:
                //buscar por este filtro y los que tienen hijos,
                //se considera que tendran mas afinidad
                filtroHijos = TENGO_HIJOS;
                break;
            case NO_QUIERO_TENER_HIJOS:
                //buscar por este filtro y  los que no tienen hijos,
                //se considera que tendran mas afinidad
                filtroHijos = NO_TENGO_HIJOS;
                break;
        }

        return filtroHijos;
    }

    /**
     * Metodo encargado de comparar los valores de las preferencias para que
     * tengan un rango en comun
     *
     * @param valorUsuario
     * @param valorAmigo
     * @return
     */
    private boolean preferenciasComunes(int valorUsuario, int valorAmigo) {

        boolean enComun = false;

        int valorMin;
        int valorMax;

        if (RANGO_MIN > (valorUsuario - RANGO_COMUN)) {
            valorMin = RANGO_MIN;
        } else {
            valorMin = valorUsuario - RANGO_COMUN;
        }

        if (RANGO_MAX < (valorUsuario + RANGO_COMUN)) {
            valorMax = RANGO_MAX;
        } else {
            valorMax = valorUsuario + RANGO_COMUN;
        }

        if (valorAmigo >= valorMin && valorAmigo <= valorMax) {
            enComun = true;
        }

        return enComun;
    }
}
