package gui;

import listas.ListaDoble;
import listas.NodoDoble;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase History_GUI, permite navegar por el historial a traves de las listas doblemente enlazadas, accede a la informacion de las
 * partidas en esas listas para mostrar lo que ocurrio en cada turno para el jugador correspondiente.
 */

public class History_GUI extends JFrame implements ActionListener {

    private Host_GUI prev_hostgui;
    private Guest_GUI prev_guestgui;

    private JPanel screen;

    private JButton next_game;
    private JButton next_turn;
    private JButton prev_game;
    private JButton prev_turn;
    private JButton go_back;

    private JLabel HP;
    private JLabel mana;
    private JLabel action;
    private JLabel decktitle;
    private JLabel nothing;
    private JLabel game;

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

    private NodoDoble currentTurn;

    private int partidaNum = 1;

    /**
     * Constructor de la clase, llama al constructor de la clase padre Jframe, de la cual hereda muchos metodos que ayudan a que
     * se forme la ventana.
     */
    public History_GUI(){
        super();
        InitializeComponents();
        ConfigureWin();

    }

    /**
     * Metodo para configurar la ventana, por ejemplo el titulo, si se le puede cambiar el tamaño, ajustar el tamaño y la
     * operación para el cierre.
     */
    public void ConfigureWin(){
        this.setTitle("History");
        setResizable(false);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Metodo para iniciar los componentes de la ventana, siendo principalmente el panel para luego llamar al metodo que inicia
     * los metodos del panel.
     */

    public void InitializeComponents(){


        screen = new JPanel();
        screen.setLayout(null);
        screen.setBackground(Color.BLUE);
        this.getContentPane().add(screen);
        ScreenComps();

    }

    /**
     * Inicia los componenetes del panel que se encuentra en la ventana, todos los labels y textos. La manera en la que se inician
     * los componentes depende de si hay partidas en la lista o no.
     */

    public void ScreenComps(){
        if (listaPartidas.getSize() == 0) {

            nothing = new JLabel();
            nothing.setBounds(240,110,300,70);
            nothing.setText("No previous games registered");
            nothing.setFont(new Font("Comic Sans MS",Font.BOLD,28));
            nothing.setForeground(Color.BLACK);

            int posx = 5;
            for (int i = 0; i < 10; i++, posx += 75) {
                label_list[i] = new JLabel(new ImageIcon("Cartas/Card-Template.png"));
                label_list[i].setBounds(posx, 330, 75, 100);
                this.screen.add(label_list[i]);
            }

            this.screen.add(nothing);

        } else{

            current = listaPartidas.getInicio();
            ListaDoble partida = (ListaDoble) current.getDato();
            currentTurn = partida.getInicio();
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

            game = new JLabel();
            game.setBounds(220,40,300,70);
            game.setText("Game "+String.valueOf(partidaNum)+", Turn "+info[0]);
            game.setFont(new Font("Comic Sans MS",Font.BOLD,28));
            game.setForeground(Color.BLACK);

            decktitle = new JLabel();
            decktitle.setBounds(30,290,300,50);
            decktitle.setText("Hand was:");
            decktitle.setFont(new Font("Comic Sans MS",Font.BOLD,28));
            decktitle.setForeground(Color.BLACK);

            HP = new JLabel();
            HP.setBounds(30,100,300,50);
            HP.setText("HP: "+info[2]);
            HP.setFont(new Font("Comic Sans MS",Font.BOLD,28));
            HP.setForeground(Color.BLACK);

            mana = new JLabel();
            mana.setBounds(30,170,300,50);
            mana.setText("Mana: "+info[3]);
            mana.setFont(new Font("Comic Sans MS",Font.BOLD,28));
            mana.setForeground(Color.BLACK);

            action = new JLabel();
            action.setBounds(30,230,800,60);
            action.setText("Action: "+info[1]);
            action.setFont(new Font("Comic Sans MS",Font.BOLD,28));
            action.setForeground(Color.BLACK);

            next_turn = new JButton();
            next_turn.setText("Next Turn");
            next_turn.setBounds(460,470,150,50);
            next_turn.addActionListener(this::actionPerformed);

            next_game = new JButton();
            next_game.setText("Next Game");
            next_game.setBounds(620,470,150,50);
            next_game.addActionListener(this::actionPerformed);

            prev_game = new JButton();
            prev_game.setText("Prev Game");
            prev_game.setBounds(10,470,150,50);
            prev_game.addActionListener(this::actionPerformed);

            prev_turn = new JButton();
            prev_turn.setText("Prev Turn");
            prev_turn.setBounds(180,470,150,50);
            prev_turn.addActionListener(this::actionPerformed);

            this.screen.add(decktitle);
            this.screen.add(game);
            this.screen.add(HP);
            this.screen.add(mana);
            this.screen.add(action);
            this.screen.add(next_turn);
            this.screen.add(next_game);
            this.screen.add(prev_turn);
            this.screen.add(prev_game);

            if (current.getSiguiente() == null){
                next_game.setEnabled(false);
            }
            prev_game.setEnabled(false);
            prev_turn.setEnabled(false);

        }
        go_back = new JButton();
        go_back.setText("Go Back");
        go_back.setBounds(320,470,150,50);
        go_back.addActionListener(this::actionPerformed);


        this.screen.add(go_back);
    }

    /**
     * Para actualizar la informacion con la del siguiente turno en la pantalla
     */
    public void NextTurn(){

        currentTurn = currentTurn.getSiguiente();
        String turno = (String) currentTurn.getDato();
        String info[] = turno.split("#");

        int infoIn = 4;
        for (int i = 0; i < 10; i++, infoIn += 1) {
            label_list[i].setIcon(new ImageIcon(info[infoIn]));
            this.screen.add(label_list[i]);
        }
        HP.setText("HP: "+info[2]);
        action.setText("Action: "+info[1]);
        mana.setText("Mana: "+info[3]);
        game.setText("Game "+String.valueOf(partidaNum)+", Turn "+info[0]);

        VerifyButtons();

    }

    /**
     * Coloca la informacion del primero turno en la segunda partida si es que hay.
     */
    public void NextGame(){
        partidaNum ++;
        current = current.getSiguiente();
        ListaDoble partida = (ListaDoble) current.getDato();
        currentTurn = partida.getInicio();
        String turno = (String) currentTurn.getDato();
        String info[] = turno.split("#");

        int infoIn = 4;
        for (int i = 0; i < 10; i++, infoIn += 1) {
            label_list[i].setIcon(new ImageIcon(info[infoIn]));
            this.screen.add(label_list[i]);
        }
        HP.setText("HP: "+info[2]);
        action.setText("Action: "+info[1]);
        mana.setText("Mana: "+info[3]);
        game.setText("Game "+String.valueOf(partidaNum)+", Turn "+info[0]);

        VerifyButtons();

    }

    /**
     * Configura la interfaz para que muestre la informacion del turno anterior al que se encuentra visible en la pantalla
     */
    public void PrevTurn(){
        currentTurn = currentTurn.getAnterior();
        String turno = (String) currentTurn.getDato();
        String info[] = turno.split("#");

        int infoIn = 4;
        for (int i = 0; i < 10; i++, infoIn += 1) {
            label_list[i].setIcon(new ImageIcon(info[infoIn]));
            this.screen.add(label_list[i]);
        }
        HP.setText("HP: "+info[2]);
        action.setText("Action: "+info[1]);
        mana.setText("Mana: "+info[3]);
        game.setText("Game "+String.valueOf(partidaNum)+", Turn "+info[0]);

        VerifyButtons();

    }

    /**
     * Coloca en la pantalla la informacion para que se observe la informacion de la partida anterior a partir del turno 1.
     */
    public void PrevGame(){
        partidaNum --;
        current = current.getAnterior();
        ListaDoble partida = (ListaDoble) current.getDato();
        currentTurn = partida.getInicio();
        String turno = (String) currentTurn.getDato();
        String info[] = turno.split("#");

        int infoIn = 4;
        for (int i = 0; i < 10; i++, infoIn += 1) {
            label_list[i].setIcon(new ImageIcon(info[infoIn]));
            this.screen.add(label_list[i]);
        }
        HP.setText("HP: "+info[2]);
        action.setText("Action: "+info[1]);
        mana.setText("Mana: "+info[3]);
        game.setText("Game "+String.valueOf(partidaNum)+", Turn "+info[0]);

        VerifyButtons();

    }

    /**
     * LLeva control para activar o desactivar los botones segun las listas, si es posible avanzar o retroceder, o si no existe
     * ningun elemento antes o despues de tal nodo, para que el programa no tire errores al presionar esos botones.
     */
    public void VerifyButtons(){
        if (currentTurn.getSiguiente() == null){
            next_turn.setEnabled(false);
        } else{
            next_turn.setEnabled(true);
        }
        if (currentTurn.getAnterior() == null){
            prev_turn.setEnabled(false);
        } else{
            prev_turn.setEnabled(true);
        }
        if (current.getSiguiente() == null){
            next_game.setEnabled(false);
        } else{
            next_game.setEnabled(true);
        }
        if (current.getAnterior() == null){
            prev_game.setEnabled(false);
        } else{
            prev_game.setEnabled(true);
        }
    }

    /**
     * Sirve para volver a la interfaz anterior segun si era host o guest quien estaba consultando el historial.
     */
    public void GoBack(){
        if (prev_hostgui != null){
            this.setVisible(false);
            prev_hostgui.setVisible(true);
        } else{
            this.setVisible(false);
            prev_guestgui.setVisible(true);
        }
    }

    /**
     * Recibe la gui del invitado para poder regresar a esta cuando el jugador es guest.
     * @param prev_guestgui
     */
    public void setPrev_guestgui(Guest_GUI prev_guestgui) {
        this.prev_guestgui = prev_guestgui;
    }

    /**
     * Recibe la gui del host para poder retornar a esta cuando el jugador es host.
     * @param prev_hostgui
     */
    public void setPrev_hostgui(Host_GUI prev_hostgui) {
        this.prev_hostgui = prev_hostgui;
    }

    /**
     * Metodo que viene con el Action Listener para implementar los eventos de los botones.
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        Object clicked = e.getSource();

        if (clicked == next_turn){
            NextTurn();
        }
        if (clicked == next_game){
            NextGame();
        }
        if (clicked == go_back){
            GoBack();
        }
        if (clicked == prev_game){
            PrevGame();
        }
        if (clicked == prev_turn){
            PrevTurn();
        }
    }
}
