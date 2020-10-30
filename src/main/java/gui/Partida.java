package gui;

import cliente.Cliente;
import servidor.Server;


import java.io.IOException;

public class Partida {

    private Cliente cliente;

    private static Partida instancia = null;

    private Partida(){

    }

    public static Partida GetInstance(){

        if (instancia == null){
            instancia = new Partida();
        }
        return instancia;

    }

    public void Host(){
        //Creo hilo para ejecutar el servidor
        Runnable iniciar_servidor = new Server();
        Thread hilo_servidor = new Thread(iniciar_servidor);
        hilo_servidor.start();

        //Creo hilo para ejercutar el cliente
        cliente = new Cliente();
        Thread hilo_cliente = new Thread(cliente);
        hilo_cliente.start();

    }
    public void Invitado(){

        //Creo hilo para ejercutar el cliente
        cliente = new Cliente();
        Thread hilo_cliente = new Thread(cliente);
        hilo_cliente.start();
    }

    public void EnviarMensaje() throws IOException {
        //Llamo a cliente para enviar un mensaje al server
        cliente.EnviarMensaje();
    }
}
