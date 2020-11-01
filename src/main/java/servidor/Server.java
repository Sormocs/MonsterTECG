package servidor;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

/**
 * Clase para crear el servidor
 */

public class Server implements Runnable{
    private final int puerto = 5000;
    private final int conexiones = 2;
    private LinkedList<Socket> usuarios = new LinkedList<Socket>();
    private boolean turno = true;

    /**
     * Hilo para aceptar a los judadores y agregar a la linkedlist
     */

    public void run() {
        try {
            //Se crea el socket server
            ServerSocket servidor = new ServerSocket(puerto, conexiones);
            System.out.println("Esperando jugadores...");

            //Ciclo donde espera a que los jugadores se conecten para agregarlos a la lista
            while (true) {
                Socket cliente = servidor.accept();
                usuarios.add(cliente);

                //Hilo para el server
                Runnable iniciar_conexion = new HiloServidor(cliente,usuarios);
                Thread hilo = new Thread(iniciar_conexion);
                hilo.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}