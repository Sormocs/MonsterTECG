package cliente;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Clase para obtener la ip de la pc.
 */

public class CheckIP {

    private static String IP;

    /**
     * metodo para retornar la ip
     * @return String
     * @throws UnknownHostException
     */
    public static String obtenerIP() throws UnknownHostException {
        InetAddress ip = InetAddress.getLocalHost();
        return ip.getHostAddress();
    }



}
