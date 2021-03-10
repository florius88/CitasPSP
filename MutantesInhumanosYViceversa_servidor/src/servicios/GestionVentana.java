package servicios;

import mensajes.entidades.Hijos;
import mensajes.entidades.Interes;
import mensajes.entidades.Relacion;
import mensajes.entidades.Rol;
import mensajes.entidades.Sexo;
import mensajes.MsjServCargaVentana;
import java.util.ArrayList;
import modelosBD.HijosBD;
import modelosBD.InteresBD;
import modelosBD.RelacionBD;
import modelosBD.RolBD;
import modelosBD.SexoBD;
import utilidades.Constantes;

/**
 * Se encarga de recuperar la informacion de los combos para las ventanas
 *
 * @author Flor
 */
public class GestionVentana {

    private RelacionBD servRelacion = null;
    private HijosBD servHijos = null;
    private SexoBD servSexo = null;
    private InteresBD servInteres = null;
    private RolBD servRol = null;

    /**
     * Constructor
     */
    public GestionVentana() {
        //Se inicializan las clases para invocar a la BD
        servRelacion = new RelacionBD();
        servHijos = new HijosBD();
        servSexo = new SexoBD();
        servInteres = new InteresBD();
        servRol = new RolBD();
    }

    /**
     * Metodo encargado de recuperar toda la informacion de los combos de la
     * aplicacion
     *
     * @param mCargaVentana
     * @return
     */
    public MsjServCargaVentana obtenerInfoCargaVentana(MsjServCargaVentana mCargaVentana) {

        //Obtiene la lista de las relaciones
        ArrayList<Relacion> listaRelacionBD = servRelacion.getListaRelacion();
        //Obtiene la lista de los hijos
        ArrayList<Hijos> listaHijosBD = servHijos.getListaHijos();
        //Obtiene la lista de los sexos
        ArrayList<Sexo> listaSexoBD = servSexo.getListaSexo();
        //Obtiene la lista de los intereses
        ArrayList<Interes> listaInteresBD = servInteres.getListaInteres();
        //Obtiene la lista de los roles
        ArrayList<Rol> listaRolBD = servRol.getListaRol();

        if (null != listaRelacionBD && null != listaHijosBD && null != listaSexoBD && null != listaInteresBD && null != listaRolBD) {

            mCargaVentana.setListaHijos(listaHijosBD);
            mCargaVentana.setListaInteres(listaInteresBD);
            mCargaVentana.setListaRelacion(listaRelacionBD);
            mCargaVentana.setListaSexo(listaSexoBD);
            mCargaVentana.setListaRol(listaRolBD);

            //Se rellena el mensaje con el codigo y el error
            mCargaVentana.setCodError(Constantes.OK);
            mCargaVentana.setMensaje("Se ha eliminado el mensaje correctamente.");
        } else {
            //Se rellena el mensaje con el codigo y el error
            mCargaVentana.setCodError(Constantes.ERROR_BD);
            mCargaVentana.setMensaje("Se ha producido un error al cargar las listas.");
        }

        return mCargaVentana;
    }
}
