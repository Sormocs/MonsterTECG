package gui;

import cliente.Cliente;
import com.fasterxml.jackson.databind.JsonNode;
import servidor.Server;

import java.io.IOException;

/**
 *
 * Esta clase pretende ser el puente de comunicación entre la GUI y la parte de cliente y servidor
 *
 */
public class Partida {

    private Cliente cliente;

    private static Partida instancia = null;

    private Partida(){

    }

    /**
     * Este método retorna la instancia de la clase según el patrón de diseño singleton.
     *
     * @return la instanca de la clase
     */

    public static Partida GetInstance(){
        //Retorna la instancia de partida.
        if (instancia == null){
            instancia = new Partida();
        }
        return instancia;

    }

    /**
     * Crea los hilos para server y cliente del host
     */

    public void Host(){
        //Creo hilo para ejecutar el servidor
        Runnable iniciar_servidor = new Server();
        Thread hilo_servidor = new Thread(iniciar_servidor);
        hilo_servidor.start();

        //Creo hilo para ejercutar el cliente
        this.cliente = new Cliente("Host");
        Thread hilo_cliente = new Thread(cliente);
        hilo_cliente.start();


    }

    /**
     * Crea el hilo del cliente guest
     */
    public void Invitado(){

        //Creo hilo para ejercutar el cliente
        this.cliente = new Cliente("Guest");
        Thread hilo_cliente = new Thread(cliente);
        hilo_cliente.start();
    }

    /**
     * LLama al método "EnviarMensaje" de cliente
     * @throws IOException
     */

    public void EnviarMensaje(JsonNode card){
        //Llamo a cliente para enviar un mensaje al server

        cliente.EnviarMensaje(card);
    }

    public int getVidaPlayer(){
        int vida = cliente.getVida();
        return vida;
    }

    public int getVidaRival(){

        int vida = cliente.getVidaR();
        return vida;

    }

    public int getManaPlayer(){
        int mana = cliente.getMana();
        return mana;
    }

    public int getManaRival(){
        int mana = cliente.getManaR();
        return mana;
    }
}
