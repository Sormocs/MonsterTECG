import listas.ListaCircular;
import servidor.Server;
import gui.GUI;

import java.sql.SQLOutput;

public class Main {

    public static void main(String[] args) {

        gui.GUI main_win = new gui.GUI();
        main_win.setVisible(true);
        ListaCircular lista = new ListaCircular();

        lista.InsertEnd(1);
        lista.InsertEnd(2);
        lista.InsertEnd(3);
        lista.InsertEnd(4);
        lista.InsertEnd(5);

        lista.Show();


    }

}
