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

    /**
     * Busca la el método correspodiente a la carta y lo ejecuta
     */

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
                EliminarCartaSecreta(cliente);
                break;
            case "EliminarCartaSpell":
                EliminarCartaSpell(cliente);
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

    /**
     * Da una defensa a si mismo
     * @param cliente Cliente
     */

    public void Defensa(Cliente cliente){
        cliente.setDefensa(this.porcentaje);
    }

    /**
     * Gana o pierde
     * @param cliente Cliente
     */

    public void WinorLose(Cliente cliente){

        int probabilidad = (int) (Math.random() * 2);

        if (probabilidad == 0){
            cliente.setAfecto(true);
            return;
        } else if (probabilidad != 0){
            cliente.setDefensa(0);
            cliente.setReflejo(false);
            cliente.setVida(0);
        }

    }

    /**
     * Elimina la defensa
     * @param cliente
     */

    public void EliminarCartaSecreta(Cliente cliente){

        cliente.setDefensa(0);

    }

    /**
     * Elimina efectos spells
     * @param cliente Cliente
     */

    public void EliminarCartaSpell(Cliente cliente){

        cliente.setMana(cliente.getMana()-100);

    }

    /**
     * Aflica ejectos
     * @param cliente Cliente
     */

    public void SecretaYGanar(Cliente cliente){

        cliente.setDefensa(0);
        cliente.setReflejo(false);
        cliente.setVida(cliente.getVida()-100);
        cliente.setMana(cliente.getMana()-50);

    }

    /**
     * Refleja daño
     * @param cliente Cliente
     */

    public void Reflejar(Cliente cliente){

        cliente.setReflejo(true);

    }

    /**
     * Mata con una posibilidad
     * @param cliente Cliente
     */

    public void Ejecutar(Cliente cliente){

        int probabilidad = (int) (Math.random() * 100);

        if (probabilidad < this.porcentaje){
            cliente.setDefensa(0);
            cliente.setReflejo(false);
            cliente.setVida(0);
        }

    }

}
