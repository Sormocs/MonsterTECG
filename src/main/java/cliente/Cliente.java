package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

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
            //Se crea el socket y los data e input stream para enviar y recibir mensajes
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
            while(true) {
                //Lee el mensaje que envió el servidor
                mensaje = in.readUTF();
                System.out.println(mensaje);
                //lógica del game

            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void ObtenerIP() throws UnknownHostException {

        //Llamo a la clase y al método para obtener la IP
        CheckIP miIP = new CheckIP();
        this.IP = miIP.obtenerIP();
        System.out.println(IP);

    }

    public void EnviarMensaje() throws IOException {
        //Enviar un mensaje al server
        String mensaje1 = "Enviando información sobre el cliente";
        out.writeUTF(mensaje1);
    }

}
