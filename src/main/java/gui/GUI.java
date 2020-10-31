package gui;

import javax.swing.*;
import java.awt.event.*;

public class GUI extends JFrame {

    private Host_GUI ventana_host;
    private Guest_GUI ventana_invitado;

    private JPanel screen;

    private JButton begin_host;
    private JButton join_guest;

    private JLabel menu_bg;


    public GUI() {
        super();
        InitializeComponents();
        ConfigureWin();

    }

    public void ConfigureWin(){
        setTitle("Monster TECG!");
        setSize(800,600);
        setResizable(false);
        setLocationRelativeTo(null);
        InitializeComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    private void InitializeComponents(){

        screen = new JPanel();
        screen.setLayout(null);
        this.getContentPane().add(screen);
        ScreenComps();

    }

    private void ScreenComps(){

        menu_bg = new JLabel(new ImageIcon("Imagenes/MenuBG.jpg"));
        menu_bg.setBounds(0,0,800,600);
        Buttons();

    }

    private void Buttons(){

        begin_host = new JButton();
        begin_host.setIcon(new ImageIcon("Imagenes/HostButton.png"));
        begin_host.setBounds(100,230,270,130);
        begin_host.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ActionButton1();

            }
        });

        //BOTON INVITADO:

        join_guest = new JButton();
        join_guest.setIcon(new ImageIcon("Imagenes/GuestButton.png"));
        join_guest.setBounds(420,230,270,130);
        join_guest.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ActionButton2();
            }
        });

        this.screen.add(begin_host);
        this.screen.add(join_guest);
        this.screen.add(menu_bg);

    }

    private void ActionButton1(){

        Partida.GetInstance().Host();
        ventana_host = new Host_GUI();
        ventana_host.setVisible(true);
        this.setVisible(false);

    }

    private void ActionButton2(){

        Partida.GetInstance().Invitado();
        ventana_invitado = new Guest_GUI();
        ventana_invitado.setVisible(true);
        this.setVisible(false);


    }
}
