package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.fasterxml.jackson.databind.JsonNode;
import listas.*;


public class Match_GUI extends JFrame implements ActionListener{

    private JLabel match_screen;
    private Stack deck;
    private ListaCircular mano;

    private JLabel fondo;
    private JLabel PlayerHP;
    private JLabel RivalHP;
    private JLabel PlayerMana;
    private JLabel RivalMana;

    private int playerhp = Partida.GetInstance().getVidaPlayer();
    private int rivalhp = Partida.GetInstance().getVidaRival();
    private int playermana = Partida.GetInstance().getManaPlayer();
    private int rivalmana = Partida.GetInstance().getManaRival();
    private int pos2change;
    private int selected_cost;

    private JButton use_card;
    private JButton take_card;
    private JButton skip_turn;

    private JButton reset;

    private JButton card0;
    private JButton card1;
    private JButton card2;
    private JButton card3;
    private JButton card4;
    private JButton card5;
    private JButton card6;
    private JButton card7;
    private JButton card8;
    private JButton card9;

    private JLabel costo;

    private boolean turno;
    private boolean[] check_pos = {true,true,true,true,false,false,false,false,false,false};

    private JButton[] btn_list = {card0,card1,card2,card3,card4,card5,card6,card7,card8,card9};

    private NodoCircular[] nodos = {};

    private static JTextArea history;

    private JScrollPane scroll;

    private NodoCircular selected;

    private Host_GUI previousHost;
    private Guest_GUI previousGuest;

    public Match_GUI() throws com.fasterxml.jackson.core.JsonProcessingException{
        super();
        InitializeComponents();
        ConfigureWin();
    }

    private void ConfigureWin(){

        this.setTitle("En partida");
        this.setSize(800,600);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void InitializeComponents() throws com.fasterxml.jackson.core.JsonProcessingException{

        match_screen = new JLabel();
        match_screen.setLayout(null);
        StartScreenComps();
        this.getContentPane().add(match_screen);

    }

    public void StartScreenComps(){

        fondo = new JLabel(new ImageIcon("Imagenes/FondoJuego.jpg"));
        fondo.setBounds(0,0,800,600);

        deck = new Stack();
        deck.Llenar();

        history = new JTextArea();
        history.setOpaque(false);
        history.setEditable(false);
        history.setLineWrap(true);
        history.setBackground(Color.BLUE);
        history.setForeground(Color.WHITE);

        scroll = new JScrollPane(history);
        scroll.setBounds(570,10,210,180);
        scroll.getViewport().setOpaque(false);
        scroll.setOpaque(false);

        PlayerHP = new JLabel();
        PlayerHP.setBounds(290,290,100,50);
        PlayerHP.setText(String.valueOf(playerhp));
        PlayerHP.setFont(new Font("Comic Sans MS",Font.BOLD,28));
        PlayerHP.setForeground(Color.RED);

        RivalHP = new JLabel();
        RivalHP.setBounds(300,80,100,50);
        RivalHP.setText(String.valueOf(rivalhp));
        RivalHP.setFont(new Font("Comic Sans MS",Font.BOLD,28));
        RivalHP.setForeground(Color.RED);

        PlayerMana = new JLabel();
        PlayerMana.setBounds(330,350,100,50);
        PlayerMana.setText(String.valueOf(playermana));
        PlayerMana.setFont(new Font("Comic Sans MS",Font.BOLD,28));
        PlayerMana.setForeground(Color.RED);

        RivalMana = new JLabel();
        RivalMana.setBounds(510,80,100,50);
        RivalMana.setText(String.valueOf(rivalmana));
        RivalMana.setFont(new Font("Comic Sans MS",Font.BOLD,28));
        RivalMana.setForeground(Color.RED);

        costo = new JLabel();
        costo.setBounds(20,210,200,50);
        costo.setText("No selection");
        costo.setFont(new Font("Comic Sans MS",Font.BOLD,28));
        costo.setForeground(Color.RED);


        skip_turn = new JButton(new ImageIcon("Imagenes/SkipBTN.jpg"));
        skip_turn.setBounds(550,260,200,60);
        skip_turn.addActionListener(this);

        use_card = new JButton(new ImageIcon("Imagenes/UseCardBTN.jpg"));
        use_card.setBounds(20,270,200,60);
        use_card.addActionListener(this);

        take_card = new JButton(new ImageIcon("Imagenes/TakeBTN.jpg"));
        take_card.setBounds(550,340,200,60);
        take_card.addActionListener(this);

        int posx = 50;
        for (int i=0 ;i<10;i++, posx+=60){
            btn_list[i] = new JButton();
            btn_list[i].setBounds(posx,430,75,100);
            btn_list[i].addActionListener(this::actionPerformed);
            this.match_screen.add(btn_list[i]);
        }


        mano = new ListaCircular();
        GenerateHand();

        NodoCircular[] nodos = {mano.getNode(0),mano.getNode(1),mano.getNode(2),mano.getNode(3),mano.getNode(4),
                mano.getNode(5),mano.getNode(6),mano.getNode(7),mano.getNode(8),mano.getNode(9)};

        this.nodos = nodos;

        buttons();
        this.match_screen.add(scroll);
        this.match_screen.add(skip_turn);
        this.match_screen.add(take_card);
        this.match_screen.add(use_card);
        this.add(costo);
        this.match_screen.add(PlayerHP);
        this.match_screen.add(RivalHP);
        this.match_screen.add(PlayerMana);
        this.match_screen.add(RivalMana);
        this.match_screen.add(fondo);
    }

    public void GenerateHand() {

        int contador = 0;
        while (contador < 10){
            JsonNode element = (JsonNode) deck.getTop();
            mano.InsertEnd(element);
            deck.pop();
            contador ++;
        }
    }

    public void CardUsed(){

        if (selected != null){
            JsonNode carta = (JsonNode) selected.getElemento();
            if (Integer.parseInt(carta.get("costo").textValue()) <= selected_cost) {
                Partida.GetInstance().EnviarMensaje(carta);

                String infoturno = carta.get("informacion").textValue();
                infoturno += " con costo de mana de ";
                infoturno += carta.get("costo");
                infoturno += "\n";
                history.append(infoturno);
                Partida.GetInstance().GuardarPartida(infoturno);

                if (deck.isEmpty() == false) {
                    mano.ChangeValue(deck.getTop(), pos2change);
                    deck.pop();
                }
                check_pos[pos2change] = false;
                btn_list[pos2change].setIcon(new ImageIcon("Cartas/Card-Template.png"));
                btn_list[pos2change].setEnabled(false);
                selected = null;
                costo.setText("No selection");
                Partida.GetInstance().setManaPropio(Partida.GetInstance().getManaPlayer() - selected_cost);
                selected_cost = 0;
                buttons();
                TerminaTurno();
                UpdateValues();
            }else{
                JOptionPane.showMessageDialog(this,"You don't have enough mana","Not enough mana",JOptionPane.INFORMATION_MESSAGE);
            }

        }else{
            JOptionPane.showMessageDialog(this,"Select a card","Nothing Selected",JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void CardTaken(){
        if (deck.isEmpty() == false){
            for (int i = 0; i<10; i++){
                if (check_pos[i] == false){
                    JsonNode nodo = (JsonNode) nodos[i].getElemento();
                    btn_list[i].setIcon(new ImageIcon(nodo.get("ruta").textValue()));
                    btn_list[i].setEnabled(true);
                    check_pos[i] = true;
                    TerminaTurno();
                    UpdateValues();
                    buttons();
                    break;
                }else if(check_pos[0] && check_pos[1] && check_pos[2] && check_pos[3] && check_pos[4] && check_pos[5]
                        && check_pos[6] && check_pos[7] && check_pos[8] && check_pos[9]){
                    JOptionPane.showMessageDialog(this, "Your hand is full", "You can't add more", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }
        }else{
            JOptionPane.showMessageDialog(this,"You're card broke","You can't take more cards",JOptionPane.INFORMATION_MESSAGE);
        }
        selected = null;
        costo.setText("No selection");
    }


    public void buttons(){
        for (int i = 0; i<10; i++){
            if (check_pos[i]){
                JsonNode nodo = (JsonNode) nodos[i].getElemento();
                btn_list[i].setIcon(new ImageIcon(nodo.get("ruta").textValue()));
            } else {
                btn_list[i].setIcon(new ImageIcon("Cartas/Card-Template.png"));
                btn_list[i].setEnabled(false);
            }
        }
    }


    public static void ShowCard(String infocarta) {

        history.append(infocarta);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        Object btn_pressed = e.getSource();

        if (btn_pressed == use_card){
            CardUsed();
        }

        if (btn_pressed == take_card){
            CardTaken();
        }
        if (btn_pressed == skip_turn){
            selected = null;
            costo.setText("No selection");
            TerminaTurno();
            UpdateValues();

        }

        if (btn_pressed == btn_list[0]){
            selected = mano.getNode(0);
            pos2change = 0;
            JsonNode nodo = (JsonNode) selected.getElemento();
            costo.setText("Cost: "+nodo.get("costo").textValue());
            selected_cost = Integer.parseInt(nodo.get("costo").textValue());

        }
        if (btn_pressed == btn_list[1]){
            selected = mano.getNode(1);
            pos2change = 1;
            JsonNode nodo = (JsonNode) selected.getElemento();
            costo.setText("Cost: "+nodo.get("costo").textValue());
            selected_cost = Integer.parseInt(nodo.get("costo").textValue());

        }
        if (btn_pressed == btn_list[2]){
            selected = mano.getNode(2);
            pos2change = 2;
            JsonNode nodo = (JsonNode) selected.getElemento();
            costo.setText("Cost: "+nodo.get("costo").textValue());
            selected_cost = Integer.parseInt(nodo.get("costo").textValue());


        }
        if (btn_pressed == btn_list[3]){
            selected = mano.getNode(3);
            pos2change = 3;
            JsonNode nodo = (JsonNode) selected.getElemento();
            costo.setText("Cost: "+nodo.get("costo").textValue());
            selected_cost = Integer.parseInt(nodo.get("costo").textValue());

        }
        if (btn_pressed == btn_list[4]){
            selected = mano.getNode(4);
            pos2change = 4;JsonNode nodo = (JsonNode) selected.getElemento();
            costo.setText("Cost: "+nodo.get("costo").textValue());
            selected_cost = Integer.parseInt(nodo.get("costo").textValue());

        }
        if (btn_pressed == btn_list[5]){
            selected = mano.getNode(5);
            pos2change = 5;
            JsonNode nodo = (JsonNode) selected.getElemento();
            costo.setText("Cost: "+nodo.get("costo").textValue());
            selected_cost = Integer.parseInt(nodo.get("costo").textValue());
        }

        if (btn_pressed == btn_list[6]){
            selected = mano.getNode(6);
            pos2change = 6;
            JsonNode nodo = (JsonNode) selected.getElemento();
            costo.setText("Cost: "+nodo.get("costo").textValue());
            selected_cost = Integer.parseInt(nodo.get("costo").textValue());
        }
        if (btn_pressed == btn_list[7]){
            selected = mano.getNode(7);
            pos2change = 7;
            JsonNode nodo = (JsonNode) selected.getElemento();
            costo.setText("Cost: "+nodo.get("costo").textValue());
            selected_cost = Integer.parseInt(nodo.get("costo").textValue());
        }
        if (btn_pressed == btn_list[8]){
            selected = mano.getNode(8);
            pos2change = 8;
            JsonNode nodo = (JsonNode) selected.getElemento();
            costo.setText("Cost: "+nodo.get("costo").textValue());
            selected_cost = Integer.parseInt(nodo.get("costo").textValue());
        }
        if (btn_pressed == btn_list[9]){
            selected = mano.getNode(9);
            pos2change = 9;
            JsonNode nodo = (JsonNode) selected.getElemento();
            costo.setText("Cost: "+nodo.get("costo").textValue());
            selected_cost = Integer.parseInt(nodo.get("costo").textValue());
        }

    }

    public void Disable(){
        for (int i=0; i<10; i++){
            if (check_pos[i]){
                btn_list[i].setEnabled(false);
            }
        }
        take_card.setEnabled(false);
        use_card.setEnabled(false);
        skip_turn.setEnabled(false);
        turno = false;
    }

    public void ComienzaTurno(){
        for (int i=0; i<10; i++){
            if (check_pos[i]){
                btn_list[i].setEnabled(true);
            }
        }

        take_card.setEnabled(true);
        use_card.setEnabled(true);
        skip_turn.setEnabled(true);
        turno = true;
        UpdateValues();
    }

    public void UpdateValues(){

        playerhp = Partida.GetInstance().getVidaPlayer();
        rivalhp = Partida.GetInstance().getVidaRival();
        playermana = Partida.GetInstance().getManaPlayer();
        rivalmana = Partida.GetInstance().getManaRival();

        PlayerHP.setText(String.valueOf(playerhp));
        RivalHP.setText(String.valueOf(rivalhp));
        PlayerMana.setText(String.valueOf(playermana));
        RivalMana.setText(String.valueOf(rivalmana));

        if (playerhp <= 0){
            Partida.GetInstance().Defeat();
            JOptionPane.showMessageDialog(this,"Better luck next time!","You lost :(",JOptionPane.INFORMATION_MESSAGE);
            Partida.GetInstance().Defeat();
            if(previousGuest != null){
                this.setVisible(false);
                this.previousGuest.setVisible(true);
            } else{
                this.setVisible(false);
                this.previousHost.setVisible(true);
            }
        }

    }

    public void TerminaTurno(){

        for (int i=0; i<10; i++){
            if (check_pos[i]){
                btn_list[i].setEnabled(false);
            }
        }
        take_card.setEnabled(false);
        use_card.setEnabled(false);
        skip_turn.setEnabled(false);
        turno = false;

        if (Partida.GetInstance().getManaPlayer() < 751) {
            Partida.GetInstance().setManaPropio(Partida.GetInstance().getManaPlayer() + 250);
        } else{
            Partida.GetInstance().setManaPropio(1000);
        }
        Partida.GetInstance().TerminaTurno(turno);

    }

    public void setPreviousHost(Host_GUI previousHost) {
        this.previousHost = previousHost;
    }

    public void setPreviousGuest(Guest_GUI previousGuest) {
        this.previousGuest = previousGuest;
    }

    public void End(){
        JOptionPane.showMessageDialog(this,"Congratulations! You earned nothing","You won :)",JOptionPane.INFORMATION_MESSAGE);
        if(previousGuest != null){
            this.setVisible(false);
            this.previousGuest.setVisible(true);
        } else{
            this.setVisible(false);
            this.previousHost.setVisible(true);
        }
    }

    public String Mostrartop(){

        JsonNode node = (JsonNode) this.deck.getTop();
        String mensaje;
        if (node == null){
            mensaje = "No hay cartas del deck por mostrar";
        } else {
            mensaje = "Se ha revelado una carta: ";
            mensaje += node.get("informacion").textValue();
        }

        return mensaje;
    }

    public Stack getDeck() {
        return deck;
    }
}
