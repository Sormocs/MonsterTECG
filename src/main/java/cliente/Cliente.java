package cliente;

import cartas.Minions;
import cartas.Secrets;
import cartas.Spells;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import gui.Match_GUI;
import gui.Partida;
import manejo.json.Json;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Clase para tener el cliente del servidor
 *
 */

public class Cliente implements Runnable {

    private Socket cliente;

    private String jugador;

    private int vida;
    private int mana;

    private int vidaR;
    private int manaR;

    private DataOutputStream out;
    private DataInputStream in;
    private int puerto = 5000;

    private String host = "localhost";
    private String mensaje;

    private boolean turno;

    public Cliente(String jugador){

        this.vida = 1000;
        this.mana = 200;
        this.vidaR = 1000;
        this.manaR = 200;
        this.jugador = jugador;
        this.turno = false;

        try{
            //Se crea el socket y los data e input stream para enviar y recibir mensajes
            this.cliente = new Socket(host,puerto);
            this.in = new DataInputStream(cliente.getInputStream());
            this.out = new DataOutputStream(cliente.getOutputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Hilo donde se ejecutará lo que el cliente recibe del servidor
     */

    @Override
    public void run(){
        try{
            while(true) {

                //Lee el mensaje que envió el servidor
                mensaje = in.readUTF();
                System.out.println(mensaje);

                this.turno = true;

                //Separar el mensaje según el protocolo establecido
                String[] leermensaje = mensaje.split("#");

                if (leermensaje[0].equals("Iniciar") && this.jugador.equals("Guest")){

                }

                //Lógica del juego
                if (leermensaje[1].equals(this.jugador)){
                    EjeccucionCliente(leermensaje);
                }

            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Envia un mensaje al servidor
     * @throws IOException
     */

    public void EnviarMensaje(JsonNode carta)  {

        //Enviar un mensaje al server

        try {

            String s_string = Json.generateString(carta,false);

            // Formación del mensaje.
            String mensaje = s_string;

            String Svida = Integer.toString(this.vida);
            String Smana = Integer.toString(this.mana);

            mensaje += "#" + this.jugador + "#" + Svida + "#" + Smana;

            this.out.writeUTF(mensaje);

            mensaje = null;
            this.turno = false;

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Ejecuta lo que recibirá del server
     * @param mensaje
     */

    public void EjeccucionCliente(String[] mensaje){
        //Aqui se hará lo que mander el servidor

        try {

            String s_nodo = mensaje[0];
            JsonNode nodo = Json.parse(s_nodo);

            String infoturno = "-Rival: " + nodo.get("informacion").textValue();
            infoturno += " con costo de mana de ";
            infoturno += nodo.get("costo") + "\n";
            Partida.GetInstance().GuardarPartida(infoturno);
            Match_GUI.ShowCard(infoturno);

            String tipo = nodo.get("tipo").textValue();

            if (tipo.equals("minions")){

                Minions(nodo);

            } else if (tipo.equals("spell")){

                Spells(nodo);

            } else if (tipo.equals("secreta")){

                Secrets(nodo);

            }


        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    /**
     * Crea un objeto de la carta recibida.
     * @param nodo JsonNode
     */

    public void Minions(JsonNode nodo){

        try {
            Minions minion = Json.fromJson(nodo,Minions.class);
            int[] vidamana = new int[2];
            vidamana = minion.Caso(this.vida,this.vidaR,this.mana);

            setVida(vidamana[0]);
            setMana(vidamana[1]);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    /**
     * Crea un objeto de la carta recibida.
     * @param nodo JsonNode
     */

    public void Spells(JsonNode nodo){

        try {
            Spells spell = Json.fromJson(nodo,Spells.class);
            spell.hola();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    /**
     * Crea un objeto de la carta recibida.
     * @param nodo JsonNode
     */

    public void Secrets(JsonNode nodo){

        try {
            Secrets secret = Json.fromJson(nodo,Secrets.class);
            secret.hola();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    public void Iniciar(){
        try {
            this.out.writeUTF("Iniciar-0-host");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //MANEJO DE VIDA Y MANA DE LOS 2 JUGADORES

    /**
     * Revisa si la vida ha llegado a 0
     * @return boolean
     */

    public boolean VerificarVida(){
        return vida < 0;
    }

    public int getVida() {
        return vida;
    }

    /**
     * Modifica la vida del jugador.
     * @param vida int
     */

    public void setVida(int vida) {
        this.vida = vida;
    }

    /**
     * Retorna el mana del jugador
     * @return int
     */

    public int getMana() {
        return mana;
    }

    /**
     * Modifica la vida del jugador.
     * @param mana int
     */

    public void setMana(int mana) {
        this.mana = mana;
    }

    /**
     * Retorna la vida rival
     * @return int
     */

    public int getVidaR() {
        return vidaR;
    }

    /**
     * Modifica la vida rival
     */

    public void setVidaR(int vidaR) {
        this.vidaR = vidaR;
    }

    /**
     * Retorna el mana rival
     * @return int
     */

    public int getManaR() {
        return manaR;
    }

    /**
     * Modifica el mana rival
     * @param manaR
     */

    public void setManaR(int manaR) {
        this.manaR = manaR;
    }

}
