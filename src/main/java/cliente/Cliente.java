package cliente;

import com.fasterxml.jackson.databind.JsonNode;
import manejo.json.Json;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente implements Runnable {

    private Socket cliente;

    private String jugador;

    private int vida;
    private int mana;

    private DataOutputStream out;
    private DataInputStream in;
    private int puerto = 5000;

    private String IP;

    private String host = "localhost";
    private String mensaje;

    private boolean turno;

    public Cliente(String jugador){
        this.vida = 1000;
        this.mana = 1000;
        this.jugador = jugador;
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
            while(true) {

                //Lee el mensaje que envió el servidor
                mensaje = in.readUTF();
                System.out.println(mensaje);

                //Separar el mensaje según el protocolo establecido
                String[] leermensaje = mensaje.split("-");

                //Lógica del juego
                if (leermensaje[2].equals(this.jugador)){
                    EjeccucionServer();
                }

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

        this.out.writeUTF("carta1");

    }

    public boolean getVida() {
        return vida < 0;
    }

    public void setVida(int vida) {
        this.vida -= vida;
        System.out.println(this.vida);
    }

    public void EjeccucionServer(){
        //Aqui se hará lo que mander el servidor
    }
}
