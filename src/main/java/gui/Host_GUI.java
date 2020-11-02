package gui;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class Host_GUI extends JFrame{

    private JPanel host_screen;

    private JLabel host_gui_bg;

    private JButton start_btn;

    public Host_GUI(){

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

        host_screen = new JPanel();
        host_screen.setLayout(null);
        this.getContentPane().add(host_screen);
        StartScreen();

    }

    private void StartScreen(){

        host_gui_bg = new JLabel(new ImageIcon("Imagenes/HostGuiBG.jpg"));
        host_gui_bg.setBounds(0,-5,800,600);

        start_btn = new JButton();
        start_btn.setBounds(50,270,160,50);
        start_btn.setText("Boton");
        start_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //PROGRA BOTON, PREFERIBLEMENTE METODO NUEVO
                Partida.GetInstance().EnviarMensaje();

            }
        });

        this.host_screen.add(start_btn);
        this.host_screen.add(host_gui_bg);

    }

}
