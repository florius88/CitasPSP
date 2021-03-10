package utilidades;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Flor
 */
public class Utilidades {
    
    /**
     * Metodo que valida si el email introducido tiene un formato correcto
     *
     * @param email
     * @return
     */
    public static boolean validarEmail(String email) {
        boolean correcto = true;
        // Patrón para validar el email
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mather = pattern.matcher(email);

        if (mather.find() != true) {
            correcto = false;
        }

        return correcto;
    }

    /**
     * Metodo que valida si las 2 pwd introducidas son iguales
     *
     * @param pwd
     * @param confirmPwd
     * @return
     */
    public static boolean validarPwd(char[] pwd, char[] confirmPwd) {
        boolean correcto = true;
        int puntero = 0;
        if (pwd.length != confirmPwd.length) {
            correcto = false;
        } else {
            while ((correcto) && (puntero < pwd.length)) {
                if (pwd[puntero] != confirmPwd[puntero]) {
                    correcto = false;
                }
                puntero++;
            }
        }
        return correcto;
    }
}
