
import cliente.CheckIP;


import java.net.UnknownHostException;

public class PUEBRASPROYECTO {

    public static void main(String[] args){

        try {
            System.out.println(CheckIP.obtenerIP());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}
