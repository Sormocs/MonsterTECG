package gui;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class Guest_GUI extends JFrame{

    private Partida partida;

    private JPanel guest_screen;

    private JButton secret_button;

    private JLabel guest_gui_bg;

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
        secret_button = new JButton();
        secret_button.setText("Boton Secreto");
        secret_button.setBounds(320,350,160,50);
        secret_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    partida.EnviarMensaje();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        this.guest_screen.add(guest_gui_bg);

    }

}