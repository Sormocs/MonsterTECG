package cartas;

import cliente.Cliente;
import gui.Match_GUI;
import gui.Partida;
import listas.Stack;

public class Spells {

    public String tipo;
    public String objetivo;
    public int porcentaje;
    public String accion;
    public int costo;
    public String ruta;
    public String informacion;
    public String afecta;

    public void Caso() {

        Cliente cliente = Partida.GetInstance().cliente();

        switch (this.accion) {
            case "RellenarMana":
                RellenarMana(cliente);
                break;
            case "QuitarTurnoRival":
                QuitarTurnoRival(cliente);
                break;
            case "curar":
                Curar(cliente);
                break;
            case "revelar":
                Revelar(cliente);
                break;
            case "robo":
                Robo(cliente);
                break;
            case "SupremePower":
                SupremePower(cliente);
                break;

        }

    }

    public void RellenarMana(Cliente cliente){

        cliente.RellenarMana(this.porcentaje);

    }

    public void QuitarTurnoRival(Cliente cliente){

        cliente.setTurno(true);

    }

    public void Curar(Cliente cliente){

        int dato = (this.porcentaje* cliente.getVida())/100;

        cliente.Curar(dato);

    }

    public void Revelar(Cliente cliente){

        String revelado = Partida.GetInstance().Gettop();

        Match_GUI.ShowCard(revelado+ "\n");

        Partida.GetInstance().GuardarPartida(revelado+ "\n");

        cliente.EnviarRevelado(revelado);

    }

    public void Robo(Cliente cliente){

        Stack deck = Partida.GetInstance().Push();

        deck.push(cliente.getUltima());

    }

    public void SupremePower(Cliente cliente){

    }
}
