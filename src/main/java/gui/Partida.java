package gui;

import cliente.Cliente;
import servidor.Server;

import java.io.IOException;

public class Partida {

    private Cliente cliente;

    public void Host(){

        Runnable iniciar_servidor = new Server();
        Thread hilo_servidor = new Thread(iniciar_servidor);
        hilo_servidor.start();

        cliente = new Cliente();
        Thread hilo_cliente = new Thread(cliente);
        hilo_cliente.start();

    }
    public void Invitado(){

        cliente = new Cliente();
        Thread hilo_cliente = new Thread(cliente);
        hilo_cliente.start();
    }

    public void EnviarMensaje() throws IOException {
        cliente.EnviarMensaje();
    }
}
