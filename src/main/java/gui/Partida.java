package gui;

import cliente.Cliente;
import com.fasterxml.jackson.databind.JsonNode;
import listas.ListaDoble;
import servidor.Server;

import java.io.IOException;

/**
 *
 * Esta clase pretende ser el puente de comunicación entre la GUI y la parte de cliente y servidor
 *
 */
public class Partida {

    private Cliente cliente;

    private ListaDoble lista = new ListaDoble();

    private static Partida instancia = null;

    private boolean hay_guest = false; //True para pruebas, valor verdadero es false

    private boolean hay_host = false;

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

    /**
     * Guarda la jugada realizada para verla en el historial
     * @param infoturno
     */

    public void GuardarPartida(String infoturno){

        lista.InsertarFinal(infoturno);

    }

    /**
     * Retorna la vida del jugador
     * @return int
     */

    public int getVidaPlayer(){
        int vida = cliente.getVida();
        return vida;
    }

    /**
     * Retorna la vida rival
     * @return int
     */

    public int getVidaRival(){

        int vida = cliente.getVidaR();
        return vida;

    }

    /**
     * Retorna el mana del jugador
     * @return int
     */

    public int getManaPlayer(){
        int mana = cliente.getMana();
        return mana;
    }

    /**
     * Retorna el mana del rival
     * @return int
     */

    public int getManaRival(){
        int mana = cliente.getManaR();
        return mana;
    }

    public void setHay_guest(boolean hay_guest) {
        this.hay_guest = hay_guest;
    }

    public boolean isHay_guest() {
        return hay_guest;
    }


    public void Iniciar() {
        cliente.Iniciar();
    }
}
