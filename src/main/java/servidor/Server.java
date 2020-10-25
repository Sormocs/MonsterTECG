package servidor;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {
    private final int puerto = 5000;
    private final int conexiones = 2;
    private LinkedList<Socket> usuarios = new LinkedList<Socket>();
    private boolean turno = true;

    public void Escuchar() {
        try {
            ServerSocket servidor = new ServerSocket(puerto, conexiones);
            System.out.println("Esperando jugadores...");

            while (true) {
                Socket cliente = servidor.accept();
                usuarios.add(cliente);
                Runnable iniciar_conexion = new HiloServidor(cliente,usuarios);
                Thread hilo = new Thread(iniciar_conexion);
                hilo.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}