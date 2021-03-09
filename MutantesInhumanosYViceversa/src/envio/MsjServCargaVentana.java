package envio;

import entidades.Relacion;
import entidades.Hijos;
import entidades.Sexo;
import entidades.Interes;
import entidades.Rol;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Flor
 */
public class MsjServCargaVentana implements Serializable {

     //Variables
    private int codError = 0;
    private String mensaje = "";
    private ArrayList<Relacion> listaRelacion = new ArrayList();
    private ArrayList<Hijos> listaHijos = new ArrayList();
    private ArrayList<Sexo> listaSexo = new ArrayList();
    private ArrayList<Interes> listaInteres = new ArrayList();
    private ArrayList<Rol> listaRol = new ArrayList();

    public int getCodError() {
        return codError;
    }

    public void setCodError(int codError) {
        this.codError = codError;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public ArrayList<Relacion> getListaRelacion() {
        return listaRelacion;
    }

    public void setListaRelacion(ArrayList<Relacion> listaRelacion) {
        this.listaRelacion = listaRelacion;
    }

    public ArrayList<Hijos> getListaHijos() {
        return listaHijos;
    }

    public void setListaHijos(ArrayList<Hijos> listaHijos) {
        this.listaHijos = listaHijos;
    }

    public ArrayList<Sexo> getListaSexo() {
        return listaSexo;
    }

    public void setListaSexo(ArrayList<Sexo> listaSexo) {
        this.listaSexo = listaSexo;
    }

    public ArrayList<Interes> getListaInteres() {
        return listaInteres;
    }

    public void setListaInteres(ArrayList<Interes> listaInteres) {
        this.listaInteres = listaInteres;
    }

    public ArrayList<Rol> getListaRol() {
        return listaRol;
    }

    public void setListaRol(ArrayList<Rol> listaRol) {
        this.listaRol = listaRol;
    }
}
