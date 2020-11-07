package gui;

import com.fasterxml.jackson.core.JsonProcessingException;
import listas.ListaDoble;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Clase Guest_GUI que crea la ventana que actua como el menu para el jugador que entra como guest al juego.
 */
public class Guest_GUI extends JFrame{

    private JPanel guest_screen;

    private JButton join_btn;
    private JButton history_btn;

    private JLabel guest_gui_bg;


    /**
     * Constructor de la clase que llama al constructor de la clase padre, además de iniciar los componentes de
     * la ventana y configurar esta misma con su título y tamaño respectivos.
     */
    public Guest_GUI(){

        super();
        InitializeComponents();
        ConfigureWin();

    }

    private void ConfigureWin(){

        setTitle("Monster TECG!");
        setResizable(false);
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void InitializeComponents(){

        guest_screen = new JPanel();
        guest_screen.setLayout(null);
        this.getContentPane().add(guest_screen);
        StartScreen();

    }

    private void StartScreen(){

        guest_gui_bg = new JLabel(new ImageIcon("Imagenes/GuestGuiBG.jpg"));
        guest_gui_bg.setBounds(0,-5,800,600);

        //BOTON SECRETO:
        join_btn = new JButton(new ImageIcon("Imagenes/JoinBTN.jpg"));
        join_btn.setBounds(30,230,270,130);
        join_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    ActionJoinBTN();
                }catch(JsonProcessingException e2){
                    e2.printStackTrace();
                }
            }
        });

        history_btn = new JButton(new ImageIcon("Imagenes/HistoryBTN.jpg"));
        history_btn.setBounds(510, 230, 270, 130);
        history_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                History_BTN_Action();
            }
        });

        this.guest_screen.add(history_btn);
        this.guest_screen.add(join_btn);
        this.guest_screen.add(guest_gui_bg);

    }

    private void ActionJoinBTN() throws com.fasterxml.jackson.core.JsonProcessingException{
        Match_GUI game_gui = new Match_GUI();
        game_gui.setVisible(true);
        Partida.GetInstance().setGame_gui(game_gui);
        game_gui.setPreviousGuest(this);
        this.setVisible(false);
        ListaDoble partida = new ListaDoble();
        game_gui.setPartida(partida);
        Partida.GetInstance().InsertarPartida(partida);
    }

    private void History_BTN_Action(){
        History_GUI history = new History_GUI();
        history.setPrev_guestgui(this);
        this.setVisible(false);
        history.setVisible(true);

    }

}