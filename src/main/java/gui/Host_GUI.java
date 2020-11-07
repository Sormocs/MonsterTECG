package gui;

import cliente.CheckIP;
import com.fasterxml.jackson.core.JsonProcessingException;
import listas.ListaDoble;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Clase Host_GUI que se encarga de abrir la ventana para el Host.
 */
public class Host_GUI extends JFrame {

    private JPanel host_screen;

    private JLabel host_gui_bg;
    private JLabel IP;
    private JLabel port;

    private JButton start_btn;
    private JButton history_btn;

    private ListaDoble listaPartidas;

    /**
     * Constructor de la clase Host_GUI que llama al constructor de la clase padre y a los metodos para iniciar
     * los componentes de la ventana y a su vez, al metodo que configura esta misma.
     */
    public Host_GUI() {

        super();
        InitializeComponents();
        ConfigureWin();

    }

    /**
     * Metodo para configurar los compoonentes de la ventana.
     */
    private void ConfigureWin() {

        setTitle("Monster TECG!");
        setResizable(false);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * Metodo para iniciar los componentes de la ventana
     */
    private void InitializeComponents() {

        host_screen = new JPanel();
        host_screen.setLayout(null);
        this.getContentPane().add(host_screen);
        StartScreen();

    }

    /**
     * Metodo para iniciar los componenetes del panel que contiene los elementos que se muestran en la ventana.
     */
    private void StartScreen() {

        host_gui_bg = new JLabel(new ImageIcon("Imagenes/HostGuiBG.jpg"));
        host_gui_bg.setBounds(0, -5, 800, 600);

        start_btn = new JButton(new ImageIcon("Imagenes/StartBTN.jpg"));
        start_btn.setBounds(30, 230, 270, 130);

        start_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    actionPerformed_StartBTN();
                } catch (JsonProcessingException jsonProcessingException) {
                    jsonProcessingException.printStackTrace();
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

        IP = new JLabel();
        IP.setBounds(280,400,300,70);
        try {
            IP.setText("IP: "+ CheckIP.obtenerIP());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        IP.setFont(new Font("Comic Sans MS",Font.BOLD,28));
        IP.setForeground(Color.BLACK);

        port = new JLabel();
        port.setBounds(320,440,300,70);
        port.setText("Port: 5000");
        port.setFont(new Font("Comic Sans MS",Font.BOLD,28));
        port.setForeground(Color.BLACK);

        this.host_screen.add(IP);
        this.host_screen.add(port);
        this.host_screen.add(history_btn);
        this.host_screen.add(start_btn);
        this.host_screen.add(host_gui_bg);

    }

    /**
     * Inicia la partida siempre que se verifique que hay un guest conectado y comenzar el juego.
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    private void actionPerformed_StartBTN() throws com.fasterxml.jackson.core.JsonProcessingException {

        if (Partida.GetInstance().isHay_guest()) {
            Match_GUI game_gui = new Match_GUI();
            ListaDoble partida = new ListaDoble();
            Partida.GetInstance().InsertarPartida(partida);
            game_gui.setPartida(partida);
            game_gui.setVisible(true);
            Partida.GetInstance().setGame_gui(game_gui);
            this.setVisible(false);
            game_gui.Disable();
            game_gui.setPreviousHost(this);
        }else{
            JOptionPane.showMessageDialog(this,"Wait for the guest to connect","No Guest Connected",JOptionPane.INFORMATION_MESSAGE);
        }

    }

    /**
     * Permite acceder al historial de partidas al presionar el boton que activa este metodo.
     */
    private void History_BTN_Action(){
        History_GUI history = new History_GUI();
        history.setPrev_hostgui(this);
        this.setVisible(false);
        history.setVisible(true);

    }

}