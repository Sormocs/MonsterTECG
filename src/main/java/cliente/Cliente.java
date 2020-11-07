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
import java.net.SocketException;

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

    private int defensa;
    private boolean afecto;
    private boolean reflejo;
    private JsonNode ultima;

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

                try {
                    //Lee el mensaje que envió el servidor
                    mensaje = in.readUTF();

                    this.turno = true;

                    //Separar el mensaje según el protocolo establecido
                    String[] leermensaje = mensaje.split("#");

                    if (leermensaje[0].equals("Iniciar")) {
                        Partida.GetInstance().setHay_guest(true);
                    } else if (leermensaje[0].equals("TerminarTurno")) {
                        Partida.GetInstance().ComenzarTurno();
                    } else if (leermensaje[0].equals("Victoria")) {
                        Partida.GetInstance().EndGame();
                    } else if (leermensaje[3].equals("REVELAR")){
                        Match_GUI.ShowCard(leermensaje[0]);
                        Partida.GetInstance().GuardarPartida(leermensaje[0] + "\n");
                    }
                    //Lógica del juego
                    else if (leermensaje[1].equals(this.jugador)) {
                        if (leermensaje[5].equals("TRUE")) {
                            EjeccucionCliente(leermensaje);
                        }
                        //Guardar la partida
                        GuardarPartida(leermensaje[0]);
                    }

                } catch(SocketException e1){
                    Partida.GetInstance().ConnectionLost();
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

    public void EnviarMensaje(JsonNode carta, boolean ejecuta)  {

        //Enviar un mensaje al server

        try {

            String s_string = Json.generateString(carta,false);

            // Formación del mensaje.
            String mensaje = s_string;

            String Svida = Integer.toString(this.vida);
            String Smana = Integer.toString(this.mana);

            mensaje += "#" + this.jugador + "#" + Svida + "#" + Smana + "#TRUE#TRUE";

            this.out.writeUTF(mensaje);

            mensaje = null;
            this.turno = false;

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void EnviarMensajePropio(JsonNode node){
        try{
            String s_string = Json.generateString(node,false);

            // Formación del mensaje.
            String mensaje = s_string;

            String Svida = Integer.toString(this.vida);
            String Smana = Integer.toString(this.mana);

            mensaje += "#" + this.jugador + "#" + Svida + "#" + Smana + "#TRUE#FALSE";

            this.out.writeUTF(mensaje);

            mensaje = null;
            this.turno = false;
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void EnviarRevelado(String mensaje){

        mensaje += "#" + this.jugador + "#REVELAR#REVELAR#TRUE#TRUE";

        try {
            this.out.writeUTF(mensaje);
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

            setVidaR(Integer.parseInt(mensaje[2]));
            setManaR(Integer.parseInt(mensaje[2]));

            setUltima(nodo);

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

    public void EjeccucionPropia(JsonNode nodo){

        String tipo = nodo.get("tipo").textValue();

        if (tipo.equals("minions")){

            Minions(nodo);

        } else if (tipo.equals("spell")){

            Spells(nodo);

        } else if (tipo.equals("secreta")){

            Secrets(nodo);

        }

        EnviarMensajePropio(nodo);

    }

    public void EjeccucionAmbos(JsonNode nodo){

        String tipo = nodo.get("tipo").textValue();

        if (tipo.equals("spell")){

            Spells(nodo);

        } else if (tipo.equals("secreta")){

            Secrets(nodo);

        } else if (isAfecto()){
            EnviarMensaje(nodo,true);
            setAfecto(false);
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
            spell.Caso();
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
            secret.Caso();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    public void Iniciar(){
        try {
            this.out.writeUTF("Iniciar#Guest#0#0#0#0");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void HostConnects(){
        try {
            this.out.writeUTF("Conecta_Host#Host#0#0#0#0");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void GuardarPartida(String mensaje){

        JsonNode nodo = null;
        try {
            nodo = Json.parse(mensaje);
            String infoturno = "-Rival: " + nodo.get("informacion").textValue();
            infoturno += " con costo de mana de ";
            infoturno += nodo.get("costo") + "\n";
            Match_GUI.ShowCard(infoturno);
            Partida.GetInstance().GuardarPartida(infoturno);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }



    public void setTurno(boolean turno) {
        try {
            this.turno = turno;
            this.out.writeUTF("TerminarTurno#"+this.jugador+"#0#0#0#0");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void EndGame(){
        try {
            this.out.writeUTF("Victoria#" + this.jugador + "#0#0#0#0");
        }catch(IOException e2){
            e2.printStackTrace();
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

        if (this.defensa != 0){

            int nvida = (this.defensa*vida)/100;

            this.vida = nvida;

            setDefensa(0);

        } else {
            this.vida = vida;
        }
        if (this.vida < 0){
            this.vida = 0;
        }

    }

    public void DañoReflejo(String daño){

        int dato = Integer.parseInt(daño);

        int dañorecibido;

        if (dato != 0){

            dañorecibido = (getVida()*50)/100;

        } else {

            dañorecibido = dato;

        }

        setVida(getVida()-dañorecibido);

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
        if (mana < 0){
            this.mana = 0;
        } else {
            this.mana = mana;
        }
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

    /**
     * Retorna la defesa activa
     * @return int
     */

    public int getDefensa() {
        return defensa;
    }

    /**
     * Aplica la nueva defensa
     * @param defensa int
     */

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public boolean isReflejo() {
        return reflejo;
    }

    public void setReflejo(boolean reflejo) {
        this.reflejo = reflejo;
    }

    public void RellenarMana(int mana){
        this.mana += mana;
    }

    public void Curar(int cura){
        this.vida += cura;
    }


    public boolean isAfecto() {
        return afecto;
    }

    public void setAfecto(boolean afecto) {
        this.afecto = afecto;
    }

    public JsonNode getUltima() {
        return ultima;
    }

    public void setUltima(JsonNode ultima) {
        this.ultima = ultima;
    }
}
