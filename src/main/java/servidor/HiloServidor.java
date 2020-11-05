package servidor;

import gui.Partida;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.LinkedList;

/**
 * Clase donde se maneja toda la lógica del servidor
 */

public class HiloServidor implements  Runnable{

    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;

    private boolean turno;
    private LinkedList<Socket> usuarios = new LinkedList<Socket>();

    public HiloServidor(Socket soc, LinkedList usuarios){
        this.socket = soc;
        this.usuarios = usuarios;
    }

    /**
     * Hilo donde se ejecuta lo que provenga del cliente y enviar mensajes al cliente
     */

    @Override
    public void run(){
        try{
            //Data output and input stream para enviar y recibir mensajes
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());

            while(true) {

                try {
                    //Leer mensaje que proviene desde el cliente
                    String mensaje = in.readUTF();

                    //Separar el mensaje según el protocolo establecido
                    String[] leermensaje = mensaje.split("#");

                    //Leerá de quien proviene el mensaje. Si es del host, lo cambiará a guest y si viceversa.
                    if (leermensaje[1].equals("Guest")) {
                        mensaje = leermensaje[0] + "#Host#" + leermensaje[2] + "#" + leermensaje[3];
                        Socket usuariosFirst = usuarios.getFirst();
                        out = new DataOutputStream(usuariosFirst.getOutputStream());
                        out.writeUTF(mensaje);
                    } else if (leermensaje[1].equals("Host")) {
                        mensaje = leermensaje[0] + "#Guest#" + leermensaje[2] + "#" + leermensaje[3];
                        Socket usuariosLast = usuarios.getLast();
                        out = new DataOutputStream(usuariosLast.getOutputStream());
                        out.writeUTF(mensaje);
                    }

                } catch(SocketException e1){
                    Partida.GetInstance().ConnectionLost();
                }
            }
        } catch (Exception e){
            //Si se desconecta un usuario que lo elimine de la lista.
            for (int i = 0; i < usuarios.size(); i++) {
                if(usuarios.get(i) == socket){
                    usuarios.remove(i);
                    break;
                }
            }
            e.printStackTrace();
        }
    }

}
