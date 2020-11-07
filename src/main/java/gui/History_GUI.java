package gui;

import listas.ListaDoble;
import listas.NodoDoble;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class History_GUI extends JFrame implements ActionListener {

    private Host_GUI prev_hostgui;
    private Guest_GUI prev_guestgui;

    private JPanel screen;

    private JButton next_game;
    private JButton next_turn;

    private JLabel HP;
    private JLabel mana;
    private JLabel action;
    private JLabel decktitle;
    private JLabel nothing;

    private JLabel btn0;
    private JLabel btn1;
    private JLabel btn2;
    private JLabel btn3;
    private JLabel btn4;
    private JLabel btn5;
    private JLabel btn6;
    private JLabel btn7;
    private JLabel btn8;
    private JLabel btn9;

    private JLabel[] label_list = {btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9};

    private ListaDoble listaPartidas = Partida.GetInstance().getListaPartidas();

    private NodoDoble current;

    private int partidaNum = 1;


    public History_GUI(){
        super();
        InitializeComponents();
        ConfigureWin();

    }

    public void ConfigureWin(){
        this.setTitle("History");
        setResizable(false);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void InitializeComponents(){


        screen = new JPanel();
        screen.setLayout(null);
        screen.setBackground(Color.BLUE);
        this.getContentPane().add(screen);
        ScreenComps();

    }

    public void ScreenComps(){
        if (listaPartidas.getSize() == 0) {

            nothing = new JLabel();
            nothing.setBounds(130,210,300,70);
            nothing.setText("No previous games registered");
            nothing.setFont(new Font("Comic Sans MS",Font.BOLD,28));
            nothing.setForeground(Color.BLACK);

            int posx = 5;
            for (int i = 0; i < 10; i++, posx += 75) {
                label_list[i] = new JLabel(new ImageIcon("Cartas/Card-Template.png"));
                label_list[i].setBounds(posx, 330, 75, 100);
                this.screen.add(label_list[i]);
            }

            this.screen.add(next_game);
            this.screen.add(next_turn);
            this.screen.add(nothing);

        } else{

            current = listaPartidas.getInicio();
            ListaDoble partida = (ListaDoble) current.getDato();
            String turno = (String) partida.getInicio().getDato();
            String info[] = turno.split("#");
            System.out.println(info);
            int infoIn = 4;
            int posx = 5;
            for (int i = 0; i < 10; i++, infoIn += 1, posx += 75) {
                label_list[i] = new JLabel(new ImageIcon(info[infoIn]));
                label_list[i].setBounds(posx, 330, 75, 100);
                this.screen.add(label_list[i]);
            }

            HP = new JLabel();
            HP.setBounds(30,160,300,50);
            HP.setText("Vida en el turno: "+info[2]);
            HP.setFont(new Font("Comic Sans MS",Font.BOLD,28));
            HP.setForeground(Color.BLACK);

            mana = new JLabel();
            mana.setBounds(30,220,300,50);
            mana.setText("Vida en el turno: "+info[2]);
            mana.setFont(new Font("Comic Sans MS",Font.BOLD,28));
            mana.setForeground(Color.BLACK);

            action = new JLabel();
            action.setBounds(30,290,300,50);
            action.setText("Vida en el turno: "+info[2]);
            action.setFont(new Font("Comic Sans MS",Font.BOLD,28));
            action.setForeground(Color.BLACK);

            next_turn = new JButton();
            next_turn.setText("Next Turn");
            next_turn.setBounds(460,470,150,50);
            next_turn.addActionListener(this::actionPerformed);

            next_game = new JButton();
            next_game.setText("Next Turn");
            next_game.setBounds(620,470,150,50);
            next_game.addActionListener(this::actionPerformed);

            this.screen.add(HP);
            this.screen.add(mana);
            this.screen.add(action);
            this.screen.add(next_turn);

        }
    }

    public void setPrev_guestgui(Guest_GUI prev_guestgui) {
        this.prev_guestgui = prev_guestgui;
    }

    public void setPrev_hostgui(Host_GUI prev_hostgui) {
        this.prev_hostgui = prev_hostgui;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        Object clicked = e.getSource();

        if (clicked == next_turn){

        }

    }
}
