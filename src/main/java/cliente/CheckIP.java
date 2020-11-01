package cliente;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Clase para obtener la ip de la pc.
 */

public class CheckIP {

    /**
     * metodo para retornar la ip
     * @return ip
     * @throws UnknownHostException
     */
    String obtenerIP() throws UnknownHostException {
        InetAddress ip = InetAddress.getLocalHost();
        return ip.getHostAddress();
    }

}
