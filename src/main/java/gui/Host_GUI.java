package gui;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class Host_GUI extends JFrame{

    private JPanel host_screen;

    private JLabel host_gui_bg;

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


        this.host_screen.add(host_gui_bg);

    }

}
