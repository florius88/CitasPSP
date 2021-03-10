package modelosBD;

import conexionBD.ConexionBD;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import mensajes.entidades.Usuario;

/**
 *
 * @author Flor
 */
public class UsuarioBD {

    /**
     * Metodo que obtiene el usuario por ID
     *
     * @param idUsuario
     * @return
     */
    public synchronized Usuario getUsuarioById(int idUsuario) {

        Usuario usuarioBD = null;

        try {

            //Se inicializa la conexion
            ConexionBD conexionBD = new ConexionBD();
            //Se abre la conexion
            java.sql.Statement sentencia_SQL = conexionBD.abrirConexion();

            //Sentencia
            String sentencia = "SELECT * FROM USUARIO WHERE ID = " + idUsuario;

            java.sql.ResultSet conj_Registros = sentencia_SQL.executeQuery(sentencia);
            while (conj_Registros.next()) {
                int idBD = conj_Registros.getInt("ID");
                String emailBD = conj_Registros.getString("EMAIL");
                String pwdBD = conj_Registros.getString("PWD");
                int rolBD = conj_Registros.getInt("ROL");
                boolean activo = conj_Registros.getBoolean("ACTIVO");

                usuarioBD = new Usuario();

                usuarioBD.setIdUsuario(idBD);
                usuarioBD.setEmail(emailBD);
                usuarioBD.setPwd(pwdBD);
                usuarioBD.setRol(rolBD);
                usuarioBD.setActivo(activo);
            }

            //Cierra la conexion
            conexionBD.cerrarConexion();

        } catch (SQLException sq) {
            usuarioBD = null;
        }

        return usuarioBD;
    }

    /**
     * Metodo que obtiene la informacion del usuario por ID
     *
     * @param idUsuario
     * @return
     */
    public synchronized Usuario getInformacionUsuarioById(int idUsuario) {

        Usuario usuarioBD = null;

        try {

            //Se inicializa la conexion
            ConexionBD conexionBD = new ConexionBD();
            //Se abre la conexion
            java.sql.Statement sentencia_SQL = conexionBD.abrirConexion();

            //Sentencia
            String sentencia = "SELECT * FROM INFORMACION_USUARIO WHERE ID_USUARIO = " + idUsuario;

            java.sql.ResultSet conj_Registros = sentencia_SQL.executeQuery(sentencia);
            while (conj_Registros.next()) {

                usuarioBD = new Usuario();

                String nickBD = conj_Registros.getString("NICK");
                int deporteBD = conj_Registros.getInt("DEPORTE");
                int arteBD = conj_Registros.getInt("ARTE");
                int politicaBD = conj_Registros.getInt("POLITICA");
                int relacionBD = conj_Registros.getInt("RELACION");
                int hijosBD = conj_Registros.getInt("HIJOS");
                int sexoBD = conj_Registros.getInt("SEXO");
                int interesBD = conj_Registros.getInt("INTERES");

                //Obtenemos y convertimos la imagen
                ImageIcon foto = null;
                if (null != conj_Registros.getBytes("FOTO")) {
                    byte[] fotoBD = conj_Registros.getBytes("FOTO");
                    foto = new ImageIcon(fotoBD);
                }

                String fechaAccesoBD = conj_Registros.getString("FECHA_ACCESO");

                usuarioBD.setNick(nickBD);
                usuarioBD.setDeporte(deporteBD);
                usuarioBD.setArte(arteBD);
                usuarioBD.setPolitica(politicaBD);
                usuarioBD.setRelacion(relacionBD);
                usuarioBD.setHijos(hijosBD);
                usuarioBD.setSexo(sexoBD);
                usuarioBD.setInteres(interesBD);
                usuarioBD.setFoto(foto);
                usuarioBD.setFechaAcceso(fechaAccesoBD);
            }
            //Cierra la conexion
            conexionBD.cerrarConexion();

        } catch (SQLException sq) {
            usuarioBD = null;
        }

        return usuarioBD;
    }

    /**
     * Metodo que obtiene el usuario por email y pwd
     *
     * @param email
     * @param pwd
     * @return
     */
    public synchronized Usuario getUsuarioByEmailPwd(String email, String pwd) {

        Usuario usuarioBD = null;

        try {

            //Se inicializa la conexion
            ConexionBD conexionBD = new ConexionBD();
            //Se abre la conexion
            java.sql.Statement sentencia_SQL = conexionBD.abrirConexion();

            //Sentencia
            String sentencia;

            //Si no se manda la pwd, se busca por el email
            if (null == pwd) {
                sentencia = "SELECT * FROM USUARIO WHERE EMAIL = '" + email + "'";
            } else {
                sentencia = "SELECT * FROM USUARIO WHERE EMAIL = '" + email + "' AND PWD = '" + pwd + "'";
            }

            java.sql.ResultSet conj_Registros = sentencia_SQL.executeQuery(sentencia);
            while (conj_Registros.next()) {
                int idBD = conj_Registros.getInt("ID");
                String emailBD = conj_Registros.getString("EMAIL");
                String pwdBD = conj_Registros.getString("PWD");
                int rolBD = conj_Registros.getInt("ROL");
                boolean activo = conj_Registros.getBoolean("ACTIVO");

                usuarioBD = new Usuario();

                usuarioBD.setIdUsuario(idBD);
                usuarioBD.setEmail(emailBD);
                usuarioBD.setPwd(pwdBD);
                usuarioBD.setRol(rolBD);
                usuarioBD.setActivo(activo);
            }

            //Cierra la conexion
            conexionBD.cerrarConexion();

        } catch (SQLException sq) {
            usuarioBD = null;
        }

        return usuarioBD;
    }

    /**
     * Metodo que inserta un usuario
     *
     * @param usuario
     * @return
     */
    public synchronized boolean insertarUsuario(Usuario usuario) {

        boolean insertado = true;

        try {

            //Se inicializa la conexion
            ConexionBD conexionBD = new ConexionBD();
            //Se abre la conexion
            conexionBD.abrirConexion();

            String[] returnIdUsuario = {"BATCHID"};

            String PWD_DEFECTO = "1234";
            int DESACTIVADO = 0;

            //Sentencia
            String sentencia = "INSERT INTO USUARIO" + " VALUES ('" + usuario.getEmail() + "'," + "'" + PWD_DEFECTO + "'," + usuario.getRol() + "," + DESACTIVADO + ")";

            java.sql.PreparedStatement statement = conexionBD.getConex().prepareStatement(sentencia, returnIdUsuario);
            int filasInsertadas = statement.executeUpdate();

            if (filasInsertadas == 0) {
                //Error al crear el usuario.
                usuario = null;
            } else {

                try ( java.sql.ResultSet rs = statement.getGeneratedKeys()) {
                    if (rs.next()) {
                        //Setea el id del usuario
                        usuario.setIdUsuario(rs.getInt(1));
                    }
                    //TODO VER
                    rs.close();
                }

                //En caso de tener ROL usuario
                if (3 == usuario.getRol()) {
                    //Inserta la informacion del usuario

                    //Sentencia
                    String Sentencia = "INSERT INTO INFORMACION_USUARIO" + " VALUES (" + usuario.getIdUsuario() + "," + "'" + usuario.getNick() + "'," + usuario.getDeporte() + ","
                            + usuario.getArte() + "," + usuario.getPolitica() + "," + usuario.getRelacion() + "," + usuario.getHijos() + "," + usuario.getSexo()
                            + "," + usuario.getInteres() + ", null, null)";

                    java.sql.PreparedStatement statement2 = conexionBD.getConex().prepareStatement(Sentencia, returnIdUsuario);
                    int filasInsertadas2 = statement2.executeUpdate();
                    if (filasInsertadas2 == 0) {
                        //Error al crear la informacion del usuario.
                        insertado = false;
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
     * Metodo que inserta la informacion del usuario
     *
     * @param usuario
     * @return
     */
    public synchronized boolean insertarInformacionUsuario(Usuario usuario) {

        boolean insertado = true;

        try {

            //Se inicializa la conexion
            ConexionBD conexionBD = new ConexionBD();
            //Se abre la conexion
            conexionBD.abrirConexion();

            //Sentencia
            String sentencia = "INSERT INTO INFORMACION_USUARIO" + " VALUES (" + usuario.getIdUsuario() + "," + "'" + usuario.getNick() + "'," + usuario.getDeporte() + ","
                    + usuario.getArte() + "," + usuario.getPolitica() + "," + usuario.getRelacion() + "," + usuario.getHijos() + "," + usuario.getSexo()
                    + "," + usuario.getInteres() + ", null, null)";

            usuario.getFoto();
            usuario.getFechaAcceso();

            java.sql.PreparedStatement statement = conexionBD.getConex().prepareStatement(sentencia);
            int filasInsertadas = statement.executeUpdate();
            if (filasInsertadas == 0) {
                //Error al crear la informacion del usuario.
                insertado = false;
            }

            //Cierra la conexion
            conexionBD.cerrarConexion();

        } catch (SQLException sq) {
            insertado = false;
        }

        return insertado;
    }

    /**
     * Metodo que actualiza la tabla de Usuario
     *
     * @param usuario
     * @return
     */
    public synchronized boolean actualizarUsuario(Usuario usuario) {
        boolean actualizado = true;

        try {

            //Se inicializa la conexion
            ConexionBD conexionBD = new ConexionBD();
            conexionBD.abrirConexion();

            //Sentencia
            String sentencia;
            if (null == usuario.getPwd()) {
                sentencia = "UPDATE USUARIO SET EMAIL = '" + usuario.getEmail() + "', ROL = " + usuario.getRol() + " WHERE ID = " + usuario.getIdUsuario();
            } else {
                sentencia = "UPDATE USUARIO SET EMAIL = '" + usuario.getEmail() + "', PWD = '" + usuario.getPwd() + "', ROL = " + usuario.getRol() + " WHERE ID = " + usuario.getIdUsuario();
            }

            java.sql.PreparedStatement statement = conexionBD.getConex().prepareStatement(sentencia);
            int filasInsertadas = statement.executeUpdate();

            if (filasInsertadas == 0) {
                //Error al actualizar el usuario.
                actualizado = false;
            }

            //Cierra la conexion
            conexionBD.cerrarConexion();

        } catch (SQLException ex) {
            actualizado = false;
        }

        return actualizado;
    }

    /**
     * Metodo que actualiza la informacion del Usuario
     *
     * @param usuario
     * @return
     */
    public synchronized boolean actualizarInformacionUsuario(Usuario usuario) {
        boolean actualizado = true;

        try {
            //Se inicializa la conexion
            ConexionBD conexionBD = new ConexionBD();
            conexionBD.abrirConexion();

            String sentencia = "UPDATE INFORMACION_USUARIO SET NICK = ?" + ", DEPORTE = ?" + ", ARTE = ?" + ", POLITICA = ?" + ", RELACION = ?" + ", HIJOS = ?"
                    + ", SEXO = ?" + ", INTERES = ?" + ", FOTO = ?" + ", FECHA_ACCESO = ?" + " WHERE ID_USUARIO = " + usuario.getIdUsuario();

            java.sql.PreparedStatement statement = conexionBD.getConex().prepareStatement(sentencia);

            statement.setString(1, usuario.getNick());
            statement.setInt(2, usuario.getDeporte());
            statement.setInt(3, usuario.getArte());
            statement.setInt(4, usuario.getPolitica());
            statement.setInt(5, usuario.getRelacion());
            statement.setInt(6, usuario.getHijos());
            statement.setInt(7, usuario.getSexo());
            statement.setInt(8, usuario.getInteres());

            if (null != usuario.getFoto()) {
                Image imgFto = usuario.getFoto().getImage();

                BufferedImage bufferedImage = new BufferedImage(imgFto.getWidth(null), imgFto.getHeight(null), BufferedImage.TYPE_INT_RGB);
                Graphics2D g2 = bufferedImage.createGraphics();
                g2.drawImage(imgFto, null, null);

                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                try {
                    ImageIO.write(bufferedImage, "jpg", outStream);
                } catch (IOException ex) {
                }

                statement.setBytes(9, outStream.toByteArray());
            } else {
                statement.setNull(9, java.sql.Types.NULL);
            }

            if (null != usuario.getFechaAcceso()) {
                statement.setString(10, usuario.getFechaAcceso());
            } else {
                statement.setNull(10, java.sql.Types.NULL);
            }

            int i = statement.executeUpdate();
            if (i == 0) {
                //Error al actualizar el usuario.
                actualizado = false;
            }

            //Cierra la conexion
            conexionBD.cerrarConexion();

        } catch (SQLException ex) {
            actualizado = false;
        }

        return actualizado;
    }

    /**
     * Metodo que obtiene la cantidad de usuarios con el mismo Rol
     *
     * @param rol
     * @return
     */
    public synchronized int cantidadUsuariosByRol(int rol) {

        int contador = 0;

        try {

            //Se inicializa la conexion
            ConexionBD conexionBD = new ConexionBD();
            //Se abre la conexion
            java.sql.Statement sentencia_SQL = conexionBD.abrirConexion();

            //Sentencia
            String sentencia = "SELECT * FROM USUARIO WHERE ROL = " + rol;

            java.sql.ResultSet conj_Registros = sentencia_SQL.executeQuery(sentencia);

            while (conj_Registros.next()) {
                contador++;
            }

            //Cierra la conexion
            conexionBD.cerrarConexion();

        } catch (SQLException sq) {
            contador = 0;
        }

        return contador;
    }

    /**
     * ---------------------------------------------------------------------------------------------------------------
     * Usuarios afines
     * ---------------------------------------------------------------------------------------------------------------
     */
    /**
     * 
     * Metodo que devuelve una lista con usuarios afines
     * Si el filtroSexo = 0, se filtra por ambos con codigo 3 (Ambos)
     * Los hijos, si tiene filtro, se usua el que llega y el del usuario
     *
     * @param filtroRelacion
     * @param sexoUsuario
     * @param filtroSexo
     * @param filtroHijos
     * @param filtroHijosUsuario
     * @return
     */
    public synchronized ArrayList<Usuario> listaUsuariosAfines(int filtroRelacion, int sexoUsuario, int filtroSexo, int filtroHijos, int filtroHijosUsuario) {

        ArrayList<Usuario> listaUsuarios = new ArrayList();
        try {

            //Se inicializa la conexion
            ConexionBD conexionBD = new ConexionBD();
            //Se abre la conexion
            java.sql.Statement sentencia_SQL = conexionBD.abrirConexion();

            String condicion = "";

            //Filtro
            if (0 != filtroSexo && 0 != filtroHijos) {
                //Se filtra por todos los filtros
                condicion = " AND SEXO = " + filtroSexo + " AND INTERES = " + sexoUsuario + " AND (HIJOS = " + filtroHijos + " OR HIJOS = " + filtroHijosUsuario + ")";

            } else if (0 != filtroSexo && 0 == filtroHijos) {
                //Se filtra por el sexo y el interes
                condicion = " AND SEXO = " + filtroSexo + " AND INTERES = " + sexoUsuario;

            } else if (0 == filtroSexo && 0 != filtroHijos) {
                //Se filtra por el interes y los hijos
                condicion = " AND INTERES = 3 AND (HIJOS = " + filtroHijos + " OR HIJOS = " + filtroHijosUsuario + ")";

            } else if (0 == filtroSexo && 0 == filtroHijos) {
                //Se filtra por el interes
                condicion = " AND INTERES = 3";
            }

            //Sentencia
            String sentencia;
            if (condicion.isEmpty()) {
                sentencia = "SELECT * FROM INFORMACION_USUARIO WHERE RELACION = " + filtroRelacion;
            } else {
                sentencia = "SELECT * FROM INFORMACION_USUARIO WHERE RELACION = " + filtroRelacion + condicion;
            }

            java.sql.ResultSet conj_Registros = sentencia_SQL.executeQuery(sentencia);

            while (conj_Registros.next()) {
                Usuario usuarioBD = new Usuario();

                int idUsuarioBD = conj_Registros.getInt("ID_USUARIO");
                String nickBD = conj_Registros.getString("NICK");
                int deporteBD = conj_Registros.getInt("DEPORTE");
                int arteBD = conj_Registros.getInt("ARTE");
                int politicaBD = conj_Registros.getInt("POLITICA");
                int relacionBD = conj_Registros.getInt("RELACION");
                int hijosBD = conj_Registros.getInt("HIJOS");
                int sexoBD = conj_Registros.getInt("SEXO");
                int interesBD = conj_Registros.getInt("INTERES");

                //Obtenemos y convertimos la imagen
                ImageIcon foto = null;
                if (null != conj_Registros.getBytes("FOTO")) {
                    byte[] fotoBD = conj_Registros.getBytes("FOTO");
                    foto = new ImageIcon(fotoBD);
                }

                String fechaAccesoBD = conj_Registros.getString("FECHA_ACCESO");

                usuarioBD.setIdUsuario(idUsuarioBD);
                usuarioBD.setNick(nickBD);
                usuarioBD.setDeporte(deporteBD);
                usuarioBD.setArte(arteBD);
                usuarioBD.setPolitica(politicaBD);
                usuarioBD.setRelacion(relacionBD);
                usuarioBD.setHijos(hijosBD);
                usuarioBD.setSexo(sexoBD);
                usuarioBD.setInteres(interesBD);
                usuarioBD.setFoto(foto);
                usuarioBD.setFechaAcceso(fechaAccesoBD);

                //Incluye los usuarios
                listaUsuarios.add(usuarioBD);
            }

            //Cierra la conexion
            conexionBD.cerrarConexion();

        } catch (SQLException ex) {

        }
        return listaUsuarios;
    }

    /**
     * Metodo que obtiene una lista con todos los usuarios de la aplicacion
     *
     * @return
     */
    public synchronized ArrayList<Usuario> listaUsuariosAplicacion() {

        ArrayList<Usuario> listaUsuarios = new ArrayList();
        try {

            //Se inicializa la conexion
            ConexionBD conexionBD = new ConexionBD();
            //Se abre la conexion
            java.sql.Statement sentencia_SQL = conexionBD.abrirConexion();

            //Sentencia
            String sentencia = "SELECT * FROM USUARIO";

            java.sql.ResultSet conj_Registros = sentencia_SQL.executeQuery(sentencia);

            while (conj_Registros.next()) {
                Usuario usuarioBD = new Usuario();

                int idBD = conj_Registros.getInt("ID");
                String emailBD = conj_Registros.getString("EMAIL");
                String pwdBD = conj_Registros.getString("PWD");
                int rolBD = conj_Registros.getInt("ROL");
                boolean activo = conj_Registros.getBoolean("ACTIVO");

                usuarioBD.setIdUsuario(idBD);
                usuarioBD.setEmail(emailBD);
                usuarioBD.setPwd(pwdBD);
                usuarioBD.setRol(rolBD);
                usuarioBD.setActivo(activo);

                //Incluye los usuarios
                listaUsuarios.add(usuarioBD);
            }

            //Cierra la conexion
            conexionBD.cerrarConexion();

        } catch (SQLException ex) {

        }
        return listaUsuarios;
    }

    /**
     * Metodo que elimina el usuario por Id
     *
     * @param idUsuario
     * @return
     */
    public synchronized boolean eliminarUsuario(int idUsuario) {

        boolean eliminado = true;

        try {

            //Se inicializa la conexion
            ConexionBD conexionBD = new ConexionBD();
            //Se abre la conexion
            conexionBD.abrirConexion();

            //Sentencia
            String sentencia = "DELETE FROM USUARIO WHERE ID = " + idUsuario;

            java.sql.PreparedStatement statement = conexionBD.getConex().prepareStatement(sentencia);
            int filasEliminadas = statement.executeUpdate();

            if (filasEliminadas == 0) {
                //Error al actualizar el usuario.
                eliminado = false;
            }

            //Cierra la conexion
            conexionBD.cerrarConexion();

        } catch (SQLException ex) {
            eliminado = false;
        }

        return eliminado;
    }

    /**
     * Metodo que elimina la informacion del usuario por ID
     *
     * @param idUsuario
     * @return
     */
    public synchronized boolean eliminarInformacionUsuario(int idUsuario) {

        boolean eliminado = true;

        try {

            //Se inicializa la conexion
            ConexionBD conexionBD = new ConexionBD();
            //Se abre la conexion
            conexionBD.abrirConexion();

            //Sentencia
            String sentencia = "DELETE FROM INFORMACION_USUARIO WHERE ID_USUARIO = " + idUsuario;

            java.sql.PreparedStatement statement = conexionBD.getConex().prepareStatement(sentencia);
            int filasEliminadas = statement.executeUpdate();

            if (filasEliminadas == 0) {
                //Error al actualizar el usuario.
                eliminado = false;
            }

            //Cierra la conexion
            conexionBD.cerrarConexion();

        } catch (SQLException ex) {
            eliminado = false;
        }

        return eliminado;
    }

    /**
     * Metodo que activa o desactiva al usuario
     *
     * @param idUsuario
     * @param activar
     * @return
     */
    public synchronized boolean activaDesactivaUsuario(int idUsuario, boolean activar) {

        boolean activado = true;

        try {

            //Se inicializa la conexion
            ConexionBD conexionBD = new ConexionBD();
            conexionBD.abrirConexion();

            int activarBD = 0;

            //Lo pone a 1 si se activa
            if (activar) {
                activarBD = 1;
            }

            //Sentencia
            String sentencia = "UPDATE USUARIO SET ACTIVO = " + activarBD + " WHERE ID = " + idUsuario;

            java.sql.PreparedStatement statement = conexionBD.getConex().prepareStatement(sentencia);
            int filasInsertadas = statement.executeUpdate();

            if (filasInsertadas == 0) {
                //Error al actualizar el usuario.
                activado = false;
            }

            //Cierra la conexion
            conexionBD.cerrarConexion();

        } catch (SQLException ex) {
            activado = false;
        }

        return activado;
    }
}
