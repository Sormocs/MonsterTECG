package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.fasterxml.jackson.databind.JsonNode;
import listas.*;

public class Match_GUI extends JFrame {

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

    private JButton use_card;
    private JButton take_card;

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


    private JTextArea history;

    private JScrollPane scroll;

    private JsonNode selected;

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

        use_card = new JButton(new ImageIcon("Imagenes/UseCardBTN.jpg"));
        use_card.setBounds(20,270,200,60);
        use_card.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardUsed();
            }
        });

        take_card = new JButton(new ImageIcon("Imagenes/TakeBTN.jpg"));
        take_card.setBounds(550,350,200,60);
        take_card.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardTaken();
            }
        });

        mano = new ListaCircular();
        GenerateHand();
        buttons();
        this.match_screen.add(scroll);
        this.match_screen.add(take_card);
        this.match_screen.add(use_card);
        this.match_screen.add(PlayerHP);
        this.match_screen.add(RivalHP);
        this.match_screen.add(PlayerMana);
        this.match_screen.add(RivalMana);
        this.match_screen.add(fondo);
    }

    public void GenerateHand() {

        int contador = 0;
        while (contador < 4){
            JsonNode element = (JsonNode) deck.getTop();
            mano.InsertEnd(element);
            deck.pop();
            contador ++;
            //mano.Show();
        }
    }

    public void CardUsed(){

        if (selected != null){
            Partida.GetInstance().EnviarMensaje(selected);

            String infoturno = selected.get("informacion").textValue();
            infoturno += " con costo de mana de ";
            infoturno += selected.get("costo");
            infoturno += "\n";

            history.append(infoturno);
            Partida.GetInstance().GuardarPartida(infoturno);
            //Object elemento = selected;
            //mano.Delete(elemento);
            //mano.Show();
            //buttons();
            //Update();
            //selected = null;

        }else{
            JOptionPane.showMessageDialog(this,"Select a card","Nothing Selected",JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void CardTaken(){
        if (mano.getSize()<10 && deck.isEmpty()==false){
            mano.InsertEnd(deck.getTop());
            deck.pop();
            Update();
        }else{
            JOptionPane.showMessageDialog(this,"Your hand is full","You can't take more",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void Update(){
        match_screen.remove(take_card);
        match_screen.remove(use_card);
        buttons();
        match_screen.revalidate();
        match_screen.repaint();
        match_screen.updateUI();
        this.match_screen.add(take_card);
        this.match_screen.add(use_card);
        this.match_screen.add(fondo);
    }


    public void buttons(){
        button0();
        button1();
        button2();
        button3();
        button4();
        button5();
        button6();
        button7();
        button8();
        button9();
    }

    private void button0(){
        if (mano.getNode(0) != null){
            NodoCircular actual = mano.getNode(0);
            JsonNode nodo = (JsonNode) actual.getElemento();
            JButton card0 = new JButton(new ImageIcon(nodo.get("ruta").textValue()));
            card0.setBounds(50,440,75,100);
            card0.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selected = nodo;

                }
            });
            this.match_screen.add(card0);
        }
    }

    private void button1(){
        if (mano.getNode(1) != null){
            NodoCircular actual = mano.getNode(1);
            JsonNode nodo = (JsonNode) actual.getElemento();
            JButton card1 = new JButton(new ImageIcon(nodo.get("ruta").textValue()));
            card1.setBounds(110,440,75,100);
            card1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selected = nodo;

                }
            });
            this.match_screen.add(card1);
        }
    }

    private void button2(){
        if (mano.getNode(2) != null){
            NodoCircular actual = mano.getNode(2);
            JsonNode nodo = (JsonNode) actual.getElemento();
            JButton card2 = new JButton(new ImageIcon(nodo.get("ruta").textValue()));
            card2.setBounds(170,440,75,100);
            card2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selected = nodo;


                }
            });
            this.match_screen.add(card2);
        }
    }

    private void button3(){
        if (mano.getNode(3) != null){
            NodoCircular actual = mano.getNode(3);
            JsonNode nodo = (JsonNode) actual.getElemento();
            JButton card3 = new JButton(new ImageIcon(nodo.get("ruta").textValue()));
            card3.setBounds(230,440,75,100);
            card3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selected = nodo;
                }
            });
            this.match_screen.add(card3);
        }
    }

    private void button4(){
        if (mano.getNode(4) != null){
            NodoCircular actual = mano.getNode(4);
            JsonNode nodo = (JsonNode) actual.getElemento();
            JButton card4 = new JButton(new ImageIcon(nodo.get("ruta").textValue()));
            card4.setBounds(290,440,75,100);
            card4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selected = nodo;
                }
            });
            this.match_screen.add(card4);
        }
    }

    private void button5(){
        if (mano.getNode(5) != null){
            NodoCircular actual = mano.getNode(5);
            JsonNode nodo = (JsonNode) actual.getElemento();
            JButton card5 = new JButton(new ImageIcon(nodo.get("ruta").textValue()));
            card5.setBounds(350,440,75,100);
            card5.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selected = nodo;
                }
            });
            this.match_screen.add(card5);
        }
    }

    private void button6(){
        if (mano.getNode(6) != null){
            NodoCircular actual = mano.getNode(6);
            JsonNode nodo = (JsonNode) actual.getElemento();
            JButton card6 = new JButton(new ImageIcon(nodo.get("ruta").textValue()));
            card6.setBounds(410,440,75,100);
            card6.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selected = nodo;
                }
            });
            this.match_screen.add(card6);
        }
    }

    private void button7(){
        if (mano.getNode(7) != null){
            NodoCircular actual = mano.getNode(7);
            JsonNode nodo = (JsonNode) actual.getElemento();
            JButton card7 = new JButton(new ImageIcon(nodo.get("ruta").textValue()));
            card7.setBounds(470,440,75,100);
            card7.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selected = nodo;
                }
            });
            this.match_screen.add(card7);
        }
    }

    private void button8(){
        if (mano.getNode(8) != null){
            NodoCircular actual = mano.getNode(8);
            JsonNode nodo = (JsonNode) actual.getElemento();
            JButton card8 = new JButton(new ImageIcon(nodo.get("ruta").textValue()));
            card8.setBounds(530,440,75,100);
            card8.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selected = nodo;
                }
            });
            this.match_screen.add(card8);
        }
    }

    private void button9(){
        if (mano.getNode(9) != null){
            NodoCircular actual = mano.getNode(9);
            JsonNode nodo = (JsonNode) actual.getElemento();
            JButton card9 = new JButton(new ImageIcon(nodo.get("ruta").textValue()));
            card9.setBounds(590,440,75,100);
            card9.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selected = nodo;
                }
            });
            this.match_screen.add(card9);
        }
    }




}
