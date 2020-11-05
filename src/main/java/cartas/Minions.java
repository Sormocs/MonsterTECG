package cartas;

public class Minions {
    public int ataque;
    public int costo;
    public String tipo;
    public int porcentaje;
    public String aplica;
    public String ruta;
    public String informacion;
    public String afecta;

    /**
     * Llama la metodo correpondiente de la carta
     * @param Vida int
     * @param VidaR int
     * @param Mana int
     * @return int[]
     */

    public int[] Caso(int Vida, int VidaR, int Mana){

        int[] vidamana = new int[2];

        switch (this.aplica){
            case "ninguno":
                vidamana = Ninguno(Vida, Mana);
                break;
            case "RivalHP":
                vidamana = RivalHP(Vida, VidaR, Mana);
                break;
            case "propiaHP":
                vidamana = PropiaHP(Vida, Mana);
                break;
        }

        return vidamana;
    }

    /**
     * Retorna la nueva vida y mana
     * @param Vida int
     * @param Mana int
     * @return int[]
     */

    public int[] PropiaHP(int Vida, int Mana){

        int dato = (this.porcentaje*Vida)/100;

        int vida = Vida - dato;
        int mana = Mana - this.costo;

        int[] vidamana = {vida,mana};

        return vidamana;


    }

    /**
     * Retorna la nueva vida y mana
     * @param Vida int
     * @param Mana int
     * @param VidaR int
     * @return int[]
     */

    public int[] RivalHP(int Vida, int VidaR, int Mana){

        int dato = (this.porcentaje*VidaR)/100;

        int vida = Vida - dato;
        int mana = Mana - this.costo;

        int[] vidamana = {vida,mana};

        return vidamana;

    }

    /**
     * Retorna la nueva vida y mana
     * @param Vida int
     * @param Mana int
     * @return int[]
     */

    public int[] Ninguno(int Vida, int Mana){

        int vida = Vida - this.ataque;
        int mana = Mana - this.costo;

        int[] vidamana = {vida,mana};

        return vidamana;

    }

}
