package cliente;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class CheckIP {

    //Método para obtener la IP de la computadora
    String obtenerIP() throws UnknownHostException {
        InetAddress ip = InetAddress.getLocalHost();
        return ip.getHostAddress();
    }

}
