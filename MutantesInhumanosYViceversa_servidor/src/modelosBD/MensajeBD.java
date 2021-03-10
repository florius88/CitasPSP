package modelosBD;

import conexionBD.ConexionBD;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import mensajes.entidades.Adjuntos;
import mensajes.entidades.Mensaje;

/**
 *
 * @author Flor
 */
public class MensajeBD {

    /**
     * Metodo que inserta el mensaje
     *
     * @param msj
     * @return
     */
    public synchronized boolean insertarMensaje(Mensaje msj) {

        boolean insertado = true;

        try {

            //Se inicializa la conexion
            ConexionBD conexionBD = new ConexionBD();
            //Se abre la conexion
            conexionBD.abrirConexion();

            String[] returnIdMensaje = {"BATCHID"};

            //Se pasa la fecha formateada
            String fecha = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date());
            int NO_LEIDO = 0;

            //Sentencia
            String sentencia = "INSERT INTO MENSAJE" + " VALUES (" + msj.getIdUsuarioEmisor()
                    + "," + msj.getIdUsuarioReceptor() + "," + "'" + msj.getMensajeEmisor() + "', '" + fecha + "'," + NO_LEIDO + ")";

            java.sql.PreparedStatement statement = conexionBD.getConex().prepareStatement(sentencia, returnIdMensaje);
            int filasInsertadas = statement.executeUpdate();

            if (filasInsertadas == 0) {
                //Error al crear el usuario.
                insertado = false;
            } else {

                int idMensaje = 0;

                try ( java.sql.ResultSet rs = statement.getGeneratedKeys()) {
                    if (rs.next()) {
                        //Setea el id del usuario
                        idMensaje = rs.getInt(1);
                    }
                    rs.close();
                }

                //Insertamos los documentos adjuntos
                if (0 != idMensaje && null != msj.getListaAdjuntosEmisor() && !msj.getListaAdjuntosEmisor().isEmpty()) {

                    for (Adjuntos adj : msj.getListaAdjuntosEmisor()) {

                        String sentencia2 = "INSERT INTO DOCUMENTO_MENSAJE VALUES (? ,?)";

                        java.sql.PreparedStatement statement2 = conexionBD.getConex().prepareStatement(sentencia2);

                        statement2.setInt(1, idMensaje);

                        Image imgFto = adj.getAdjunto().getImage();

                        BufferedImage bufferedImage = new BufferedImage(imgFto.getWidth(null), imgFto.getHeight(null), BufferedImage.TYPE_INT_RGB);
                        //bufferedImage is the RenderedImage to be written
                        Graphics2D g2 = bufferedImage.createGraphics();
                        g2.drawImage(imgFto, null, null);

                        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                        try {
                            ImageIO.write(bufferedImage, "jpg", outStream);
                        } catch (IOException ex) {
                            Logger.getLogger(UsuarioBD.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        statement2.setBytes(2, outStream.toByteArray());

                        int i = statement2.executeUpdate();
                        //TODO Revisar para dejar registro de errores en los adjuntos!!!!!!!!
                        if (i == 0) {
                            //Error al actualizar el usuario.
                            insertado = false;
                        }
                    }
                }

                //Cierra la conexion
                conexionBD.cerrarConexion();
            }

        } catch (SQLException sq) {
            insertado = false;
        }

        return insertado;

    }

    /**
     * Metodo que elimina el mensaje
     *
     * @param msj
     * @return
     */
    public synchronized boolean eliminarMensaje(Mensaje msj) {

        boolean eliminado = true;

        try {

            //Se inicializa la conexion
            ConexionBD conexionBD = new ConexionBD();
            //Se abre la conexion
            conexionBD.abrirConexion();

            //Sentencia
            String sentencia = "DELETE FROM MENSAJE WHERE ID = " + msj.getIdMensaje();

            java.sql.PreparedStatement statement = conexionBD.getConex().prepareStatement(sentencia);
            statement.executeUpdate();

            //Cierra la conexion
            conexionBD.cerrarConexion();

        } catch (SQLException ex) {
            eliminado = false;
        }

        return eliminado;
    }

    /**
     * Metodo que elimina los adjuntos del mensaje
     *
     * @param msj
     * @return
     */
    public synchronized boolean eliminarAdjuntosMensaje(Mensaje msj) {

        boolean eliminado = true;

        try {

            //Se inicializa la conexion
            ConexionBD conexionBD = new ConexionBD();
            //Se abre la conexion
            conexionBD.abrirConexion();

            //Sentencia
            String sentencia = "DELETE FROM DOCUMENTO_MENSAJE WHERE ID_MENSAJE = " + msj.getIdMensaje();

            java.sql.PreparedStatement statement = conexionBD.getConex().prepareStatement(sentencia);
            statement.executeUpdate();

            //Cierra la conexion
            conexionBD.cerrarConexion();

        } catch (SQLException ex) {
            eliminado = false;
        }

        return eliminado;
    }

    /**
     * Metodo que actualiza el mensaje a leido
     *
     * @param msj
     * @return
     */
    public synchronized boolean actualizarMensaje(Mensaje msj) {

        boolean leido = true;

        try {

            //Se inicializa la conexion
            ConexionBD conexionBD = new ConexionBD();
            conexionBD.abrirConexion();

            int leidoBD = 0;

            //Lo pone a 1 si se activa
            if (msj.isLeidoReceptor()) {
                leidoBD = 1;
            }

            //Sentencia
            String sentencia = "UPDATE MENSAJE SET LEIDO = " + leidoBD + " WHERE ID = " + msj.getIdMensaje();

            java.sql.PreparedStatement statement = conexionBD.getConex().prepareStatement(sentencia);
            int filasInsertadas = statement.executeUpdate();

            if (filasInsertadas == 0) {
                //Error al actualizar el usuario.
                leido = false;
            }

            //Cierra la conexion
            conexionBD.cerrarConexion();

        } catch (SQLException ex) {
            leido = false;
        }

        return leido;
    }

    /**
     * Metodo que obtiene una lista de mensajes por Id usuario
     *
     * @param idUsuario
     * @return
     */
    public synchronized ArrayList<Mensaje> obtenerListaMensajesPorIdUsuario(int idUsuario) {

        ArrayList<Mensaje> listaMensajesBD = new ArrayList<>();

        try {

            //Se inicializa la conexion
            ConexionBD conexionBD = new ConexionBD();
            //Se abre la conexion
            java.sql.Statement sentencia_SQL = conexionBD.abrirConexion();

            //Sentencia
            String sentencia = "SELECT * FROM MENSAJE WHERE ID_USUARIO_RECEPTOR = " + idUsuario;

            java.sql.ResultSet conj_Registros = sentencia_SQL.executeQuery(sentencia);

            while (conj_Registros.next()) {
                Mensaje msjBD = new Mensaje();

                int idMensajeBD = conj_Registros.getInt("ID");
                int idUsuarioEmisorBD = conj_Registros.getInt("ID_USUARIO_EMISOR");
                int idUsuarioReceptorBD = conj_Registros.getInt("ID_USUARIO_RECEPTOR");
                String mensajeBD = conj_Registros.getString("MENSAJE");
                String fechaBD = conj_Registros.getString("FECHA_ENVIO");
                boolean leidoBD = conj_Registros.getBoolean("LEIDO");

                msjBD.setIdMensaje(idMensajeBD);
                msjBD.setIdUsuarioEmisor(idUsuarioEmisorBD);
                msjBD.setIdUsuarioReceptor(idUsuarioReceptorBD);
                msjBD.setMensajeEmisor(mensajeBD);
                msjBD.setFechaEnvioEmisor(fechaBD);
                msjBD.setLeidoReceptor(leidoBD);

                //Incluye los usuarios
                listaMensajesBD.add(msjBD);
            }

            //Cierra la conexion
            conexionBD.cerrarConexion();

        } catch (SQLException ex) {

        }

        return listaMensajesBD;
    }

    /**
     * Metodo que devuelve una lista con las imagenes adjuntas por Id de mensaje
     *
     * @param idMensaje
     * @return
     */
    public synchronized ArrayList<Adjuntos> obtenerListaAdjuntosPorIdMensaje(int idMensaje) {

        ArrayList<Adjuntos> listaAdjuntosBD = new ArrayList<>();

        try {

            //Se inicializa la conexion
            ConexionBD conexionBD = new ConexionBD();
            //Se abre la conexion
            java.sql.Statement sentencia_SQL = conexionBD.abrirConexion();

            //Sentencia
            String sentencia = "SELECT * FROM DOCUMENTO_MENSAJE WHERE ID_MENSAJE = " + idMensaje;

            java.sql.ResultSet conj_Registros = sentencia_SQL.executeQuery(sentencia);

            while (conj_Registros.next()) {
                Adjuntos adjBD = new Adjuntos();

                //Obtenemos y convertimos la imagen
                ImageIcon foto = null;
                if (null != conj_Registros.getBytes("FOTO")) {
                    byte[] fotoBD = conj_Registros.getBytes("FOTO");
                    foto = new ImageIcon(fotoBD);
                }

                adjBD.setAdjunto(foto);

                //Incluye los usuarios
                listaAdjuntosBD.add(adjBD);
            }

            //Cierra la conexion
            conexionBD.cerrarConexion();

        } catch (SQLException ex) {

        }

        return listaAdjuntosBD;
    }
}
