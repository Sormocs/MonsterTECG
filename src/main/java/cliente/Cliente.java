package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente implements Runnable {

    private Socket cliente;

    private DataOutputStream out;
    private DataInputStream in;
    private int puerto = 5000;

    private String IP;

    private String host = "localhost";
    private String mensaje;

    private boolean turno;

    public Cliente(){
        try{
            this.cliente = new Socket(host,puerto);
            this.in = new DataInputStream(cliente.getInputStream());
            this.out = new DataOutputStream(cliente.getOutputStream());
            ObtenerIP();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        try{
            //mensaje = in.readUTF();
            while(true){
                mensaje = in.readUTF();
                System.out.println(mensaje);
                //lógica del game
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void ObtenerIP() throws UnknownHostException {

        CheckIP miIP = new CheckIP();
        this.IP = miIP.obtenerIP();
        System.out.println(IP);

    }


}
