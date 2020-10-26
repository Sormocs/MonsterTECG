package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;

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

    @Override
    public void run(){
        try{
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());

            while(true){
                //logica del juego
                String mensajeRecibido = in.readUTF();
                System.out.println(mensajeRecibido);

                for (Socket usuario : usuarios){
                    out = new DataOutputStream(usuario.getOutputStream());
                    out.writeUTF("enviando mensaje desde el servidor");
                }
            }
        } catch (Exception e){
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
