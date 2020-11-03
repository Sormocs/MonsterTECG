package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
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

            while(true){

                //Leer mensaje que proviene desde el cliente
                String mensaje = in.readUTF();
                //System.out.println(mensaje);

                //Separar el mensaje según el protocolo establecido
                String[] leermensaje = mensaje.split("#");

                //Leerá de quien proviene el mensaje. Si es del host, lo cambiará a guest y si viceversa.
                if (leermensaje[2].equals("host")){
                    leermensaje[2] = "guest";
                } else if (leermensaje[2].equals("guest")){
                    leermensaje[2] = "host";
                }

                //Ciclo para enviar el mensaje a los 2 usuarios
                for (Socket usuario : usuarios){
                    out = new DataOutputStream(usuario.getOutputStream());
                    out.writeUTF(mensaje);
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
