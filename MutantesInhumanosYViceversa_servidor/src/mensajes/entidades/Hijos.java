package mensajes.entidades;

import java.io.Serializable;

/**
 *
 * @author Flor
 */
public class Hijos implements Serializable{

    private int id;
    private String descripcion;

    public Hijos(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
