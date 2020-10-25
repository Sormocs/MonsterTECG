package gui;

import cliente.Cliente;
import servidor.Server;

public class Partida {
    public void Host(){

        Runnable iniciar_servidor = new Server();
        Thread hilo_servidor = new Thread(iniciar_servidor);
        hilo_servidor.start();

        Runnable iniciar_conexion = new Cliente();
        Thread hilo_cliente = new Thread(iniciar_conexion);
        hilo_cliente.start();

    }
    public void Invitado(){

        Runnable iniciar_conexion = new Cliente();
        Thread hilo_cliente = new Thread(iniciar_conexion);
        hilo_cliente.start();
    }
}
