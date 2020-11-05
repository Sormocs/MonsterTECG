package cartas;

import cliente.Cliente;
import gui.Partida;

public class Secrets {
    public int ataque;
    public String tipo;
    public int costo;
    public String evento;
    public int porcentaje;
    public String ruta;
    public String informacion;
    public String afecta;

    public void Caso() {

        Cliente cliente = Partida.GetInstance().cliente();

        switch (this.evento){
            case "defensa":
                Defensa(cliente);
                break;
            case "winorlose":
                WinorLose(cliente);
                break;
            case "EliminarCartaSecreta":
                EliminarCartaSecreta();
                break;
            case "EliminarCartaSpell":
                EliminarCartaSpell();
                break;
            case "Secreta_y_Ganar":
                SecretaYGanar(cliente);
                break;
            case "reflejar":
                Reflejar(cliente);
                break;
            case "Ejecutar":
                Ejecutar(cliente);
                break;
        }

    }

    public void Defensa(Cliente cliente){
        cliente.setDefensa(this.porcentaje);
    }

    public void WinorLose(Cliente cliente){

        int probabilidad = (int) (Math.random() * 2);

        if (probabilidad == 0){
            return;
        } else{
            cliente.setDefensa(0);
            cliente.setReflejo(false);
            cliente.setVida(0);
        }

    }

    public void EliminarCartaSecreta(){

    }

    public void EliminarCartaSpell(){

    }

    public void SecretaYGanar(Cliente cliente){

        cliente.setDefensa(0);
        cliente.setReflejo(false);
        cliente.setVida(0);

    }

    public void Reflejar(Cliente cliente){

        cliente.setReflejo(true);

    }

    public void Ejecutar(Cliente cliente){

        int probabilidad = (int) (Math.random() * 4);

        if (probabilidad < this.porcentaje){
            cliente.setDefensa(0);
            cliente.setReflejo(false);
            cliente.setVida(0);
        }

    }

}
