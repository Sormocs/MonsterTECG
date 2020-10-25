package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class GUI extends JFrame {

    private JPanel screen1;
    private JPanel screen2;
    private JButton begin_host;
    private JButton join_guest;
    private JButton secret_button;


    public GUI(){
        super();
        InitializeComponents();
        setTitle("Monster TECG!");
        setSize(800,600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    private void InitializeComponents(){

        screen2 = new JPanel();
        screen2.setBackground(Color.BLUE);


        screen1 = new JPanel();
        screen1.setBackground(Color.BLACK);
        this.getContentPane().add(screen1);
        Screen1Comps();
    }

    private void Screen1Comps(){

        screen1.setLayout(null);

        //BOTON HOST:

        begin_host = new JButton();
        begin_host.setText("Entrar como host");
        begin_host.setBounds(100,350,160,50);
        begin_host.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //AQUI CÓDIGO PARA EJECUTAR, SI ES MUCHO CREAR METODO APARTE
                Partida host = new Partida();
                host.Host();

                Change2Screen2();
            }
        });

        //BOTON INVITADO:

        join_guest = new JButton();
        join_guest.setText("Entrar como Invitado");
        join_guest.setBounds(540,350,160,50);
        join_guest.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //AQUI CODIGO PARA EJECUTAR, SI ES MUCHO CREAR METODO APARTE

                Partida partida = new Partida();
                partida.Invitado();

                //public void host....
                //crea el hilo cliente....

                Change2Screen2();
            }
        });

        //BOTON SECRETO:

        secret_button = new JButton();
        secret_button.setText("Boton Secreto");
        secret_button.setBounds(320,350,160,50);
        secret_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //AQUI CODIGO PARA EJECUTAR, SI ES MUCHO CREAR METODO APARTE

            }
        });


        this.screen1.add(secret_button);
        this.screen1.add(join_guest);
        this.screen1.add(begin_host);
    }

    private void Screen2Comps(){

        screen2.setLayout(null);
        JLabel hellow = new JLabel("Welcome to an unfinished game");
        hellow.setBounds(300,300,250,50);
        screen2.add(hellow);

    }

    private void Change2Screen2(){

        this.screen1.setVisible(false);
        this.getContentPane().add(screen2);
        Screen2Comps();

    }


}
