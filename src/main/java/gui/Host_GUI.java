package gui;

import com.fasterxml.jackson.core.JsonProcessingException;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

/**
 * Clase Host_GUI que se encarga de abrir la ventana para el Host.
 */
public class Host_GUI extends JFrame {

    private JPanel host_screen;

    private JLabel host_gui_bg;

    private JButton start_btn;
    private JButton history_btn;

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

    private void InitializeComponents() {

        host_screen = new JPanel();
        host_screen.setLayout(null);
        this.getContentPane().add(host_screen);
        StartScreen();

    }

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

            }
        });

        this.host_screen.add(history_btn);
        this.host_screen.add(start_btn);
        this.host_screen.add(host_gui_bg);

    }

    public void actionPerformed_StartBTN() throws com.fasterxml.jackson.core.JsonProcessingException {

        if (Partida.GetInstance().isHay_guest()) {
            Match_GUI game_gui = new Match_GUI();
            game_gui.setVisible(true);
            Partida.GetInstance().setGame_gui(game_gui);
            this.setVisible(false);
            game_gui.Disable();
        }else{
            JOptionPane.showMessageDialog(this,"Wait for the guest to connect","No Guest Connected",JOptionPane.INFORMATION_MESSAGE);
        }

    }
}