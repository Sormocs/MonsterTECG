
import cliente.CheckIP;
import cliente.Cliente;
import gui.Partida;


import java.net.UnknownHostException;

public class PUEBRASPROYECTO {

    public static void main(String[] args){


        Cliente cliente = Partida.GetInstance().cliente();

        System.out.println(cliente.isReflejo());

    }
}
