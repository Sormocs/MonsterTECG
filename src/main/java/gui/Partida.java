package gui;

import cliente.Cliente;
import com.fasterxml.jackson.databind.JsonNode;
import listas.ListaDoble;
import listas.Stack;
import servidor.Server;

import javax.swing.*;
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

    private Match_GUI game_gui;

    private GUI main_gui;

    private ListaDoble listaPartidas = new ListaDoble();

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

        String afecta = card.get("afecta").textValue();

        if (afecta.equals("Rival")){

            if (card.get("tipo").textValue().equals("minions") && cliente.isAfecto()){

                cliente.DañoReflejo(card.get("ataque").textValue());
                cliente.setReflejo(false);

            }

            cliente.EnviarMensaje(card,true);

        } else if (afecta.equals("Propio")) {


            cliente.EjeccucionPropia(card);

        } else if (afecta.equals("Ambos")){

            cliente.EjeccucionAmbos(card);

        }

    }

    /**
     * Guarda la jugada realizada para verla en el historial
     * @param infoturno
     */

    public void GuardarPartida(String infoturno){

        lista.InsertarFinal(infoturno);

    }

    public Cliente cliente(){
        return this.cliente;
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

    /**
     * Coloca la vida del jugador en cliente
     * @param vida
     */
    public void setVidaPlayer(int  vida){
        cliente.setVida(vida);
    }

    /**
     * Coloca el valor de la vida del rival en el cliente
     * @param vidaR
     */
    public void setVidaRival(int vidaR){
        cliente.setVidaR(vidaR);
    }

    /**
     * Coloca la cantidad de mana que posee el rival en el cliente.
     * @param manaR
     */
    public void setManaR(int manaR){
        cliente.setManaR(manaR);
    }

    /**
     * Activa la variable para saber que hay un guest, lo que permite al host iniciar la partida.
     * @param hay_guest
     */
    public void setHay_guest(boolean hay_guest) {
        this.hay_guest = hay_guest;
    }

    /**
     * Permite al host saber si hay un guest conectado para poder iniciar la partida
     * @return valor booleano de si hay_guest.
     */
    public boolean isHay_guest() {
        return hay_guest;
    }

    /**
     * Llama al iniciar del cliente, envia un mensaje por parte del guest para cambiar el valor de la variable que permite al
     * host iniciar la partida.
     */
    public void Iniciar() {
        cliente.Iniciar();
    }

    /**
     * La GUI principal del programa se almacena con este metodo.
     * @param main_gui
     */
    public void setMain_gui(GUI main_gui) {
        this.main_gui = main_gui;
    }

    /**
     * Se crea un acceso a la GUI del juego para poder acceder a sus metodos y que a traves de esta clase intercambie
     * informacion con el cliente.
     * @param game_gui
     */
    public void setGame_gui(Match_GUI game_gui) {
        this.game_gui = game_gui;
    }

    /**
     * Evita errores de conexion con los sockets y cierra cliente y server al perder la conexion de alguno.
     */
    public void ConnectionLost(){
        JOptionPane.showMessageDialog(game_gui,"Rival has disconnected","Connection Lost",JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    /**
     * Permite comenzar el turno en la gui del juego, solo se activa cuando el cliente le comunica que el otro termino el turno.
     */
    public void ComenzarTurno(){
        game_gui.ComienzaTurno();
    }

    /**
     * Asigna un valor en el cliente al mana del jugador
     * @param mana
     */
    public void setManaPropio(int mana){
        cliente.setMana(mana);
    }

    /**
     * Indica al cliente que ya se termino el turno, por lo que manda un mensaje al otro jugador para que este comience su turno.
     * @param valor
     */
    public void TerminaTurno(boolean valor){
        cliente.setTurno(valor);
    }

    /**
     * Indica al cliente que el jugador perdio para que envie un mensaje.
     */
    public void Defeat(){
        cliente.EndGame();
    }

    /**
     * Al recibir la derrota del otro jugador, llama a la gui del juego para notificar la victoria.
     */
    public void EndGame(){
        game_gui.End();
    }

    /**
     * Retorna el primer item que se encuentra en la pila que es el deck de cartas.
     * @return
     */
    public String Gettop(){
        return game_gui.Mostrartop();
    }

    /**
     * Agrega al deck del jugador la carta que recibe.
     * @return
     */
    public Stack Push(){
        return game_gui.getDeck();
    }

    public void Freeze(){
        game_gui.UpdateValues();
        game_gui.Disable();
        game_gui.TerminaTurno();
    }

    public ListaDoble getListaPartidas() {
        return listaPartidas;
    }

    public void InsertarPartida(Object partida){
        listaPartidas.InsertarFinal(partida);
    }
}
