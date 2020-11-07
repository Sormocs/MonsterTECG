package listas;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import manejo.json.Json;

/**
 *Clase para crear un stack para el manejo de las cartas
 */

public class Stack {
    int max = 21;
    Object nodo;
    int top;
    Object[] stack = new Object[max];

    public Stack(){
        this.top = -1;
    }

    /**
     * Revisa si el stack está vacío.
     * @return
     */

    public boolean isEmpty(){
        return (top<0);
    }

    /**
     * Agrega un objeto al inicio del stack
     * @param nodo
     * @return boolean
     */

    public boolean push(Object nodo){
        //Ingresar dato al inicio
        if (top>=(max-1)){
            //System.out.println("la lista esta llena");
            return false;
        } else{
            this.stack[++this.top] = nodo;
            return true;
        }
    }

    /**
     * Quita el primer elemento del stack
     * @return
     */

    public boolean pop(){
        //Sacar dato al inicio
        if(this.isEmpty()){
            System.out.println("la lista esta vacia");
            return false;
        } else{
            this.stack[this.top--] = null;
            return true;
        }
    }

    public Object getTop(){
        return stack[top];
    }

    /**
     * Imprime en conosola los datos de la lista.
     */

    public void print(){
        //Imprimir la clase
        for (Object i : stack){
            System.out.println(i);
        }
    }

    /**
     * Método que llena el stack de 16 cartas aleatorias
     * @throws JsonProcessingException
     */

    public void Llenar() {
        try{

            String file = Json.ReadJson("cards.json");

            JsonNode nodo = Json.parse(file);

            String[] tipo_carta = {"minions","secrets","Spells","minions"};

            String[] minions = {"attackOwn18_141HP","attackOwn25","attackOwn35_413HP","attackOwn36","attackOwn40","attackRival16_148HP",
                    "attackRival20","attackRival24_134HP","attackRival32_58HP","attackRival40","attackRival56","attack100HP","attack125HP","attack172HP",
                    "attack200HP","attack258HP","attack269HP","attack300HP","attack321HP","attack400HP"};

            String[] secrets = {"fifty_fifty","def50","def75","def90","elimSecretCard","elimSpellCard","killIfSecret","reflect50DMG",
                    "InstaWin4","InstaWin10"};

            String[] spells = {"BoostMana","DuplicateCard","Fill40Mana","Fill100Mana","Freeze","Heal50","Heal10","RevealCard",
                    "StealCard","SupremePower"};

            for (int i = 0; i <= 16;i++){

                int valor_carta = (int) (Math.random() * 4);

                if (tipo_carta[valor_carta].equals("minions")){

                    int valor_tipo = (int) (Math.random()*20);

                    push(nodo.get("cartas").get("minions").get(minions[valor_tipo]));

                } else if (tipo_carta[valor_carta].equals("secrets")){

                    int valor_tipo = (int) (Math.random()*10);

                    push(nodo.get("cartas").get("secrets").get(secrets[valor_tipo]));

                } else if (tipo_carta[valor_carta].equals("Spells")){

                    int valor_tipo = (int) (Math.random()*10);

                    push(nodo.get("cartas").get("Spells").get(spells[valor_tipo]));
                }

            }

            //print();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

